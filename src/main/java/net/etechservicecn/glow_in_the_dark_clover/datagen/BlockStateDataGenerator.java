package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BlockStateDataGenerator extends BlockStateProvider {
    public BlockStateDataGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, StartModApplication.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.buildTopSideBottomBlock(BlockList.FIRE_DIRT_BLOCK);
        this.buildTopSideBottomBlock(BlockList.TELEPORT_SCHEDULER_BLOCK);
        this.buildTopSideBottomBlock(BlockList.TELEPORT_BLOCK);
        this.buildBuildingBlockFromSource(
                flameTreePackageInfo.FLAME_TREE_LOG_BLOCK,
                flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK,
                flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK,
                flameTreePackageInfo.FLAME_TREE_TRIPPED_LOG_BLOCK,
                flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK,
                flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK,
                flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK,
                flameTreePackageInfo.FLAME_TREE_BUTTON_BLOCK,
                flameTreePackageInfo.FLAME_TREE_PRESSURE_PLATE_BLOCK,
                flameTreePackageInfo.FLAME_TREE_FENCE_BLOCK,
                flameTreePackageInfo.FLAME_TREE_FENCE_GATE_BLOCK,
                flameTreePackageInfo.FLAME_TREE_WALL_BLOCK,
                flameTreePackageInfo.FLAME_TREE_DOOR_BLOCK,
                flameTreePackageInfo.FLAME_TREE_TRAP_DOOR_BLOCK,
                flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK);
    }
    private void buildCrossBlock(RegistryObject<? extends Block>block){
        simpleBlock(block.get(),models().singleTexture(block.getId().getPath(),mcLoc("block/cross"),"cross",modLoc("block/"+block.getId().getPath())).renderType("cutout"));
    }
    private void buildTopSideBlock(RegistryObject<? extends Block>block){
        simpleBlock(block.get(),models().cubeBottomTop(
                block.getId().getPath(),
                modLoc("block/"+block.getId().getPath()+"_side"),
                modLoc("block/"+block.getId().getPath()+"_tb"),
                modLoc("block/"+block.getId().getPath()+"_tb")));
    }
    private void buildTopSideBottomBlock(RegistryObject<? extends Block>block){
        simpleBlock(block.get(),models().cubeBottomTop(
                block.getId().getPath(),
                modLoc("block/"+block.getId().getPath()+"_side"),
                modLoc("block/"+block.getId().getPath()+"_bottom"),
                modLoc("block/"+block.getId().getPath()+"_top")));
    }
    private void buildOnePatternBlock(RegistryObject<? extends Block>block){
        simpleBlock(block.get(),models().cubeAll(block.getId().getPath(),modLoc("block/"+block.getId().getPath())));
    }
    private void buildBuildingBlockFromSource(RegistryObject<? extends Block>log_block,
                                              RegistryObject<? extends Block>leaves_block,
                                              RegistryObject<? extends Block>sapling_block,
                                              RegistryObject<? extends Block>tripped_log_block,
                                              RegistryObject<? extends Block>wood_block,
                                              RegistryObject<? extends Block>stair_block,
                                              RegistryObject<? extends Block>slab_block,
                                              RegistryObject<? extends Block>button_block,
                                              RegistryObject<? extends Block>pressure_plate_block,
                                              RegistryObject<? extends Block>fence_block,
                                              RegistryObject<? extends Block>fence_gate_block,
                                              RegistryObject<? extends Block>wall_block,
                                              RegistryObject<? extends Block>door_block,
                                              RegistryObject<? extends Block>trap_door_block,
                                              RegistryObject<? extends Block>source_block){
        this.buildTopSideBlock(log_block);
        this.buildOnePatternBlock(leaves_block);
        this.buildCrossBlock(sapling_block);
        this.buildTopSideBlock(tripped_log_block);
        this.buildOnePatternBlock(source_block);
        this.buildOnePatternBlock(wood_block);
        stairsBlock((StairBlock) stair_block.get(),blockTexture(source_block.get()));
        slabBlock((SlabBlock) slab_block.get(),blockTexture(source_block.get()),blockTexture(source_block.get()));
        buttonBlock((ButtonBlock) button_block.get(),blockTexture(source_block.get()));
        pressurePlateBlock((PressurePlateBlock) pressure_plate_block.get(),blockTexture(source_block.get()));
        fenceBlockWithRenderType((FenceBlock) fence_block.get(),blockTexture(source_block.get()),"cutout");
        fenceGateBlockWithRenderType((FenceGateBlock) fence_gate_block.get(),blockTexture(source_block.get()),"cutout");
        wallBlockWithRenderType((WallBlock) wall_block.get(),blockTexture(source_block.get()),"cutout");
        doorBlockWithRenderType((DoorBlock) door_block.get(),modLoc("block/"+door_block.getId().getPath()+"_bottom"),modLoc("block/"+door_block.getId().getPath()+"_top"),"cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) trap_door_block.get(),blockTexture(source_block.get()),true,"cutout");
    }

}
