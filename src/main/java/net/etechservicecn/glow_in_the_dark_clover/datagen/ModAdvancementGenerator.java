package net.etechservicecn.glow_in_the_dark_clover.datagen;

import com.google.gson.JsonObject;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.etechservicecn.glow_in_the_dark_clover.triggers.TeleportTrigger;
import net.etechservicecn.glow_in_the_dark_clover.world.dimensions.FireBurnWorld;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> advancementConsumer, ExistingFileHelper existingFileHelper) {
        Advancement root=Advancement.Builder.advancement()
                .display(new ItemStack(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM.get()),
                        Component.translatable("advancement.root.title"),
                        Component.translatable("advancement.root.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_scheduler_block_side.png"),
                        FrameType.GOAL,true,true,false)
                .addCriterion("get_teleporter",InventoryChangeTrigger.TriggerInstance.hasItems(ItemList.TELEPORT_SCHEDULER_BLOCK_ITEM.get()))
                .save(advancementConsumer,"root");

        Advancement fire_world_advancement_goals=Advancement.Builder.advancement()
                .display(new ItemStack(ItemList.FIRE_WORLD_TOKEN.get()),
                        Component.translatable("advancement.fire_world_root.title"),
                        Component.translatable("advancement.fire_world_root.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/item/fire_world_token.png"),
                        FrameType.TASK,true,true,false)
                .addCriterion("get_fire_world_token", InventoryChangeTrigger.TriggerInstance.hasItems(ItemList.FIRE_WORLD_TOKEN.get()))
                .parent(root)
                .save(advancementConsumer,"fire_world_root");

        Advancement activate_fire_world_portal_goal=Advancement.Builder.advancement()
                .display(new ItemStack(BlockList.TELEPORT_BLOCK.get()),
                        Component.translatable("advancement.fire_world_activate_portal.title"),
                        Component.translatable("advancement.fire_world_activate_portal.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/block/teleport_block.png"),
                        FrameType.CHALLENGE,true,true,false)
                .parent(fire_world_advancement_goals)
                .addCriterion("activate_fire_world_portal", TeleportTrigger.instance())
                .save(advancementConsumer,"fire_world_activate_portal");

        Advancement fire_world_entrance_goal=Advancement.Builder.advancement()
                .display(new ItemStack(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get()),
                        Component.translatable("advancement.fire_world_entrance.title"),
                        Component.translatable("advancement.fire_world_entrance.desc"),
                        new ResourceLocation(StartModApplication.MODID,"textures/block/flame_tree_log_side.png"),
                        FrameType.GOAL,true,true,false)
                .addCriterion("entered_fire_world", ChangeDimensionTrigger.TriggerInstance.changedDimension(Level.OVERWORLD, FireBurnWorld.FIRE_BURN_LEVEL))
                .parent(activate_fire_world_portal_goal)
                .save(advancementConsumer,"fire_world_entrance_goal");
    }
}
