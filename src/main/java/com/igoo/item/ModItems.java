package com.igoo.item;

import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;
import java.util.stream.Stream;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

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
    //  Raw
    public static final Item Raw_aluminum = registerItem("raw_aluminum", Item:: new);
    public static final Item Raw_chromium = registerItem("raw_chromium", Item:: new);
    public static final Item Raw_tin = registerItem("raw_tin", Item:: new);
    public static final Item Raw_titanium = registerItem("raw_titanium", Item:: new);
    public static final Item Raw_tungsten = registerItem("raw_tungsten", Item:: new);
    
    public static final List<Item> ALL_INGOTS = List.of(
        Aluminum_ingot,
        Bronze_ingot,
        Carbon_steel_ingot,
        Chromium_ingot,
        Stainless_steel_ingot,
        Tin_ingot,
        Titanium_alloy,
        Titanium_ingot,
        Tungsten_carbide_ingot,
        Tungsten_ingot
    );
    
    public static final List<Item> ALL_RAWS = List.of(
        Raw_aluminum,
        Raw_chromium,
        Raw_tin,
        Raw_titanium,
        Raw_tungsten
    );
    
    public static final List<Item> ALL_ITEMS = Stream.concat(ALL_INGOTS.stream(), ALL_RAWS.stream()).toList();
    
//    ================================= TOOLS ===========================================
    // ===== ALUMINUM =====
    public static final Item Aluminum_sword   = registerSword("aluminum",   ModToolMaterials.ALUMINUM);
    public static final Item Aluminum_pickaxe = registerPickaxe("aluminum", ModToolMaterials.ALUMINUM);
    public static final Item Aluminum_axe     = registerAxe("aluminum",     ModToolMaterials.ALUMINUM);
    public static final Item Aluminum_shovel  = registerShovel("aluminum",  ModToolMaterials.ALUMINUM);
    public static final Item Aluminum_hoe     = registerHoe("aluminum",     ModToolMaterials.ALUMINUM);

    // ===== TIN =====
    public static final Item Tin_sword   = registerSword("tin",   ModToolMaterials.TIN);
    public static final Item Tin_pickaxe = registerPickaxe("tin", ModToolMaterials.TIN);
    public static final Item Tin_axe     = registerAxe("tin",     ModToolMaterials.TIN);
    public static final Item Tin_shovel  = registerShovel("tin",  ModToolMaterials.TIN);
    public static final Item Tin_hoe     = registerHoe("tin",     ModToolMaterials.TIN);

    // ===== CHROMIUM =====
    public static final Item Chromium_sword   = registerSword("chromium",   ModToolMaterials.CHROMIUM);
    public static final Item Chromium_pickaxe = registerPickaxe("chromium", ModToolMaterials.CHROMIUM);
    public static final Item Chromium_axe     = registerAxe("chromium",     ModToolMaterials.CHROMIUM);
    public static final Item Chromium_shovel  = registerShovel("chromium",  ModToolMaterials.CHROMIUM);
    public static final Item Chromium_hoe     = registerHoe("chromium",     ModToolMaterials.CHROMIUM);

    // ===== TITANIUM =====
    public static final Item Titanium_sword   = registerSword("titanium",   ModToolMaterials.TITANIUM);
    public static final Item Titanium_pickaxe = registerPickaxe("titanium", ModToolMaterials.TITANIUM);
    public static final Item Titanium_axe     = registerAxe("titanium",     ModToolMaterials.TITANIUM);
    public static final Item Titanium_shovel  = registerShovel("titanium",  ModToolMaterials.TITANIUM);
    public static final Item Titanium_hoe     = registerHoe("titanium",     ModToolMaterials.TITANIUM);

    // ===== TUNGSTEN =====
    public static final Item Tungsten_sword   = registerSword("tungsten",   ModToolMaterials.TUNGSTEN);
    public static final Item Tungsten_pickaxe = registerPickaxe("tungsten", ModToolMaterials.TUNGSTEN);
    public static final Item Tungsten_axe     = registerAxe("tungsten",     ModToolMaterials.TUNGSTEN);
    public static final Item Tungsten_shovel  = registerShovel("tungsten",  ModToolMaterials.TUNGSTEN);
    public static final Item Tungsten_hoe     = registerHoe("tungsten",     ModToolMaterials.TUNGSTEN);

    public static final List<Item> ALL_TOOLS = List.of(
        Aluminum_sword, Aluminum_pickaxe, Aluminum_axe, Aluminum_shovel, Aluminum_hoe,
        Tin_sword, Tin_pickaxe, Tin_axe, Tin_shovel, Tin_hoe,
        Chromium_sword, Chromium_pickaxe, Chromium_axe, Chromium_shovel, Chromium_hoe,
        Titanium_sword, Titanium_pickaxe, Titanium_axe, Titanium_shovel, Titanium_hoe,
        Tungsten_sword, Tungsten_pickaxe, Tungsten_axe, Tungsten_shovel, Tungsten_hoe
    );

    private static Item registerSword(String base, ToolMaterial mat) {
        return registerItem(base + "_sword", p -> new Item(p.sword(mat, 3.0f, -2.4f)));
    }

    private static Item registerPickaxe(String base, ToolMaterial mat) {
        return registerItem(base + "_pickaxe", p -> new Item(p.pickaxe(mat, 1.0f, -2.8f)));
    }

    private static Item registerAxe(String base, ToolMaterial mat) {
        return registerItem(base + "_axe", p -> new AxeItem(mat, 6.0f, -3.0f, p));
    }

    private static Item registerShovel(String base, ToolMaterial mat) {
        return registerItem(base + "_shovel", p -> new ShovelItem(mat, 1.5f, -3.0f, p));
    }

    private static Item registerHoe(String base, ToolMaterial mat) {
        return registerItem(base + "_hoe", p -> new HoeItem(mat, -3.0f, 0.0f, p));
    }
    
//    =====================================================================================================
    
    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name),
        function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name)))));
    }
    
    
    public static void registerModItems() {
        Igoo.LOGGER.info("Registering Mod Items for " + Igoo.MOD_ID);
    }
}