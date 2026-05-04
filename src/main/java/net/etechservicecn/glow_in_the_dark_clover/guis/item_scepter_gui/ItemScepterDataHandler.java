package net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class ItemScepterDataHandler {
    public static final String ITEMS_KEY="Scepter";
    public static void write_nbt(ItemStack stack, Container container, int containerSize){
        if (container!=null){
            CompoundTag compoundTag=stack.getOrCreateTag();
            ListTag listTag=new ListTag();
            for (int i = 0; i < containerSize; i++) {
                ItemStack itemStack=container.getItem(i);
                if (!itemStack.isEmpty()){
                    CompoundTag saved_compound=new CompoundTag();
                    saved_compound.putInt("slot",i);
                    itemStack.save(saved_compound);
                    listTag.add(saved_compound);
                }
            }
            compoundTag.put(ITEMS_KEY,listTag);
            stack.setTag(compoundTag);
        }
    }
    public static Container read_nbt(ItemStack stack, int containerSize){
        SimpleContainer result=new SimpleContainer(containerSize);

        if (!stack.hasTag()){
            return result;
        }
        CompoundTag compoundTag=stack.getTag();
        if (!compoundTag.contains(ITEMS_KEY)){

            return result;
        }
        ListTag listTag=compoundTag.getList(ITEMS_KEY, ListTag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag item_tag=listTag.getCompound(i);
            int slot_index=item_tag.getInt("slot");
            if (slot_index>=0&&slot_index<containerSize){
                ItemStack itemStack=ItemStack.of(item_tag);
                result.setItem(slot_index,itemStack);
            }
        }
        return result;
    }
}
