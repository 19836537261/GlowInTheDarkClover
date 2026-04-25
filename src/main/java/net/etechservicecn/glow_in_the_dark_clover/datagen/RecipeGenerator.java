package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        this.build_special_thing(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM.get(),p_251297_);
        this.build_fire_world_token(ItemList.FIRE_WORLD_TOKEN.get(),p_251297_);
        this.build_wood_series(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_TRIPPED_LOG_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_BUTTON_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_PRESSURE_PLATE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_FENCE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_FENCE_GATE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_WALL_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_DOOR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_TRAP_DOOR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK.get(),flameTreePackageInfo.FLAME_TREE_STICK_ITEM.get(),p_251297_);
    }
    private void build_special_thing(Item item,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item)
                .pattern("565")
                .pattern("343")
                .pattern("121")
                .define('1', Blocks.OBSIDIAN)
                .define('2',Blocks.ENCHANTING_TABLE)
                .define('3', Items.BLAZE_ROD)
                .define('4',Items.NETHER_STAR)
                .define('5',Items.ENDER_EYE)
                .define('6',Items.DRAGON_BREATH)
                .unlockedBy(getHasName(Blocks.OBSIDIAN),has(Blocks.OBSIDIAN))
                .unlockedBy(getHasName(Blocks.ENCHANTING_TABLE),has(Blocks.ENCHANTING_TABLE))
                .unlockedBy(getHasName(Items.BLAZE_ROD),has(Items.BLAZE_ROD))
                .unlockedBy(getHasName(Items.NETHER_STAR),has(Items.NETHER_STAR))
                .unlockedBy(getHasName(Items.ENDER_EYE),has(Items.ENDER_EYE))
                .unlockedBy(getHasName(Items.DRAGON_BREATH),has(Items.DRAGON_BREATH))
                .save(consumer);
    }
    private void build_fire_world_token(Item item,Consumer<FinishedRecipe>consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,item)
                .requires(Items.BLAZE_POWDER,3)
                .requires(Items.PAPER)
                .unlockedBy(getHasName(Items.BLAZE_POWDER),has(Items.BLAZE_POWDER))
                .unlockedBy(getHasName(Items.PAPER),has(Items.PAPER))
                .save(consumer);
    }
    private void build_wood_series(Block log_block,
                                   Block leaves_block,
                                   Block sapling_block,
                                   Block tripped_log_block,
                                   Block wood_block,
                                   Block stair_block,
                                   Block slab_block,
                                   Block button_block,
                                   Block pressure_plate_block,
                                   Block fence_block,
                                   Block fence_gate_block,
                                   Block wall_block,
                                   Block door_block,
                                   Block trap_door_block,
                                   Block plank_block,Item stick_item,Consumer<FinishedRecipe>consumer){
        this.build_from_logs_to_plank(log_block,plank_block,consumer);
        this.build_from_planks_to_sticks(plank_block,stick_item,consumer);
        this.build_from_planks_to_crafting_table(plank_block,consumer);
        this.build_from_planks_to_button(plank_block,button_block,consumer);
        this.build_from_planks_to_door(plank_block,door_block,consumer);
    }
    private void build_from_logs_to_plank(Block log_block,Block plank_block,Consumer<FinishedRecipe>consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,plank_block.asItem(),4)
                .requires(log_block.asItem(),1)
                .unlockedBy(getHasName(log_block.asItem()),has(log_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_crafting_table(Block plank_block,Consumer<FinishedRecipe>consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.CRAFTING_TABLE,1)
                .requires(plank_block.asItem(),4)
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }

    private void build_from_planks_to_sticks(Block plank_block,Item stick_item,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,stick_item,4)
                .pattern("   ")
                .pattern("## ")
                .pattern("   ")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_button(Block plank_block,Block button_block,Consumer<FinishedRecipe>consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,button_block.asItem())
                .requires(plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_door(Block plank_block,Block door_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,door_block.asItem(),3)
                .pattern("## ")
                .pattern("## ")
                .pattern("## ")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
}
