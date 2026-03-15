package net.etechservicecn.glow_in_the_dark_clover.items.item;

import net.etechservicecn.glow_in_the_dark_clover.events.ModConfigEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FireLauncherItem extends Item {
    private int counter;

    public FireLauncherItem(Properties properties) {
        super(properties.stacksTo(1)
                .rarity(Rarity.EPIC)
                .durability(1800));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("item.fire_launcher.tooltip"));
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return ModConfigEvent.fire_launcher_item_max_use_time;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack itemStack=p_41433_.getItemInHand(p_41434_);
        if (itemStack.is(this)){
            p_41433_.startUsingItem(p_41434_);
        }
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
    }

    @Override
    public void onUseTick(Level p_41428_, LivingEntity p_41429_, ItemStack p_41430_, int p_41431_) {
        if (counter%100==0){
            if (p_41429_ instanceof Player player){
                if (!p_41428_.isClientSide()){
                    double x=player.getX();
                    double y=player.getEyeY();
                    double z=player.getZ();
                    double speed=1.2;
                    Vec3 look_angle=player.getLookAngle();
                    for (int i = -1; i < 2; i++) {
                        SmallFireball smallFireball=new SmallFireball(p_41428_,player,look_angle.x*speed,look_angle.y*speed,look_angle.z*speed);
                        smallFireball.setPos(x+i+look_angle.x,y+look_angle.y,z+i+look_angle.z);
                        p_41428_.addFreshEntity(smallFireball);
                    }
                    p_41430_.hurtAndBreak(1,player,p->p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
                    if (!player.isCreative()){
                        player.getCooldowns().addCooldown(this,100);
                    }
                    counter=0;
                }
            }
        }else {
            counter++;
        }
    }
    //    @Override
//    public InteractionResult useOn(UseOnContext p_41427_) {
//        Level level=p_41427_.getLevel();
//        Player player=p_41427_.getPlayer();
//        ItemStack itemStack=p_41427_.getItemInHand();
//        if (!level.isClientSide()&&player!=null){
//            double x=player.getX();
//            double y=player.getEyeY();
//            double z=player.getZ();
//            Vec3 look_angle=player.getLookAngle();
//            SmallFireball smallFireball=new SmallFireball(level,player,look_angle.x,look_angle.y,look_angle.z);
//            smallFireball.setDeltaMovement(look_angle.x,3,look_angle.z);
//            level.addFreshEntity(smallFireball);
//            itemStack.hurtAndBreak(1,player,p->p.broadcastBreakEvent(p_41427_.getHand()));
//            player.getCooldowns().addCooldown(this,20);
//        }
//        return InteractionResult.sidedSuccess(level.isClientSide());
//    }
}
