package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken.ai.goals;

import net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken.FlameChickenEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.*;

public class FlameChickenWanderGoal extends Goal {
    private FlameChickenEntity flameChickenEntity;
    private int counter=0;
    public FlameChickenWanderGoal(FlameChickenEntity flameChickenEntity){
        this.flameChickenEntity=flameChickenEntity;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }
    @Override
    public boolean canUse() {
        return true;
    }
    @Override
    public void tick() {
        if (counter<=0){
            BlockPos target=get_target_pos();
            flameChickenEntity.getNavigation().moveTo(target.getX(),target.getY(),target.getZ(),1.0d);
            counter=flameChickenEntity.getRandom().nextInt(50,70);
        }else {
            counter--;
        }
    }
    private BlockPos get_target_pos(){
        double offsetX=(flameChickenEntity.getRandom().nextDouble()-0.5)*10;
        double offsetZ=(flameChickenEntity.getRandom().nextDouble()-0.5)*10;
        return new BlockPos((int) (flameChickenEntity.getX()+offsetX), (int) flameChickenEntity.getY(), (int) ((int) flameChickenEntity.getZ()+offsetZ));
    }
}
