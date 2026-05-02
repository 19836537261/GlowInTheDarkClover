package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {


    public ItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, StartModApplication.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(ItemTags.HOES)
                .add(BlockList.FIRE_DIRT_BLOCK.get().asItem());
        this.tag(ItemTags.LEAVES)
                .add(flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get().asItem());
        addBuildingTags(flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK.get(),flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK.get());
        this.tag(FireBurnWorldTags.Items.BUILD_WOODEN_WEAPON_MATERIALS)
                .addTags(ItemTags.PLANKS);
        this.tag(FireBurnWorldTags.Items.BUILD_STONE_WEAPON_MATERIALS)
                .add(Items.STONE, Blocks.BLACKSTONE.asItem(),Blocks.COBBLED_DEEPSLATE.asItem());
        this.tag(FireBurnWorldTags.Items.BUILD_DIAMOND_WEAPON_MATERIALS)
                .add(Items.DIAMOND);
        this.tag(FireBurnWorldTags.Items.BUILD_GOLDEN_WEAPON_MATERIALS)
                .add(Items.GOLD_INGOT);
        this.tag(FireBurnWorldTags.Items.BUILD_IRON_WEAPON_MATERIALS)
                .add(Items.IRON_INGOT);
        this.tag(FireBurnWorldTags.Items.STICK_MATERIALS)
                .add(flameTreePackageInfo.FLAME_TREE_STICK_ITEM.get(),Items.STICK);
    }
    private void addBuildingTags(Block stair_block,Block slab_block){
        this.tag(ItemTags.STAIRS).add(stair_block.asItem());
        this.tag(ItemTags.SLABS).add(slab_block.asItem());
    }
}
