package com.igoo.client;

import com.igoo.client.datagen.ModModelProvider;
import com.igoo.client.datagen.ModBlockTagsProvider;
import com.igoo.client.datagen.ModBlockLootTableProvider;
import com.igoo.client.datagen.ModRecipeProvider;
import com.igoo.client.datagen.ModItemTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class IgooDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(
        FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockTagsProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModItemTagsProvider::new);
    }
}