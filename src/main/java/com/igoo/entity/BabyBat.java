package com.igoo.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.Level;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class BabyBat extends TamableAnimal {

    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
    };

    private static final int SWING_ANIM_DURATION = 6;
    private int manualSwingTimer = 0;

    public BabyBat(EntityType<? extends BabyBat> entityType, Level level) {
        super(entityType, level);
        this.setTame(false, false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ATTACK_SPEED, 4.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(5, new EatBlockGoal(this));
        this.goalSelector.addGoal(6, new SeekItemGoal(this, 1.0D, 12));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));

        TargetingConditions.Selector enemySelector = (entity, level) ->
                entity instanceof Enemy && !(entity instanceof Creeper);

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
                this, Mob.class, 10, true, false, enemySelector
        ));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.manualSwingTimer > 0) {
            this.manualSwingTimer--;
        }
    }

    @Override
    public float getAttackAnim(float partialTick) {
        if (this.manualSwingTimer > 0) {
            float progress = (SWING_ANIM_DURATION - this.manualSwingTimer + partialTick)
                    / (float) SWING_ANIM_DURATION;
            return (float) Math.sin(progress * Math.PI);
        }
        return super.getAttackAnim(partialTick);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        this.manualSwingTimer = SWING_ANIM_DURATION;
        this.swing(InteractionHand.MAIN_HAND);

        DamageSource source = this.damageSources().mobAttack(this);
        float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        boolean hurt = target.hurtServer(level, source, damage);

        if (hurt) {
            this.causeExtraKnockback(target, 0.4f, target.getDeltaMovement(), source, damage, false);
            this.playAttackSound();

            level.sendParticles(ParticleTypes.DAMAGE_INDICATOR,
                    target.getX(), target.getY() + target.getBbHeight() * 0.5, target.getZ(),
                    3, 0.2, 0.2, 0.2, 0.1);

            level.sendParticles(ParticleTypes.CRIT,
                    this.getX() + this.getLookAngle().x * 0.5,
                    this.getY() + this.getEyeHeight() * 0.5,
                    this.getZ() + this.getLookAngle().z * 0.5,
                    5, 0.15, 0.15, 0.15, 0.05);
        }
        return hurt;
    }

    @Override
    protected void playAttackSound() {
        this.playSound(SoundEvents.BAT_HURT, 0.8f, 1.5f);
    }

    public boolean tryEquipArmor(ItemStack stack) {
        if (stack.isEmpty()) return false;

        // Xác định slot từ EQUIPPABLE component
        EquipmentSlot slot = this.getEquipmentSlotForItem(stack);
        if (slot == EquipmentSlot.MAINHAND) return false;       // không phải armor
        if (!this.canUseSlot(slot)) return false;
        if (!this.getItemBySlot(slot).isEmpty()) return false;  // slot đã có đồ

        // Mặc vào
        ItemStack toEquip = stack.copy();
        toEquip.setCount(1);
        this.setItemSlot(slot, toEquip);

        // Âm thanh mặc giáp
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (equippable != null) {
            this.level().playSound(null, this.blockPosition(),
                    equippable.equipSound().value(),
                    SoundSource.NEUTRAL, 0.8f, 1.0f);
        } else {
            this.level().playSound(null, this.blockPosition(),
                    SoundEvents.ARMOR_EQUIP_GENERIC.value(),
                    SoundSource.NEUTRAL, 0.8f, 1.0f);
        }

        // Particle vui mắt
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    this.getX(), this.getY() + 0.5, this.getZ(),
                    5, 0.3, 0.3, 0.3, 0.1);
        }

        return true;
    }

    // Kiểm tra còn slot armor nào trống không
    public boolean hasEmptyArmorSlot() {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (this.getItemBySlot(slot).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.is(Items.BUCKET) && this.isTame()) {
            ItemStack milkBucket = new ItemStack(Items.MILK_BUCKET);

            if (player.isCreative()) {
                if (!player.getInventory().add(milkBucket)) {
                    player.drop(milkBucket, false);
                }
            } else {
                if (itemstack.getCount() == 1) {
                    player.setItemInHand(hand, milkBucket);
                } else {
                    itemstack.shrink(1);
                    if (!player.getInventory().add(milkBucket)) {
                        player.drop(milkBucket, false);
                    }
                }
            }

            this.level().playSound(null, this.blockPosition(),
                    SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);

            return InteractionResult.SUCCESS;
        }

        if (itemstack.is(Items.WHEAT)) {
            if (!this.isTame()) {
                if (!player.isCreative()) itemstack.shrink(1);
                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            } else if (this.getHealth() < this.getMaxHealth()) {
                if (!player.isCreative()) itemstack.shrink(1);
                this.heal(4.0F);
                this.level().playSound(null, this.blockPosition(),
                        SoundEvents.GENERIC_EAT.value(), this.getSoundSource(), 1.0F, 1.0F);
                if (this.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.HEART,
                            this.getX(), this.getY() + 0.5, this.getZ(),
                            3, 0.5, 0.5, 0.5, 0.1);
                }
                return InteractionResult.SUCCESS;
            } else if (this.canFallInLove() && this.isTame()) {
                if (!player.isCreative()) itemstack.shrink(1);
                this.setInLove(player);
                this.level().playSound(null, this.blockPosition(),
                        SoundEvents.GENERIC_EAT.value(), this.getSoundSource(), 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }

        if (this.isTame() && itemstack.isEmpty() && this.isOwnedBy(player)) {
            if (player.isShiftKeyDown()) {
                ItemStack held = this.getItemBySlot(EquipmentSlot.MAINHAND);
                if (!held.isEmpty()) {
                    if (this.level() instanceof ServerLevel serverLevel) {
                        this.spawnAtLocation(serverLevel, held);
                    }
                    this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    this.level().playSound(null, this.blockPosition(),
                            SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 0.5f, 1.0f);
                    return InteractionResult.SUCCESS;
                }
            }

            this.setOrderedToSit(!this.isOrderedToSit());
            this.jumping = false;
            this.navigation.stop();
            this.setTarget(null);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target == this.getOwner()) return false;
        return super.canAttack(target);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);
        // Drop main hand
        dropSlot(level, EquipmentSlot.MAINHAND);
        // Drop armor
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            dropSlot(level, slot);
        }
    }

    private void dropSlot(ServerLevel level, EquipmentSlot slot) {
        ItemStack stack = this.getItemBySlot(slot);
        if (!stack.isEmpty()) {
            this.spawnAtLocation(level, stack);
            this.setItemSlot(slot, ItemStack.EMPTY);
        }
    }

    @Override
    protected void dropEquipment(ServerLevel level) {}

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 7) {
            this.spawnTamingParticles(true);
        } else if (id == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.BABY_BAT.create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.WHEAT);
    }

    public static class SeekItemGoal extends Goal {
        private final BabyBat bat;
        private final double speedModifier;
        private final int searchRange;
        private ItemEntity targetItem;
        private int cooldown;

        public SeekItemGoal(BabyBat bat, double speed, int range) {
            this.bat = bat;
            this.speedModifier = speed;
            this.searchRange = range;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.cooldown > 0) {
                this.cooldown--;
                return false;
            }
            if (this.bat.isOrderedToSit()) return false;

            boolean mainhandFull = !this.bat.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            boolean armorFull = !this.bat.hasEmptyArmorSlot();
            if (mainhandFull && armorFull) return false;

            List<ItemEntity> items = this.bat.level().getEntitiesOfClass(
                    ItemEntity.class,
                    this.bat.getBoundingBox().inflate(this.searchRange),
                    item -> !item.isRemoved() && !item.getItem().isEmpty()
            );
            if (items.isEmpty()) return false;

            items.sort(Comparator.comparingDouble(this.bat::distanceToSqr));
            this.targetItem = items.get(0);
            return true;
        }

        @Override
        public void start() {
            if (this.targetItem != null) {
                this.bat.getNavigation().moveTo(this.targetItem, this.speedModifier);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.targetItem != null
                    && !this.targetItem.isRemoved()
                    && this.bat.distanceToSqr(this.targetItem) < (double) (this.searchRange * this.searchRange);
        }

        @Override
        public void tick() {
            if (this.targetItem == null || this.targetItem.isRemoved()) return;

            this.bat.getLookControl().setLookAt(this.targetItem);

            if (this.bat.distanceToSqr(this.targetItem) < 4.0) {
                this.bat.getNavigation().stop();

                ItemStack stack = this.targetItem.getItem();
                if (stack.isEmpty()) return;

                if (this.bat.tryEquipArmor(stack)) {
                    stack.shrink(1);
                    if (stack.isEmpty()) {
                        this.targetItem.discard();
                    } else {
                        this.targetItem.setItem(stack);
                    }
                    return;
                }

                if (this.bat.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                    ItemStack toHold = stack.copy();
                    toHold.setCount(1);
                    this.bat.setItemSlot(EquipmentSlot.MAINHAND, toHold);

                    stack.shrink(1);
                    if (stack.isEmpty()) {
                        this.targetItem.discard();
                    } else {
                        this.targetItem.setItem(stack);
                    }

                    this.bat.level().playSound(null, this.bat.blockPosition(),
                            SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 0.5f, 1.0f);

                    if (this.bat.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.HEART,
                                this.bat.getX(), this.bat.getY() + 0.5, this.bat.getZ(),
                                3, 0.3, 0.3, 0.3, 0.1);
                    }
                }
            } else {
                this.bat.getNavigation().moveTo(this.targetItem, this.speedModifier);
            }
        }

        @Override
        public void stop() {
            this.targetItem = null;
            this.cooldown = 20;
        }
    }
}