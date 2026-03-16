package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.teleporters.FireWorldTeleporter;
import net.etechservicecn.glow_in_the_dark_clover.world.dimension.FireBurnWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TeleportBlock extends Block implements EntityBlock {
    public TeleportBlock(Properties p_49795_) {
        super(p_49795_.lightLevel(p->{return 15;}).noCollission());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TeleportBlockEntity(p_153215_,p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (blockEntityType== BlockEntityTypeList.TELEPORT_BLOCK_ENTITY_TYPE.get()){
            return ((level1, blockPos, blockState1, blockEntity) -> {
                if (level1.isClientSide()){
                    if (blockEntity instanceof TeleportBlockEntity teleportBlockEntity){
                        teleportBlockEntity.calculate_counter();
                    }
                }
            });
        }
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.INVISIBLE;
    }
    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (!level.isClientSide()){
            if (entity instanceof Player player){
                ResourceKey<Level>levelResourceKey=level.dimension()== FireBurnWorld.FIRE_BURN_LEVEL?Level.OVERWORLD:FireBurnWorld.FIRE_BURN_LEVEL;
                MinecraftServer minecraftServer=level.getServer();
                ServerLevel serverLevel=minecraftServer.getLevel(levelResourceKey);
                if (serverLevel!=null&&!player.isPassenger()){
                    player.changeDimension(serverLevel,new FireWorldTeleporter(blockPos));
                }
            }
        }
    }
}
