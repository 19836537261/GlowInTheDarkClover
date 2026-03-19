package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.FlameTreeBlock;

import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FlameTreeBlockEntity extends BlockEntity {
    private int base_height=4;
    private int rand_a=0;
    private int rand_b=1;
    private int radius=3;
    private int grow_counter=600;
    private boolean is_activate=false;
    private List<Integer>blocks_x=new ArrayList<>();
    private List<Integer>blocks_y=new ArrayList<>();
    private List<Integer>blocks_z=new ArrayList<>();
    public FlameTreeBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityTypeList.FLAME_TREE_BLOCK_ENTITY_TYPE.get(), p_155229_, p_155230_);
    }
    public void enqueue(BlockPos blockPos){
        boolean tag=false;
        for (int i = 0; i < blocks_x.size(); i++) {
            if (blockPos.getX()==blocks_x.get(i)&&blockPos.getY()==blocks_y.get(i)&&blockPos.getZ()==blocks_z.get(i))
            {
                tag=true;
            }
        }
        if (!tag)
        {
            System.out.println(blockPos.getX()+":"+blockPos.getY()+":"+blockPos.getZ());
            blocks_x.add(blockPos.getX());
            blocks_y.add(blockPos.getY());
            blocks_z.add(blockPos.getZ());
            for (int i = 0; i < blocks_x.size(); i++) {
                System.out.println("x:"+blocks_x.get(i)+"y:"+blocks_y.get(i)+"z:"+blocks_z.get(i));
            }
            setChanged();
        }
    }
    private BlockPos dequeue(){
        if (blocks_z.size()>0&&blocks_y.size()>0&&blocks_x.size()>0){
            BlockPos blockPos=new BlockPos(blocks_x.get(0),blocks_y.get(0),blocks_z.get(0));
            blocks_x.remove(0);
            blocks_y.remove(0);
            blocks_z.remove(0);
            setChanged();
            return blockPos;
        }
        else {
            return new BlockPos(0,-32,0);
        }
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
            ListTag blocks_x_list =tag.getList("blocks_x",Tag.TAG_INT);
            reset_list(blocks_x_list,blocks_x);
            ListTag blocks_y_list=tag.getList("blocks_y",Tag.TAG_INT);
            reset_list(blocks_y_list,blocks_y);
            ListTag blocks_z_list=tag.getList("blocks_z",Tag.TAG_INT);
            reset_list(blocks_z_list,blocks_z);
        }
    }
    private void reset_list(ListTag listTag,List<Integer>list){
        list.clear();
        for (int i = 0; i < listTag.size(); i++) {
            list.add(i,listTag.getInt(i));
        }
    }
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blocks_y.size()>0){
            if (grow_counter==0){
                BlockPos running_pos=dequeue();
                int real_height=base_height+randomSource.nextInt(rand_a,rand_a+1)+randomSource.nextInt(rand_b,rand_b+1);
                for (int i = 0; i < real_height; i++) {
                    level.setBlock(running_pos.above(i), BlockList.FLAME_TREE_LOG_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                }
                BlockPos center=running_pos.above(real_height-1);
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
                System.out.println(grow_counter);
                if (!blocks_z.isEmpty()&&grow_counter==0){
                    grow_counter=600;
                    setChanged();
                }
            }else {
                grow_counter--;
                this.setChanged();
            }
        }else {
            grow_counter=600;
            setChanged();
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
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putInt("base_height",base_height);
        p_187471_.putInt("rand_a",rand_a);
        p_187471_.putInt("rand_b",rand_b);
        p_187471_.putInt("radius",radius);
        p_187471_.putInt("grow_counter",grow_counter);
        p_187471_.putBoolean("is_activate",is_activate);
        p_187471_.putIntArray("blocks_x",blocks_x);
        p_187471_.putIntArray("blocks_y",blocks_y);
        p_187471_.putIntArray("blocks_z",blocks_z);
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
        tag.putIntArray("blocks_x",blocks_x);
        tag.putIntArray("blocks_y",blocks_y);
        tag.putIntArray("blocks_z",blocks_z);
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
        ListTag blocks_x_list =tag.getList("blocks_x",Tag.TAG_INT);
        reset_list(blocks_x_list,blocks_x);
        ListTag blocks_y_list=tag.getList("blocks_y",Tag.TAG_INT);
        reset_list(blocks_y_list,blocks_y);
        ListTag blocks_z_list=tag.getList("blocks_z",Tag.TAG_INT);
        reset_list(blocks_z_list,blocks_z);
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
}
