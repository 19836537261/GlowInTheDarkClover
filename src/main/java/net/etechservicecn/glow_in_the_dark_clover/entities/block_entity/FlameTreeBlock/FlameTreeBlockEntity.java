package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlameTreeBlockEntity extends BlockEntity {
    private int base_height=4;
    private int rand_a=0;
    private int rand_b=1;
    private int radius=3;
    private int grow_counter=900;
    private boolean is_activate=false;
    private int block_x=0;
    private int block_y=0;
    private int block_z=0;
    public FlameTreeBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityTypeList.FLAME_TREE_BLOCK_ENTITY_TYPE.get(), p_155229_, p_155230_);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("base_height")&&tag.contains("is_activate")){
            this.base_height=tag.getInt("base_height");
            this.rand_a=tag.getInt("rand_a");
            this.rand_b=tag.getInt("rand_b");
            this.radius=tag.getInt("radius");
            this.grow_counter=tag.getInt("grow_counter");
            this.is_activate=tag.getBoolean("is_activate");
            this.block_x=tag.getInt("block_x");
            this.block_y=tag.getInt("block_y");
            this.block_z=tag.getInt("block_z");
        }
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        int real_height=base_height+randomSource.nextInt(rand_a,rand_a+1)+randomSource.nextInt(rand_b,rand_b+1);
        if (grow_counter==0){
            for (int i = 0; i < real_height; i++) {
                level.setBlock(blockPos.above(i), BlockList.FLAME_TREE_LOG_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
            }
            BlockPos center=blockPos.above(real_height-1);
            fill_leaves(level,center,radius);
            int random_pattern=randomSource.nextInt(0,10);
            if (random_pattern>5){
                fill_leaves(level,center.below(1),radius-1);
                fill_leaves(level,center.above(1),radius-1);
                fill_leaves(level,center.above(2),radius-2);
            } else if (random_pattern<=5) {
                fill_leaves(level,center.below(1),radius);
                fill_leaves(level,center.above(1),radius);
                fill_leaves(level,center.above(2),radius-1);
            }
        }else {
            /**
             * 这里的条件判断应改为使用队列判断位置是否在队列中。在的话开始动画，不在的话不播放动画
             */
            if (this.is_activate&&blockPos.getX()==block_x&&blockPos.getY()==block_y&&blockPos.getZ()==block_z){
                grow_counter--;
                this.setChanged();
            }
        }
        System.out.println(grow_counter);
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
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putInt("base_height",base_height);
        p_187471_.putInt("rand_a",rand_a);
        p_187471_.putInt("rand_b",rand_b);
        p_187471_.putInt("radius",radius);
        p_187471_.putInt("grow_counter",grow_counter);
        p_187471_.putBoolean("is_activate",is_activate);
        p_187471_.putInt("block_x",block_x);
        p_187471_.putInt("block_y",block_y);
        p_187471_.putInt("block_z",block_z);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag=super.getUpdateTag();
        tag.putInt("base_height",base_height);
        tag.putInt("rand_a",rand_a);
        tag.putInt("rand_b",rand_b);
        tag.putInt("radius",radius);
        tag.putInt("grow_counter",grow_counter);
        tag.putBoolean("is_activate",is_activate);
        tag.putInt("block_x",block_x);
        tag.putInt("block_y",block_y);
        tag.putInt("block_z",block_z);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.base_height=tag.getInt("base_height");
        this.is_activate=tag.getBoolean("is_activate");
        this.rand_a=tag.getInt("rand_a");
        this.rand_b=tag.getInt("rand_b");
        this.radius=tag.getInt("radius");
        this.grow_counter=tag.getInt("grow_counter");
        this.block_x=tag.getInt("block_x");
        this.block_y=tag.getInt("block_y");
        this.block_z=tag.getInt("block_z");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag compoundTag=pkt.getTag();
        if (compoundTag!=null){
            handleUpdateTag(compoundTag);
        }
    }

    public void set_activate(boolean is_activate) {
        this.is_activate = is_activate;
        setChanged();
    }
    public void setBlockPos(BlockPos blockPos){
        block_x=blockPos.getX();
        block_y=blockPos.getY();
        block_z=blockPos.getZ();
        setChanged();
    }
}
