package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock.TeleportSchedulerBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportSchedulerBlock extends Block implements EntityBlock {
    public TeleportSchedulerBlock(Properties p_49795_) {
        super(p_49795_.randomTicks().lightLevel((p)->{return 10;}));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TeleportSchedulerBlockEntity(p_153215_,p_153216_);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        if (p_153214_==BlockEntityTypeList.TELEPORT_SCHEDULER_ENTITY_TYPE.get()){
            return (level, blockPos, blockState,blockEntity) ->{
                if (blockEntity instanceof TeleportSchedulerBlockEntity entity){
                    entity.calculate_counter();
                }
            };
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.translatable("block.teleport_scheduler_block.tooltip"));
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double x=blockPos.getX();
        double y=blockPos.getY();
        double z=blockPos.getZ();
        double theta=(double) 1/360;
        double summon_height= ModConfigEvent.teleport_scheduler_parameters.get(0);
        for (int i = 0; i < ModConfigEvent.teleport_scheduler_parameters.get(2); i++) {
            double angle=theta*randomSource.nextInt(-1080,1080);
            double radius=ModConfigEvent.teleport_scheduler_parameters.get(1);
            double relative_x=x+0.5d+Math.cos(angle)*radius;
            double relative_z=z+0.5d+Math.sin(angle)*radius;
            level.addParticle(ParticleTypes.HAPPY_VILLAGER,false,relative_x,y+summon_height,relative_z,0,0,0);
        }
    }
}
