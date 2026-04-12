package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StartModApplication.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(BlockList.FIRE_DIRT_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockList.FIRE_DIRT_BLOCK.get());

        this.tag(FireBurnWorldTags.Blocks.FLAME_TREE_FARMLAND).add(BlockList.FIRE_DIRT_BLOCK.get());
        this.add_building_block_tags(
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
    private void add_building_block_tags(RegistryObject<? extends Block>log_block,
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
        this.tag(BlockTags.LEAVES).add(leaves_block.get());
        this.tag(BlockTags.SAPLINGS).add(sapling_block.get());
        this.tag(BlockTags.LOGS)
                .add(log_block.get())
                .add(tripped_log_block.get());
        this.tag(BlockTags.STAIRS).add(stair_block.get());
        this.tag(BlockTags.SLABS).add(slab_block.get());
        this.tag(BlockTags.BUTTONS).add(button_block.get());
        this.tag(BlockTags.PRESSURE_PLATES).add(pressure_plate_block.get());
        this.tag(BlockTags.FENCES).add(fence_block.get());
        this.tag(BlockTags.FENCE_GATES).add(fence_gate_block.get());
        this.tag(BlockTags.WALLS).add(wall_block.get());
        this.tag(BlockTags.DOORS).add(door_block.get());
        this.tag(BlockTags.TRAPDOORS).add(trap_door_block.get());
        this.tag(BlockTags.PLANKS).add(source_block.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(log_block.get())
                .add(tripped_log_block.get())
                .add(wood_block.get())
                .add(stair_block.get())
                .add(slab_block.get())
                .add(pressure_plate_block.get())
                .add(fence_block.get())
                .add(fence_gate_block.get())
                .add(wall_block.get())
                .add(door_block.get())
                .add(trap_door_block.get())
                .add(source_block.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(log_block.get())
                .add(tripped_log_block.get())
                .add(wood_block.get())
                .add(stair_block.get())
                .add(slab_block.get())
                .add(pressure_plate_block.get())
                .add(fence_block.get())
                .add(fence_gate_block.get())
                .add(wall_block.get())
                .add(door_block.get())
                .add(trap_door_block.get())
                .add(source_block.get());
    }
}
