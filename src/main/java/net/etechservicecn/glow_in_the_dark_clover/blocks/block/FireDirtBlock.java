package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireDirtBlock extends Block {
    public FireDirtBlock(Properties p_49795_) {
        super(p_49795_.randomTicks().strength(0.6F).requiresCorrectToolForDrops());
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.translatable("block.fire_dirt_block.tooltip"));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        double x=blockPos.getX();
        double y=blockPos.getY();
        double z=blockPos.getZ();
        int effect_height= ModConfigEvent.fire_dirt_block_effect_max_height;
        double effect_speed= ModConfigEvent.fire_dirt_block_effect_speed;
        level.addParticle(ParticleTypes.SMOKE,x,y+randomSource.nextDouble()*effect_height,z,0.0d,effect_speed,0.0d);
        if (randomSource.nextInt(0,10)>= ModConfigEvent.fire_dirt_block_flame_summon_chance){
            level.addParticle(ParticleTypes.FLAME,x,y+randomSource.nextDouble()*effect_height,z,0.0d,effect_speed,0.0d);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if (!entity.fireImmune()&&entity instanceof LivingEntity livingEntity){
            livingEntity.setSecondsOnFire(ModConfigEvent.fire_dirt_block_self_burn_effect_time);
        }
        super.stepOn(level, blockPos, blockState, entity);
    }
}
