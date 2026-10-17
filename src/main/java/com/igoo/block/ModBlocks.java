/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igoo.block;

import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;
import com.igoo.item.ModItems;
import java.util.Map;
import java.util.stream.Stream;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 *
 * @author tranx
 */
public class ModBlocks {
    // Blocks
    public static final Block Aluminum_block = registerBlock("aluminum_block",
        properties -> new Block(properties.strength(3.0f, 6.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Bronze_block = registerBlock("bronze_block",
        properties -> new Block(properties.strength(4.0f, 8.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Carbon_steel_block = registerBlock("carbon_steel_block",
        properties -> new Block(properties.strength(5.0f, 12.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Chromium_block = registerBlock("chromium_block",
        properties -> new Block(properties.strength(5.0f, 10.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Stainless_steel_block = registerBlock("stainless_steel_block",
        properties -> new Block(properties.strength(6.0f, 15.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Tin_block = registerBlock("tin_block",
        properties -> new Block(properties.strength(2.5f, 5.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Titanium_alloy_block = registerBlock("titanium_alloy_block",
        properties -> new Block(properties.strength(8.0f, 20.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Titanium_block = registerBlock("titanium_block",
        properties -> new Block(properties.strength(7.0f, 18.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Tungsten_block = registerBlock("tungsten_block",
        properties -> new Block(properties.strength(8.0f, 22.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Tungsten_carbide_block = registerBlock("tungsten_carbide_block",
        properties -> new Block(properties.strength(10.0f, 30.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );

    // Ores
    public static final Block Aluminum_ore = registerBlock("aluminum_ore",
        properties -> new DropExperienceBlock(UniformInt.of(2, 5),
        properties.strength(2.8f, 5.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Chromium_ore = registerBlock("chromium_ore",
        properties -> new DropExperienceBlock(UniformInt.of(2, 5),
        properties.strength(4.0f, 8.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Tin_ore = registerBlock("tin_ore",
        properties -> new DropExperienceBlock(UniformInt.of(2, 5),
        properties.strength(2.25f, 4.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Titanium_ore = registerBlock("titanium_ore",
        properties -> new DropExperienceBlock(UniformInt.of(2, 5),
        properties.strength(6.5f, 15.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    public static final Block Tungsten_ore = registerBlock("tungsten_ore",
        properties -> new DropExperienceBlock(UniformInt.of(2, 5),
        properties.strength(7f, 20.0f)
        .requiresCorrectToolForDrops().sound(SoundType.STONE))
    );
    
    public static final List<Block> ALL_METALS = List.of(
        Aluminum_block,
        Bronze_block,
        Carbon_steel_block,
        Chromium_block,
        Stainless_steel_block,
        Tin_block,
        Titanium_alloy_block,
        Titanium_block,
        Tungsten_block,
        Tungsten_carbide_block
    );
    
    public static final List<Block> ALL_ORES = List.of(
        Aluminum_ore,
        Chromium_ore,
        Tin_ore,
        Titanium_ore,
        Tungsten_ore
    );
    
    // Yêu cầu cuốc chim đá trở lên
    public static final List<Block> MINE_WITH_STONE = List.of(
        Tin_ore,
        Chromium_ore,
        Aluminum_block,
        Bronze_block,
        Carbon_steel_block,
        Tin_block,
        Chromium_block
    );

    // Yêu cầu cuốcchim sắt trở lên
    public static final List<Block> MINE_WITH_IRON = List.of(
        Stainless_steel_block
    );

    // Yêu cầu cuốc chim kim cương trở lên
    public static final List<Block> MINE_WITH_DIAMOND = List.of(
        Titanium_ore,
        Tungsten_ore,
        Titanium_alloy_block,
        Titanium_block,
        Tungsten_block,
        Tungsten_carbide_block
    );
    
    public record OreDrop(Item raw, int min, int max) {}

    public static final Map<Block, OreDrop> ORE_DROPS = Map.of(
        Aluminum_ore, new OreDrop(ModItems.Raw_aluminum, 2, 5),
        Tin_ore,      new OreDrop(ModItems.Raw_tin,      2, 4),
        Chromium_ore, new OreDrop(ModItems.Raw_chromium, 1, 2),
        Titanium_ore, new OreDrop(ModItems.Raw_titanium, 1, 2),
        Tungsten_ore, new OreDrop(ModItems.Raw_tungsten, 1, 1)
    );
    
    public static final List<Block> ALL_BLOCKS = Stream.concat(ALL_METALS.stream(), ALL_ORES.stream()).toList();
    
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name), toRegister);
    }
    
    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, name)))));
    }
    
    public static void registerModBlocks() {
        Igoo.LOGGER.info("Registering Mod Blocks for " + Igoo.MOD_ID);
    }
}
