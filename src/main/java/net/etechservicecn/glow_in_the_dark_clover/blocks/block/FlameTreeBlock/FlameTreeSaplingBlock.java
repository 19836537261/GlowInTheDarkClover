package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.etechservicecn.glow_in_the_dark_clover.world.ConfigurationFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class FlameTreeSaplingBlock extends SaplingBlock{
    public static final IntegerProperty GROW_AGE=IntegerProperty.create("age",0,3);
    public FlameTreeSaplingBlock( Properties p_55979_) {
        super(new AbstractTreeGrower() {
            @Nullable
            @Override
            protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
                return ConfigurationFeatures.FLAME_TREE_FEATURE;
            }
        }, p_55979_.randomTicks());
        this.registerDefaultState(this.getStateDefinition().any().setValue(GROW_AGE,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56001_) {
        super.createBlockStateDefinition(p_56001_);
        p_56001_.add(GROW_AGE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return p_49921_.getValue(GROW_AGE)<3;
    }
    private int counter=2;
    private int base_height=4;
    private int rand_a=0;
    private int rand_b=1;
    private int radius=3;
    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (counter==0){
            int current_age=blockState.getValue(GROW_AGE);
            if (current_age>=2){
                BlockPos running_pos=blockPos;
                int real_height=base_height+randomSource.nextInt(rand_a,rand_a+1)+randomSource.nextInt(rand_b,rand_b+1);
                for (int i = 0; i < real_height; i++) {
                    serverLevel.setBlock(running_pos.above(i), BlockList.FLAME_TREE_LOG_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                }
                BlockPos center=running_pos.above(real_height-1);
                fill_leaves(serverLevel,center,radius);
                int random_pattern=randomSource.nextInt(0,10);
                if (random_pattern>5){
                    fill_leaves(serverLevel,center.below(1),radius-1);
                    fill_leaves(serverLevel,center.above(1),radius-1);
                    fill_leaves(serverLevel,center.above(2),radius-2);
                } else if (random_pattern<=5) {
                    fill_leaves(serverLevel,center.below(1),radius);
                    fill_leaves(serverLevel,center.above(1),radius);
                    fill_leaves(serverLevel,center.above(2),radius-1);
                }
                counter=0;
            }else {
                serverLevel.setBlock(blockPos,blockState.setValue(GROW_AGE,current_age+1),Block.UPDATE_ALL);
            }
            counter=2;
        }else {
            counter--;
        }
    }
    private void fill_leaves(Level level, BlockPos fill_pos,int radius) {
        for (int x = -radius+1; x < radius; x++) {
            for (int z = -radius+1; z < radius; z++) {
                BlockPos blockPos=fill_pos.offset(x,0,z);
                if (!level.getBlockState(blockPos).is(BlockList.FLAME_TREE_LOG_BLOCK.get())){
                    level.setBlock(blockPos,BlockList.FLAME_TREE_LEAVES_BLOCK.get().defaultBlockState(),Block.UPDATE_ALL);
                }
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel p_221996_, RandomSource p_221997_, BlockPos p_221998_, BlockState p_221999_) {
        super.performBonemeal(p_221996_, p_221997_, p_221998_, p_221999_);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256124_, BlockPos p_55992_, BlockState p_55993_, boolean p_55994_) {
        return false;
    }

    @Override
    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
        BlockPos blockpos = p_51030_.below();
        if (p_51028_.getBlock() == this)
            return p_51029_.getBlockState(blockpos).is(FireBurnWorldTags.Blocks.FLAME_TREE_FARMLAND);
        return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(FireBurnWorldTags.Blocks.FLAME_TREE_FARMLAND);
    }
    private int consume=3;
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()){
            ItemStack itemStack=player.getMainHandItem();
            if (itemStack.is(Items.FIRE_CHARGE)&&blockState.is(this)){
                int current_age=blockState.getValue(GROW_AGE);
                if (consume<0){
                    if (current_age>=2){
                        BlockPos running_pos=blockPos;
                        int real_height=base_height+level.random.nextInt(rand_a,rand_a+1)+level.random.nextInt(rand_b,rand_b+1);
                        for (int i = 0; i < real_height; i++) {
                            level.setBlock(running_pos.above(i), BlockList.FLAME_TREE_LOG_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                        }
                        BlockPos center=running_pos.above(real_height-1);
                        fill_leaves(level,center,radius);
                        int random_pattern=level.random.nextInt(0,10);
                        if (random_pattern>5){
                            fill_leaves(level,center.below(1),radius-1);
                            fill_leaves(level,center.above(1),radius-1);
                            fill_leaves(level,center.above(2),radius-2);
                        } else if (random_pattern<=5) {
                            fill_leaves(level,center.below(1),radius);
                            fill_leaves(level,center.above(1),radius);
                            fill_leaves(level,center.above(2),radius-1);
                        }
                        consume=level.random.nextInt(3,7);
                    }else {
                        level.setBlock(blockPos,blockState.setValue(GROW_AGE,current_age+1),Block.UPDATE_ALL);
                    }
                }else {
                    consume--;
                }
                if (!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.playSound(null,blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,1.0f,1.0f);
            }
        }
        if (level.isClientSide()){
            ItemStack itemStack=player.getMainHandItem();
            if (itemStack.is(Items.FIRE_CHARGE)&&blockState.is(this)){
                Integer particles= ModConfigEvent.flame_tree_sapling_block_particles;
                Double speed=ModConfigEvent.flame_tree_sapling_block_particle_speed;
                for (int i = 0; i < particles; i++) {
                    level.addParticle(ParticleTypes.FLAME,blockPos.getX()+level.random.nextFloat(),blockPos.getY()+0.5,blockPos.getZ()+level.random.nextFloat(),0,speed,0);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
