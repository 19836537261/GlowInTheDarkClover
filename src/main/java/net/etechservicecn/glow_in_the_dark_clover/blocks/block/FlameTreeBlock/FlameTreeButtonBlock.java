package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FlameTreeButtonBlock extends ButtonBlock {
    public FlameTreeButtonBlock(Properties p_273290_, BlockSetType p_273462_, int p_273212_, boolean p_272786_) {
        super(p_273290_.strength(10.0f), p_273462_, p_273212_, p_272786_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
