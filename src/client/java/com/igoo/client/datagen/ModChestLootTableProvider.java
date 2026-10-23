package com.igoo.client.datagen;

import com.igoo.block.ModBlocks;
import com.igoo.item.ModItems;
import com.igoo.worldgen.ModLootTables;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables; // 👈 Import cho End City
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable; // 👈 Import cho Nested Loot
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableSubProvider {

    public ModChestLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        
        // ==========================================
        // 1. RƯƠNG TRASH HOME (Cũ)
        // ==========================================
        consumer.accept(ModLootTables.TRASH_HOME_CHEST, LootTable.lootTable()
                // 10% ra 1-5 iron_ingot
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.80F))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                )
                // 20% ra 1-3 ingot mod
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Aluminum_ingot).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(ModItems.Tin_ingot).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(ModItems.Titanium_ingot).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        .add(LootItem.lootTableItem(ModItems.Tungsten_ingot).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))
                )
                // 100% ra 1-5 bánh mì pháp
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Baguette)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                )
                // 1% ra 1 titanium_alloy_block
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.01F))
                        .add(LootItem.lootTableItem(ModBlocks.Titanium_alloy_block))
                )
        );


        // ==========================================
        // 2. RƯƠNG HEROBRINE ALTAR (Mới)
        // ==========================================
        consumer.accept(ModLootTables.HEROBRINE_ALTAR_CHEST, LootTable.lootTable()
                // 100% ra 1 khối vàng
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK))
                )
                // 50% ra 8-20 xương
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.50F))
                        .add(LootItem.lootTableItem(Items.BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 20.0F))))
                )
                // 50% ra loot của End City
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))
                        .add(NestedLootTable.lootTableReference(BuiltInLootTables.END_CITY_TREASURE))
                )
        );
    }
}