package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
        registerItem(flameTreePackageInfo.FLAME_TREE_STICK_ITEM);
        registerItem(ItemList.ITEM_SCEPTER_ITEM);
        registerBlockItem(ItemList.FIRE_DIRT_BLOCK_ITEM);
        registerBlockItem(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM);
        registerBlockItem(ItemList.TELEPORT_BLOCK_ITEM);
        registerSpawnEggItem(ItemList.FLAME_PIG_SPAWN_ITEM);
        registerSpawnEggItem(ItemList.FLAME_CHICKEN_SPAWN_ITEM);

        registerBuildingBlockItems(
                flameTreePackageInfo.FLAME_TREE_LOG_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_STRIPPED_LOG_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_BUTTON_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_PRESSURE_PLATE_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_FENCE_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_FENCE_GATE_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_WALL_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_DOOR_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_TRAP_DOOR_BLOCK_ITEM,
                flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK_ITEM);
    }
    private void registerItem(RegistryObject<Item>itemRegistryObject){
        this.withExistingParent(itemRegistryObject.getId().getPath(),mcLoc("generated"))
                .texture("layer0",new ResourceLocation(StartModApplication.MODID,"item/"+itemRegistryObject.getId().getPath()));
    }
    private void registerBlockItem(RegistryObject<Item>blockRegistryObject){
        this.getBuilder(blockRegistryObject.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/"+blockRegistryObject.getId().getPath())));
    }
    private void registerButtonBlock(RegistryObject<Item>blockItem,RegistryObject<Item>source_block){
        this.withExistingParent(blockItem.getId().getPath(),mcLoc("block/button_inventory"))
                .texture("texture",modLoc("block/"+source_block.getId().getPath()));
    }
    private void registerFenceBlock(RegistryObject<Item>blockItem,RegistryObject<Item>source_block){
        this.withExistingParent(blockItem.getId().getPath(),mcLoc("block/fence_inventory"))
                .texture("texture",modLoc("block/"+source_block.getId().getPath()));
    }
    private void registerWallBlock(RegistryObject<Item>blockItem,RegistryObject<Item>source_block){
        this.withExistingParent(blockItem.getId().getPath(),mcLoc("block/wall_inventory"))
                .texture("wall",modLoc("block/"+source_block.getId().getPath()));
    }
    private void registerTrapDoorBlock(RegistryObject<Item>blockItem){
        getBuilder(blockItem.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/"+blockItem.getId().getPath()+"_bottom")));
    }
    private void registerSpawnEggItem(RegistryObject<Item>spawn_item){
        this.withExistingParent(spawn_item.getId().getPath(),mcLoc("item/template_spawn_egg"));
    }
    private void registerBuildingBlockItems(RegistryObject<Item>log_block,
                                            RegistryObject<Item>leaves_block,
                                            RegistryObject<Item>sapling_block,
                                            RegistryObject<Item>tripped_log_block,
                                            RegistryObject<Item>wood_block,
                                            RegistryObject<Item>stair_block,
                                            RegistryObject<Item>slab_block,
                                            RegistryObject<Item>button_block,
                                            RegistryObject<Item>pressure_plate_block,
                                            RegistryObject<Item>fence_block,
                                            RegistryObject<Item>fence_gate_block,
                                            RegistryObject<Item>wall_block,
                                            RegistryObject<Item>door_block,
                                            RegistryObject<Item>trap_door_block,
                                            RegistryObject<Item>source_block){
        registerBlockItem(log_block);
        registerBlockItem(leaves_block);
        registerBlockItem(sapling_block);
        registerBlockItem(tripped_log_block);
        registerBlockItem(wood_block);
        registerBlockItem(stair_block);
        registerBlockItem(slab_block);
        registerButtonBlock(button_block,source_block);
        registerBlockItem(pressure_plate_block);
        registerFenceBlock(fence_block,source_block);
        registerBlockItem(fence_gate_block);
        registerWallBlock(wall_block,source_block);
        registerItem(door_block);
        registerTrapDoorBlock(trap_door_block);
        registerBlockItem(source_block);

    }
}
