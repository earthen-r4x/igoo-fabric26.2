package com.igoo.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class CursedHerobrineBoss extends HerobrineBoss {

    public CursedHerobrineBoss(EntityType<? extends HerobrineBoss> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 10000; // Kinh nghiệm rơi ra gấp 10 lần
    }

    // Ghi đè lại chỉ số, buff gấp 10 lần
    public static AttributeSupplier.Builder createCursedAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 4000.0D)       // 400 x 10
                .add(Attributes.ATTACK_DAMAGE, 180.0D)     // 18 x 10 (Đấm phát chết luôn)
                .add(Attributes.ARMOR, 100.0D)             // 10 x 10 (Gần như miễn nhiễm sát thương)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)      // Nhanh hơn bản gốc
                .add(Attributes.FOLLOW_RANGE, 100.0D)      // Phạm vi truy đuổi cực xa
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }
}