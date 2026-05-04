package net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui;

import net.etechservicecn.glow_in_the_dark_clover.guis.MenuList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemScepterMenu extends AbstractContainerMenu {
    private final Container container;
    private final Player player;
    private ItemStack scepterStack;
    public static final int CONTAINER_SIZE=27;

    public ItemScepterMenu(int container_id, Inventory playerInventory, Container container,ItemStack itemStack) {
        super(MenuList.ITEM_SCEPTER_MENU.get(), container_id);
        this.container=container;
        this.player=playerInventory.player;
        this.scepterStack=itemStack.copy();
        checkContainerSize(container,CONTAINER_SIZE);
        container.startOpen(playerInventory.player);
        addSelfSlots();
        addPlayInventorySlots(playerInventory);
        addPlayerHotbar(playerInventory);
    }
    public ItemScepterMenu(int container_id, Inventory playerInventory, FriendlyByteBuf extraData){
        this(container_id,playerInventory,new SimpleContainer(CONTAINER_SIZE),playerInventory.player.getMainHandItem());
    }
    private void addPlayerHotbar(Inventory playerInventory) {
        int start_x=8;
        int start_y=142;
        int index=0;
        for (int i = 0; i < 9; i++) {
            int x=start_x+i*18;
            int y=start_y;
            this.addSlot(new Slot(playerInventory,index,x,y));
            index++;
        }
    }

    private void addPlayInventorySlots(Inventory playerInventory) {
        int start_x=8;
        int start_y=84;
        int index=9;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x=start_x+j*18;
                int y=start_y+i*18;
                this.addSlot(new Slot(playerInventory,index,x,y));
                index++;
            }
        }
    }

    private void addSelfSlots() {
        int index=0;
        int start_x=8;
        int start_y=19;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int x=start_x+j*18;
                int y=start_y+i*18;
                this.addSlot(new ContainerSlot(container,index,x,y));
                index++;
            }
        }
    }



    @Override
    public ItemStack quickMoveStack(Player current_player, int slot_index) {
        ItemStack itemStack=ItemStack.EMPTY;
        Slot slot=this.slots.get(slot_index);
        if (slot!=null&&slot.hasItem()){
            ItemStack slotStack=slot.getItem();
            itemStack=slotStack.copy();
            if (slot_index<CONTAINER_SIZE){
                if (!this.moveItemStackTo(slotStack,CONTAINER_SIZE,this.slots.size(),false)){
                    return ItemStack.EMPTY;
                }
            }else {
                if (!this.moveItemStackTo(slotStack,0,CONTAINER_SIZE,false)){
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.hasTag()&&slotStack.getTag().contains(ItemScepterDataHandler.ITEMS_KEY)){
                return ItemStack.EMPTY;
            }
            if (slotStack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }else {
                slot.setChanged();
            }
            if (slotStack.getCount()== itemStack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(player,slotStack);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player checked_player) {
        ItemStack itemStack=checked_player.getMainHandItem();
        if (itemStack.is(scepterStack.getItem()))
        {
            return true;
        }else {
            return false;
        }
    }
    public static class ContainerSlot extends Slot{

        public ContainerSlot(Container self_container, int self_index, int self_x, int self_y) {
            super(self_container, self_index, self_x, self_y);
        }

        @Override
        public boolean mayPlace(ItemStack check_item) {
            if (check_item.hasTag()&&check_item.getTag().contains(ItemScepterDataHandler.ITEMS_KEY))
            {
                return false;
            }
            return true;
        }

        @Override
        public int getMaxStackSize(ItemStack p_40238_) {
            return 64;
        }
    }

    @Override
    public void removed(Player event_player) {
        super.removed(event_player);
        if (!event_player.level().isClientSide){
            ItemStack itemStack=get_current_scepter(event_player);
            if (!itemStack.isEmpty()){
                ItemScepterDataHandler.write_nbt(itemStack,container,CONTAINER_SIZE);
                if (player.getMainHandItem()==scepterStack){
                    player.getInventory().setItem(player.getInventory().selected,itemStack);
                }
            }
        }
        container.stopOpen(event_player);
    }
    private ItemStack get_current_scepter(Player player_in_check){
        //这个地方需要调整一下物品菜单打开的优先级。副手最高，主手为次
        ItemStack itemStack=player_in_check.getMainHandItem();
        if (itemStack.isEmpty()){
            return ItemStack.EMPTY;
        }
        return itemStack;
    }

    public Container getContainer() {
        return container;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getScepterStack() {
        return scepterStack;
    }
}
