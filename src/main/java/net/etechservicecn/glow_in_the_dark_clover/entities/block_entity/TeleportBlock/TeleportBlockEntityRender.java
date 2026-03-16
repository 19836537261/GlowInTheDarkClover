package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class TeleportBlockEntityRender implements BlockEntityRenderer<TeleportBlockEntity> {
    public static final ResourceLocation SIDE_TEXTURES= new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_block/fire_world_teleport_block_side.png");
    @Override
    public void render(TeleportBlockEntity teleportBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {

        float[][]uvs={
                {0.0f,0.75f,1.0f,0.75f,1.0f,1.0f,0.0f,1.0f},
                {0.0f,0.5f,1.0f,0.5f,1.0f,0.75f,0.0f,0.75f},
                {0.0f,0.25f,1.0f,0.25f,1.0f,0.5f,0.0f,0.5f},
                {0.0f,0.0f,1.0f,0.0f,1.0f,0.25f,0.0f,0.25f}
        };
        int index=teleportBlockEntity.getRotate_index();
        poseStack.pushPose();
        for (int i = 0; i < uvs[index].length; i++) {
            VertexConsumer vertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES));
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,1.0f,0.0f).endVertex();

            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,-1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,-1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,-1.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,-1.0f,0.0f).endVertex();

            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),1.0f,0.0f,0.0f).endVertex();

            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),-1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),-1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),-1.0f,0.0f,0.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),-1.0f,0.0f,0.0f).endVertex();

            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,-1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,-1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,-1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,0.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,-1.0f).endVertex();

            vertexConsumer.vertex(poseStack.last().pose(),1.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][0],uvs[index][1]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,1.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][2],uvs[index][3]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),0.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][4],uvs[index][5]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,1.0f).endVertex();
            vertexConsumer.vertex(poseStack.last().pose(),1.0f,0.0f,1.0f).color(1.0f,1.0f,1.0f,1.0f)
                    .uv(uvs[index][6],uvs[index][7]).overlayCoords(packedOverlay).uv2(packedLight).normal(poseStack.last().normal(),0.0f,0.0f,1.0f).endVertex();
        }


        poseStack.popPose();
    }
}
