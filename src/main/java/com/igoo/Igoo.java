package com.igoo;

import com.igoo.creativemodtab.ModCreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igoo.item.ModItems;
import com.igoo.block.ModBlocks;
import com.igoo.command.ModCommands;
import com.igoo.entity.ModEntities;
import com.igoo.event.AltarSummonEvent;
import com.igoo.worldgen.ModBiomeModifications;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

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
        ModBiomeModifications.register();
        ModCommands.register();
        LOGGER.info("Igoo mod initialized");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}