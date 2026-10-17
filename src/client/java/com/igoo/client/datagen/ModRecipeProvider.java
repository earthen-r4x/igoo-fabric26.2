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
import net.minecraft.world.item.crafting.CookingBookCategory;
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
            @Override
            public void buildRecipes() {
                // ===== Nấu chảy: raw + ore -> ingot =====
                for (Block ore : ModBlocks.ALL_ORES) {
                    String base = stripSuffix(path(ore), "_ore"); // aluminum
                    Item raw   = findItem("raw_" + base); // Raw_aluminum
                    Item ingot = findItem(base + "_ingot"); // Aluminum_ingot
                    if (raw == null || ingot == null) continue;

                    List<ItemLike> smeltables = List.of(raw, ore);

                    oreSmelting(smeltables, RecipeCategory.MISC, CookingBookCategory.MISC,
                            ingot, 0.25f, 200, base); // Lò nung 10s
                    oreBlasting(smeltables, RecipeCategory.MISC, CookingBookCategory.MISC,
                            ingot, 0.25f, 100, base); // Lò sắt 5s
                }

                // ===== Nén: 9 ingot <-> 1 block =====
                for (Block block : ModBlocks.ALL_METALS) {
                    String base = stripSuffix(path(block), "_block"); // aluminum
                    Item ingot = findItem(base + "_ingot");
                    if (ingot == null) ingot = findItem(base); // titanium_alloy (không có _ingot)
                    if (ingot == null) continue;

                    nineBlockStorageRecipes(
                            RecipeCategory.MISC, ingot,
                            RecipeCategory.MISC, block);
                }
                
                record ToolSet(Item ingot, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe) {}

                List<ToolSet> toolSets = List.of(
                    new ToolSet(ModItems.Aluminum_ingot, ModItems.Aluminum_sword, ModItems.Aluminum_pickaxe, ModItems.Aluminum_axe, ModItems.Aluminum_shovel, ModItems.Aluminum_hoe),
                    new ToolSet(ModItems.Tin_ingot, ModItems.Tin_sword, ModItems.Tin_pickaxe, ModItems.Tin_axe, ModItems.Tin_shovel, ModItems.Tin_hoe),
                    new ToolSet(ModItems.Chromium_ingot, ModItems.Chromium_sword, ModItems.Chromium_pickaxe, ModItems.Chromium_axe, ModItems.Chromium_shovel, ModItems.Chromium_hoe),
                    new ToolSet(ModItems.Titanium_ingot, ModItems.Titanium_sword, ModItems.Titanium_pickaxe, ModItems.Titanium_axe, ModItems.Titanium_shovel, ModItems.Titanium_hoe),
                    new ToolSet(ModItems.Tungsten_ingot, ModItems.Tungsten_sword, ModItems.Tungsten_pickaxe, ModItems.Tungsten_axe, ModItems.Tungsten_shovel, ModItems.Tungsten_hoe)
                );

                for (ToolSet set : toolSets) {
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

                    // Axe
                    shaped(RecipeCategory.TOOLS, set.axe())
                        .pattern("XX").pattern("X|").pattern(" |")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);

                    // Shovel
                    shaped(RecipeCategory.TOOLS, set.shovel())
                        .pattern("X").pattern("|").pattern("|")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);

                    // Hoe
                    shaped(RecipeCategory.TOOLS, set.hoe())
                        .pattern("XX").pattern(" |").pattern(" |")
                        .define('X', set.ingot()).define('|', Items.STICK)
                        .unlockedBy(getHasName(set.ingot()), has(set.ingot())).save(output);
                }
            }
        };
    }

    @Override
    public String getName() {
        return "IGOO Recipes";
    }

    // Lấy path của block
    private static String path(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    // Bỏ hậu tố
    private static String stripSuffix(String name, String suffix) {
        return name.endsWith(suffix) ? name.substring(0, name.length() - suffix.length()) : name;
    }

    // Tìm item theo ID, trả về null nếu không tồn tại
    private static Item findItem(String id) {
        return BuiltInRegistries.ITEM
                .getOptional(Identifier.fromNamespaceAndPath(Igoo.MOD_ID, id))
                .orElse(null);
    }
}