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
                .add(Attributes.MAX_HEALTH, 2500.0D)
                .add(Attributes.ATTACK_DAMAGE, 70.0D)
                .add(Attributes.ARMOR, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 200.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }
}