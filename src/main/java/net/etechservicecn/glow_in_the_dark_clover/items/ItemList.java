package net.etechservicecn.glow_in_the_dark_clover.items;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.EntityList;
import net.etechservicecn.glow_in_the_dark_clover.items.item.FireLauncherItem;
import net.etechservicecn.glow_in_the_dark_clover.items.item.FireWorldTokenItem;
import net.etechservicecn.glow_in_the_dark_clover.items.item.FlameTreeStickItem;
import net.etechservicecn.glow_in_the_dark_clover.items.item.ItemScepterItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemList {
    public static final DeferredRegister<Item>ITEMS=DeferredRegister.create(Registries.ITEM, StartModApplication.MODID);
    public static final RegistryObject<Item>FIRE_LAUNCHER=ITEMS.register("fire_launcher",()->new FireLauncherItem(new Item.Properties()));
    public static final RegistryObject<Item>FIRE_DIRT_BLOCK_ITEM=ITEMS.register("fire_dirt_block",()->new BlockItem(BlockList.FIRE_DIRT_BLOCK.get(),new Item.Properties()));
    public static final RegistryObject<Item>FIRE_WORLD_TOKEN=ITEMS.register("fire_world_token",()->new FireWorldTokenItem(new Item.Properties()));
    public static final RegistryObject<Item>TELEPORT_SCHEDULER_BLOCK_ITEM=ITEMS.register("teleport_scheduler_block",()->new BlockItem(BlockList.TELEPORT_SCHEDULER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item>TELEPORT_BLOCK_ITEM=ITEMS.register("teleport_block",()->new BlockItem(BlockList.TELEPORT_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item>ITEM_SCEPTER_ITEM=ITEMS.register("item_scepter",()->new ItemScepterItem(new Item.Properties()));
    public static final RegistryObject<Item>FLAME_PIG_SPAWN_ITEM=ITEMS.register("flame_pig_spawn_egg",()->new ForgeSpawnEggItem(()->EntityList.FLAME_PIG_ENTITY.get(),0xEC9090,0xE91B1B,new Item.Properties()));
    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
