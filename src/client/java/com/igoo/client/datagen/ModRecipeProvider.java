package com.igoo.client.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.igoo.Igoo;
import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricPackOutput output,
                             CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            
            // 👇 KHAI BÁO HÀM COOKFOOD Ở ĐÂY (Ngoài buildRecipes)
            private void cookFood(List<ItemLike> inputs, ItemLike result, String groupName) {
                // 1. Smelting
                oreCooking(SmeltingRecipe::new, inputs, RecipeCategory.FOOD, CookingBookCategory.FOOD, 
                           result, 0.35f, 200, groupName, "_from_smelting");
                // 2. Smoking
                oreCooking(SmokingRecipe::new, inputs, RecipeCategory.FOOD, CookingBookCategory.FOOD, 
                           result, 0.35f, 100, groupName, "_from_smoking");
                // 3. Campfire
                oreCooking(CampfireCookingRecipe::new, inputs, RecipeCategory.FOOD, CookingBookCategory.FOOD, 
                           result, 0.35f, 600, groupName, "_from_campfire_cooking");
            }

            @Override
            public void buildRecipes() {
                // ===== Nấu chảy: raw + ore -> ingot =====
                for (Block ore : ModBlocks.ALL_ORES) {
                    String base = stripSuffix(path(ore), "_ore");
                    Item raw   = findItem("raw_" + base);
                    Item ingot = findItem(base + "_ingot");
                    if (raw == null || ingot == null) continue;

                    List<ItemLike> smeltables = List.of(raw, ore);

                    oreSmelting(smeltables, RecipeCategory.MISC, CookingBookCategory.MISC, ingot, 0.25f, 200, base);
                    oreBlasting(smeltables, RecipeCategory.MISC, CookingBookCategory.MISC, ingot, 0.25f, 100, base);
                }

                // ===== Nén: 9 ingot <-> 1 block =====
                for (Block block : ModBlocks.ALL_METALS) {
                    String base = stripSuffix(path(block), "_block");
                    Item ingot = findItem(base + "_ingot");
                    if (ingot == null) ingot = findItem(base);
                    if (ingot == null) continue;

                    nineBlockStorageRecipes(RecipeCategory.MISC, ingot, RecipeCategory.MISC, block);
                }
                
                // ===== Dụng cụ =====
                record ToolSet(Item ingot, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe) {}

                List<ToolSet> toolSets = List.of(
                    new ToolSet(ModItems.Aluminum_ingot, ModItems.Aluminum_sword, ModItems.Aluminum_pickaxe, ModItems.Aluminum_axe, ModItems.Aluminum_shovel, ModItems.Aluminum_hoe),
                    new ToolSet(ModItems.Tin_ingot, ModItems.Tin_sword, ModItems.Tin_pickaxe, ModItems.Tin_axe, ModItems.Tin_shovel, ModItems.Tin_hoe),
                    new ToolSet(ModItems.Chromium_ingot, ModItems.Chromium_sword, ModItems.Chromium_pickaxe, ModItems.Chromium_axe, ModItems.Chromium_shovel, ModItems.Chromium_hoe),
                    new ToolSet(ModItems.Titanium_ingot, ModItems.Titanium_sword, ModItems.Titanium_pickaxe, ModItems.Titanium_axe, ModItems.Titanium_shovel, ModItems.Titanium_hoe),
                    new ToolSet(ModItems.Tungsten_ingot, ModItems.Tungsten_sword, ModItems.Tungsten_pickaxe, ModItems.Tungsten_axe, ModItems.Tungsten_shovel, ModItems.Tungsten_hoe)
                );

                for (ToolSet set : toolSets) {
                    String axeName = BuiltInRegistries.ITEM.getKey(set.axe()).getPath();
                    String hoeName = BuiltInRegistries.ITEM.getKey(set.hoe()).getPath();

                    // Sword
                    shaped(RecipeCategory.COMBAT, set.sword())
                        .pattern("X").pattern("X").pattern("|")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);

                    // Pickaxe
                    shaped(RecipeCategory.TOOLS, set.pickaxe())
                        .pattern("XXX").pattern(" | ").pattern(" | ")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);

                    // Axe (2 kiểu)
                    shaped(RecipeCategory.TOOLS, set.axe())
                        .pattern("XX").pattern("X|").pattern(" |")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output, axeName);

                    shaped(RecipeCategory.TOOLS, set.axe())
                        .pattern("XX").pattern("|X").pattern("| ")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output, axeName + "_mirrored");

                    // Shovel
                    shaped(RecipeCategory.TOOLS, set.shovel())
                        .pattern("X").pattern("|").pattern("|")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);

                    // Hoe (2 kiểu)
                    shaped(RecipeCategory.TOOLS, set.hoe())
                        .pattern("XX").pattern(" |").pattern(" |")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output, hoeName);

                    shaped(RecipeCategory.TOOLS, set.hoe())
                        .pattern("XX").pattern("| ").pattern("| ")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output, hoeName + "_mirrored");
                }

                // ===== THỨC ĂN =====
                List<ItemLike> chickenSmeltables = List.of(ModItems.Chicken_wing);
                List<ItemLike> porkSmeltables = List.of(ModItems.Pork);
                List<ItemLike> baguetteSmeltables = List.of(Items.HAY_BLOCK);

                cookFood(chickenSmeltables, ModItems.Chicken_wing_cooked, "chicken_wing");
                cookFood(porkSmeltables, ModItems.Pork_cooked, "pork");
                cookFood(baguetteSmeltables, ModItems.Baguette, "baguette");

                // ===== PHÔ MAI =====
                shapeless(RecipeCategory.FOOD, ModItems.Cheese_full)
                    .requires(Items.MILK_BUCKET)
                    .requires(Items.WHEAT, 3)
                    .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                    .save(output);

                shaped(RecipeCategory.FOOD, ModItems.Cheese_full)
                    .pattern("CCC").pattern("CCC").pattern("CCC")
                    .define('C', ModItems.Cheese_slice)
                    .unlockedBy(getHasName(ModItems.Cheese_slice), has(ModItems.Cheese_slice))
                    .save(output, "cheese_full_from_slices");

                shapeless(RecipeCategory.FOOD, ModItems.Cheese_slice, 9)
                    .requires(ModItems.Cheese_full)
                    .unlockedBy(getHasName(ModItems.Cheese_full), has(ModItems.Cheese_full))
                    .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "IGOO Recipes";
    }

    // --- Các hàm helper ---
    private static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private static String stripSuffix(String name, String suffix) {
        return name.endsWith(suffix) ? name.substring(0, name.length() - suffix.length()) : name;
    }

    private static Item findItem(String id) {
        return BuiltInRegistries.ITEM
                .getOptional(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, id))
                .orElse(null);
    }
}