package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.items.item.FlameTreeStickItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class flameTreePackageInfo {
    public static final DeferredRegister<Block> BLOCKS=DeferredRegister.create(Registries.BLOCK, StartModApplication.MODID);
    public static final DeferredRegister<Item>ITEMS=DeferredRegister.create(Registries.ITEM, StartModApplication.MODID);

    public static final RegistryObject<FlameTreeLogBlock> FLAME_TREE_LOG_BLOCK=BLOCKS.register("flame_tree_log",
            ()->new FlameTreeLogBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LOG)));
    public static final RegistryObject<Item>FLAME_TREE_LOG_BLOCK_ITEM=ITEMS.register("flame_tree_log",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<FlameTreeLeavesBlock>FLAME_TREE_LEAVES_BLOCK=BLOCKS.register("flame_tree_leaves",
            ()->new FlameTreeLeavesBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistryObject<Item>FLAME_TREE_LEAVES_BLOCK_ITEM=ITEMS.register("flame_tree_leaves",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<FlameTreeSaplingBlock>FLAME_TREE_SAPLING_BLOCK=BLOCKS.register("flame_tree_sapling",
            ()->new FlameTreeSaplingBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SAPLING)));
    public static final RegistryObject<Item>FLAME_TREE_SAPLING_BLOCK_ITEM=ITEMS.register("flame_tree_sapling",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<FlameTreeTrippedLogBlock>FLAME_TREE_TRIPPED_LOG_BLOCK=BLOCKS.register("flame_tree_stripped_log",
            ()->new FlameTreeTrippedLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_LOG)));
    public static final RegistryObject<Item>FLAME_TREE_STRIPPED_LOG_BLOCK_ITEM=ITEMS.register("flame_tree_stripped_log",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_TRIPPED_LOG_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<FlameTreeWoodBlock>FLAME_TREE_WOOD_BLOCK=BLOCKS.register("flame_tree_wood",
            ()->new FlameTreeWoodBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD)));
    public static final RegistryObject<Item>FLAME_TREE_WOOD_BLOCK_ITEM=ITEMS.register("flame_tree_wood",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<FlameTreePlanksBlock>FLAME_TREE_PLANKS_BLOCK=BLOCKS.register("flame_tree_planks",
            ()->new FlameTreePlanksBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Item>FLAME_TREE_PLANKS_BLOCK_ITEM=ITEMS.register("flame_tree_planks",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<FlameTreeStairBlock>FLAME_TREE_STAIR_BLOCK=BLOCKS.register("flame_tree_stair",
            ()->new FlameTreeStairBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistryObject<Item>FLAME_TREE_STAIR_BLOCK_ITEM=ITEMS.register("flame_tree_stair",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<FlameTreeSlabBlock>FLAME_TREE_SLAB_BLOCK=BLOCKS.register("flame_tree_slab",
            ()->new FlameTreeSlabBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SLAB)));
    public static final RegistryObject<Item>FLAME_TREE_SLAB_BLOCK_ITEM=ITEMS.register("flame_tree_slab",()->new BlockItem(flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<FlameTreeButtonBlock>FLAME_TREE_BUTTON_BLOCK=BLOCKS.register("flame_tree_button",
            ()->new FlameTreeButtonBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_BUTTON), BlockSetType.ACACIA,20,true));
    public static final RegistryObject<Item>FLAME_TREE_BUTTON_BLOCK_ITEM=ITEMS.register("flame_tree_button",()->new BlockItem(FLAME_TREE_BUTTON_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<FlameTreePressurePlateBlock>FLAME_TREE_PRESSURE_PLATE_BLOCK=BLOCKS.register("flame_tree_pressure_plate",
            ()->new FlameTreePressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.ACACIA_PRESSURE_PLATE),BlockSetType.ACACIA));
    public static final RegistryObject<Item>FLAME_TREE_PRESSURE_PLATE_BLOCK_ITEM=ITEMS.register("flame_tree_pressure_plate",()->new BlockItem(FLAME_TREE_PRESSURE_PLATE_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<FlameTreeFenceBlock>FLAME_TREE_FENCE_BLOCK=BLOCKS.register("flame_tree_fence",
            ()->new FlameTreeFenceBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_FENCE)));
    public static final RegistryObject<Item>FLAME_TREE_FENCE_BLOCK_ITEM=ITEMS.register("flame_tree_fence",()->new BlockItem(FLAME_TREE_FENCE_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<FlameTreeFenceGateBlock>FLAME_TREE_FENCE_GATE_BLOCK=BLOCKS.register("flame_tree_fence_gate",
            ()->new FlameTreeFenceGateBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_FENCE_GATE), WoodType.ACACIA));
    public static final RegistryObject<Item>FLAME_TREE_FENCE_GATE_BLOCK_ITEM=ITEMS.register("flame_tree_fence_gate",()->new BlockItem(FLAME_TREE_FENCE_GATE_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Block>FLAME_TREE_WALL_BLOCK=BLOCKS.register("flame_tree_wall",
            ()->new FlameTreeWallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL)));
    public static final RegistryObject<Item>FLAME_TREE_WALL_BLOCK_ITEM=ITEMS.register("flame_tree_wall",
            ()->new BlockItem(FLAME_TREE_WALL_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<FlameTreeDoorBlock>FLAME_TREE_DOOR_BLOCK=BLOCKS.register("flame_tree_door",
            ()->new FlameTreeDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_DOOR),BlockSetType.ACACIA));
    public static final RegistryObject<Item>FLAME_TREE_DOOR_BLOCK_ITEM=ITEMS.register("flame_tree_door",
            ()->new BlockItem(FLAME_TREE_DOOR_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<FlameTreeTrapDoorBlock>FLAME_TREE_TRAP_DOOR_BLOCK=BLOCKS.register("flame_tree_trap_door",
            ()->new FlameTreeTrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR),BlockSetType.ACACIA));
    public static final RegistryObject<Item>FLAME_TREE_TRAP_DOOR_BLOCK_ITEM=ITEMS.register("flame_tree_trap_door",
            ()->new BlockItem(FLAME_TREE_TRAP_DOOR_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<Item>FLAME_TREE_STICK_ITEM=ITEMS.register("flame_tree_stick",()->new FlameTreeStickItem(new Item.Properties()));
    public static void register(IEventBus bus){
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }

}
