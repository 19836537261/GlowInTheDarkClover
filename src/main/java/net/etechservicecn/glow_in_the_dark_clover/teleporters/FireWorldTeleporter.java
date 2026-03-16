package net.etechservicecn.glow_in_the_dark_clover.teleporters;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.chunks.FireBurnChunkGenerator;
import net.etechservicecn.glow_in_the_dark_clover.world.dimension.FireBurnWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.ITeleporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FireWorldTeleporter implements ITeleporter {
    private BlockPos blockPos;
    private Map<List<Integer>, BlockState>reflect_map=new HashMap<>();
    public FireWorldTeleporter(BlockPos blockPos){
        this.blockPos=blockPos;
        this.fill_block_state();
    }
    private void fill_block_state(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i>=1&&i<=3&&j>=1&&j<=3){
                    reflect_map.put(List.of(i,j),BlockList.TELEPORT_BLOCK.get().defaultBlockState());
                }else {
                    reflect_map.put(List.of(i,j),Blocks.OBSIDIAN.defaultBlockState());
                }
            }
        }
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity teleport_entity=repositionEntity.apply(false);
        if (!destWorld.isClientSide()&&teleport_entity!=null){
            if (currentWorld.dimension()== Level.OVERWORLD&&destWorld.dimension()== FireBurnWorld.FIRE_BURN_LEVEL){
                for (int i = FireBurnChunkGenerator.logical_height; i >FireBurnChunkGenerator.minY; i--) {
                    BlockPos judge_pos=new BlockPos(blockPos.getX(),i,blockPos.getZ());
                    if (destWorld.getBlockState(judge_pos).is(Blocks.AIR)&&destWorld.getBlockState(judge_pos.below(1)).is(BlockList.FIRE_DIRT_BLOCK.get()))
                    {
                        BlockPos summon_pos=judge_pos.below(1).north(6);
                        for (int x = 0; x < 5; x++) {
                            for (int z = 0; z < 5; z++) {
                                destWorld.setBlock(summon_pos.west(x).south(z),reflect_map.get(List.of(x,z)), Block.UPDATE_ALL);
                            }
                        }
                        teleport_entity.teleportTo(judge_pos.getX(),judge_pos.getY(),judge_pos.getZ());
                        break;
                    }
                }
            } else if (currentWorld.dimension()==FireBurnWorld.FIRE_BURN_LEVEL&&destWorld.dimension()==Level.OVERWORLD) {
                for (int i = 384; i >-64 ; i--) {
                    BlockPos judge_pos=new BlockPos(blockPos.getX(),i,blockPos.getZ());
                    if (destWorld.getBlockState(judge_pos).is(Blocks.AIR)&&!destWorld.getBlockState(judge_pos.below(1)).is(Blocks.AIR)){
                        teleport_entity.teleportTo(judge_pos.getX(),judge_pos.getY(),judge_pos.getZ());
                        break;
                    }
                }
            }
        }
        return teleport_entity;
    }

    @Override
    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
        return true;
    }
}
