package com.igoo.item;

import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItems {
    // Ingot
    public static final Item Aluminum_ingot = registerItem("aluminum_ingot", Item::new);
    public static final Item Bronze_ingot = registerItem("bronze_ingot", Item:: new);
    public static final Item Carbon_steel_ingot = registerItem("carbon_steel_ingot", Item:: new);
    public static final Item Chromium_ingot = registerItem("chromium_ingot", Item:: new);
    public static final Item Stainless_steel_ingot = registerItem("stainless_steel_ingot", Item:: new);
    public static final Item Tin_ingot = registerItem("tin_ingot", Item:: new);
    public static final Item Titanium_alloy = registerItem("titanium_alloy", Item:: new);
    public static final Item Titanium_ingot = registerItem("titanium_ingot", Item:: new);
    public static final Item Tungsten_carbide_ingot = registerItem("tungsten_carbide_ingot", Item:: new);
    public static final Item Tungsten_ingot = registerItem("tungsten_ingot", Item:: new);
//    Raw
    public static final Item Raw_aluminum = registerItem("raw_aluminum", Item:: new);
    public static final Item Raw_chromium = registerItem("raw_chromium", Item:: new);
    public static final Item Raw_tin = registerItem("raw_tin", Item:: new);
    public static final Item Raw_titanium = registerItem("raw_titanium", Item:: new);
    public static final Item Raw_tungsten = registerItem("raw_tungsten", Item:: new);

    public static final List<Item> ALL_ITEMS = List.of(
        Aluminum_ingot,
        Bronze_ingot,
        Carbon_steel_ingot,
        Chromium_ingot,
        Stainless_steel_ingot,
        Tin_ingot,
        Titanium_alloy,
        Titanium_ingot,
        Tungsten_carbide_ingot,
        Tungsten_ingot,
        Raw_aluminum,
        Raw_chromium,
        Raw_tin,
        Raw_titanium,
        Raw_tungsten
    );
    
    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name),
        function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name)))));
    }
    
    
    public static void registerModItems() {
        Igoo.LOGGER.info("Registering Mod Items for " + Igoo.MOD_ID);
    }
}