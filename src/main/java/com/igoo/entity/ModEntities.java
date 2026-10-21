package com.igoo.entity;

import com.igoo.Igoo;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> HEROBRINE_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "herobrine")
    );

    public static final EntityType<HerobrineBoss> HEROBRINE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            HEROBRINE_KEY,
            EntityType.Builder.<HerobrineBoss>of(HerobrineBoss::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.8F) // Kích thước giống người chơi
                    .clientTrackingRange(10)
                    .build(HEROBRINE_KEY)
    );
    
    // Thêm Key và Entity cho Cursed Herobrine
    public static final ResourceKey<EntityType<?>> CURSED_HEROBRINE_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "cursed_herobrine")
    );

    public static final EntityType<CursedHerobrineBoss> CURSED_HEROBRINE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            CURSED_HEROBRINE_KEY,
            EntityType.Builder.<CursedHerobrineBoss>of(CursedHerobrineBoss::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.8F)
                    .clientTrackingRange(10)
                    .build(CURSED_HEROBRINE_KEY)
    );

    public static void registerModEntities() {
        Igoo.LOGGER.info("Registering Entities for " + Igoo.MOD_ID);
        // Đăng ký thuộc tính cho entity
        FabricDefaultAttributeRegistry.register(HEROBRINE, HerobrineBoss.createHerobrineAttributes());
        FabricDefaultAttributeRegistry.register(CURSED_HEROBRINE, CursedHerobrineBoss.createCursedAttributes());
    }
}