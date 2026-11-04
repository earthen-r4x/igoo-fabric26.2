package com.igoo.client;

import com.igoo.client.renderer.BabyBatRenderer;
import com.igoo.client.renderer.HerobrineRenderer;
import com.igoo.client.renderer.CursedHerobrineRenderer;

import com.igoo.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class IgooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntities.HEROBRINE, HerobrineRenderer::new);
        EntityRenderers.register(ModEntities.CURSED_HEROBRINE, CursedHerobrineRenderer::new);
        EntityRenderers.register(ModEntities.BABY_BAT, BabyBatRenderer::new);
    }
}