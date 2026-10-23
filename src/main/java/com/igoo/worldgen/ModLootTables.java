package com.igoo.worldgen;

import com.igoo.Igoo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {
    public static final ResourceKey<LootTable> TRASH_HOME_CHEST = ResourceKey.create(
            Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "chests/trash_home")
    );

    // 👇 Thêm key cho rương Herobrine Altar
    public static final ResourceKey<LootTable> HEROBRINE_ALTAR_CHEST = ResourceKey.create(
            Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "chests/herobrine_altar")
    );
}