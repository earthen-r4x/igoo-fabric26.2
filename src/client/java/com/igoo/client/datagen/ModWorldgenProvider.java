package com.igoo.client.datagen;

import com.igoo.worldgen.ModBiomes;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.concurrent.CompletableFuture;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;

public class ModWorldgenProvider extends FabricDynamicRegistryProvider {

    public ModWorldgenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.add(ModBiomes.CURSED_SAVANNA, createCursedSavanna(registries));
    }

    private Biome createCursedSavanna(HolderLookup.Provider registries) {
        // 1. Hiệu ứng cơ bản (Nước, cỏ, lá)
        BiomeSpecialEffects.Builder effectsBuilder = new BiomeSpecialEffects.Builder()
                .waterColor(0x8B0000)             // Nước màu đỏ sẫm
                .grassColorOverride(0x550000)     // Cỏ màu nâu đỏ úa tàn
                .foliageColorOverride(0x550000);  // Lá màu nâu đỏ úa tàn

        // 2. Thuộc tính môi trường (Bầu trời, Sương mù)
        // Sử dụng int (mã Hex 0xAARRGGBB) thay vì ColorRGBA
        EnvironmentAttributeMap environmentAttributes = EnvironmentAttributeMap.builder()
                .set(EnvironmentAttributes.SKY_COLOR, 0xFF8B0000)
                .set(EnvironmentAttributes.FOG_COLOR, 0xFF8B0000)
                .set(EnvironmentAttributes.WATER_FOG_COLOR, 0xFF8B0000)
                .build();

        // 3. Cài đặt sinh thế giới
        var placedFeatureGetter = registries.lookupOrThrow(Registries.PLACED_FEATURE);
        var carverGetter = registries.lookupOrThrow(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings generationSettings = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)
                .build();

        // 4. Khởi tạo Biome
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .specialEffects(effectsBuilder.build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .generationSettings(generationSettings)
                .build();
    }
    
    @Override
    public String getName() {
        return "Igoo Worldgen";
    }
}