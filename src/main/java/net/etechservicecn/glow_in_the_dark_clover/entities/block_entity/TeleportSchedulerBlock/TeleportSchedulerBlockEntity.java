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
    private boolean is_active=false;

    public TeleportSchedulerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityTypeList.TELEPORT_SCHEDULER_ENTITY_TYPE.get(), blockPos,blockState);
    }
    private int counter=0;
    public void calculate_counter(boolean active) {
        if (counter%10==0&&counter!=0){
            this.is_active=active;
            this.setChanged();
            if (is_active){
                this.setCircle_index();
                counter=0;
            }else {
                this.circle_index = 0;
                this.circle_index1=0;
                this.circle_index2=0;
                this.circle_index3=0;
                this.setChanged();
                counter=0;
            }
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
            this.is_active=tag.getBoolean("is_active");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("circle_index",this.circle_index);
        tag.putInt("circle_index1",this.circle_index1);
        tag.putInt("circle_index2",this.circle_index2);
        tag.putInt("circle_index3",this.circle_index3);
        tag.putBoolean("is_active",this.is_active);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag=super.getUpdateTag();
        compoundTag.putInt("circle_index",this.circle_index);
        compoundTag.putInt("circle_index1",this.circle_index1);
        compoundTag.putInt("circle_index2",this.circle_index2);
        compoundTag.putInt("circle_index3",this.circle_index3);
        compoundTag.putBoolean("is_active",this.is_active);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.circle_index=tag.getInt("circle_index");
        this.circle_index1=tag.getInt("circle_index1");
        this.circle_index2=tag.getInt("circle_index2");
        this.circle_index3=tag.getInt("circle_index3");
        this.is_active=tag.getBoolean("is_active");
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
    public void setActive(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean IsActive() {
        return is_active;
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
