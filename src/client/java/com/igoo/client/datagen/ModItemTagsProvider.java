package com.igoo.client.datagen;

import java.util.concurrent.CompletableFuture;

import com.igoo.Igoo;
import com.igoo.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        // 1. Gắn công cụ vào tag tương ứng của Vanilla
        builder(ItemTags.SWORDS).add(
            key(ModItems.Aluminum_sword), key(ModItems.Tin_sword), key(ModItems.Chromium_sword), key(ModItems.Titanium_sword), key(ModItems.Tungsten_sword)
        );
        builder(ItemTags.PICKAXES).add(
            key(ModItems.Aluminum_pickaxe), key(ModItems.Tin_pickaxe), key(ModItems.Chromium_pickaxe), key(ModItems.Titanium_pickaxe), key(ModItems.Tungsten_pickaxe)
        );
        builder(ItemTags.AXES).add(
            key(ModItems.Aluminum_axe), key(ModItems.Tin_axe), key(ModItems.Chromium_axe), key(ModItems.Titanium_axe), key(ModItems.Tungsten_axe)
        );
        builder(ItemTags.SHOVELS).add(
            key(ModItems.Aluminum_shovel), key(ModItems.Tin_shovel), key(ModItems.Chromium_shovel), key(ModItems.Titanium_shovel), key(ModItems.Tungsten_shovel)
        );
        builder(ItemTags.HOES).add(
            key(ModItems.Aluminum_hoe), key(ModItems.Tin_hoe), key(ModItems.Chromium_hoe), key(ModItems.Titanium_hoe), key(ModItems.Tungsten_hoe)
        );

        // 2. Đăng ký tag sửa chữa (Repair) cho từng kim loại
        addRepairTag("aluminum_ingot", ModItems.Aluminum_ingot);
        addRepairTag("tin_ingot", ModItems.Tin_ingot);
        addRepairTag("chromium_ingot", ModItems.Chromium_ingot);
        addRepairTag("titanium_ingot", ModItems.Titanium_ingot);
        addRepairTag("tungsten_ingot", ModItems.Tungsten_ingot);
    }

    private void addRepairTag(String tagName, Item item) {
        TagKey<Item> tag = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, tagName));
        builder(tag).add(key(item));
    }

    /** Helper: Chuyển đổi Item thành ResourceKey<Item> cho API 26.2 */
    private static ResourceKey<Item> key(Item item) {
        return item.builtInRegistryHolder().key();
    }
}