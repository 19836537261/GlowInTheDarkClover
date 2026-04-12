package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FlameTreeDoorBlock extends DoorBlock {
    public FlameTreeDoorBlock(Properties p_273303_, BlockSetType p_272854_) {
        super(p_273303_.requiresCorrectToolForDrops().strength(10.0f), p_272854_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
