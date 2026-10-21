package com.igoo;

import com.igoo.creativemodtab.ModCreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igoo.item.ModItems;
import com.igoo.block.ModBlocks;
import com.igoo.entity.ModEntities;
import com.igoo.worldgen.ModWorldPlacedFeatures;
import com.igoo.event.AltarSummonEvent;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.GenerationStep;

public class Igoo implements ModInitializer {
    public static final String MOD_ID = "igoo";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    @Override
    public void onInitialize() {
        ModCreativeModeTabs.registerModCreativeModeTabs();
	ModItems.registerModItems();
	ModBlocks.registerModBlocks();
        ModEntities.registerModEntities();
        AltarSummonEvent.register();
        
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModWorldPlacedFeatures.ALUMINUM_ORE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModWorldPlacedFeatures.TIN_ORE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModWorldPlacedFeatures.CHROMIUM_ORE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                 GenerationStep.Decoration.UNDERGROUND_ORES,
                ModWorldPlacedFeatures.TITANIUM_ORE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModWorldPlacedFeatures.TUNGSTEN_ORE_PLACED_KEY
        );
            LOGGER.info("Igoo mod initialized");
    }
    
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
