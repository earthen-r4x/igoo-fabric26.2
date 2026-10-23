package com.igoo.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.zombie.Zombie;

public class HerobrineBoss extends PathfinderMob {

    private final ServerBossEvent bossBar = new ServerBossEvent(
            UUID.randomUUID(),
            this.getDisplayName(),
            BossEvent.BossBarColor.RED,
            BossEvent.BossBarOverlay.PROGRESS
    );

    private int skillCooldown = 100; // Khởi đầu chờ 5 giây

    public HerobrineBoss(EntityType<? extends HerobrineBoss> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500;
    }

    public static AttributeSupplier.Builder createHerobrineAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 30.0D)
                .add(Attributes.FOLLOW_RANGE, 200.0D)
                .add(Attributes.ARMOR, 20.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        // 1. Cập nhật thanh tiến trình (độ dài của thanh máu)
        float healthRatio = this.getHealth() / this.getMaxHealth();
        this.bossBar.setProgress(healthRatio);

        // 2. Tự động đổi màu thanh máu dựa trên % máu
        if (healthRatio > 0.66F) {
            // Trên 66% máu: Màu Xanh Lá (Khỏe mạnh)
            this.bossBar.setColor(BossEvent.BossBarColor.GREEN);
        } else if (healthRatio > 0.33F) {
            // Từ 33% đến 66% máu: Màu Vàng (Bị thương)
            this.bossBar.setColor(BossEvent.BossBarColor.YELLOW);
        } else {
            // Dưới 33% máu: Màu Đỏ (Nguy kịch)
            this.bossBar.setColor(BossEvent.BossBarColor.RED);
        }

        // 3. Logic kỹ năng
        if (!this.level().isClientSide() && this.isAlive()) {
            LivingEntity target = this.getTarget();
            
            if (target != null) {
                if (this.skillCooldown > 0) {
                    this.skillCooldown--;
                } else {
                    useRandomSkill(target);
                    this.skillCooldown = 200; // Hồi chiêu 10 giây
                }
            }
        }
    }

    private void useRandomSkill(LivingEntity target) {
        // Tăng lên 5 kỹ năng (từ 0 đến 4)
        int skill = this.getRandom().nextInt(5);
        switch (skill) {
            case 0 -> teleportBehindTarget(target);
            case 1 -> summonLightning(target);
            case 2 -> curseOfDarkness(target);
            case 3 -> summonUndead(target);
            case 4 -> fearKnockback(target);
        }
    }

    // Hàm gửi tin nhắn đe dọa cho người chơi
    private void sendBossMessage(LivingEntity target, String translationKey) {
        if (target instanceof ServerPlayer serverPlayer) {
            Component message = Component.translatable(translationKey).withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD);
            serverPlayer.sendSystemMessage(message);
        }
    }

    // ==========================================
    // KỸ NĂNG 1: DỊCH CHUYỂN TỨC THỜI
    // ==========================================
    private void teleportBehindTarget(LivingEntity target) {
        sendBossMessage(target, "message.igoo.herobrine.skill.teleport");
        
        Vec3 lookVec = target.getLookAngle();
        double x = target.getX() - lookVec.x * 2;
        double y = target.getY();
        double z = target.getZ() - lookVec.z * 2;

        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1, this.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
            this.level().playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0f, 1.0f);
            
            this.randomTeleport(x, y, z, true);
            
            serverLevel.sendParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1, this.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
            this.level().playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_SCREAM, SoundSource.HOSTILE, 1.0f, 0.5f);
            
            this.swing(InteractionHand.MAIN_HAND);
            this.doHurtTarget(serverLevel, target);
        }
    }

    // ==========================================
    // KỸ NĂNG 2: TRIỆU HỒI SẤM SÉT
    // ==========================================
    private void summonLightning(LivingEntity target) {
        sendBossMessage(target, "message.igoo.herobrine.skill.lightning");
        
        if (this.level() instanceof ServerLevel serverLevel) {
            LightningBolt lightning = new LightningBolt(EntityTypes.LIGHTNING_BOLT, serverLevel);
            lightning.setPos(target.getX(), target.getY(), target.getZ());
            
            serverLevel.addFreshEntity(lightning);
            this.level().playSound(null, this.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.HOSTILE, 1.0f, 1.0f);
        }
    }

    // ==========================================
    // KỸ NĂNG 3: LỜI NGUYỀN BÓNG TỐI
    // ==========================================
    private void curseOfDarkness(LivingEntity target) {
        sendBossMessage(target, "message.igoo.herobrine.skill.curse");
        
        if (target instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0), this);
            player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 0), this);
            
            this.level().playSound(null, this.blockPosition(), SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.HOSTILE, 1.0f, 0.5f);
            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SQUID_INK, player.getX(), player.getY() + 1, player.getZ(), 40, 1.0, 1.0, 1.0, 0.1);
            }
        }
    }

    // ==========================================
    // KỸ NĂNG 4: TRIỆU HỒI QUÂN ĐOÀN XÁC SỐNG
    // ==========================================
    private void summonUndead(LivingEntity target) {
        sendBossMessage(target, "message.igoo.herobrine.skill.undead");
        
        if (this.level() instanceof ServerLevel serverLevel) {
            this.level().playSound(null, this.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0f, 0.5f);
            
            // Triệu hồi 2-3 con Zombie xung quanh Herobrine
            int count = this.getRandom().nextInt(2) + 2; // 2 hoặc 3 con
            for (int i = 0; i < count; i++) {
                double dx = this.getRandom().nextDouble() * 4 - 2;
                double dz = this.getRandom().nextDouble() * 4 - 2;
                
                Zombie zombie = EntityTypes.ZOMBIE.spawn(serverLevel, 
                        this.blockPosition().offset((int)dx, 0, (int)dz), 
                        EntitySpawnReason.TRIGGERED);
                
                if (zombie != null) {
                    zombie.setTarget(target);
                    serverLevel.sendParticles(ParticleTypes.SMOKE, zombie.getX(), zombie.getY() + 1, zombie.getZ(), 20, 0.5, 0.5, 0.5, 0.1);
                }
            }
        }
    }

    // ==========================================
    // KỸ NĂNG 5: SÓNG XUNG KÍCH ĐẨY LÙI (FEAR)
    // ==========================================
    private void fearKnockback(LivingEntity target) {
        sendBossMessage(target, "message.igoo.herobrine.skill.fear");
        
        // Tiếng Warden gầm thét
        this.level().playSound(null, this.blockPosition(), SoundEvents.WARDEN_ROAR, SoundSource.HOSTILE, 1.0f, 0.8f);
        
        // Tính toán hướng đẩy lùi từ Herobrine đến mục tiêu
        Vec3 pushVec = target.position().subtract(this.position()).normalize();
        
        // Đẩy lùi mục tiêu ra xa (tốc độ X/Z, độ cao Y)
        target.push(pushVec.x * 1.5, 0.6, pushVec.z * 1.5);
        
        // Gây sát thương nhẹ (2 máu)
        if (this.level() instanceof ServerLevel serverLevel) {
            target.hurtServer(serverLevel, this.damageSources().mobAttack(this), 2.0f);
            
            // Hiệu ứng sóng âm thanh
            serverLevel.sendParticles(ParticleTypes.SONIC_BOOM, this.getX(), this.getY() + 1, this.getZ(), 1, 0, 0, 0, 0);
        }
    }
    
    @Override
    public void die(DamageSource source) {
        super.die(source);

        if (!this.level().isClientSide()) {
            // Kiểm tra xem ai là người kết liễu boss
            if (source.getEntity() instanceof ServerPlayer player) {
                // Gửi thông báo toàn server với tên người chơi và tên boss
                this.level().getServer().getPlayerList().broadcastSystemMessage(
                        Component.translatable("message.igoo.boss.defeated", 
                                player.getDisplayName(), 
                                this.getDisplayName()),
                        false // false = không ghi vào log chat, chỉ hiện trên màn hình
                );
            } else {
                // Trường hợp boss chết do sát thương môi trường (rơi, cháy,...)
                this.level().getServer().getPlayerList().broadcastSystemMessage(
                        Component.translatable("message.igoo.boss.defeated_unknown", 
                                this.getDisplayName()),
                        false
                );
            }
            
            // (Tùy chọn) Phát âm thanh chiến thắng
            this.level().playSound(null, this.blockPosition(), net.minecraft.sounds.SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, net.minecraft.sounds.SoundSource.MASTER, 1.0f, 1.0f);
        }
    }
}