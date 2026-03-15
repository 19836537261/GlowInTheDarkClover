package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StartModApplication.MODID)
public class OnBlockDigEvent {
    @SubscribeEvent
    public static void trigger_dig_event(BlockEvent.BreakEvent event){
        Player player=event.getPlayer();
        BlockState blockState=event.getState();
        ItemStack itemStack=player.getMainHandItem();
        if (itemStack.is(Items.GOLDEN_SHOVEL)&&blockState.is(BlockList.FIRE_DIRT_BLOCK.get())){
            event.setCanceled(true);
            event.getLevel().destroyBlock(event.getPos(),false);
            Block.popResource((Level) event.getLevel(),event.getPos(),new ItemStack(BlockList.FIRE_DIRT_BLOCK.get(),1));
        }
    }
}
