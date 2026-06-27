package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken;

import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.EntityList;
import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken.ai.goals.FlameChickenTemptGoal;
import net.etechservicecn.glow_in_the_dark_clover.tags.FireBurnWorldTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


public class FlameChickenEntity extends Animal {
    public final AnimationState flyingAnimationState=new AnimationState();
    public static final EntityDataAccessor<Integer>FLAP_POS_Y=SynchedEntityData.defineId(FlameChickenEntity.class,EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer>FLAP_COOL_DOWN=SynchedEntityData.defineId(FlameChickenEntity.class,EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean>CAN_FLY=SynchedEntityData.defineId(FlameChickenEntity.class,EntityDataSerializers.BOOLEAN);
    private final FlyingMoveControl flyingMoveControl;
    private final MoveControl groundMoveControl;

    private final FlyingPathNavigation flyingNavigation;
    private final GroundPathNavigation groundNavigation;
    public FlameChickenEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.flyingMoveControl=new FlyingMoveControl(this,10,false);
        this.flyingNavigation=new FlyingPathNavigation(this,level);
        this.flyingNavigation.setCanFloat(true);
        this.flyingNavigation.setCanPassDoors(false);
        this.flyingNavigation.setCanOpenDoors(false);
        this.groundMoveControl=new MoveControl(this);
        this.groundNavigation=new GroundPathNavigation(this,level);
        this.moveControl=this.groundMoveControl;
        this.navigation=this.groundNavigation;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED,0.25D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLAP_POS_Y,0);
        this.entityData.define(FLAP_COOL_DOWN,0);
        this.entityData.define(CAN_FLY,false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3,new PanicGoal(this,1.0D));
        this.goalSelector.addGoal(3, new FlameChickenTemptGoal(this, 1.2D, Ingredient.of(FireBurnWorldTags.Items.FLAME_CHICKEN_ATES), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }


    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide){
            if (this.getY()!=(int)getFlapPosY()){
                this.setFlapPosY((int) this.getY());
                if (getFlapCoolDown()<=0){
                    this.startFlapAnimation();
                    setFlapCoolDown(20);
                }else {
                    setFlapCoolDown(getFlapCoolDown()-1);
                }
            }
        }
    }

    @Override
    public void aiStep() {
        if (can_fly()){
            this.moveControl=this.flyingMoveControl;
            this.navigation=this.flyingNavigation;
        }
        else {
            this.moveControl=this.groundMoveControl;
            this.navigation=this.groundNavigation;
        }
        super.aiStep();
    }

    public void startFlapAnimation(){
        flyingAnimationState.start(this.tickCount);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityList.FLAME_CHICKEN_ENTITY.get().create(serverLevel);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28262_) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    protected void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(FireBurnWorldTags.Items.FLAME_CHICKEN_ATES);
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
    public int getFlapPosY(){
        return this.entityData.get(FLAP_POS_Y);
    }
    public void setFlapPosY(int y_pos){
        this.entityData.set(FLAP_POS_Y,y_pos,true);
    }
    public int getFlapCoolDown(){
        return this.entityData.get(FLAP_COOL_DOWN);
    }
    public void setFlapCoolDown(int times){
        this.entityData.set(FLAP_COOL_DOWN,times);
    }
    public boolean can_fly(){
        return this.entityData.get(CAN_FLY);
    }
    public void set_fly(boolean fly_status){
        this.entityData.set(CAN_FLY,fly_status);
    }
}
