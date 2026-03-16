package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid = StartModApplication.MODID)
public class OnMouseRightClickEvent {
    @SubscribeEvent
    public static void when_player_right_click(PlayerInteractEvent.RightClickBlock event){
//        Level level=event.getLevel();
//        Player player=event.getEntity();
//        ItemStack itemStack=player.getItemInHand(event.getHand());
//        BlockPos blockPos=event.getPos();
//        BlockState blockState=level.getBlockState(blockPos);
//        if (itemStack.is(ItemList.FIRE_WORLD_TOKEN.get())&&blockState.is(BlockList.TELEPORT_SCHEDULER_BLOCK.get())){
//            fill_north_south_block(level,blockPos,itemStack,player);
//        }
    }
    public static void fill_blocks(Level level,BlockPos blockPos,ItemStack itemStack,Player player){
        BlockPos north=blockPos.north(1);
        BlockPos north_pillar=north.north(1).above(1);
        BlockPos south=blockPos.south(1);
        BlockPos south_pillar=south.south(1).above(1);
        BlockPos top=blockPos.above(4);
        BlockPos north_top=top.north(1);
        BlockPos south_top=top.south(1);
        if (level.getBlockState(north).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_top).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_top).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(top).is(Blocks.OBSIDIAN)){
            if (!player.isCreative()){
                itemStack.shrink(1);
            }
            level.playSound(null,blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,1.0F,1.0F);
            level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),1.0d,1.0d,0.0d);
            level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),-1.0d,1.0d,0.0d);
            BlockPos start_fill_pos=north.above(1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    level.setBlock(start_fill_pos.south(i).above(j),BlockList.TELEPORT_BLOCK.get().defaultBlockState(), Block.UPDATE_NEIGHBORS|Block.UPDATE_CLIENTS);
                }
            }
        }else {
            BlockPos east=blockPos.east(1);
            BlockPos east_pillar=east.east(1).above(1);
            BlockPos west=blockPos.west(1);
            BlockPos west_pillar=west.west(1).above(1);
            BlockPos east_top=top.east(1);
            BlockPos west_top=top.west(1);
            if (level.getBlockState(east).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_top).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_top).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(top).is(Blocks.OBSIDIAN)){
                if (!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.playSound(null,blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,1.0F,1.0F);
                level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),0.0d,1.0d,1.0d);
                level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),0.0d,1.0d,-1.0d);
                BlockPos start_fill_pos=east.above(1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        level.setBlock(start_fill_pos.west(i).above(j),BlockList.TELEPORT_BLOCK.get().defaultBlockState(), Block.UPDATE_NEIGHBORS|Block.UPDATE_CLIENTS);
                    }
                }
            }
        }
    }
}
