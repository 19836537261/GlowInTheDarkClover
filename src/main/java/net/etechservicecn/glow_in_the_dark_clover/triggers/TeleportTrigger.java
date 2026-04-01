package net.etechservicecn.glow_in_the_dark_clover.triggers;

import com.google.gson.JsonObject;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class TeleportTrigger extends SimpleCriterionTrigger<TeleportTrigger.Instance> {
    public static final ResourceLocation ID=new ResourceLocation(StartModApplication.MODID,"teleport_activated");
    @Override
    public Instance createInstance(JsonObject p_66248_, ContextAwarePredicate p_286603_, DeserializationContext p_66250_) {
        return new Instance(ID,p_286603_);
    }

    public void trigger(ServerPlayer p_66235_) {
        this.trigger(p_66235_,instance -> true);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }
    public static Instance instance(){
        return new Instance(ID,ContextAwarePredicate.ANY);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        public Instance(ResourceLocation p_286357_, ContextAwarePredicate p_286466_) {
            super(p_286357_, p_286466_);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext p_16979_) {
            JsonObject jsonObject=super.serializeToJson(p_16979_);
            return jsonObject;
        }
    }
}
