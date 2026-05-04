package net.etechservicecn.glow_in_the_dark_clover.items.item;

import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterDataHandler;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterMenu;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterNetwork;
import net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui.ItemScepterScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;
import java.util.concurrent.Callable;

public class ItemScepterItem extends Item {

    public ItemScepterItem(Properties p_41383_) {
        super(p_41383_.fireResistant().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack=player.getItemInHand(interactionHand);
        if (!level.isClientSide){
            Container container= ItemScepterDataHandler.read_nbt(itemStack,ItemScepterMenu.CONTAINER_SIZE);
            NetworkHooks.openScreen((ServerPlayer) player,
                    new SimpleMenuProvider(
                            (p_39954_, p_39955_, p_39956_) -> new ItemScepterMenu(p_39954_,p_39955_,container,itemStack),
                            Component.translatable("container.item.item_scepter.title")),
                    (buf)->{
                        ItemScepterNetwork.write_extra_data(buf,player);
                    });
        }
        return InteractionResultHolder.success(itemStack);
    }
}
