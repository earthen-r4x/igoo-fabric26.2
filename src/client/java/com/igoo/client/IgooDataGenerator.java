package com.igoo.client;

import com.igoo.client.datagen.*;
import com.igoo.worldgen.ModWorldConfiguredFeatures;
import com.igoo.worldgen.ModWorldPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class IgooDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModItemTagsProvider::new);
        pack.addProvider(ModWorldgenProvider::new);
        pack.addProvider(ModBiomeTagsProvider::new); 
        
        pack.addProvider(ModChestLootTableProvider::new);
        pack.addProvider(ModEntityLootTableProvider::new);
    }
    
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        // Chỉ đăng ký feature của Mod, bỏ hoàn toàn Vanilla
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModWorldConfiguredFeatures::configure);
        registryBuilder.add(Registries.PLACED_FEATURE, ModWorldPlacedFeatures::configure);
        registryBuilder.add(Registries.BIOME, ModWorldgenProvider::bootstrapBiomes);
    }
}