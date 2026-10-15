package com.igoo.item;

import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    // Item registration
    public static final Item Aluminum_ingot = registerItem("aluminum_ingot", Item::new);
    public static final Item Bronze_ingot = registerItem("bronze_ingot", Item:: new);
    public static final Item Carbon_steel_ingot = registerItem("carbon_steel_ingot", Item:: new);
    
    
    public static final List<Item> ALL_ITEMS = List.of(Aluminum_ingot, Bronze_ingot, Carbon_steel_ingot);
    
    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name),
        function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Igoo.LOGGER.info("Registering Mod Items for " + Igoo.MOD_ID);
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            ALL_ITEMS.forEach(output::accept);
        });
    }

//    private static void registerItem(ResourceKey<Item> key, Item item) {
//        Registry.register(BuiltInRegistries.ITEM, key, item);
//    }
}