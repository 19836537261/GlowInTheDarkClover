package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.triggers.TeleportTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = StartModApplication.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdvanceTriggerRegisterEvent {
    public static final TeleportTrigger TELEPORT_TRIGGER=new TeleportTrigger();
    @SubscribeEvent
    public static void register_trigger(FMLCommonSetupEvent event){
        event.enqueueWork(()->{
            CriteriaTriggers.register(TELEPORT_TRIGGER);
        });
    }
}
