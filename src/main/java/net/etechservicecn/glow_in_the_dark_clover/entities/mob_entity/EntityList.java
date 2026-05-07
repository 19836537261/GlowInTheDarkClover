package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken.FlameChickenEntity;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_pig.FlamePigEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityList {
    public static final DeferredRegister<EntityType<?>>ENTITIES=DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StartModApplication.MODID);
    public static final RegistryObject<EntityType<FlamePigEntity>>FLAME_PIG_ENTITY=ENTITIES.register("flame_pig",
            ()->EntityType.Builder
                    .of(FlamePigEntity::new, MobCategory.CREATURE)
                    .sized(1.0F,0.8F)
                    .build("flame_pig"));
    public static final RegistryObject<EntityType<FlameChickenEntity>>FLAME_CHICKEN_ENTITY=ENTITIES.register("flame_chicken",
            ()->EntityType.Builder
                    .of(FlameChickenEntity::new,MobCategory.CREATURE)
                    .sized(0.7F,0.6F)
                    .build("flame_chicken"));
    public static void register(IEventBus bus){
        ENTITIES.register(bus);
    }
}
