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
    public static final CreativeModeTab ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Aluminum_ingot))
    .title(Component.translatable("creativemodetab.igoo.items"))
    .displayItems((parameters, output) -> {ModItems.ALL_ITEMS.forEach(output::accept);})
    .build());

//    Food
    public static final CreativeModeTab FOOD_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "foods"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Chicken_wing))
    .title(Component.translatable("creativemodetab.igoo.foods"))
    .displayItems((parameters, output) -> {ModItems.ALL_FOODS.forEach(output::accept);})
    .build());

    
//    Block
    public static final CreativeModeTab BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.Aluminum_block))
    .title(Component.translatable("creativemodetab.igoo.blocks"))
    .displayItems((parameters, output) -> {
        ModBlocks.ALL_BLOCKS.forEach(output::accept);
        ModBlocks.ALL_ENVIROMENT_BLOCKS.forEach(output::accept);
        })
    .build());
    
//    Tools
    public static final CreativeModeTab TOOL_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "tools"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.Aluminum_pickaxe))
    .title(Component.translatable("creativemodetab.igoo.tools"))
    .displayItems((parameters, output) -> {ModItems.ALL_TOOLS.forEach(output::accept);})
    .build());
    
    public static void registerModCreativeModeTabs() {
        Igoo.LOGGER.info("Registering Creative Mode Tabs for " + Igoo.MOD_ID);
    }
}