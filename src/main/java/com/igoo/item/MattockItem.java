package com.igoo.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Mattock = rìu + xẻng + cuốc trong một.
 *  - Đào nhanh mọi block của rìu / xẻng / cuốc (tool rules được ghép trong ModItems).
 *  - Chuột phải: bóc vỏ gỗ, cạo đồng, tẩy sáp (như rìu); nếu không phải thì xới đất (như cuốc).
 *  - Shift + chuột phải: làm đường đất (như xẻng).
 */
public class MattockItem extends AxeItem {

    public MattockItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // 1. Hành vi của rìu (bóc vỏ, cạo, tẩy sáp)
        InteractionResult axeResult = super.useOn(context);
        if (axeResult.consumesAction()) return axeResult;

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);
        boolean pathMode = player != null && player.isShiftKeyDown();

        if (context.getClickedFace() == Direction.DOWN || !level.getBlockState(pos.above()).isAir()) {
            return InteractionResult.PASS;
        }

        BlockState result = null;
        SoundEvent sound = null;

        if (pathMode) {
            // 2. Xẻng: làm đường đất
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.PODZOL)
                    || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.MYCELIUM)) {
                result = Blocks.DIRT_PATH.defaultBlockState();
                sound = SoundEvents.SHOVEL_FLATTEN;
            }
        } else {
            // 3. Cuốc: xới đất
            if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.DIRT_PATH)) {
                result = Blocks.FARMLAND.defaultBlockState();
                sound = SoundEvents.HOE_TILL;
            } else if (state.is(Blocks.COARSE_DIRT)) {
                result = Blocks.DIRT.defaultBlockState();
                sound = SoundEvents.HOE_TILL;
            }
        }

        if (result == null) return InteractionResult.PASS;

        level.playSound(player, pos, sound, SoundSource.BLOCKS, 1.0f, 1.0f);
        if (!level.isClientSide()) {
            level.setBlock(pos, result, 11);
            if (player != null) {
                EquipmentSlot slot = context.getHand() == InteractionHand.MAIN_HAND
                        ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                context.getItemInHand().hurtAndBreak(1, player, slot);
            }
        }
        return InteractionResult.SUCCESS;
    }
}