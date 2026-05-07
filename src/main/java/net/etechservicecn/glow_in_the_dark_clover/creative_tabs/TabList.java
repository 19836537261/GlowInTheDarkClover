package net.etechservicecn.glow_in_the_dark_clover.creative_tabs;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
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
                p_259752_.accept(ItemList.ITEM_SCEPTER_ITEM.get());
            }))
            .build());
    public static final RegistryObject<CreativeModeTab>BLOCK_TAB=TABS.register("block_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(BlockList.TELEPORT_SCHEDULER_BLOCK.get()))
            .title(Component.translatable("creative_tab.block")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(BlockList.FIRE_DIRT_BLOCK.get());
                p_259752_.accept(BlockList.TELEPORT_SCHEDULER_BLOCK.get());
                flameTreePackageInfo.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(p_259752_::accept);
            })).build());
    public static final RegistryObject<CreativeModeTab>ITEM_TAB=TABS.register("item_tab",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(ItemList.FIRE_WORLD_TOKEN.get()))
            .title(Component.translatable("creative_tab.item")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(ItemList.FIRE_WORLD_TOKEN.get());
                p_259752_.accept(flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK_ITEM.get());
                p_259752_.accept(flameTreePackageInfo.FLAME_TREE_STICK_ITEM.get());
            })).build());
    public static final RegistryObject<CreativeModeTab>SPAWN_EGG_TAB=TABS.register("spawn_egg",()-> CreativeModeTab.builder()
            .icon(()->new ItemStack(ItemList.FLAME_PIG_SPAWN_ITEM.get()))
            .title(Component.translatable("creative_tab.spawn_egg")).displayItems(((p_270258_, p_259752_) -> {
                p_259752_.accept(ItemList.FLAME_PIG_SPAWN_ITEM.get());
            }))
            .build());




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
