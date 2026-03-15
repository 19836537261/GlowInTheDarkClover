package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class TeleportBlockRender implements BlockEntityRenderer<TeleportBlockEntity> {
    public static final ResourceLocation SIDE_TEXTURES= new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_block/fire_world_teleport_block_side.png");
    @Override
    public void render(TeleportBlockEntity teleportBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        float[][] vertices = {
                {0.0f,1.0f,1.0f,0.0f,1.0f,0.0f},
                {1.0f,1.0f,1.0f,0.0f,1.0f,0.0f},
                {1.0f,1.0f,0.0f,0.0f,1.0f,0.0f},
                {0.0f,1.0f,0.0f,0.0f,1.0f,0.0f},
                {0.0f,0.0f,0.0f,0.0f,-1.0f,0.0f},
                {1.0f,0.0f,0.0f,0.0f,-1.0f,0.0f},
                {1.0f,0.0f,1.0f,0.0f,-1.0f,0.0f},
                {0.0f,0.0f,1.0f,0.0f,-1.0f,0.0f},
                {1.0f,0.0f,1.0f,1.0f,0.0f,0.0f},
                {1.0f,0.0f,0.0f,1.0f,0.0f,0.0f},
                {1.0f,1.0f,0.0f,1.0f,0.0f,0.0f},
                {1.0f,1.0f,1.0f,1.0f,0.0f,0.0f},
                {0.0f,0.0f,0.0f,-1.0f,0.0f,0.0f},
                {0.0f,0.0f,1.0f,-1.0f,0.0f,0.0f},
                {0.0f,1.0f,1.0f,-1.0f,0.0f,0.0f},
                {0.0f,1.0f,0.0f,-1.0f,0.0f,0.0f},
                {1.0f,0.0f,0.0f,0.0f,0.0f,-1.0f},
                {0.0f,0.0f,0.0f,0.0f,0.0f,-1.0f},
                {0.0f,1.0f,0.0f,0.0f,0.0f,-1.0f},
                {1.0f,1.0f,0.0f,0.0f,0.0f,-1.0f},
                {0.0f,0.0f,1.0f,0.0f,0.0f,1.0f},
                {1.0f,0.0f,1.0f,0.0f,0.0f,1.0f},
                {1.0f,1.0f,1.0f,0.0f,0.0f,1.0f},
                {0.0f,1.0f,1.0f,0.0f,0.0f,1.0f}
        };//需要配置uv纹理相对顶点的位置
        VertexConsumer sideVertexConsumer= multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES));
        int rotate_index= teleportBlockEntity.getRotate_index();
        String world_type= teleportBlockEntity.getWorld_type();
        if (world_type.contains("fire_world")){
            System.out.println("fire_world");
        }
        poseStack.popPose();
    }
}
