package com.igoo.worldgen;

import com.igoo.Igoo;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
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

//        Aluminum
        HeightProvider aluminumHeight = TrapezoidHeight.of(VerticalAnchor.absolute(48), VerticalAnchor.absolute(128));
        List<PlacementModifier> aluminumModifiers = List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(aluminumHeight),
                BiomeFilter.biome()
        );
        context.register(ALUMINUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.ALUMINUM_ORE_KEY), aluminumModifiers));

//        Tin
        HeightProvider tinHeight = TrapezoidHeight.of(VerticalAnchor.absolute(24), VerticalAnchor.absolute(96));
        List<PlacementModifier> tinModifiers = List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(tinHeight),
                BiomeFilter.biome()
        );
        context.register(TIN_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TIN_ORE_KEY), tinModifiers));

//        Chromium
        HeightProvider chromiumHeight = TrapezoidHeight.of(VerticalAnchor.absolute(8), VerticalAnchor.absolute(48));
        List<PlacementModifier> chromiumModifiers = List.of(
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(chromiumHeight),
                BiomeFilter.biome()
        );
        context.register(CHROMIUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.CHROMIUM_ORE_KEY), chromiumModifiers));


//        Titan
        HeightProvider titaniumHeight = TrapezoidHeight.of(VerticalAnchor.absolute(4), VerticalAnchor.absolute(24));
        List<PlacementModifier> titaniumModifiers = List.of(
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(titaniumHeight),
                BiomeFilter.biome()
        );
        context.register(TITANIUM_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TITANIUM_ORE_KEY), titaniumModifiers));

//        Tungsten
        HeightProvider tugnstenHeight = TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128));
        List<PlacementModifier> tungstenModifiers = List.of(
                CountPlacement.of(6),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(tugnstenHeight),
                BiomeFilter.biome()
        );
        context.register(TUNGSTEN_ORE_PLACED_KEY, new PlacedFeature(
                configured.getOrThrow(ModWorldConfiguredFeatures.TUNGSTEN_ORE_KEY), tungstenModifiers));
    }
}