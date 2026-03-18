package net.etechservicecn.glow_in_the_dark_clover.world;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.world.biomes.biome_settings.FireLandBiomeSetting;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeModifiers {
    public static final ResourceKey<BiomeModifier>FLAME_TREE_BIOME_MODIFIER=createKey("flame_tree_biome_modifier");
    public static void bootstrap(BootstapContext<BiomeModifier>context){
        var biomes=context.lookup(Registries.BIOME);
        var features=context.lookup(Registries.PLACED_FEATURE);
        context.register(FLAME_TREE_BIOME_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(biomes.getOrThrow(FireLandBiomeSetting.FIRE_LAND_BIOME)),HolderSet.direct(features.getOrThrow(PlacedFeatures.FLAME_TREE_PLACE_FEATURE)), GenerationStep.Decoration.VEGETAL_DECORATION));
    }
    public static ResourceKey<BiomeModifier> createKey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,new ResourceLocation(StartModApplication.MODID,name));
    }
}
