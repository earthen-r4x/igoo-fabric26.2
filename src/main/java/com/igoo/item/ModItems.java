package com.igoo.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;
import com.igoo.entity.ModEntities;
import java.util.stream.Stream;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.equipment.ArmorType;

public class ModItems {
//    ==================================== Ingots ==============================================
    // Ingot
    public static final Item Aluminum_ingot = registerItem("aluminum_ingot", Item::new);
    public static final Item Azurite_ingot = registerItem("azurite_ingot", Item::new);
    public static final Item Bronze_ingot = registerItem("bronze_ingot", Item::new);
    public static final Item Carbon_steel_ingot = registerItem("carbon_steel_ingot", Item::new);
    public static final Item Chromium_ingot = registerItem("chromium_ingot", Item::new);
    public static final Item Stainless_steel_ingot = registerItem("stainless_steel_ingot", Item::new);
    public static final Item Tin_ingot = registerItem("tin_ingot", Item::new);
    public static final Item Titanium_alloy = registerItem("titanium_alloy", Item::new);
    public static final Item Titanium_ingot = registerItem("titanium_ingot", Item::new);
    public static final Item Tungsten_carbide_ingot = registerItem("tungsten_carbide_ingot", Item::new);
    public static final Item Tungsten_ingot = registerItem("tungsten_ingot", Item::new);
    //  Raw
    public static final Item Raw_aluminum = registerItem("raw_aluminum", Item::new);
    public static final Item Raw_chromium = registerItem("raw_chromium", Item::new);
    public static final Item Raw_tin = registerItem("raw_tin", Item::new);
    public static final Item Raw_titanium = registerItem("raw_titanium", Item::new);
    public static final Item Raw_tungsten = registerItem("raw_tungsten", Item::new);
    
    // List
    public static final List<Item> ALL_INGOTS = List.of(
        Aluminum_ingot,
        Azurite_ingot,
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

    // ===== AZURITE =====
    public static final Item Azurite_sword   = registerSword("azurite",   ModToolMaterials.AZURITE);
    public static final Item Azurite_pickaxe = registerPickaxe("azurite", ModToolMaterials.AZURITE);
    public static final Item Azurite_axe     = registerAxe("azurite",     ModToolMaterials.AZURITE);
    public static final Item Azurite_shovel  = registerShovel("azurite",  ModToolMaterials.AZURITE);
    public static final Item Azurite_hoe     = registerHoe("azurite",     ModToolMaterials.AZURITE);

    // Dolabra: cuốc chim nâng cấp từ cuốc chim kim cương (smithing table)
    public static final Item Azurite_dolabra = registerItem("azurite_dolabra",
        p -> new Item(p.pickaxe(ModToolMaterials.AZURITE_DOLABRA, 1.5f, -2.8f)));

    // Mattock: rìu + xẻng + cuốc trong một
    public static final Item Azurite_mattock = registerItem("azurite_mattock",
        p -> new MattockItem(ModToolMaterials.AZURITE, 5.0f, -3.0f, p));

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
        Azurite_sword, Azurite_pickaxe, Azurite_axe, Azurite_shovel, Azurite_hoe, Azurite_dolabra, Azurite_mattock,
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
    
//    ================================= ARMOR ===========================================
    public static final Item Azurite_helmet     = registerItem("azurite_helmet",
        p -> new Item(p.humanoidArmor(ModArmorMaterials.AZURITE, ArmorType.HELMET)));
    public static final Item Azurite_chestplate = registerItem("azurite_chestplate",
        p -> new Item(p.humanoidArmor(ModArmorMaterials.AZURITE, ArmorType.CHESTPLATE)));
    public static final Item Azurite_leggings   = registerItem("azurite_leggings",
        p -> new Item(p.humanoidArmor(ModArmorMaterials.AZURITE, ArmorType.LEGGINGS)));
    public static final Item Azurite_boots      = registerItem("azurite_boots",
        p -> new Item(p.humanoidArmor(ModArmorMaterials.AZURITE, ArmorType.BOOTS)));
    public static final Item Azurite_horse_armor = registerItem("azurite_horse_armor",
        p -> new Item(p.horseArmor(ModArmorMaterials.AZURITE)));

    public static final List<Item> ALL_ARMORS = List.of(
        Azurite_helmet, Azurite_chestplate, Azurite_leggings, Azurite_boots, Azurite_horse_armor
    );

//    ================================================= Foods =================================================
    //  Khai báo thuộc tính thức ăn
    public static final FoodProperties RAW_MEAT             = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties COOKED_MEAT          = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();
    public static final FoodProperties BAGUETTE_FOOD        = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build();
    public static final FoodProperties CHEESE_FULL_FOOD     = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build();
    public static final FoodProperties CHEESE_SLICE_FOOD    = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).build();

    // Consumable cho thức ăn
    public static final Consumable DEFAULT_FOOD  = Consumables.defaultFood().build();
    public static final Consumable FAST_FOOD     = Consumables.defaultFood().consumeSeconds(0.8f) .build();
    
    public static final Item Chicken_wing        = registerItem("chicken_wing", p -> new Item(p.food(RAW_MEAT, DEFAULT_FOOD)));
    public static final Item Chicken_wing_cooked = registerItem("chicken_wing_cooked", p -> new Item(p.food(COOKED_MEAT, DEFAULT_FOOD)));
    public static final Item Pork                = registerItem("pork", p -> new Item(p.food(RAW_MEAT, DEFAULT_FOOD)));
    public static final Item Pork_cooked         = registerItem("pork_cooked", p -> new Item(p.food(COOKED_MEAT, DEFAULT_FOOD)));
    public static final Item Baguette            = registerItem("baguette", p -> new Item(p.food(BAGUETTE_FOOD, DEFAULT_FOOD)));
    public static final Item Cheese_full         = registerItem("cheese_full", p -> new Item(p.food(CHEESE_FULL_FOOD, DEFAULT_FOOD)));
    public static final Item Cheese_slice        = registerItem("cheese_slice", p -> new Item(p.food(CHEESE_SLICE_FOOD, FAST_FOOD)));
    
    public static final List<Item> ALL_FOODS = List.of(Chicken_wing, Chicken_wing_cooked, Pork, Pork_cooked, Baguette, Cheese_full, Cheese_slice);

    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name),
        function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name)))));
    }
    
// ===================================================== Entities =======================================
    public static final Item Herobrine_spawn_egg = registerItem("herobrine_spawn_egg",
        p -> new SpawnEggItem(p.spawnEgg(ModEntities.HEROBRINE)));
    
    
    
    public static void registerModItems() {
        Igoo.LOGGER.info("Registering Mod Items for " + Igoo.MOD_ID);

        // Mattock đào nhanh cả block của xẻng và cuốc: ghép thêm tool rules vào component TOOL
        DefaultItemComponentEvents.MODIFY.register(context -> context.modify(Azurite_mattock, builder -> {
            Tool base = Azurite_mattock.components().get(DataComponents.TOOL);
            if (base == null) return;
            float speed = ModToolMaterials.AZURITE.speed();
            List<Tool.Rule> rules = new ArrayList<>(base.rules());
            rules.add(Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_SHOVEL), speed));
            rules.add(Tool.Rule.minesAndDrops(BuiltInRegistries.BLOCK.getOrThrow(BlockTags.MINEABLE_WITH_HOE), speed));
            builder.set(DataComponents.TOOL, new Tool(rules, base.defaultMiningSpeed(), base.damagePerBlock(), base.canDestroyBlocksInCreative()));
        }));
    }
}