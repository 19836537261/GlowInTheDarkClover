package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
        this.buildOnePatternBlock(BlockList.TELEPORT_BLOCK);
        this.buildTopSideBlock(BlockList.FLAME_TREE_LOG_BLOCK);
        this.buildOnePatternBlock(BlockList.FLAME_TREE_LEAVES_BLOCK);
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
    private void buildMulVariantBlock(RegistryObject<? extends Block>block){
        simpleBlock(block.get(),models().cube(block.getId().getPath(),
                modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_bottom"),
                        modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_top"),
                        modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_side0"),
                        modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_side0"),
                        modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_side0"),
                        modLoc("block/"+block.getId().getPath()+"/"+block.getId().getPath()+"_side0")));

    }

}
