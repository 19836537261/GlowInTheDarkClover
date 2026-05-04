package net.etechservicecn.glow_in_the_dark_clover.tags;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class FireBurnWorldTags {
    public static class Blocks{
        public static final TagKey<Block>CAN_INFINITE_BURN_BLOCK=create("can_infinite_burn_block");

        public static final TagKey<Block>FLAME_TREE_FARMLAND=create("flame_tree_farmland");
        public static TagKey<Block>create(String name){
            return BlockTags.create(new ResourceLocation(name));
        }
    }
    public static class Items{
        public static final TagKey<Item>BUILD_WOODEN_WEAPON_MATERIALS=create("build_wooden_weapon_materials");
        public static final TagKey<Item>BUILD_STONE_WEAPON_MATERIALS=create("build_stone_weapon_materials");
        public static final TagKey<Item>BUILD_DIAMOND_WEAPON_MATERIALS=create("build_diamond_weapon_materials");
        public static final TagKey<Item>BUILD_GOLDEN_WEAPON_MATERIALS=create("build_golden_weapon_materials");
        public static final TagKey<Item>BUILD_IRON_WEAPON_MATERIALS=create("build_iron_weapon_materials");
        public static final TagKey<Item>BUILD_CRAFTING_TABLE_MATERIALS=create("build_crafting_table_materials");
        public static final TagKey<Item>STICK_MATERIALS=create("stick_materials");
        public static TagKey<Item>create(String name){
            return ItemTags.create(new ResourceLocation(name));
        }
    }
}
