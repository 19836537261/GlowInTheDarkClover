package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TeleportBlock extends Block implements EntityBlock {
    public TeleportBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TeleportBlockEntity(p_153215_,p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return EntityBlock.super.getTicker(p_153212_, p_153213_, p_153214_);
    }
}
