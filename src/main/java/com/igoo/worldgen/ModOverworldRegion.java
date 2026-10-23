package com.igoo.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Chèn biome của bạn với khí hậu y hệt Savanna của Vanilla
        this.addBiomeSimilar(mapper, net.minecraft.world.level.biome.Biomes.SAVANNA, ModBiomes.CURSED_SAVANNA);
    }

    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(Identifier.fromNamespaceAndPath("igoo", "overworld"), 10));
    }
}