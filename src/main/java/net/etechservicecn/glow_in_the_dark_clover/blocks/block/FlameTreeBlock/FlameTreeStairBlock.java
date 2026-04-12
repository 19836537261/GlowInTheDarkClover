package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FlameTreeStairBlock extends StairBlock {
    public FlameTreeStairBlock( Properties p_56863_) {
        super(flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK.get().defaultBlockState(), p_56863_.requiresCorrectToolForDrops().strength(10.0f));
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
}
