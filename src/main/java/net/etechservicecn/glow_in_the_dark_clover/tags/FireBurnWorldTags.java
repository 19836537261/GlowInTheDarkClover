package net.etechservicecn.glow_in_the_dark_clover.tags;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FireBurnWorldTags {
    public static class Blocks{
        public static final TagKey<Block>CAN_INFINITE_BURN_BLOCK=create("can_infinite_burn_block");
        public static final TagKey<Block>FLAME_TREE_FARMLAND=create("flame_tree_farmland");
        public static TagKey<Block>create(String name){
            return BlockTags.create(new ResourceLocation(name));
        }
    }
}
