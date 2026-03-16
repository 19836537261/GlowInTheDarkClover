package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntity;
import net.minecraft.core.BlockPos;
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
}
