package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LootTableGenerator extends LootTableProvider {
    public LootTableGenerator(PackOutput packOutput) {
        super(packOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)
        ));

    }
    public static class BlockLootTables extends BlockLootSubProvider{

        public BlockLootTables() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            this.dropSelf(BlockList.FIRE_DIRT_BLOCK.get());
            this.dropSelf(BlockList.TELEPORT_SCHEDULER_BLOCK.get());
            this.dropSelf(BlockList.FLAME_TREE_LOG_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }

}
