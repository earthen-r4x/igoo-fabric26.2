/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igoo.client.renderer;

import com.igoo.entity.CursedHerobrineBoss;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;

/**
 *
 * @author tranx
 */
public class CursedHerobrineRenderer extends HumanoidMobRenderer<CursedHerobrineBoss, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("igoo", "textures/entity/cursed_herobrine.png");

    public CursedHerobrineRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }
    
    @Override
    public HumanoidRenderState createRenderState() {
        return new HumanoidRenderState();
    }
    
    @Override
    public Identifier getTextureLocation(HumanoidRenderState renderState) {
        return TEXTURE;
    }
    
    
}
