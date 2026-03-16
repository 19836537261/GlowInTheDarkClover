package net.etechservicecn.glow_in_the_dark_clover.world.biomes.biome_settings;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

public class FireLandBiomeSettings {
    public static final ResourceKey<Biome>FIRE_LAND_BIOME=ResourceKey.create(Registries.BIOME,new ResourceLocation(StartModApplication.MODID,"fire_land_biome"));
    public static void bootstrap(BootstapContext<Biome>context){
        Biome.BiomeBuilder builder=new Biome.BiomeBuilder();
        builder.hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.7f)
                .mobSpawnSettings(get_spawn_builder().build())
                .generationSettings(get_biome_generation_builder(context).build())
                .specialEffects(get_special_builder().build());
        context.register(FIRE_LAND_BIOME,builder.build());
    }
    private static MobSpawnSettings.Builder get_spawn_builder(){
        MobSpawnSettings.Builder builder=new MobSpawnSettings.Builder();
        builder.addSpawn(MobCategory.CREATURE,new MobSpawnSettings.SpawnerData(EntityType.SHEEP,6,2,3));
        builder.addSpawn(MobCategory.CREATURE,new MobSpawnSettings.SpawnerData(EntityType.PIG,7,1,2));
        BiomeDefaultFeatures.farmAnimals(builder);
        BiomeDefaultFeatures.commonSpawns(builder);
        BiomeDefaultFeatures.snowySpawns(builder);//任选一个或多个即可
        return builder;
    }
    private static BiomeGenerationSettings.Builder get_biome_generation_builder(BootstapContext<Biome>context){
        BiomeGenerationSettings.Builder builder=new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addAncientDebris(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addMossyStoneBlock(builder);
        BiomeDefaultFeatures.addForestFlowers(builder);
        BiomeDefaultFeatures.addFerns(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addExtraGold(builder);
        return builder;
    }
    private static BiomeSpecialEffects.Builder get_special_builder(){
        BiomeSpecialEffects.Builder builder=new BiomeSpecialEffects.Builder();
        builder.ambientMoodSound(
                        AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .waterColor(0x4159204)
                .waterFogColor(0x329011)
                .skyColor(0x8364543)
                .fogColor(0x22a1e6)
                .grassColorOverride(0x7f03fc);
        return builder;
    }



}
