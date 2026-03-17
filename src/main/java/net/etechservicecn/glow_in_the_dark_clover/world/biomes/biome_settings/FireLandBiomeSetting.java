package net.etechservicecn.glow_in_the_dark_clover.world.biomes.biome_settings;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class FireLandBiomeSetting {
    public static final ResourceKey<Biome>FIRE_LAND_BIOME=ResourceKey.create(Registries.BIOME,new ResourceLocation(StartModApplication.MODID,"fire_land_biome"));
    public static void bootstrap(BootstapContext<Biome>context){
        Biome.BiomeBuilder builder=new Biome.BiomeBuilder();
        builder.hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(5.2f)
                .mobSpawnSettings(get_spawn_builder().build())
                .generationSettings(get_biome_generation_builder(context).build())
                .specialEffects(get_special_builder().build());
        context.register(FIRE_LAND_BIOME,builder.build());
    }
    private static MobSpawnSettings.Builder get_spawn_builder(){
        MobSpawnSettings.Builder builder=new MobSpawnSettings.Builder();
        builder.addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(EntityType.BLAZE,100,2,8));
        builder.addSpawn(MobCategory.MONSTER,new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE,95,2,10));
        return builder;
    }
    private static BiomeGenerationSettings.Builder get_biome_generation_builder(BootstapContext<Biome>context){
        BiomeGenerationSettings.Builder builder=new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        return builder;
    }
    private static BiomeSpecialEffects.Builder get_special_builder(){
        BiomeSpecialEffects.Builder builder=new BiomeSpecialEffects.Builder();
        builder.ambientMoodSound(
                        AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .waterColor(0xEE3D11)
                .waterFogColor(0xDD2222)
                .skyColor(0xC43C3C)
                .fogColor(0xC43C3C)
                .grassColorOverride(0xC43C3C);
        return builder;
    }



}
