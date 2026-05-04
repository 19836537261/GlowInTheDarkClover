package net.etechservicecn.glow_in_the_dark_clover;

import com.mojang.logging.LogUtils;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.etechservicecn.glow_in_the_dark_clover.creative_tabs.TabList;
import net.etechservicecn.glow_in_the_dark_clover.guis.MenuList;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterScreen;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.etechservicecn.glow_in_the_dark_clover.world.chunk_gens.ChunkList;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.FoliagePlacerList;
import net.etechservicecn.glow_in_the_dark_clover.world.trees.TrunkPlacerList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(StartModApplication.MODID)
public class StartModApplication
{
    public static final String MODID = "glow_in_the_dark_clover";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StartModApplication()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ItemList.register(modEventBus);
        TabList.register(modEventBus);
        BlockList.register(modEventBus);
        ChunkList.register(modEventBus);
        BlockEntityTypeList.register(modEventBus);
        TrunkPlacerList.register(modEventBus);
        FoliagePlacerList.register(modEventBus);
        flameTreePackageInfo.register(modEventBus);
        MenuList.register(modEventBus);
        //BiomeList.register();
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigEvent.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
//
//        if (Config.logDirtBlock)
//            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
//
//        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
//
//        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
