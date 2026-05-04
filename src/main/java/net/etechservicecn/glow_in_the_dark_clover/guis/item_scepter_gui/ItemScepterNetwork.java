package net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui;

import net.etechservicecn.glow_in_the_dark_clover.items.item.ItemScepterItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.IContainerFactory;

public class ItemScepterNetwork implements IContainerFactory<ItemScepterMenu> {
    @Override
    public ItemScepterMenu create(int windowId, Inventory inv, FriendlyByteBuf data) {
        ItemStack itemStack=data.readItem();
        boolean has_item=data.readBoolean();
        if (has_item){
            CompoundTag compoundTag=data.readNbt();
            itemStack.setTag(compoundTag);
            Container container=ItemScepterDataHandler.read_nbt(itemStack,ItemScepterMenu.CONTAINER_SIZE);
            return new ItemScepterMenu(windowId,inv,container,itemStack);
        }
        else {
            SimpleContainer simpleContainer=new SimpleContainer(ItemScepterMenu.CONTAINER_SIZE);
            return new ItemScepterMenu(windowId,inv,simpleContainer,itemStack);
        }
    }
    public static void write_extra_data(FriendlyByteBuf byteBuf, Player player){
        ItemStack itemStack=player.getMainHandItem();
        byteBuf.writeItem(itemStack);
        boolean hasData=itemStack.hasTag()&&itemStack.getTag().contains(ItemScepterDataHandler.ITEMS_KEY);
        byteBuf.writeBoolean(hasData);
        if (hasData){
            byteBuf.writeNbt(itemStack.getTag());
        }
    }
}
