package net.etechservicecn.glow_in_the_dark_clover.blocks.block;

import net.etechservicecn.glow_in_the_dark_clover.entities.BlockEntityTypeList;
import net.etechservicecn.glow_in_the_dark_clover.entities.block_entity.TeleportBlock.TeleportBlockEntity;
import net.etechservicecn.glow_in_the_dark_clover.teleporters.FireWorldTeleporter;
import net.etechservicecn.glow_in_the_dark_clover.world.dimensions.FireBurnWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TeleportBlock extends Block implements EntityBlock {
    public static final Map<UUID,TeleportData>PLAYERS_PRE_TELEPORT=new HashMap<>();
    public TeleportBlock(Properties p_49795_) {
        super(p_49795_.lightLevel(p->{return 15;}).noCollission().noLootTable());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TeleportBlockEntity(p_153215_,p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (blockEntityType== BlockEntityTypeList.TELEPORT_BLOCK_ENTITY_TYPE.get()){
            return ((level1, blockPos, blockState1, blockEntity) -> {
                if (level1.isClientSide()){
                    if (blockEntity instanceof TeleportBlockEntity teleportBlockEntity){
                        teleportBlockEntity.calculate_counter();
                        handle_teleport_events(level1.getNearestPlayer(blockPos.getX(),blockPos.getY(),blockPos.getZ(),20,null));
                    }
                }
            });
        }
        return null;
    }

    private void handle_teleport_events(Player player) {
        var iterator =PLAYERS_PRE_TELEPORT.entrySet().iterator();
        while (iterator.hasNext()){
            var entry=iterator.next();
            UUID uuid=entry.getKey();
            if (PLAYERS_PRE_TELEPORT.containsKey(uuid)){
                TeleportData teleportData=entry.getValue();
                if (teleportData.is_entity_in_block(teleportData.getPlayer())){
                    teleportData.teleport();
                }
                else {
                    teleportData.cancel_teleport();
                }
                if(teleportData.getTeleport_time()==-1){
                    iterator.remove();
                }
            }
        }
    }


    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.INVISIBLE;
    }
    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        super.entityInside(blockState, level, blockPos, entity);
        if (!level.isClientSide()){
            if (entity instanceof Player player){
                UUID uuid=player.getUUID();
                if (!PLAYERS_PRE_TELEPORT.containsKey(uuid)){
                    ResourceKey<Level>source_level=level.dimension();
                    ResourceKey<Level>dest_level=source_level==FireBurnWorld.FIRE_BURN_LEVEL?Level.OVERWORLD:FireBurnWorld.FIRE_BURN_LEVEL;
                    PLAYERS_PRE_TELEPORT.put(player.getUUID(),new TeleportData(player.getUUID(),blockPos,source_level,dest_level,2000,level,player));
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,2000,0,false,false));
                }
            }
        }
    }
    private static class TeleportData{
        private UUID player_id;
        private BlockPos blockPos;
        private ResourceKey<Level>source_level;
        private ResourceKey<Level>dest_level;
        private Level level;
        private Integer teleport_time;
        private Player player;
        private boolean is_in_block;
        public TeleportData(UUID player_id,BlockPos blockPos,ResourceKey<Level>source_level,ResourceKey<Level>dest_level,Integer teleport_time,Level level,Player player){
            this.player_id=player_id;
            this.blockPos=blockPos;
            this.source_level=source_level;
            this.dest_level=dest_level;
            this.teleport_time=teleport_time;
            this.level=level;
            this.player=player;
            this.is_in_block=false;
        }
        public void teleport(){
            if (this.teleport_time==0){
                MinecraftServer minecraftServer=level.getServer();
                ServerLevel serverLevel=minecraftServer.getLevel(dest_level);
                if (serverLevel!=null&&!player.isPassenger()){
                    player.removeEffect(MobEffects.CONFUSION);
                    player.changeDimension(serverLevel,new FireWorldTeleporter(blockPos));
                    this.teleport_time=-1;
                }
            }else {
                this.teleport_time--;
            }
            System.out.println(teleport_time);
        }
        public boolean is_teleport(){
            return this.teleport_time==0;
        }

        public ResourceKey<Level> getDest_level() {
            return dest_level;
        }

        public ResourceKey<Level> getSource_level() {
            return source_level;
        }
        public boolean is_entity_in_block(Player player_in_time){
            Vec3 player_pos=player_in_time.getOnPos().getCenter();
            Vec3 block_pos=blockPos.getCenter();
            double d0=player_pos.x-block_pos.x;
            double d1=player_pos.y-block_pos.y;
            double d2=player_pos.z-block_pos.z;
            double distance=d0*d0+d1*d1+d2*d2;
            double distance_sqrt=Math.sqrt(distance);
           if (distance_sqrt<2.3d){
                return true;
            }else {
                return false;
            }
        }
        public void cancel_teleport(){
            this.teleport_time=-1;
            this.player.removeEffect(MobEffects.CONFUSION);
        }

        public Player getPlayer() {
            return player;
        }

        public Integer getTeleport_time() {
            return teleport_time;
        }
    }
}
