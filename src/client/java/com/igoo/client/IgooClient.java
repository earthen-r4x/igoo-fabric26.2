package com.igoo.client;

import com.igoo.client.renderer.HerobrineRenderer;
import com.igoo.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class IgooClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
            EntityRenderers.register(ModEntities.HEROBRINE, HerobrineRenderer::new);
	}
}