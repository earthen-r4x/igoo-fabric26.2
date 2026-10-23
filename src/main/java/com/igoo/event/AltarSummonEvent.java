package com.igoo.event;

import com.igoo.Igoo;
import com.igoo.entity.HerobrineBoss;
import com.igoo.entity.ModEntities;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class AltarSummonEvent {

    // 👇 TẠO DANH SÁCH LƯU CÁC BÀN THỜ ĐANG HOẠT ĐỘNG
    private static final Set<BlockPos> ACTIVE_ALTARS = new HashSet<>();

    public static void register() {
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (level.isClientSide() || hand != InteractionHand.MAIN_HAND) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);
            BlockPos pos = hitResult.getBlockPos();

            if (stack.is(Items.FLINT_AND_STEEL) && level.getBlockState(pos).is(Blocks.NETHERRACK)) {
                
                // 👇 KIỂM TRA: Nếu bàn thờ này đang triệu hồi thì bỏ qua
                if (ACTIVE_ALTARS.contains(pos)) {
                    return InteractionResult.FAIL;
                }

                if (isValidAltar(level, pos)) {
                    if (level instanceof ServerLevel serverLevel) {
                        
                        // Khóa bàn thờ lại
                        ACTIVE_ALTARS.add(pos);

                        level.setBlockAndUpdate(pos.above(), Blocks.FIRE.defaultBlockState());
                        level.playSound(null, pos, SoundEvents.WITHER_AMBIENT, SoundSource.HOSTILE, 1.0f, 1.0f);
                        player.sendSystemMessage(Component.translatable("message.igoo.herobrine.summoning"));
                        
                        if (!player.isCreative()) {
                            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                        }

                        CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
                            serverLevel.getServer().execute(() -> {
                                
                                ACTIVE_ALTARS.remove(pos);

                                destroyAltar(level, pos);
                                
                                ResourceKey<Biome> cursedBiomeKey = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "cursed_savanna"));
                                boolean isCursed = level.getBiome(pos).is(cursedBiomeKey);

                                HerobrineBoss boss;

                                if (isCursed) {
                                    // Triệu hồi bản Cursed (Mạnh gấp 10)
                                    boss = ModEntities.CURSED_HEROBRINE.spawn(serverLevel, pos.above(), net.minecraft.world.entity.EntitySpawnReason.TRIGGERED);
                                    player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.igoo.herobrine.cursed_summoned"));
                                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WARDEN_EMERGE, net.minecraft.sounds.SoundSource.HOSTILE, 1.0f, 0.5f);
                                } else {
                                    // Triệu hồi bản bình thường
                                    boss = ModEntities.HEROBRINE.spawn(serverLevel, pos.above(), net.minecraft.world.entity.EntitySpawnReason.TRIGGERED);
                                    player.sendSystemMessage(net.minecraft.network.chat.Component.translatable("message.igoo.herobrine.summoned"));
                                    level.playSound(null, pos, net.minecraft.sounds.SoundEvents.WITHER_SPAWN, net.minecraft.sounds.SoundSource.HOSTILE, 1.0f, 1.0f);
                                }

                                if (boss != null) {
                                    level.playSound(null, pos, SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 1.0f, 1.0f);
                                    player.sendSystemMessage(Component.translatable("message.igoo.herobrine.summoned"));
                                }
                            });
                        });

                        return InteractionResult.SUCCESS;
                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    // Hàm phá hủy bàn thờ
    private static void destroyAltar(Level level, BlockPos center) {
        level.removeBlock(center.above(), false);
        level.removeBlock(center, false);

        BlockPos goldCenter = center.below();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                level.removeBlock(goldCenter.offset(x, 0, z), false);
            }
        }

        BlockPos[] torchPositions = { center.north(), center.south(), center.east(), center.west() };
        for (BlockPos torchPos : torchPositions) {
            level.removeBlock(torchPos, false);
        }

        level.playSound(null, center, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.BLOCKS, 1.0f, 1.0f);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION, 
                center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5, 
                20, 1.5, 1.5, 1.5, 0.1);
            serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, 
                center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5, 
                30, 1.0, 1.0, 1.0, 0.05);
        }
    }

    private static boolean isValidAltar(Level level, BlockPos center) {
        BlockPos goldCenter = center.below();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (!level.getBlockState(goldCenter.offset(x, 0, z)).is(Blocks.GOLD_BLOCK)) {
                    return false;
                }
            }
        }

        BlockPos[] torchPositions = { center.north(), center.south(), center.east(), center.west() };
        for (BlockPos torchPos : torchPositions) {
            var state = level.getBlockState(torchPos);
            if (!state.is(Blocks.REDSTONE_TORCH) && !state.is(Blocks.REDSTONE_WALL_TORCH)) {
                return false;
            }
        }
        return true;
    }
}