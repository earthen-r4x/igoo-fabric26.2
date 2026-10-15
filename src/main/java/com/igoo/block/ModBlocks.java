/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igoo.block;

import java.util.List;
import java.util.function.Function;

import com.igoo.Igoo;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 *
 * @author tranx
 */
public class ModBlocks {
    
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
    
    public static final List<Block> ALL_BLOCKS = List.of(
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
