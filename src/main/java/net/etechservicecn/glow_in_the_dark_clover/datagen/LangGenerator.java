package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.items.ItemList;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class LangGenerator extends LanguageProvider {
    public LangGenerator(PackOutput output) {
        super(output, StartModApplication.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        this.add(ItemList.FIRE_LAUNCHER.get(),"火焰权杖");
        this.add(ItemList.FIRE_WORLD_TOKEN.get(),"火焰令牌");
        this.add("creative_tab.weapon", "GITDC武器");
        this.add("creative_tab.block","GITDC方块");
        this.add("creative_tab.item","GITDC物品");
        this.addItemTranslate(ItemList.FIRE_LAUNCHER,"点击鼠标右键即可发射火焰");
        this.addItemTranslate(ItemList.FIRE_WORLD_TOKEN,"使用该物品去激活火焰世界维度吧");
        this.addBlockTranslate(BlockList.FIRE_DIRT_BLOCK,"看起来是岩浆和草方块的结合体。。。");
        this.addBlockTranslate(BlockList.TELEPORT_SCHEDULER_BLOCK,"可以使用该令牌去激活任意传送门");
        this.add(BlockList.FIRE_DIRT_BLOCK.get(),"火焰泥土");
        this.add(BlockList.TELEPORT_SCHEDULER_BLOCK.get(),"任意传送门");
        this.addAdvancement("fire_world_root","由此开启了异世界的冒险故事","使用黑曜石、附魔台、烈焰棒、下界之星、末影之眼、龙息合成任意传送门。");
        this.addAdvancement("root","浴火焚身","使用烈焰粉和纸合成火焰令牌。");

    }
    private void addItemTranslate(RegistryObject<Item>itemRegistryObject,String description){
        this.add("item."+itemRegistryObject.getId().getPath()+".tooltip",description);
    }
    private void addBlockTranslate(RegistryObject<? extends Block>itemRegistryObject, String description){
        this.add("block."+itemRegistryObject.getId().getPath()+".tooltip",description);
    }
    private void addAdvancement(String title_key,String title,String desc){
        this.add("advancement."+title_key+".title",title);
        this.add("advancement."+title_key+".desc",desc);
    }
}
