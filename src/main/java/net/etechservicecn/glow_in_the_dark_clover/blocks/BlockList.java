package net.etechservicecn.glow_in_the_dark_clover.blocks;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FireDirtBlock;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.FlameTreeLeavesBlock;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.FlameTreeLogBlock;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.FlameTreeSaplingBlock;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.TeleportBlock;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.TeleportSchedulerBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockList {
    public static final DeferredRegister<Block>BLOCKS=DeferredRegister.create(Registries.BLOCK, StartModApplication.MODID);
    public static final RegistryObject<FireDirtBlock> FIRE_DIRT_BLOCK=BLOCKS.register("fire_dirt_block",
            ()->new FireDirtBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<TeleportSchedulerBlock>TELEPORT_SCHEDULER_BLOCK=BLOCKS.register("teleport_scheduler_block",
            ()->new TeleportSchedulerBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)));
    public static final RegistryObject<TeleportBlock>TELEPORT_BLOCK=BLOCKS.register("teleport_block",
            ()->new TeleportBlock(BlockBehaviour.Properties.of()));
    public static final RegistryObject<FlameTreeLogBlock>FLAME_TREE_LOG_BLOCK=BLOCKS.register("flame_tree_log",
            ()->new FlameTreeLogBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG)));
    public static final RegistryObject<FlameTreeLeavesBlock>FLAME_TREE_LEAVES_BLOCK=BLOCKS.register("flame_tree_leaves",
            ()->new FlameTreeLeavesBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistryObject<FlameTreeSaplingBlock>FLAME_TREE_SAPLING_BLOCK=BLOCKS.register("flame_tree_sapling",
            ()->new FlameTreeSaplingBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SAPLING)));
    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
