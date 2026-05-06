package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.EntityList;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_pig.FlamePigEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StartModApplication.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnEntityLayerEvent {
    @SubscribeEvent
    public static void register_mob_attributes(EntityAttributeCreationEvent event){
        event.put(EntityList.FLAME_PIG_ENTITY.get(), FlamePigEntity.createAttributes().build());
    }
}
