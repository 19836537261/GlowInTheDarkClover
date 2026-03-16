package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> advancementConsumer, ExistingFileHelper existingFileHelper) {
        Advancement root=Advancement.Builder.advancement()
                .display(new ItemStack(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM.get()),
                        Component.translatable("advancement.fire_world_root.title"),
                        Component.translatable("advancement.fire_world_root.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block_side.png"),
                        FrameType.GOAL,true,true,false)
                .addCriterion("get_teleporter",InventoryChangeTrigger.TriggerInstance.hasItems(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM.get()))
                .save(advancementConsumer,"root");

        Advancement fire_world_advancement_goals=Advancement.Builder.advancement()
                .display(new ItemStack(ItemList.FIRE_WORLD_TOKEN.get()),
                        Component.translatable("advancement.root.title"),
                        Component.translatable("advancement.root.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/block/fire_dirt_block.png"),
                        FrameType.TASK,true,true,false)
                .addCriterion("get_fire_world_token", InventoryChangeTrigger.TriggerInstance.hasItems(ItemList.FIRE_WORLD_TOKEN.get()))
                .parent(root)
                .save(advancementConsumer,"fire_world_root");
//        Advancement fire_world_entrance_advancement_goal=Advancement.Builder.advancement()
//                .display(new ItemStack(BlockList.))
    }
}
