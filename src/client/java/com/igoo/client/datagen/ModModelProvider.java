package com.igoo.client.datagen;

import com.igoo.Igoo;
import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
        ModBlocks.ALL_BLOCKS.forEach(block -> blockModelGenerator.createTrivialCube(block));
        ModBlocks.ALL_DIRT_BLOCKS.forEach(block -> blockModelGenerator.createTrivialCube(block));

        TextureMapping normalMapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_dirt")))
            .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block_top")))
            .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block")));
            
        Identifier normalModelId = Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block");
        ModelTemplates.CUBE_BOTTOM_TOP.create(normalModelId, normalMapping, blockModelGenerator.modelOutput);
        MultiVariant normalVariant = BlockModelGenerators.plainVariant(normalModelId);

        TextureMapping snowyMapping = new TextureMapping()
            .put(TextureSlot.BOTTOM, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_dirt")))
            .put(TextureSlot.TOP, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block_snow_top")))
            .put(TextureSlot.SIDE, new Material(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block_snow")));
            
        Identifier snowyModelId = Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "block/cursed_grass_block_snow");
        ModelTemplates.CUBE_BOTTOM_TOP.create(snowyModelId, snowyMapping, blockModelGenerator.modelOutput);
        MultiVariant snowyVariant = BlockModelGenerators.plainVariant(snowyModelId);

        blockModelGenerator.createGrassLikeBlock(
                ModBlocks.Cursed_grass_block,
                normalVariant,
                snowyVariant
        );
    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        ModItems.ALL_ITEMS.forEach(item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
        ModItems.ALL_FOODS.forEach(food -> itemModelGenerator.generateFlatItem(food, ModelTemplates.FLAT_HANDHELD_ITEM));
        ModItems.ALL_TOOLS.forEach(tool -> itemModelGenerator.generateFlatItem(tool, ModelTemplates.FLAT_HANDHELD_ITEM));
        itemModelGenerator.generateFlatItem(ModItems.Herobrine_spawn_egg, ModelTemplates.FLAT_ITEM);
    }
}
