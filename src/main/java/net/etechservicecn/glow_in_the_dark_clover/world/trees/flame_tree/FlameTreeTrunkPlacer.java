package net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.TrunkPlacerList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class FlameTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<FlameTreeTrunkPlacer>FLAME_TREE_TRUNK_PLACER_CODEC= RecordCodecBuilder.create(
            inst->trunkPlacerParts(inst).apply(inst,FlameTreeTrunkPlacer::new));
    public FlameTreeTrunkPlacer(int base_height, int height_rand_a, int height_rand_b) {
        super(base_height,height_rand_a,height_rand_b);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return TrunkPlacerList.FLAME_TREE_TRUNK_PLACER_TYPE.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int height, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        int real_height=baseHeight+randomSource.nextInt(heightRandA,heightRandA+1)+randomSource.nextInt(heightRandB,heightRandB+1);
        if (levelSimulatedReader.isStateAtPosition(blockPos.below(1),p->p.is(BlockList.FIRE_DIRT_BLOCK.get()))){
            for (int i = 0; i < real_height; i++) {
                this.placeLog(levelSimulatedReader,biConsumer,randomSource,blockPos.above(i),treeConfiguration);
            }
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(real_height),0,false));
    }
}
