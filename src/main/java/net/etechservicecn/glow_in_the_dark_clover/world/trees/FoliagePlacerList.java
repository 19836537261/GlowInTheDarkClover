package net.etechservicecn.glow_in_the_dark_clover.world.trees;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.flame_tree.FlameTreeFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FoliagePlacerList {
    public static final DeferredRegister<FoliagePlacerType<?>>FOLIAGE_PLACERS=DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, StartModApplication.MODID);
    public static final RegistryObject<FoliagePlacerType<?>>FLAME_TREE_FOLIAGE_PLACER_TYPE=FOLIAGE_PLACERS.register("flame_tree_foliage_placer_type",()->
            new FoliagePlacerType<>(FlameTreeFoliagePlacer.FLAME_TREE_FOLIAGE_PLACER_CODEC));
    public static void register(IEventBus bus){
        FOLIAGE_PLACERS.register(bus);
    }
}
