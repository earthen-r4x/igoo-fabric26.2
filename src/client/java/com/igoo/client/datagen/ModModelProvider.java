package com.igoo.client.datagen;

import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
        ModBlocks.ALL_BLOCKS.forEach(block -> blockModelGenerator.createTrivialCube(block));
    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        ModItems.ALL_ITEMS.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }
}
