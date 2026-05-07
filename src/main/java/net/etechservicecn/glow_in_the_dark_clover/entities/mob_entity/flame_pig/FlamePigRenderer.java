package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_pig;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.kinds.IdF;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.EntityLayerList;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlamePigRenderer extends MobRenderer<FlamePigEntity,FlamePigModel<FlamePigEntity>> {
    public FlamePigRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new FlamePigModel<>(p_174304_.bakeLayer(EntityLayerList.FLAME_PIG_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamePigEntity p_114482_) {
        return new ResourceLocation(StartModApplication.MODID,"textures/entity/flame_pig.png");
    }

    @Override
    public void render(FlamePigEntity flamePigEntity, float p_115456_, float p_115457_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_115460_) {
        if (flamePigEntity.isBaby()){
            poseStack.scale(0.5f,0.5f,0.5f);
        }
        super.render(flamePigEntity, p_115456_, p_115457_, poseStack, multiBufferSource, p_115460_);
    }
}
