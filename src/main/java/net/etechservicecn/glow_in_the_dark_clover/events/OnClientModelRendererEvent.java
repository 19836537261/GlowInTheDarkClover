package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntityRender;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock.TeleportSchedulerBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock.TeleportSchedulerBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StartModApplication.MODID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnClientModelRendererEvent {
    @SubscribeEvent
    public static void register_model(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityTypeList.TELEPORT_SCHEDULER_ENTITY_TYPE.get(), new BlockEntityRendererProvider<TeleportSchedulerBlockEntity>() {
            @Override
            public BlockEntityRenderer<TeleportSchedulerBlockEntity> create(Context p_173571_) {
                return new TeleportSchedulerBlockEntityRenderer();
            }
        });
        event.registerBlockEntityRenderer(BlockEntityTypeList.TELEPORT_BLOCK_ENTITY_TYPE.get(), new BlockEntityRendererProvider<TeleportBlockEntity>() {
            @Override
            public BlockEntityRenderer<TeleportBlockEntity> create(Context p_173571_) {
                return new TeleportBlockEntityRender();
            }
        });
    }
}
