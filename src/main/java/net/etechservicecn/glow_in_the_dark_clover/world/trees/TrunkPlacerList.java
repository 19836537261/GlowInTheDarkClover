package net.etechservicecn.glow_in_the_dark_clover.world.trees;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree.FlameTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TrunkPlacerList {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS=DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, StartModApplication.MODID);
    public static final RegistryObject<TrunkPlacerType<?>>FLAME_TREE_TRUNK_PLACER_TYPE=TRUNK_PLACERS.register("flame_tree_trunk_placer_type",
            ()->new TrunkPlacerType<>(FlameTreeTrunkPlacer.FLAME_TREE_TRUNK_PLACER_CODEC));
    public static void register(IEventBus bus){
        TRUNK_PLACERS.register(bus);
    }
}
