package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FlameTreeLogBlock extends RotatedPillarBlock {
    public FlameTreeLogBlock(Properties p_55926_) {
        super(p_55926_.requiresCorrectToolForDrops().strength(10.0f));
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
