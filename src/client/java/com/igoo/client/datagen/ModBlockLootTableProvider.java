package com.igoo.client.datagen;

import java.util.concurrent.CompletableFuture;

import com.igoo.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(
            FabricPackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    
    
    @Override
    public void generate() {
        ModBlocks.ALL_METALS.forEach(this::dropSelf);
        ModBlocks.ALL_DIRT_BLOCKS.forEach(this::dropSelf);
        
        // Quặng: rơi raw tương ứng
        ModBlocks.ORE_DROPS.forEach((ore, drop) ->
            add(ore, block -> createMultipleOreDrops(block, drop.raw(), drop.min(), drop.max())));
        
        // Khối cỏ: Rơi ra đất
        add(ModBlocks.Cursed_grass_block, 
            createSilkTouchDispatchTable(
                ModBlocks.Cursed_grass_block, 
                this.applyExplosionDecay(
                    ModBlocks.Cursed_grass_block, 
                    LootItem.lootTableItem(ModBlocks.Cursed_dirt)
                )
            )
        );
    }

    public LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(
                                        enchantments.getOrThrow(Enchantments.FORTUNE))
                                )
                )
        );
    }
}