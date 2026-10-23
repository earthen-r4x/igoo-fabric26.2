package com.igoo.client.datagen;

import com.igoo.entity.ModEntities;
import com.igoo.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends FabricEntityLootSubProvider {

    public ModEntityLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        // ==========================================
        // 1. LOOT CỦA HEROBRINE (Bình thường)
        // ==========================================
        this.add(ModEntities.HEROBRINE, LootTable.lootTable()
                // 3–6 Thỏi Netherite
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHERITE_INGOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                
                // 4–8 Thỏi Titan
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Titanium_ingot)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))))
                
                // 2–5 Thỏi Hợp Kim Titan
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Titanium_alloy)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))))
                
                // 2–4 Thỏi Cacbua Vonfram
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Tungsten_carbide_ingot)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))))
                
                // 8–16 Kim Cương
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))))
                
                // 8–16 Ngọc Lục Bảo
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))))
        );

        // ==========================================
        // 2. LOOT CỦA CURSED HEROBRINE (Siêu mạnh)
        // ==========================================
        this.add(ModEntities.CURSED_HEROBRINE, LootTable.lootTable()
                // 16–32 Thỏi Hợp Kim Titan
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Titanium_alloy)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))))

                // 8–16 Thỏi Cacbua Vonfram
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.Tungsten_carbide_ingot)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F)))))

                // 16–32 Mảnh Vụn Netherite
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))))

                // 32–64 Kim Cương
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(32.0F, 64.0F)))))

                // 16–32 Ngọc Lục Bảo
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(16.0F, 32.0F)))))

                // 4–8 Táo Vàng Phù Phép
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 8.0F)))))

                // 3–5 Ngôi Sao Nether (100%)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.NETHER_STAR)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F)))))

                // 1–2 Totem of Undying (100%)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))

                // 1 Đầu Rồng (50%)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.50F))
                        .add(LootItem.lootTableItem(Items.DRAGON_HEAD)))

                // 1–3 Táo Vàng Phù Phép bổ sung (50%)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.50F))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))

                // 1–2 vật phẩm trang trí hiếm (25%) - Ở đây dùng Elytra (Cánh cụt) và Trident (Đinh ba)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.25F))
                        .add(LootItem.lootTableItem(Items.ELYTRA))
                        .add(LootItem.lootTableItem(Items.TRIDENT))
                )
        );
    }
}