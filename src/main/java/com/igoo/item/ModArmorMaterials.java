package com.igoo.item;

import java.util.EnumMap;
import java.util.Map;

import com.igoo.Igoo;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ModArmorMaterials {

    /** Trỏ tới assets/igoo/equipment/azurite.json (model giáp khi mặc). */
    public static final ResourceKey<EquipmentAsset> AZURITE_ASSET =
            ResourceKey.create(EquipmentAssets.ROOT_ID, Igoo.id("azurite"));

    /** Giáp Azurite: mạnh hơn kim cương, gần bằng netherite. */
    public static final ArmorMaterial AZURITE = new ArmorMaterial(
            38,                                   // độ bền (nhân với hệ số từng món)
            defense(3, 6, 8, 3, 11),              // boots, leggings, chestplate, helmet, body (ngựa)
            18,                                   // enchantability
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.5f,                                 // toughness
            0.05f,                                // knockback resistance
            TagKey.create(Registries.ITEM, Igoo.id("azurite_ingot")),
            AZURITE_ASSET
    );

    private static Map<ArmorType, Integer> defense(int boots, int leggings, int chestplate, int helmet, int body) {
        Map<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        map.put(ArmorType.BODY, body);
        return map;
    }
}