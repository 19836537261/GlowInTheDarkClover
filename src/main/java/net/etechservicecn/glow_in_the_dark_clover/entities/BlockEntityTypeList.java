package net.etechservicecn.glow_in_the_dark_clover.entities;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypeList {
    public static final DeferredRegister<BlockEntityType<?>>BLOCK_ENTITY_TYPES=DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StartModApplication.MODID);
    public static final RegistryObject<BlockEntityType<TeleportSchedulerBlockEntity>>TELEPORT_SCHEDULER_ENTITY_TYPE=BLOCK_ENTITY_TYPES.register("teleport_scheduler_entity_type",
            ()-> BlockEntityType.Builder.of(TeleportSchedulerBlockEntity::new, BlockList.TELEPORT_SCHEDULER_BLOCK.get()).build(null));
    public static void register(IEventBus bus){
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
