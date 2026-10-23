package com.igoo.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeModifications {

    public static void register() {
        // Quặng Mod
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.ALUMINUM_ORE_PLACED_KEY);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.TIN_ORE_PLACED_KEY);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CHROMIUM_ORE_PLACED_KEY);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.TITANIUM_ORE_PLACED_KEY);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.TUNGSTEN_ORE_PLACED_KEY);

        // Quặng Vanilla
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_COAL_UPPER);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_COAL_LOWER);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_IRON_UPPER);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_IRON_MIDDLE);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_IRON_SMALL);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_COPPER);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_GOLD);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_REDSTONE);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_LAPIS);
        
        // Kim cương
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_DIAMOND_SMALL);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_DIAMOND_MEDIUM);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_DIAMOND_LARGE);
        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacedFeatures.CURSED_ORE_DIAMOND_BURIED);

        // Thảm thực vật
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacedFeatures.CURSED_TREES_SAVANNA);
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacedFeatures.CURSED_PATCH_TALL_GRASS);
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModWorldPlacedFeatures.CURSED_PATCH_DEAD_BUSH);
    }

    private static void addFeature(GenerationStep.Decoration step, net.minecraft.resources.ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> key) {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.CURSED_SAVANNA),
                step,
                key
        );
    }
}