package com.igoo.entity;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

public class BotPlayer extends FakePlayer {

    public enum Mode {
        IDLE, WANDER, FOLLOW, FLEE, ATTACK, PVP, GUARD, PATROL,
        BUILD_BRIDGE, BUILD_TOWER, MINER, JUMPER, DANCER
    }

    private static final double WALK_FACTOR = 2.16D;
    private static final double REACH = 3.0D;

    private Mode mode = Mode.WANDER;
    private UUID ownerUUID;

    private int wanderTimer = 0;
    private int wanderPause = 0;
    private int buildCooldown = 0;
    private int actionCooldown = 0;
    private int blockedTicks = 0;
    private int deadTicks = 0;
    private float patrolYaw = 0f;
    private BlockPos patrolCenter;

    private boolean wantSprint = false;
    private boolean wantSneak = false;

    public BotPlayer(ServerLevel level, GameProfile profile) {
        super(level, profile);
        this.setInvulnerable(false);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        this.patrolCenter = null;
        this.blockedTicks = 0;
        this.wanderTimer = 0;
        this.wanderPause = 0;
        this.buildCooldown = 0;
        this.actionCooldown = 0;
    }

    public Mode getMode() { return this.mode; }
    public void setOwner(Player player) { this.ownerUUID = player != null ? player.getUUID() : null; }
    public UUID getOwnerUUID() { return this.ownerUUID; }

    @Override
    public boolean isEffectiveAi() {
        return true;
    }

    @Override
    public boolean canSimulateMovement() {
        return true;
    }

    @Override
    public float getSpeed() {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    public void tick() {
        MinecraftServer server = this.level().getServer();
        if (server == null) return;

        if (!this.isAlive()) {
            // Bot không tự hồi sinh được -> gỡ khỏi server sau 2 giây
            if (++this.deadTicks == 40) {
                server.execute(() -> server.getPlayerList().remove(this));
            }
            return;
        }

        if (this.buildCooldown > 0) this.buildCooldown--;
        if (this.actionCooldown > 0) this.actionCooldown--;

        this.zza = 0f;
        this.xxa = 0f;
        this.yya = 0f;
        this.wantSprint = false;
        this.wantSneak = false;

        switch (this.mode) {
            case IDLE         -> { }
            case WANDER       -> this.tickWander();
            case FOLLOW       -> this.tickFollow();
            case FLEE         -> this.tickFlee();
            case ATTACK       -> this.tickAttack(false);
            case PVP          -> this.tickAttack(true);
            case GUARD        -> this.tickGuard();
            case PATROL       -> this.tickPatrol();
            case BUILD_BRIDGE -> this.tickBuildBridge();
            case BUILD_TOWER  -> this.tickBuildTower();
            case MINER        -> this.tickMiner();
            case JUMPER       -> this.tickJumper();
            case DANCER       -> this.tickDancer();
        }

        if (this.isSprinting() != this.wantSprint) this.setSprinting(this.wantSprint);
        if (this.isShiftKeyDown() != this.wantSneak) this.setShiftKeyDown(this.wantSneak);

        try {
            super.tick();
            this.doTick();
        } catch (NullPointerException ignored) {
        }

        if (this.tickCount % 10 == 0) {
            ((ServerLevel) this.level()).getChunkSource().move(this);
        }
    }

    private void tickWander() {
        if (this.wanderPause > 0) {
            this.wanderPause--;
            return;
        }
        if (--this.wanderTimer <= 0) {
            this.wanderTimer = 40 + this.random.nextInt(60);
            if (this.random.nextInt(4) == 0) {
                this.wanderPause = 20 + this.random.nextInt(30);
                return;
            }
            this.rotateTo(this.random.nextFloat() * 360f);
        }
        this.walkForward(1.0f);
        this.autoJump();
    }

    private void tickFollow() {
        Player target = this.findFollowTarget(48.0);
        if (target == null) { this.tickWander(); return; }

        this.faceEntity(target);
        double dist = this.distanceTo(target);
        if (dist > 3.0) {
            this.wantSprint = dist > 8.0;
            this.walkForward(1.0f);
            this.autoJump();
        }
    }

    private void tickFlee() {
        Player threat = this.nearestRealPlayer(16.0);
        if (threat == null) { this.tickWander(); return; }

        Vec3 away = this.position().subtract(threat.position());
        this.rotateTo((float) Math.toDegrees(Math.atan2(-away.x, away.z)));
        this.wantSprint = true;
        this.walkForward(1.0f);
        this.autoJump();
    }

    private void tickAttack(boolean pvp) {
        LivingEntity target = pvp ? this.findPlayerTarget(24.0) : this.findEnemyTarget(24.0);
        if (target == null) {
            if (this.findFollowTarget(48.0) != null) this.tickFollow();
            return;
        }

        this.selectHotbarWhere(s -> s.is(ItemTags.SWORDS));
        this.faceEntity(target);

        double reachSqr = REACH * REACH;
        boolean inReach = target.getBoundingBox().distanceToSqr(this.getEyePosition()) <= reachSqr;

        if (inReach) {
            this.tryHit(target);
        } else {
            this.wantSprint = this.distanceTo(target) > 5.0;
            this.walkForward(1.0f);
            this.autoJump();
        }
    }

    private void tickGuard() {
        LivingEntity target = this.findEnemyTarget(8.0);
        if (target == null) return;

        this.faceEntity(target);
        if (target.getBoundingBox().distanceToSqr(this.getEyePosition()) <= REACH * REACH) {
            this.selectHotbarWhere(s -> s.is(ItemTags.SWORDS));
            this.tryHit(target);
        }
    }

    private void tickPatrol() {
        if (this.patrolCenter == null) {
            this.patrolCenter = this.blockPosition();
            this.patrolYaw = this.getYRot();
        }

        double ox = this.getX() - (this.patrolCenter.getX() + 0.5);
        double oz = this.getZ() - (this.patrolCenter.getZ() + 0.5);
        double dist = Math.sqrt(ox * ox + oz * oz);

        double rad = Math.toRadians(this.patrolYaw);
        double fx = -Math.sin(rad);
        double fz = Math.cos(rad);
        boolean movingAway = (ox * fx + oz * fz) > 0;

        if (dist > 10.0 && movingAway) {
            this.patrolYaw += 180f + (this.random.nextFloat() - 0.5f) * 40f;
        }

        if (this.horizontalCollision) {
            if (++this.blockedTicks > 15) {
                this.patrolYaw += 90f + this.random.nextInt(90);
                this.blockedTicks = 0;
            }
        } else {
            this.blockedTicks = 0;
        }

        this.rotateTo(this.patrolYaw);
        this.walkForward(0.7f);
        this.autoJump();
    }

    private void tickBuildBridge() {
        this.wantSneak = true;
        this.autoJump();

        Direction dir = this.getDirection();
        BlockPos under = this.blockPosition().below();
        BlockPos frontUnder = under.relative(dir);

        if (this.buildCooldown <= 0) {
            if (this.canPlaceAt(under)) {
                if (this.tryPlaceBlock(under)) this.buildCooldown = 3;
            } else if (this.canPlaceAt(frontUnder)) {
                if (this.tryPlaceBlock(frontUnder)) this.buildCooldown = 3;
            }
        }

        if (!this.canPlaceAt(frontUnder)) {
            this.walkForward(1.0f);
        }
    }

    private void tickBuildTower() {
        if (this.blockPosition().getY() >= this.level().getMaxY() - 2) return;

        if (this.onGround()) {
            this.jumpFromGround();
            return;
        }

        BlockPos below = this.blockPosition().below();
        if (this.buildCooldown <= 0 && this.getY() >= below.getY() + 1.0 && this.canPlaceAt(below)) {
            if (this.tryPlaceBlock(below)) this.buildCooldown = 3;
        }
    }

    private void tickMiner() {
        this.selectHotbarWhere(s -> s.is(ItemTags.PICKAXES));

        Direction dir = this.getDirection();
        this.rotateTo(dir.toYRot());

        BlockPos front = this.blockPosition().relative(dir);
        BlockPos[] hazards = { front, front.above(), front.below() };
        for (BlockPos p : hazards) {
            if (!this.level().getFluidState(p).isEmpty()) {
                this.rotateTo(this.getYRot() + 90f);
                return;
            }
        }

        BlockPos[] column = { front, front.above() };
        for (BlockPos pos : column) {
            BlockState state = this.level().getBlockState(pos);
            if (state.isAir()) continue;

            if (state.getDestroySpeed(this.level(), pos) < 0) { // bedrock...
                this.rotateTo(this.getYRot() + 90f);
                return;
            }
            if (this.actionCooldown > 0) return;

            this.gameMode.destroyBlock(pos);
            this.swing(InteractionHand.MAIN_HAND);
            this.actionCooldown = 5;
            return;
        }

        if (this.level().getBlockState(front.below()).isAir()) {
            this.rotateTo(this.getYRot() + 90f);
            return;
        }
        this.walkForward(0.8f);
    }

    private void tickJumper() {
        if (this.onGround()) this.jumpFromGround();
    }

    private void tickDancer() {
        if (this.tickCount % 5 == 0) this.rotateTo(this.getYRot() + 45f);
        if (this.onGround() && this.random.nextInt(10) == 0) this.jumpFromGround();
        this.wantSneak = (this.tickCount / 8) % 2 == 0;
        if (this.random.nextInt(10) == 0) {
            this.swing(this.random.nextBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
        }
    }

    private void walkForward(float factor) {
        double speed = this.getAttributeValue(Attributes.MOVEMENT_SPEED) * WALK_FACTOR * factor;
        if (this.wantSneak) speed *= 0.3D;

        float yaw = this.getYRot() * Mth.DEG_TO_RAD;
        Vec3 d = this.getDeltaMovement();
        double dy = d.y;
        if (this.isInWater()) {
            speed *= 0.6D;
            dy = Math.min(dy + 0.04D, 0.2D);
        }
        this.setDeltaMovement(-Mth.sin(yaw) * speed, dy, Mth.cos(yaw) * speed);
    }

    private void autoJump() {
        if (this.horizontalCollision && this.onGround()) this.jumpFromGround();
    }

    private void tryHit(LivingEntity target) {
        if (this.getAttackStrengthScale(0.5F) < 1.0F) return;
        this.swing(InteractionHand.MAIN_HAND);
        this.attack(target); 
    }

    private LivingEntity findEnemyTarget(double range) {
        return this.level().getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(range),
                        e -> e != this && e.isAlive() && e instanceof Enemy)
                .stream()
                .min(Comparator.comparingDouble(this::distanceToSqr))
                .orElse(null);
    }

    private LivingEntity findPlayerTarget(double range) {
        return this.level().getEntitiesOfClass(Player.class,
                        this.getBoundingBox().inflate(range),
                        p -> p != this && p.isAlive() && !p.isCreative() && !p.isSpectator()
                                && !(p instanceof BotPlayer)
                                && (this.ownerUUID == null || !p.getUUID().equals(this.ownerUUID)))
                .stream()
                .min(Comparator.comparingDouble(this::distanceToSqr))
                .orElse(null);
    }

    private Player findFollowTarget(double range) {
        if (this.ownerUUID != null) {
            Player owner = this.level().getPlayerByUUID(this.ownerUUID);
            if (owner != null && owner.isAlive() && !owner.isSpectator()
                    && owner.level() == this.level()) {
                return owner;
            }
        }
        return this.nearestRealPlayer(range);
    }

    private Player nearestRealPlayer(double range) {
        Player best = null;
        double bestDist = range * range;
        for (Player p : this.level().players()) {
            if (p == this || p instanceof BotPlayer || !p.isAlive() || p.isSpectator()) continue;
            double d = this.distanceToSqr(p);
            if (d < bestDist) {
                bestDist = d;
                best = p;
            }
        }
        return best;
    }

    private boolean selectHotbarWhere(Predicate<ItemStack> predicate) {
        Inventory inv = this.getInventory();
        if (predicate.test(inv.getItem(inv.getSelectedSlot()))) return true;
        for (int i = 0; i < 9; i++) {
            if (predicate.test(inv.getItem(i))) {
                inv.setSelectedSlot(i);
                return true;
            }
        }
        return false;
    }

    private boolean canPlaceAt(BlockPos pos) {
        return this.level().getBlockState(pos).canBeReplaced();
    }

    private ItemStack findPlaceableStack() {
        Inventory inv = this.getInventory();
        for (int i = 0; i < 36; i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem bi && isPlaceable(bi)) {
                if (i < 9) inv.setSelectedSlot(i);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean isPlaceable(BlockItem item) {
        var block = item.getBlock();
        return block != Blocks.AIR
                && !(block instanceof FallingBlock)
                && !block.defaultBlockState().hasBlockEntity();
    }

    private boolean tryPlaceBlock(BlockPos pos) {
        ItemStack stack = this.findPlaceableStack();
        if (stack.isEmpty()) return false;

        BlockItem blockItem = (BlockItem) stack.getItem();
        BlockState state = blockItem.getBlock().defaultBlockState();
        if (!state.canSurvive(this.level(), pos)) return false;

        if (this.level().setBlock(pos, state, 3)) {
            this.level().playSound(null, pos, state.getSoundType().getPlaceSound(),
                    SoundSource.BLOCKS, 1.0f, 1.0f);
            if (!this.isCreative()) stack.shrink(1);
            this.swing(InteractionHand.MAIN_HAND);
            return true;
        }
        return false;
    }

    private void faceEntity(Entity target) {
        Vec3 diff = target.getEyePosition().subtract(this.getEyePosition());
        double horiz = Math.sqrt(diff.x * diff.x + diff.z * diff.z);
        this.rotateTo((float) Math.toDegrees(Math.atan2(-diff.x, diff.z)));
        this.setXRot((float) -Math.toDegrees(Math.atan2(diff.y, horiz)));
    }

    private void rotateTo(float yaw) {
        this.setYRot(yaw);
        this.setYHeadRot(yaw);
        this.setYBodyRot(yaw);
    }
}