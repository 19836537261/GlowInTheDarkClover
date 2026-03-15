package net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens;

import com.mojang.serialization.Codec;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.chunks.FireBurnChunkGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ChunkList {
    public static final DeferredRegister<Codec<? extends ChunkGenerator>>CHUNKS=DeferredRegister.create(Registries.CHUNK_GENERATOR, StartModApplication.MODID);
    public static final RegistryObject<Codec<? extends ChunkGenerator>>FIRE_BURN_WORLD_CHUNK_GENERATOR=CHUNKS.register("fire_burn_world_chunk_generator",()->
            FireBurnChunkGenerator.CODEC);

    public static void register(IEventBus bus){
        CHUNKS.register(bus);
    }

}
