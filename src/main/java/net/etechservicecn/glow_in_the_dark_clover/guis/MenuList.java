package net.etechservicecn.glow_in_the_dark_clover.guis;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuList {
    public static final DeferredRegister<MenuType<?>>MENUS=DeferredRegister.create(ForgeRegistries.MENU_TYPES, StartModApplication.MODID);
    public static final RegistryObject<MenuType<ItemScepterMenu>>ITEM_SCEPTER_MENU=MENUS.register("item_scepter_menu",()-> IForgeMenuType.create(ItemScepterMenu::new));

    public static void register(IEventBus bus){
        MENUS.register(bus);
    }
}
