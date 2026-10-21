package com.igoo.client.renderer;

import com.igoo.entity.HerobrineBoss;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState; // Import RenderState
import net.minecraft.resources.Identifier;

public class HerobrineRenderer extends HumanoidMobRenderer<HerobrineBoss, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("igoo", "textures/entity/herobrine.png");

    public HerobrineRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }

    // 👇 THÊM PHƯƠNG THỨC NÀY ĐỂ SỬA LỖI
    @Override
    public HumanoidRenderState createRenderState() {
        return new HumanoidRenderState();
    }

    @Override
    public Identifier getTextureLocation(HumanoidRenderState renderState) {
        return TEXTURE;
    }
}