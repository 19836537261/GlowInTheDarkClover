package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockList.FLAME_TREE_LOG_BLOCK.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(BlockList.FLAME_TREE_LOG_BLOCK.get());
        this.tag(BlockTags.LOGS).add(BlockList.FLAME_TREE_LOG_BLOCK.get());
    }
}
