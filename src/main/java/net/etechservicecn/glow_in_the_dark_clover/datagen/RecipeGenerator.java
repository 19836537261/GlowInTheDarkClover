package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
}
