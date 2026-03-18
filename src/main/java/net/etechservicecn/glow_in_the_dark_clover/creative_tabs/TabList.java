package net.etechservicecn.glow_in_the_dark_clover.creative_tabs;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabList {
    public static final DeferredRegister<CreativeModeTab>TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StartModApplication.MODID);
    public static final RegistryObject<CreativeModeTab>WEAPON_TAB=TABS.register("weapon_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(ItemList.FIRE_LAUNCHER.get()))
            .title(Component.translatable("creative_tab.weapon")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(ItemList.FIRE_LAUNCHER.get());
            }))
            .build());
    public static final RegistryObject<CreativeModeTab>BLOCK_TAB=TABS.register("block_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(BlockList.TELEPORT_SCHEDULER_BLOCK.get()))
            .title(Component.translatable("creative_tab.block")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(BlockList.FIRE_DIRT_BLOCK.get());
                p_259752_.accept(BlockList.TELEPORT_SCHEDULER_BLOCK.get());
                p_259752_.accept(BlockList.FLAME_TREE_LOG_BLOCK.get());
                p_259752_.accept(BlockList.FLAME_TREE_LEAVES_BLOCK.get());
            })).build());
    public static final RegistryObject<CreativeModeTab>ITEM_TAB=TABS.register("item_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(ItemList.FIRE_WORLD_TOKEN.get()))
            .title(Component.translatable("creative_tab.item")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(ItemList.FIRE_WORLD_TOKEN.get());
            })).build());




    public static final RegistryObject<CreativeModeTab>DEV_TAB=TABS.register("dev_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(Blocks.STRUCTURE_BLOCK))
            .title(Component.literal("Dev Tab"))
            .displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(ItemList.TELEPORT_BLOCK_ITEM.get());
            })).build());
    public static void register(IEventBus bus){
        TABS.register(bus);
    }
}
