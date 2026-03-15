package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class TeleportSchedulerBlockEntityRenderer implements BlockEntityRenderer<TeleportSchedulerBlockEntity> {
    public TeleportSchedulerBlockEntityRenderer(){

    }
    public static final ResourceLocation TOP_TEXTURE=new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_top.png");
    public static final ResourceLocation BOTTOM_TEXTURE=new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_bottom.png");
    public static final ResourceLocation[]SIDE_TEXTURES=new ResourceLocation[]{
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side0.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side1.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side2.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side3.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side4.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side5.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side6.png"),
            new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block/teleport_scheduler_block_side7.png")
    };
    @Override
    public void render(TeleportSchedulerBlockEntity teleportSchedulerBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        int index = teleportSchedulerBlockEntity.getCircle_index();
        int index1= teleportSchedulerBlockEntity.getCircle_index1();
        int index2= teleportSchedulerBlockEntity.getCircle_index2();
        int index3= teleportSchedulerBlockEntity.getCircle_index3();
        poseStack.pushPose();
        float[][] top_vertices = {
                {0.0f,1.0f,1.0f,0.0f,0.0f,0.0f,1.0f,0.0f},
                {1.0f,1.0f,1.0f,1.0f,0.0f,0.0f,1.0f,0.0f},
                {1.0f,1.0f,0.0f,1.0f,1.0f,0.0f,1.0f,0.0f},
                {0.0f,1.0f,0.0f,0.0f,1.0f,0.0f,1.0f,0.0f}
        };
        float[][] bottom_vertices={
                {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,-1.0f,0.0f},
                {1.0f,0.0f,0.0f,1.0f,0.0f,0.0f,-1.0f,0.0f},
                {1.0f,0.0f,1.0f,1.0f,1.0f,0.0f,-1.0f,0.0f},
                {0.0f,0.0f,1.0f,0.0f,1.0f,0.0f,-1.0f,0.0f}
        };
        float[][] east_vertices={
                {1.0f,0.0f,1.0f,1.0f,1.0f,1.0f,0.0f,0.0f},
                {1.0f,0.0f,0.0f,0.0f,1.0f,1.0f,0.0f,0.0f},
                {1.0f,1.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f},
                {1.0f,1.0f,1.0f,1.0f,0.0f,1.0f,0.0f,0.0f}
        };
        float[][] west_vertices={
                {0.0f,0.0f,0.0f,1.0f,1.0f,-1.0f,0.0f,0.0f},
                {0.0f,0.0f,1.0f,0.0f,1.0f,-1.0f,0.0f,0.0f},
                {0.0f,1.0f,1.0f,0.0f,0.0f,-1.0f,0.0f,0.0f},
                {0.0f,1.0f,0.0f,1.0f,0.0f,-1.0f,0.0f,0.0f}
        };
        float[][] north_vertices={
                {1.0f,0.0f,0.0f,1.0f,1.0f,0.0f,0.0f,-1.0f},
                {0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,-1.0f},
                {0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,0.0f,-1.0f},
                {1.0f,1.0f,0.0f,1.0f,0.0f,0.0f,0.0f,-1.0f}
        };
        float[][] south_vertices={
                {0.0f,0.0f,1.0f,1.0f,1.0f,0.0f,0.0f,1.0f},
                {1.0f,0.0f,1.0f,0.0f,1.0f,0.0f,0.0f,1.0f},
                {1.0f,1.0f,1.0f,0.0f,0.0f,0.0f,0.0f,1.0f},
                {0.0f,1.0f,1.0f,1.0f,0.0f,0.0f,0.0f,1.0f}
        };
        VertexConsumer topVertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(TOP_TEXTURE));
        for (int i = 0; i < top_vertices.length; i++) {
            render_texture(poseStack,topVertexConsumer,top_vertices[i],packedLight,packedOverlay );
        }
        VertexConsumer bottomVertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(BOTTOM_TEXTURE));
        for (int i = 0; i < bottom_vertices.length; i++) {
            render_texture(poseStack,bottomVertexConsumer,bottom_vertices[i],packedLight,packedOverlay );
        }
        VertexConsumer eastVertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES[index]));
        for (int i = 0; i < east_vertices.length; i++) {
            render_texture(poseStack,eastVertexConsumer,east_vertices[i],packedLight,packedOverlay );
        }
        VertexConsumer westVertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES[index1]));
        for (int i = 0; i < west_vertices.length; i++) {
            render_texture(poseStack,westVertexConsumer,west_vertices[i],packedLight,packedOverlay );
        }
        VertexConsumer northVertexConsumer= multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES[index2]));
        for (int i = 0; i < north_vertices.length; i++) {
            render_texture(poseStack,northVertexConsumer,north_vertices[i],packedLight,packedOverlay );
        }
        VertexConsumer southVertexConsumer=multiBufferSource.getBuffer(RenderType.entitySolid(SIDE_TEXTURES[index3]));
        for (int i = 0; i < south_vertices.length; i++) {
            render_texture(poseStack,southVertexConsumer,south_vertices[i],packedLight,packedOverlay );
        }
        poseStack.popPose();
    }
    private void render_texture(PoseStack poseStack,VertexConsumer vertexConsumer,float[]dat,int packedLight, int packedOverlay){
        vertexConsumer.vertex(poseStack.last().pose(),dat[0],dat[1],dat[2])
                .color(1.0f,1.0f,1.0f,1.0f)
                .uv(dat[3],dat[4])
                .overlayCoords(packedOverlay)
                .uv2(packedLight)
                .normal(poseStack.last().normal(),dat[5],dat[6],dat[7]).endVertex();
    }
}
