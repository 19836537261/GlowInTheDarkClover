package net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TeleportBlockEntity extends BlockEntity {
    private int rotate_index=0;
    private String world_type="";
    public TeleportBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityTypeList.TELEPORT_BLOCK_ENTITY_TYPE.get(), p_155229_, p_155230_);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("rotate_index")&&tag.contains("world_type"))
        {
            this.rotate_index=tag.getInt("rotate_index");
            this.world_type=tag.getString("world_type");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("rotate_index",rotate_index);
        tag.putString("world_type",world_type);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag=super.getUpdateTag();
        tag.putInt("rotate_index",rotate_index);
        tag.putString("world_type",world_type);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        this.rotate_index=tag.getInt("rotate_index");
        this.world_type=tag.getString("world_type");
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
            update_client_immediately(this.level);
        }
    }
    private void update_client_immediately(Level level){
        if (this.level!=null&&this.level.isClientSide()){
            BlockState blockState=this.level.getBlockState(worldPosition);
            level.sendBlockUpdated(worldPosition,blockState,blockState,3);
        }
    }
    private int counter=0;
    public void calculate_counter(){
        if (counter%10==0&&counter!=0){
            counter=0;
            this.setRotate_index();
        }
        else {
            counter++;
        }
    }
    public void setRotate_index() {
        this.rotate_index = (rotate_index+1)%4;
        this.setChanged();
        this.update_client_immediately(this.level);
    }

    public void setWorld_type(String world_type) {
        this.world_type = world_type;
        this.setChanged();
        this.update_client_immediately(this.level);
    }

    public int getRotate_index() {
        return rotate_index;
    }

    public String getWorld_type() {
        return world_type;
    }
}
