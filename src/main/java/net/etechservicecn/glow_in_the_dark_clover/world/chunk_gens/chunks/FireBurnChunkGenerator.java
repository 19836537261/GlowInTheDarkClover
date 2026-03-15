package net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.chunks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class FireBurnChunkGenerator extends ChunkGenerator {
    public static final Integer minY= -64;
    public static final Integer height=256;
    public static final Integer logical_height=240;
    public static final Integer sea_level=0;

    public static final Integer min_terrain_height=16;
    public static final Integer max_terrain_height=196;

    public static final Double base_noise_scale = 0.5;
    public static final Codec<FireBurnChunkGenerator>CODEC= RecordCodecBuilder.create(inst->inst.group(
            BiomeSource.CODEC.fieldOf("biomeSource")
                    .forGetter(FireBurnChunkGenerator::getBiomeSource)).apply(inst,FireBurnChunkGenerator::new));
    public FireBurnChunkGenerator(BiomeSource p_256133_) {
        super(p_256133_);
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion p_223043_, long p_223044_, RandomState p_223045_, BiomeManager p_223046_, StructureManager p_223047_, ChunkAccess p_223048_, GenerationStep.Carving p_223049_) {

    }

    @Override
    public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_) {

    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion p_62167_) {

    }

    @Override
    public int getGenDepth()
    {
        return height;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.supplyAsync(()->{
            BlockPos.MutableBlockPos mutableBlockPos=new BlockPos.MutableBlockPos();
            for (int worldx = chunkAccess.getPos().getMinBlockX(); worldx <= chunkAccess.getPos().getMaxBlockX(); worldx++) {
                for (int worldz = chunkAccess.getPos().getMinBlockZ(); worldz <= chunkAccess.getPos().getMaxBlockZ(); worldz++) {
                    for (int i = chunkAccess.getMinBuildHeight(); i < chunkAccess.getMaxBuildHeight(); i++) {
                        mutableBlockPos.set(worldx,i,worldz);
                        int base_height= (int) calculateNoiseHeight(worldx,i,worldz,randomState);
                        if (i>minY&&i<=base_height){
                            fill_sketeon(chunkAccess,worldx,i,worldz,base_height);
                        } else if (i>base_height&&i<=sea_level) {
                            chunkAccess.setBlockState(new BlockPos(worldx,i,worldz),Blocks.WATER.defaultBlockState(), false);
                        } else {
                            chunkAccess.setBlockState(new BlockPos(worldx,i,worldz),Blocks.AIR.defaultBlockState(), false);
                        }
                    }
                    Heightmap.primeHeightmaps(chunkAccess,Set.of(Heightmap.Types.OCEAN_FLOOR_WG,Heightmap.Types.WORLD_SURFACE_WG));
                }
            }
            return chunkAccess;
        });
    }
    private Integer bedrock_height=5;
    private Integer stone_height=48;
    private Integer dirt_height=8;

    private void fill_sketeon(ChunkAccess chunkAccess, int worldx, int worldy, int worldz,int max_height) {
        int bed_rock_layer=minY+bedrock_height;
        int stone_layer=bed_rock_layer+stone_height;
        int dirt_layer=stone_layer+dirt_height;
        BlockPos blockPos=new BlockPos(worldx,worldy,worldz);
        if (worldy>minY&&worldy<=bed_rock_layer){
            chunkAccess.setBlockState(blockPos,Blocks.BEDROCK.defaultBlockState(), false);
        } else if (worldy>bed_rock_layer&&worldy<=stone_layer) {
            chunkAccess.setBlockState(blockPos,Blocks.STONE.defaultBlockState(), false);
        } else if (worldy>stone_layer&&worldy<=dirt_layer) {
            chunkAccess.setBlockState(blockPos,Blocks.DIRT.defaultBlockState(), false);
        } else if (worldy>dirt_layer&&worldy<=max_height) {
            chunkAccess.setBlockState(blockPos,BlockList.FIRE_DIRT_BLOCK.get().defaultBlockState(),false);
        }
    }

    private double calculateNoiseHeight(int x,int y,int z,RandomState randomState){
        NormalNoise baseNoise=randomState.getOrCreateNoise(Noises.CONTINENTALNESS);
        double base_noise_value=baseNoise.getValue(x*base_noise_scale,y,z*base_noise_scale);
        double summon_height_rate=(base_noise_value+1)/2;
        return minY+(max_terrain_height-min_terrain_height)*summon_height_rate;
    }

    @Override
    public int getSeaLevel() {
        return sea_level;
    }

    @Override
    public int getMinY() {
        return minY;
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types heightMapTypes, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return (int) calculateNoiseHeight(x,0,z,randomState);
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        BlockState[] blockStates=new BlockState[levelHeightAccessor.getHeight()];
        for (int i = levelHeightAccessor.getMinBuildHeight(); i < levelHeightAccessor.getMaxBuildHeight(); i++) {
            blockStates[i-levelHeightAccessor.getMinBuildHeight()]=Blocks.AIR.defaultBlockState();
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(),blockStates);
    }

    @Override
    public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {

    }
}
