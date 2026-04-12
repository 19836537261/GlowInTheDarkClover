package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FlameTreeTrapDoorBlock extends TrapDoorBlock {
    public FlameTreeTrapDoorBlock(Properties p_273079_, BlockSetType p_272964_) {
        super(p_273079_.requiresCorrectToolForDrops().strength(10.0f), p_272964_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
