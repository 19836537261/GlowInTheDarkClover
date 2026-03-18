package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.world.BiomeModifiers;
import net.etechservicecn.glow_in_the_dark_clover.world.ConfigurationFeatures;
import net.etechservicecn.glow_in_the_dark_clover.world.PlacedFeatures;
import net.etechservicecn.glow_in_the_dark_clover.world.biomes.biome_settings.FireLandBiomeSetting;
import net.etechservicecn.glow_in_the_dark_clover.world.dimensions.FireBurnWorld;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldDataGenerator extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER=new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, FireBurnWorld::bootstrap_dim_type)
            .add(Registries.LEVEL_STEM,FireBurnWorld::bootstrap)
            .add(Registries.BIOME, FireLandBiomeSetting::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ConfigurationFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifiers::bootstrap);
    public WorldDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER, Set.of(StartModApplication.MODID));

    }
}
