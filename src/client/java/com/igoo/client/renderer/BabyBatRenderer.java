package com.igoo.client.renderer;

import com.igoo.entity.BabyBat;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;

public class BabyBatRenderer
        extends HumanoidMobRenderer<BabyBat, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("igoo", "textures/entity/baby_vampire_bat.png");

    public BabyBatRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.25F);

        // 👈 Dùng PLAYER_ARMOR chuẩn vanilla (4 layer riêng: helmet/chestplate/leggings/boots)
        ArmorModelSet<HumanoidModel<HumanoidRenderState>> armorModels = ArmorModelSet.bake(
                ModelLayers.PLAYER_ARMOR,
                context.getModelSet(),
                HumanoidModel::new
        );

        this.addLayer(new HumanoidArmorLayer<>(
                this,
                armorModels,
                context.getEquipmentRenderer()
        ));
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