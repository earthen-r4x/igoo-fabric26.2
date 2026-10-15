package com.igoo.creativemodtab;

import com.igoo.Igoo;
import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
//    Item
    public static final CreativeModeTab ALUMINUM_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "aluminum_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Aluminum_ingot))
    .title(Component.translatable("creativemodetab.igoo.aluminum_items"))
    .displayItems((parameters, output) -> {ModItems.ALL_ITEMS.forEach(output::accept);})
                    
    .build());
    
//    Block
    public static final CreativeModeTab ALUMINUM_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "aluminum_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.Aluminum_block))
    .title(Component.translatable("creativemodetab.igoo.aluminum_blocks"))
    .displayItems((parameters, output) -> {ModBlocks.ALL_BLOCKS.forEach(output::accept);})
                    
    .build());
    
    public static void registerModCreativeModeTabs() {
        Igoo.LOGGER.info("Registering Creative Mode Tabs for " + Igoo.MOD_ID);
    }
}