package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            this.buildingBlockDrops(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_TRIPPED_LOG_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_BUTTON_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_PRESSURE_PLATE_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_FENCE_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_FENCE_GATE_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_WALL_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_DOOR_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_TRAP_DOOR_BLOCK.get(),
                    flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK.get(),flameTreePackageInfo.FLAME_TREE_STICK_ITEM.get(),0.3f);
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
        private void buildingBlockDrops(Block log_block,
                                        Block leaves_block,
                                        Block sapling_block,
                                        Block tripped_log_block,
                                        Block wood_block,
                                        Block stair_block,
                                        Block slab_block,
                                        Block button_block,
                                        Block pressure_plate_block,
                                        Block fence_block,
                                        Block fence_gate_block,
                                        Block wall_block,
                                        Block door_block,
                                        Block trap_door_block,
                                        Block source_block,Item sapling_drop_item,float drop_perhaps){
            this.dropSelf(log_block);
            this.add(leaves_block,
                    build_leaves_multiple_drops(leaves_block,
                            sapling_block,
                            sapling_drop_item,drop_perhaps));
            this.dropSelf(sapling_block);
            this.dropSelf(tripped_log_block);
            this.dropSelf(source_block);
            this.dropSelf(wood_block);
            this.dropSelf(stair_block);
            this.dropSelf(slab_block);
            dropSelf(button_block);
            dropSelf(pressure_plate_block);
            dropSelf(fence_block);
            dropSelf(fence_gate_block);
            dropSelf(wall_block);
            dropSelf(door_block);
            dropSelf(trap_door_block);
        }
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Stream.concat(
                    BlockList.BLOCKS.getEntries().stream(),
                    flameTreePackageInfo.BLOCKS.getEntries().stream()
            ).map(RegistryObject::get).filter(Objects::nonNull).toList();
        }
    }

}
