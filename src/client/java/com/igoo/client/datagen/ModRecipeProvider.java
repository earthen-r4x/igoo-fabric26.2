/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igoo.client.datagen;

import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;
import java.util.List;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
/**
 *
 * @author tranx
 */
public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> Amethyst_smeltable = List.of(ModItems.Raw_aluminum, ModBlocks.Aluminum_ore);
                oreSmelting(Amethyst_smeltable, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.Aluminum_ingot, 0.25f, 200, "aluminum");
                oreBlasting(Amethyst_smeltable, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.Aluminum_ingot, 0.25f, 100, "aluminum");
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.Aluminum_ingot, RecipeCategory.MISC, ModBlocks.Aluminum_block);
//                shaped(RecipeCategory.MISC, ModBlocks.Aluminum_block)
//                        .pattern("RRR")
//                        .pattern("RRR")
//                        .pattern("RRR")
//                        .define('R', ModItems.Aluminum_ingot)
//                        .unlockedBy(getHasName(ModItems.Raw_aluminum), has(ModItems.Raw_aluminum))
//                        .group("aluminum")
//                        .save(output);
                
            }
        };
    }
    
    @Override
    public String getName() {
        return "IGOO Recipes";
    }
}
