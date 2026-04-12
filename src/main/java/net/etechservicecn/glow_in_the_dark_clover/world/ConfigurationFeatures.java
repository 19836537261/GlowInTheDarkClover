package net.etechservicecn.glow_in_the_dark_clover.world;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree.FlameTreeFoliagePlacer;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree.FlameTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ConfigurationFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>>FLAME_TREE_FEATURE=createKey("flame_tree_feature");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>>context){
        context.register(FLAME_TREE_FEATURE,new ConfiguredFeature<>(Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get()),
                new FlameTreeTrunkPlacer(4,0,1),
                BlockStateProvider.simple(flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get()),
                new FlameTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),3),
                new TwoLayersFeatureSize(1,10,10)
        ).build()));
    }

    private static ResourceKey<ConfiguredFeature<?,?>> createKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(StartModApplication.MODID,name));
    }
}
