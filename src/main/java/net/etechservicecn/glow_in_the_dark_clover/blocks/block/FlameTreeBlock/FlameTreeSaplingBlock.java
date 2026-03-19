package net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.FlameTreeBlock.FlameTreeBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.etechservicecn.glow_in_the_dark_clover.world.ConfigurationFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.Nullable;

public class FlameTreeSaplingBlock extends SaplingBlock implements EntityBlock{


    public FlameTreeSaplingBlock( Properties p_55979_) {
        super(new AbstractTreeGrower() {
            @Nullable
            @Override
            protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
                return ConfigurationFeatures.FLAME_TREE_FEATURE;
            }
        }, p_55979_);
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new FlameTreeBlockEntity(p_153215_,p_153216_);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()){
            ItemStack itemStack=player.getMainHandItem();
            if (itemStack.is(Items.FIRE_CHARGE)){
                this.is_grow=true;
                this.blockPos=blockPos;
                if (!player.isCreative()){
                    itemStack.shrink(1);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
    private boolean is_grow=false;
    private BlockPos blockPos=new BlockPos(0,0,0);
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        if (p_153214_==BlockEntityTypeList.FLAME_TREE_BLOCK_ENTITY_TYPE.get()){
            return ((level, blockPos, blockState, blockEntity) -> {
                if (blockEntity instanceof FlameTreeBlockEntity flameTreeBlockEntity){
                    flameTreeBlockEntity.set_activate(this.is_grow);
                    flameTreeBlockEntity.setBlockPos(this.blockPos);
                    flameTreeBlockEntity.animateTick(blockState,level,blockPos,level.random);
                }
            });
        }
        return EntityBlock.super.getTicker(p_153212_, p_153213_, p_153214_);
    }
}
