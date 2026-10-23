package com.igoo.client.datagen;

import com.igoo.worldgen.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends FabricTagsProvider<Biome> {
    
    public static final TagKey<Biome> HAS_HEROBRINE_ALTAR = TagKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath("igoo", "has_structure/herobrine_altar")
    );
    public static final TagKey<Biome> HAS_TRASH_HOME = TagKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath("igoo", "has_structure/trash_home")
    );
    
    public ModBiomeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(BiomeTags.IS_OVERWORLD).add(ModBiomes.CURSED_SAVANNA);
        builder(BiomeTags.IS_SAVANNA).add(ModBiomes.CURSED_SAVANNA);
        builder(HAS_HEROBRINE_ALTAR).add(ModBiomes.CURSED_SAVANNA);
        builder(HAS_TRASH_HOME).add(ModBiomes.CURSED_SAVANNA);
    }
}