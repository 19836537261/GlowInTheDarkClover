package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock.TeleportSchedulerBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.etechservicecn.glow_in_the_dark_clover.triggers.TeleportTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TeleportSchedulerBlock extends Block implements EntityBlock {
    private long current_seed_song=0l;
    public static final BooleanProperty IS_ACTIVE=BooleanProperty.create("is_activate");
    public TeleportSchedulerBlock(Properties p_49795_) {
        super(p_49795_.lightLevel((p)->{return 10;}));
        this.registerDefaultState(this.getStateDefinition().any().setValue(IS_ACTIVE,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        super.createBlockStateDefinition(p_49915_);
        p_49915_.add(IS_ACTIVE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TeleportSchedulerBlockEntity(p_153215_,p_153216_);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level source_level, BlockState source_blockState, BlockEntityType<T> blockEntityType) {
        if (blockEntityType==BlockEntityTypeList.TELEPORT_SCHEDULER_ENTITY_TYPE.get()){
            return (level, blockPos, blockState,blockEntity) ->{
                if (blockEntity instanceof TeleportSchedulerBlockEntity entity){
                    entity.calculate_counter(source_blockState.getValue(IS_ACTIVE));
                    if (blockState.getValue(IS_ACTIVE))
                    {
                        if (counter==0){
                            this.current_seed_song=source_level.random.nextLong();
                            source_level.playSeededSound(null,blockPos.getX(),blockPos.getY(),blockPos.getZ(),SoundEvents.PORTAL_AMBIENT,SoundSource.BLOCKS,1.0F,1.0F,this.current_seed_song);
                            counter=300;
                        }else {
                            counter--;
                        }
                    }
                }
            };
        }
        return null;
    }
    private int counter=300;

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
        if (blockState.getValue(IS_ACTIVE)){
            for (int i = 0; i < ModConfigEvent.teleport_scheduler_parameters.get(2); i++) {
                double angle=theta*randomSource.nextInt(-1080,1080);
                double radius=ModConfigEvent.teleport_scheduler_parameters.get(1);
                double relative_x=x+0.5d+Math.cos(angle)*radius;
                double relative_z=z+0.5d+Math.sin(angle)*radius;
                level.addParticle(ParticleTypes.HAPPY_VILLAGER,false,relative_x,y+summon_height,relative_z,0,0,0);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()){
            ItemStack itemStack=player.getMainHandItem();
            if (level.getBlockState(blockPos).is(BlockList.TELEPORT_SCHEDULER_BLOCK.get())&&itemStack.is(ItemList.FIRE_WORLD_TOKEN.get())){
                fill_blocks(level,blockPos,itemStack,player);
            }
        }
        return InteractionResult.SUCCESS;
    }
    public void fill_blocks(Level level,BlockPos blockPos,ItemStack itemStack,Player player){
        BlockPos north=blockPos.north(1);
        BlockPos north_pillar=north.north(1).above(1);
        BlockPos south=blockPos.south(1);
        BlockPos south_pillar=south.south(1).above(1);
        BlockPos top=blockPos.above(4);
        BlockPos north_top=top.north(1);
        BlockPos south_top=top.south(1);
        if (level.getBlockState(north).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_top).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_top).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(south_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(north_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                level.getBlockState(top).is(Blocks.OBSIDIAN)){
            if (!player.isCreative()){
                itemStack.shrink(1);
            }
            level.setBlock(blockPos,level.getBlockState(blockPos).setValue(IS_ACTIVE,true),Block.UPDATE_ALL);
            level.playSound(null,blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,1.0F,1.0F);

            if (player instanceof ServerPlayer serverPlayer){
                net.etechservicecn.glow_in_the_dark_clover.events.AdvanceTriggerRegisterEvent.TELEPORT_TRIGGER.trigger(serverPlayer);
            }


            level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),1.0d,1.0d,0.0d);
            level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),-1.0d,1.0d,0.0d);
            BlockPos start_fill_pos=north.above(1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    level.setBlock(start_fill_pos.south(i).above(j),BlockList.TELEPORT_BLOCK.get().defaultBlockState(), Block.UPDATE_NEIGHBORS|Block.UPDATE_CLIENTS);
                }
            }
        }else {
            BlockPos east=blockPos.east(1);
            BlockPos east_pillar=east.east(1).above(1);
            BlockPos west=blockPos.west(1);
            BlockPos west_pillar=west.west(1).above(1);
            BlockPos east_top=top.east(1);
            BlockPos west_top=top.west(1);
            if (level.getBlockState(east).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(east_top).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar.above(1)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_pillar.above(2)).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(west_top).is(Blocks.OBSIDIAN)&&
                    level.getBlockState(top).is(Blocks.OBSIDIAN)){
                if (!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.setBlock(blockPos,level.getBlockState(blockPos).setValue(IS_ACTIVE,true),Block.UPDATE_ALL);
                level.playSound(null,blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,1.0F,1.0F);

                if (player instanceof ServerPlayer serverPlayer){
                    net.etechservicecn.glow_in_the_dark_clover.events.AdvanceTriggerRegisterEvent.TELEPORT_TRIGGER.trigger(serverPlayer);
                }

                level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),0.0d,1.0d,1.0d);
                level.addParticle(ParticleTypes.FLAME,blockPos.getX(),blockPos.getY(),blockPos.getZ(),0.0d,1.0d,-1.0d);
                BlockPos start_fill_pos=east.above(1);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        level.setBlock(start_fill_pos.west(i).above(j),BlockList.TELEPORT_BLOCK.get().defaultBlockState(), Block.UPDATE_NEIGHBORS|Block.UPDATE_CLIENTS);
                    }
                }
            }
        }
    }
}
