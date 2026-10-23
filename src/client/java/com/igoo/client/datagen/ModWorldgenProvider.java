package com.igoo.client.datagen;

import com.igoo.block.ModBlocks; // Import ModBlocks
import com.igoo.worldgen.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.SurfaceRules; // Import SurfaceRules
import net.minecraft.world.level.levelgen.placement.CaveSurface; // Import CaveSurface

import java.util.concurrent.CompletableFuture;

public class ModWorldgenProvider extends FabricDynamicRegistryProvider {

    public ModWorldgenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
    }

    public static void bootstrapBiomes(BootstrapContext<Biome> context) {
        var placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        var carverGetter = context.lookup(Registries.CONFIGURED_CARVER);

        // 1. Hiệu ứng màu sắc
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .waterColor(0x8B0000)
                .grassColorOverride(0x550000)
                .foliageColorOverride(0x550000);

        // 3. Cấu hình sinh thế giới (Rỗng, vì feature đã được thêm qua BiomeModifications)
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)
                .build();

        // 4. Cấu hình sinh vật
        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIE, 4, 4))
                .addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, 4, 4))
                .addSpawn(MobCategory.MONSTER, 20, new MobSpawnSettings.SpawnerData(EntityTypes.WITCH, 1, 2))
                .addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityTypes.HORSE, 2, 6))
                .build();

        // 5. Đăng ký Biome
        context.register(ModBiomes.CURSED_SAVANNA, new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(effects.build())
                .setAttribute(EnvironmentAttributes.SKY_COLOR, Integer.valueOf(0xFF8B0000))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, Integer.valueOf(0xFF8B0000))
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, Integer.valueOf(0xFF8B0000))
                .mobSpawnSettings(spawns)
                .generationSettings(generation)
                .build());
    }

    @Override
    public String getName() {
        return "Igoo Worldgen";
    }
}