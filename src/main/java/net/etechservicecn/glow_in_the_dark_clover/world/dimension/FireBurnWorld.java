package net.etechservicecn.glow_in_the_dark_clover.world.dimension;

import com.mojang.datafixers.util.Pair;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.chunks.FireBurnChunkGenerator;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.OptionalLong;

public class FireBurnWorld {
    public static final ResourceKey<LevelStem>FIRE_BURN_LEVEL_STEM=ResourceKey.create(Registries.LEVEL_STEM,new ResourceLocation(StartModApplication.MODID,"burn_level"));
    public static final ResourceKey<Level>FIRE_BURN_LEVEL=ResourceKey.create(Registries.DIMENSION,new ResourceLocation(StartModApplication.MODID,"burn_level"));
    public static final ResourceKey<DimensionType>FIRE_BURN_WORLD_DIMENSION_TYPE=ResourceKey.create(Registries.DIMENSION_TYPE,new ResourceLocation(StartModApplication.MODID,"burn_world_dimension_level"));
    public static void bootstrap_dim_type(BootstapContext<DimensionType>context){
        context.register(FIRE_BURN_WORLD_DIMENSION_TYPE,new DimensionType(OptionalLong.of(13000),
                true,false,true,true,
                1.0d,true,false, FireBurnChunkGenerator.minY,FireBurnChunkGenerator.height
                ,FireBurnChunkGenerator.logical_height, FireBurnWorldTags.Blocks.CAN_INFINITE_BURN_BLOCK, BuiltinDimensionTypes.OVERWORLD_EFFECTS,0.1f,new DimensionType.MonsterSettings(false,false, UniformInt.of(0,7),0)));
    }
    public static void bootstrap(BootstapContext<LevelStem>context){
        HolderGetter<DimensionType>dimensionTypeHolderGetter=context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomeHolderGetter=context.lookup(Registries.BIOME);
        context.register(FIRE_BURN_LEVEL_STEM,new LevelStem(dimensionTypeHolderGetter.getOrThrow(FIRE_BURN_WORLD_DIMENSION_TYPE),new FireBurnChunkGenerator(MultiNoiseBiomeSource.createFromList(
                new Climate.ParameterList<>(List.of(
                        Pair.of(Climate.parameters(0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f),biomeHolderGetter.getOrThrow(Biomes.PLAINS))
                ))
        ))));
    }
}
