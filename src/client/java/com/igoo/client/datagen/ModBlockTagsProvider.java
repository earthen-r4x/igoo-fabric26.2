package com.igoo.client.datagen;

import java.util.concurrent.CompletableFuture;

import com.igoo.block.ModBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        var pickaxe = builder(BlockTags.MINEABLE_WITH_PICKAXE);
        var stoneTool = builder(BlockTags.NEEDS_STONE_TOOL);
        var ironTool = builder(BlockTags.NEEDS_IRON_TOOL);
        var diamondTool = builder(BlockTags.NEEDS_DIAMOND_TOOL);

        var dirt = builder(BlockTags.DIRT);
        var shovel = builder(BlockTags.MINEABLE_WITH_SHOVEL);


        ModBlocks.ALL_BLOCKS.forEach(block -> {
            var key = BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();

            // Tất cả block đều đào bằng cuốc
            pickaxe.add(key);

            // Phân loại cấp công cụ
            if (ModBlocks.MINE_WITH_STONE.contains(block)) {
                stoneTool.add(key);
            } else if (ModBlocks.MINE_WITH_IRON.contains(block)) {
                ironTool.add(key);
            } else if (ModBlocks.MINE_WITH_DIAMOND.contains(block)) {
                diamondTool.add(key);
            }
        });

        ModBlocks.ALL_DIRT_BLOCKS.forEach(block -> {
           var key = BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
           dirt.add(key);
           shovel.add(key);
        });
    }
}