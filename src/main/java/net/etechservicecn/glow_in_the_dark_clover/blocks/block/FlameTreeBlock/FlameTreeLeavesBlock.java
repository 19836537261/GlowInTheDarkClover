package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FlameTreeLeavesBlock extends LeavesBlock {
    public FlameTreeLeavesBlock(Properties p_54422_) {
        super(p_54422_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
