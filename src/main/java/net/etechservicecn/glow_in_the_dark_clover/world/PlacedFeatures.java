package net.etechservicecn.glow_in_the_dark_clover.world;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature>FLAME_TREE_PLACE_FEATURE=createKey("flame_tree_place_feature");
    public static void bootstrap(BootstapContext<PlacedFeature>context){
        var configuration=context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(FLAME_TREE_PLACE_FEATURE,new PlacedFeature(configuration.getOrThrow(ConfigurationFeatures.FLAME_TREE_FEATURE), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.2f,2))));
    }
    private static ResourceKey<PlacedFeature> createKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(StartModApplication.MODID,name));
    }
}
