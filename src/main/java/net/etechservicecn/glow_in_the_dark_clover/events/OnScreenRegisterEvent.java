package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.guis.MenuList;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterScreen;
import net.etechservicecn.glow_in_the_dark_clover.triggers.TeleportTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = StartModApplication.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnScreenRegisterEvent {

    @SubscribeEvent
    @OnlyIn(value = Dist.CLIENT)
    public static void register_screen(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            MenuScreens.register(MenuList.ITEM_SCEPTER_MENU.get(), ItemScepterScreen::new);
        });
    }
}
