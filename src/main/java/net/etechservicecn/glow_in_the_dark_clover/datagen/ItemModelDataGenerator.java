package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelDataGenerator extends ItemModelProvider {
    public ItemModelDataGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StartModApplication.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItem(ItemList.FIRE_LAUNCHER);
        registerItem(ItemList.FIRE_WORLD_TOKEN);
        registerBlockItem(ItemList.FIRE_DIRT_BLOCK_ITEM);
        registerBlockItem(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM);
        registerBlockItem(ItemList.FLAME_TREE_LOG_BLOCK_ITEM);
        registerBlockItem(ItemList.FLAME_TREE_LEAVES_BLOCK_ITEM);
        registerBlockItem(ItemList.FLAME_TREE_SAPLING_BLOCK_ITEM);
    }
    private void registerItem(RegistryObject<Item>itemRegistryObject){
        this.withExistingParent(itemRegistryObject.getId().getPath(),mcLoc("generated"))
                .texture("layer0",new ResourceLocation(StartModApplication.MODID,"item/"+itemRegistryObject.getId().getPath()));
    }
    private void registerBlockItem(RegistryObject<Item>blockRegistryObject){
        this.getBuilder(blockRegistryObject.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/"+blockRegistryObject.getId().getPath())));
    }
}
