package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FlameTreePressurePlateBlock extends PressurePlateBlock {
    public FlameTreePressurePlateBlock(Sensitivity p_273523_, Properties p_273571_, BlockSetType p_273284_) {
        super(p_273523_, p_273571_.requiresCorrectToolForDrops().strength(10.0f), p_273284_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
