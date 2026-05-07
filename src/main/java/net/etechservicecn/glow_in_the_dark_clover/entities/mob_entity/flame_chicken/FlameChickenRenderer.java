package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken;

import com.mojang.blaze3d.vertex.PoseStack;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.EntityLayerList;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlameChickenRenderer extends MobRenderer<FlameChickenEntity,FlameChickenModel<FlameChickenEntity>> {
    public FlameChickenRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new FlameChickenModel<>(p_174304_.bakeLayer(EntityLayerList.FLAME_CHICKEN_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FlameChickenEntity flameChickenEntity) {
        return new ResourceLocation(StartModApplication.MODID,"textures/entity/flame_chicken.png");
    }

    @Override
    public void render(FlameChickenEntity flameChickenEntity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if (flameChickenEntity.isBaby()){
            poseStack.scale(0.5f,0.5f,0.5f);
        }
        super.render(flameChickenEntity, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
