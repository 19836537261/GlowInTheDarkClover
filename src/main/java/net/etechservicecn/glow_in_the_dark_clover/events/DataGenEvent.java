package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.datagen.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = StartModApplication.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {
    @SubscribeEvent
    public static void summonDat(GatherDataEvent event){
        DataGenerator dataGenerator=event.getGenerator();
        PackOutput packOutput=dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        dataGenerator.addProvider(event.includeClient(),new ItemModelDataGenerator(packOutput,existingFileHelper));
        dataGenerator.addProvider(event.includeServer(),new LangGenerator(packOutput));
        dataGenerator.addProvider(event.includeClient(),new BlockStateDataGenerator(packOutput,existingFileHelper));
        CompletableFuture<HolderLookup.Provider>completableFuture=event.getLookupProvider();
        BlockTagGenerator blockTagGenerator=dataGenerator.addProvider(event.includeServer(),new BlockTagGenerator(packOutput,completableFuture,existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new ItemTagGenerator(packOutput,completableFuture,blockTagGenerator.contentsGetter(),existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new LootTableGenerator(packOutput));
        dataGenerator.addProvider(event.includeClient(),new WorldDataGenerator(packOutput,completableFuture));
        dataGenerator.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput,completableFuture,existingFileHelper, List.of(new ModAdvancementGenerator())));
        dataGenerator.addProvider(event.includeServer(),new RecipeGenerator(packOutput));
    }
}
