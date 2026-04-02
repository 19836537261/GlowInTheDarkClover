package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.FlameTreeSaplingBlock;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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
            this.add(BlockList.FLAME_TREE_LEAVES_BLOCK.get(),
                    build_leaves_multiple_drops(BlockList.FLAME_TREE_LEAVES_BLOCK.get(), BlockList.FLAME_TREE_SAPLING_BLOCK.get(), ItemList.FLAME_TREE_STICK_ITEM.get(),0.1f));
            this.dropSelf(BlockList.FLAME_TREE_SAPLING_BLOCK.get());
            this.dropSelf(BlockList.FLAME_TREE_TRIPPED_LOG_BLOCK.get());
            this.dropSelf(BlockList.FLAME_TREE_PLANKS_BLOCK.get());
            this.dropSelf(BlockList.FLAME_TREE_WOOD_BLOCK.get());
        }
        protected LootTable.Builder build_leaves_multiple_drops(Block leaves_block,Block sapling_block,Item stick,float perhaps){
            return createSilkTouchOrShearsDispatchTable(leaves_block, this.applyExplosionCondition(leaves_block, LootItem.lootTableItem(sapling_block))
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,perhaps)))
                    .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_SHEARS.or(HAS_SILK_TOUCH).invert())
                            .add(this.applyExplosionDecay(leaves_block, LootItem.lootTableItem(stick)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
                                            new float[]{0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f}))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BlockList.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }

}
