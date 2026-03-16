package net.etechservicecn.glow_in_the_dark_clover.teleporters;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.chunks.FireBurnChunkGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class FireWorldTeleporter implements ITeleporter {
    private BlockPos blockPos;
    public FireWorldTeleporter(BlockPos blockPos){
        this.blockPos=blockPos;
    }
    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity teleport_entity=repositionEntity.apply(false);
        if (!destWorld.isClientSide()&&teleport_entity!=null){
            for (int i = FireBurnChunkGenerator.logical_height; i >FireBurnChunkGenerator.minY; i--) {
                BlockPos judge_pos=new BlockPos(blockPos.getX(),i,blockPos.getZ());
                if (destWorld.getBlockState(judge_pos).is(Blocks.AIR)&&destWorld.getBlockState(judge_pos.below(1)).is(BlockList.FIRE_DIRT_BLOCK.get()))
                {
                    teleport_entity.teleportTo(judge_pos.getX(),judge_pos.getY(),judge_pos.getZ());
                    break;
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
