package com.igoo.worldgen;

import com.igoo.Igoo;
import com.igoo.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModWorldConfiguredFeatures {

    // Khai báo Key cho từng quặng
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, Igoo.id("aluminum_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, Igoo.id("tin_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHROMIUM_ORE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, Igoo.id("chromium_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> TITANIUM_ORE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, Igoo.id("titanium_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, Igoo.id("tungsten_ore"));

    public static void configure(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // Quy tắc: Quặng có thể thay thế Đá (Stone) và Đá sâu (Deepslate)
        var stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

        // --- Cấu hình cho Nhôm (Mạch 9 khối) ---
        List<OreConfiguration.TargetBlockState> aluminumTargets = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.Aluminum_ore.defaultBlockState())
        );
        context.register(ALUMINUM_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(aluminumTargets, 9)));

        // --- Cấu hình cho Thiếc (Mạch 8 khối) ---
        List<OreConfiguration.TargetBlockState> tinTargets = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.Tin_ore.defaultBlockState())
        );
        context.register(TIN_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(tinTargets, 8)));

        // --- Cấu hình cho Crom (Mạch 6 khối) ---
        List<OreConfiguration.TargetBlockState> chromiumTargets = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.Chromium_ore.defaultBlockState())
        );
        context.register(CHROMIUM_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(chromiumTargets, 6)));

        // --- Cấu hình cho Titan (Mạch 5 khối) ---
        List<OreConfiguration.TargetBlockState> titaniumTargets = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.Titanium_ore.defaultBlockState())
        );
        context.register(TITANIUM_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(titaniumTargets, 5)));

        // --- Cấu hình cho Vonfram (Mạch 4 khối) ---
        List<OreConfiguration.TargetBlockState> tungstenTargets = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.Tungsten_ore.defaultBlockState())
        );
        context.register(TUNGSTEN_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(tungstenTargets, 4)));
    }
}