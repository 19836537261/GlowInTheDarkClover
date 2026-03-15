package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportSchedulerBlock;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TeleportSchedulerBlockEntity extends BlockEntity {
    private int circle_index=0;
    private int circle_index1=1;
    private int circle_index2=2;
    private int circle_index3=3;

    public static final int TEXTURE_COUNT=8;

    public TeleportSchedulerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeList.TELEPORT_SCHEDULER_ENTITY_TYPE.get(), blockPos,blockState);
    }
    private int counter=0;
    public void calculate_counter() {
        if (counter%10==0&&counter!=0){
            this.setCircle_index();
            counter=0;
        }else {
            counter++;
        }
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("circle_index")){
            this.circle_index=tag.getInt("circle_index");
            this.circle_index1=tag.getInt("circle_index1");
            this.circle_index2=tag.getInt("circle_index2");
            this.circle_index3=tag.getInt("circle_index3");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("circle_index",this.circle_index);
        tag.putInt("circle_index1",this.circle_index1);
        tag.putInt("circle_index2",this.circle_index2);
        tag.putInt("circle_index3",this.circle_index3);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag=super.getUpdateTag();
        compoundTag.putInt("circle_index",this.circle_index);
        compoundTag.putInt("circle_index1",this.circle_index1);
        compoundTag.putInt("circle_index2",this.circle_index2);
        compoundTag.putInt("circle_index3",this.circle_index3);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.circle_index=tag.getInt("circle_index");
        this.circle_index1=tag.getInt("circle_index1");
        this.circle_index2=tag.getInt("circle_index2");
        this.circle_index3=tag.getInt("circle_index3");
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
            if (this.level!=null&&this.level.isClientSide()){
                BlockState blockState=this.level.getBlockState(worldPosition);
                level.sendBlockUpdated(worldPosition,blockState,blockState,3);
            }
        }
    }
    public void setCircle_index() {
        this.circle_index = (circle_index+1)%TEXTURE_COUNT;
        this.circle_index1=(circle_index1+1)%TEXTURE_COUNT;
        this.circle_index2=(circle_index2+1)%TEXTURE_COUNT;
        this.circle_index3=(circle_index3+1)%TEXTURE_COUNT;
        this.setChanged();
        if (this.level!=null&&this.level.isClientSide()){
            BlockState blockState=this.level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition,blockState,blockState,3);
        }
    }

    public int getCircle_index() {
        return circle_index;
    }
    public int getCircle_index1(){
        return circle_index1;
    }

    public int getCircle_index2() {
        return circle_index2;
    }

    public int getCircle_index3() {
        return circle_index3;
    }
}
