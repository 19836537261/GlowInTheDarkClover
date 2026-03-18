package net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.FoliagePlacerList;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class FlameTreeFoliagePlacer extends FoliagePlacer {
    public int height;
    public static final Codec<FlameTreeFoliagePlacer>FLAME_TREE_FOLIAGE_PLACER_CODEC= RecordCodecBuilder.create(inst->foliagePlacerParts(inst).and(Codec.intRange(0,16).fieldOf("height").forGetter(l->l.height)).apply(inst,FlameTreeFoliagePlacer::new));
    public FlameTreeFoliagePlacer(IntProvider radius, IntProvider offset,int height) {
        super(radius,offset);
        this.height=height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return FoliagePlacerList.FLAME_TREE_FOLIAGE_PLACER_TYPE.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int max_height, FoliageAttachment foliageAttachment, int height, int radius, int offset) {
        foliageSetter.set(foliageAttachment.pos().above(1), BlockList.FLAME_TREE_LEAVES_BLOCK.get().defaultBlockState());
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int p_225602_, TreeConfiguration treeConfiguration) {
        return height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource p_225595_, int p_225596_, int p_225597_, int p_225598_, int p_225599_, boolean p_225600_) {
        return false;
    }
}
