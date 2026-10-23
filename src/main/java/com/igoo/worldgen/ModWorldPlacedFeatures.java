package com.igoo.worldgen;

import com.igoo.Igoo;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import net.minecraft.data.worldgen.placement.PlacementUtils;

public class ModWorldPlacedFeatures {

    // ===== 1. Key quặng của Mod =====
    public static final ResourceKey<PlacedFeature> ALUMINUM_ORE_PLACED_KEY = key("aluminum_ore_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = key("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> CHROMIUM_ORE_PLACED_KEY = key("chromium_ore_placed");
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY = key("titanium_ore_placed");
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = key("tungsten_ore_placed");

    // ===== 2. Key quặng Vanilla =====
    public static final ResourceKey<PlacedFeature> CURSED_ORE_COAL_UPPER = key("cursed_ore_coal_upper");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_COAL_LOWER = key("cursed_ore_coal_lower");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_IRON_UPPER = key("cursed_ore_iron_upper");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_IRON_MIDDLE = key("cursed_ore_iron_middle");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_IRON_SMALL = key("cursed_ore_iron_small");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_COPPER = key("cursed_ore_copper");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_GOLD = key("cursed_ore_gold");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_REDSTONE = key("cursed_ore_redstone");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_LAPIS = key("cursed_ore_lapis");
    
    // Biến thể Kim Cương
    public static final ResourceKey<PlacedFeature> CURSED_ORE_DIAMOND_SMALL = key("cursed_ore_diamond_small");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_DIAMOND_MEDIUM = key("cursed_ore_diamond_medium");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_DIAMOND_LARGE = key("cursed_ore_diamond_large");
    public static final ResourceKey<PlacedFeature> CURSED_ORE_DIAMOND_BURIED = key("cursed_ore_diamond_buried");

    // ===== 3. Key thảm thực vật =====
    public static final ResourceKey<PlacedFeature> CURSED_TREES_SAVANNA = key("cursed_trees_savanna");
    public static final ResourceKey<PlacedFeature> CURSED_PATCH_TALL_GRASS = key("cursed_patch_tall_grass");
    public static final ResourceKey<PlacedFeature> CURSED_PATCH_DEAD_BUSH = key("cursed_patch_dead_bush");

    private static ResourceKey<PlacedFeature> key(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name));
    }

    public static void configure(BootstrapContext<PlacedFeature> context) {
        var cf = context.lookup(Registries.CONFIGURED_FEATURE);

        // ==========================================
        // A. QUẶNG CỦA MOD
        // ==========================================
        registerOre(context, cf, ALUMINUM_ORE_PLACED_KEY, ModWorldConfiguredFeatures.ALUMINUM_ORE_KEY, 12, 48, 128);
        registerOre(context, cf, TIN_ORE_PLACED_KEY, ModWorldConfiguredFeatures.TIN_ORE_KEY, 10, 24, 96);
        registerOre(context, cf, CHROMIUM_ORE_PLACED_KEY, ModWorldConfiguredFeatures.CHROMIUM_ORE_KEY, 6, 8, 48);
        registerOre(context, cf, TITANIUM_ORE_PLACED_KEY, ModWorldConfiguredFeatures.TITANIUM_ORE_KEY, 6, 4, 24);
        registerOre(context, cf, TUNGSTEN_ORE_PLACED_KEY, ModWorldConfiguredFeatures.TUNGSTEN_ORE_KEY, 6, 0, 128);

        // ==========================================
        // B. QUẶNG VANILLA (Clone)
        // ==========================================
        
        // Than (Coal)
        context.register(CURSED_ORE_COAL_UPPER, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_COAL), 
            List.of(CountPlacement.of(30), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(136), VerticalAnchor.top()), BiomeFilter.biome())));
        context.register(CURSED_ORE_COAL_LOWER, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_COAL_BURIED), 
            List.of(CountPlacement.of(20), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192)), BiomeFilter.biome())));

        // Sắt (Iron)
        context.register(CURSED_ORE_IRON_UPPER, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_IRON), 
            List.of(CountPlacement.of(90), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)), BiomeFilter.biome())));
        context.register(CURSED_ORE_IRON_MIDDLE, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_IRON), 
            List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)), BiomeFilter.biome())));
        context.register(CURSED_ORE_IRON_SMALL, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_IRON_SMALL), 
            List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)), BiomeFilter.biome())));

        // Đồng, Vàng, Đá đỏ, Lapis
        context.register(CURSED_ORE_COPPER, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_COPPER_LARGE), 
            List.of(CountPlacement.of(16), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)), BiomeFilter.biome())));
        context.register(CURSED_ORE_GOLD, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_GOLD), 
            List.of(CountPlacement.of(4), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)), BiomeFilter.biome())));
        context.register(CURSED_ORE_REDSTONE, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_REDSTONE), 
            List.of(CountPlacement.of(8), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)), BiomeFilter.biome())));
        context.register(CURSED_ORE_LAPIS, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_LAPIS), 
            List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)), BiomeFilter.biome())));

        // Kim cương (Diamond) - Đầy đủ 4 biến thể
        context.register(CURSED_ORE_DIAMOND_SMALL, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_DIAMOND_SMALL), 
            List.of(CountPlacement.of(7), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)), BiomeFilter.biome())));
        context.register(CURSED_ORE_DIAMOND_MEDIUM, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_DIAMOND_MEDIUM), 
            List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4)), BiomeFilter.biome())));
        context.register(CURSED_ORE_DIAMOND_LARGE, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_DIAMOND_LARGE), 
            List.of(RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)), BiomeFilter.biome())));
        context.register(CURSED_ORE_DIAMOND_BURIED, new PlacedFeature(cf.getOrThrow(OreFeatures.ORE_DIAMOND_BURIED), 
            List.of(CountPlacement.of(4), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)), BiomeFilter.biome())));

        // ==========================================
        // C. THẢM THỰC VẬT (Đã sửa lỗi không mọc)
        // ==========================================
        
        // Cây Savanna (Cần HEIGHTMAP và BlockPredicate để mọc trên đất/cỏ)
        context.register(CURSED_TREES_SAVANNA, new PlacedFeature(
                cf.getOrThrow(VegetationFeatures.TREES_SAVANNA),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                )
        ));

        // Cỏ dại (Cần HEIGHTMAP)
        context.register(CURSED_PATCH_TALL_GRASS, new PlacedFeature(
                cf.getOrThrow(VegetationFeatures.TALL_GRASS),
                List.of(
                        CountPlacement.of(10), 
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP, // 👈 Tìm bề mặt
                        BiomeFilter.biome()
                )
        ));

        // Bụi cây chết (Cần HEIGHTMAP)
        context.register(CURSED_PATCH_DEAD_BUSH, new PlacedFeature(
                cf.getOrThrow(VegetationFeatures.DEAD_BUSH),
                List.of(
                        CountPlacement.of(4), 
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                )
        ));
    }

    private static void registerOre(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> cf,
                                    ResourceKey<PlacedFeature> placedKey, ResourceKey<ConfiguredFeature<?, ?>> configuredKey,
                                    int count, int minY, int maxY) {
        var height = TrapezoidHeight.of(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY));
        var modifiers = List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(height),
                BiomeFilter.biome()
        );
        context.register(placedKey, new PlacedFeature(cf.getOrThrow(configuredKey), modifiers));
    }
}