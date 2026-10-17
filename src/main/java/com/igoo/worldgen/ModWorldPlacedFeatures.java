package com.igoo.worldgen;

import com.igoo.Igoo;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModWorldPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ALUMINUM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Igoo.id("aluminum_ore_placed"));
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Igoo.id("tin_ore_placed"));
    public static final ResourceKey<PlacedFeature> CHROMIUM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Igoo.id("chromium_ore_placed"));
    public static final ResourceKey<PlacedFeature> TITANIUM_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Igoo.id("titanium_ore_placed"));
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE, Igoo.id("tungsten_ore_placed"));

    public static void configure(BootstrapContext<PlacedFeature> context) {
        HolderGetter<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

        // --- NHÔM ---
        // Sinh 12 mạch mỗi chunk, độ cao từ 0 đến 128 (phân bố đều)
        List<PlacementModifier> aluminumModifiers = List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );
        context.register(ALUMINUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.ALUMINUM_ORE_KEY), aluminumModifiers));

        // --- THIẾC ---
        // Sinh 10 mạch mỗi chunk, độ cao từ 0 đến 96
        List<PlacementModifier> tinModifiers = List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.bottom(), VerticalAnchor.absolute(96))),
                BiomeFilter.biome()
        );
        context.register(TIN_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TIN_ORE_KEY), tinModifiers));

        // --- CROM ---
        // Sinh 6 mạch mỗi chunk, độ cao từ -16 đến 64, tập trung nhiều ở đáy
        HeightProvider chromiumHeight = TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(64));
        List<PlacementModifier> chromiumModifiers = List.of(
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(chromiumHeight),
                BiomeFilter.biome()
        );
        context.register(CHROMIUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.CHROMIUM_ORE_KEY), chromiumModifiers));

        // --- TITAN ---
        // Sinh 4 mạch mỗi chunk, độ cao từ -48 đến 32
        HeightProvider titaniumHeight = TrapezoidHeight.of(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(32));
        List<PlacementModifier> titaniumModifiers = List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(titaniumHeight),
                BiomeFilter.biome()
        );
        context.register(TITANIUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TITANIUM_ORE_KEY), titaniumModifiers));

        // --- VONFRAM ---
        // Sinh 2 mạch mỗi chunk, độ cao từ -64 đến -16 (sâu nhất, như cổ đại)
        HeightProvider tungstenHeight = TrapezoidHeight.of(VerticalAnchor.bottom(), VerticalAnchor.absolute(-16));
        List<PlacementModifier> tungstenModifiers = List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(tungstenHeight),
                BiomeFilter.biome()
        );
        context.register(TUNGSTEN_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TUNGSTEN_ORE_KEY), tungstenModifiers));
    }
}