package com.igoo;

import com.igoo.creativemodtab.ModCreativeModeTabs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igoo.item.ModItems;
import com.igoo.block.ModBlocks;

import net.fabricmc.api.ModInitializer;

public class Igoo implements ModInitializer {
	
	public static final String MOD_ID = "igoo";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		LOGGER.info("Igoo mod initialized");
	}
}
