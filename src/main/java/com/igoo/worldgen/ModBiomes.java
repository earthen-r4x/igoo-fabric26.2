package com.igoo.worldgen;

import com.igoo.Igoo;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class ModBiomes {
    public static final ResourceKey<Biome> CURSED_SAVANNA = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "cursed_savanna")
    );
}