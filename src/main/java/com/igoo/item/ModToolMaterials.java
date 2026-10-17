package com.igoo.item;

import com.igoo.Igoo;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {

    public static final ToolMaterial ALUMINUM = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 150, 2.0f, 0.0f, 15,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "aluminum_ingot"))
    );

    public static final ToolMaterial TIN = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 180, 4.0f, 1.0f, 5,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "tin_ingot"))
    );

    public static final ToolMaterial CHROMIUM = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 220, 4.5f, 1.5f, 8,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "chromium_ingot"))
    );

    public static final ToolMaterial TITANIUM = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1600, 8.0f, 3.0f, 12,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "titanium_ingot"))
    );

    public static final ToolMaterial TUNGSTEN = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2500, 10.0f, 4.5f, 15,
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "tungsten_ingot"))
    );
}