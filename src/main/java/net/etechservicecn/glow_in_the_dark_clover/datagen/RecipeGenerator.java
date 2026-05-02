package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
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
        this.build_from_planks_to_fence(plank_block,stick_item,fence_block,consumer);
        this.build_from_planks_to_fence_door(plank_block,stick_item,fence_gate_block,consumer);
        this.build_from_planks_to_pressure_plate(plank_block,pressure_plate_block,consumer);
        this.build_from_planks_to_slab(plank_block,slab_block,consumer);
        this.build_from_planks_to_stairs(plank_block,stair_block,consumer);
        this.build_from_planks_to_trap_door(plank_block,trap_door_block,consumer);
        this.build_from_planks_to_wall(plank_block,wall_block,consumer);
        this.build_from_stick_to_weapon(stick_item,Items.WOODEN_SWORD,Items.WOODEN_AXE,Items.WOODEN_HOE,Items.WOODEN_SHOVEL,Items.WOODEN_PICKAXE,FireBurnWorldTags.Items.BUILD_WOODEN_WEAPON_MATERIALS,consumer);
        this.build_from_stick_to_weapon(stick_item,Items.STONE_SWORD,Items.STONE_AXE,Items.STONE_HOE,Items.STONE_SHOVEL,Items.STONE_PICKAXE,FireBurnWorldTags.Items.BUILD_STONE_WEAPON_MATERIALS,consumer);
        this.build_from_stick_to_weapon(stick_item,Items.DIAMOND_SWORD,Items.DIAMOND_AXE,Items.DIAMOND_HOE,Items.DIAMOND_SHOVEL,Items.DIAMOND_PICKAXE,FireBurnWorldTags.Items.BUILD_DIAMOND_WEAPON_MATERIALS,consumer);
        this.build_from_stick_to_weapon(stick_item,Items.GOLDEN_SWORD,Items.GOLDEN_AXE,Items.GOLDEN_HOE,Items.GOLDEN_SHOVEL,Items.GOLDEN_PICKAXE,FireBurnWorldTags.Items.BUILD_GOLDEN_WEAPON_MATERIALS,consumer);
        this.build_from_stick_to_weapon(stick_item,Items.IRON_SWORD,Items.IRON_AXE,Items.IRON_HOE,Items.IRON_SHOVEL,Items.IRON_PICKAXE,FireBurnWorldTags.Items.BUILD_IRON_WEAPON_MATERIALS,consumer);
        this.build_from_stick_to_misc(FireBurnWorldTags.Items.STICK_MATERIALS,consumer);
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
                .pattern(" # ")
                .pattern(" # ")
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
    private void build_from_planks_to_fence(Block plank_block,Item stick_item,Block fence_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,fence_block.asItem(),3)
                .pattern("S#S")
                .pattern("S#S")
                .pattern("   ")
                .define('#',stick_item)
                .define('S',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .save(consumer);
    }
    private void build_from_planks_to_fence_door(Block plank_block,Item stick_item,Block fence_door_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,fence_door_block.asItem(),1)
                .pattern("S#S")
                .pattern("S#S")
                .pattern("   ")
                .define('#',plank_block.asItem())
                .define('S',stick_item)
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .save(consumer);
    }
    private void build_from_planks_to_pressure_plate(Block plank_block,Block pressure_plate_block,Consumer<FinishedRecipe>consumer){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS,pressure_plate_block.asItem(),1)
                .requires(plank_block.asItem(),2)
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_slab(Block plank_block,Block slab_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,slab_block.asItem(),6)
                .pattern("   ")
                .pattern("   ")
                .pattern("###")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_stairs(Block plank_block,Block stair_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,stair_block.asItem(),4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_trap_door(Block plank_block,Block trap_door_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,trap_door_block.asItem(),2)
                .pattern("   ")
                .pattern("## ")
                .pattern("## ")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_planks_to_wall(Block plank_block,Block wall_block,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,wall_block.asItem(),6)
                .pattern("   ")
                .pattern("###")
                .pattern("###")
                .define('#',plank_block.asItem())
                .unlockedBy(getHasName(plank_block.asItem()),has(plank_block.asItem()))
                .save(consumer);
    }
    private void build_from_stick_to_weapon(Item stick_item, Item sword, Item axe, Item hoe, Item shovel,Item pickaxe, TagKey<Item>materials, Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,sword)
                .pattern(" 1 ")
                .pattern(" 1 ")
                .pattern(" # ")
                .define('#',stick_item)
                .define('1', materials)
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .unlockedBy(materials.location().getPath(),has(materials))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,axe)
                .pattern("11 ")
                .pattern("1# ")
                .pattern(" # ")
                .define('#',stick_item)
                .define('1', materials)
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .unlockedBy(materials.location().getPath(),has(materials))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,hoe)
                .pattern("11 ")
                .pattern(" # ")
                .pattern(" # ")
                .define('#',stick_item)
                .define('1', materials)
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .unlockedBy(materials.location().getPath(),has(materials))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,shovel)
                .pattern(" 1 ")
                .pattern(" # ")
                .pattern(" # ")
                .define('#',stick_item)
                .define('1', materials)
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .unlockedBy(materials.location().getPath(),has(materials))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,pickaxe)
                .pattern("111")
                .pattern(" # ")
                .pattern(" # ")
                .define('#',stick_item)
                .define('1', materials)
                .unlockedBy(getHasName(stick_item),has(stick_item))
                .unlockedBy(materials.location().getPath(),has(materials))
                .save(consumer);
    }
    private void build_from_stick_to_misc(TagKey<Item>stick_tag,Consumer<FinishedRecipe>consumer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,Items.BOW)
                .pattern(" #T")
                .pattern("# T")
                .pattern(" #T")
                .define('#',stick_tag)
                .define('T', Items.STRING)
                .unlockedBy(stick_tag.location().getPath(),has(stick_tag))
                .unlockedBy(getHasName(Items.STRING),has(Items.STRING))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,Items.ARROW)
                .pattern("#")
                .pattern("T")
                .pattern("F")
                .define('#',Items.FLINT)
                .define('T', stick_tag)
                .define('F',Items.FEATHER)
                .unlockedBy(getHasName(Items.FLINT),has(Items.FLINT))
                .unlockedBy(stick_tag.location().getPath(),has(stick_tag))
                .unlockedBy(getHasName(Items.FEATHER),has(Items.FEATHER))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,Items.FISHING_ROD)
                .pattern("  S")
                .pattern(" ST")
                .pattern("S T")
                .define('S', stick_tag)
                .define('T',Items.STRING)
                .unlockedBy(stick_tag.location().getPath(),has(stick_tag))
                .unlockedBy(getHasName(Items.STRING),has(Items.STRING))
                .save(consumer);
    }
}
