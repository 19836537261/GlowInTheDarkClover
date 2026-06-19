package net.etechservicecn.glow_in_the_dark_clover.datagen;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.etechservicecn.glow_in_the_dark_clover.blocks.BlockList;
import net.etechservicecn.glow_in_the_dark_clover.blocks.block.FlameTreeBlock.flameTreePackageInfo;
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
        this.add(ItemList.ITEM_SCEPTER_ITEM.get(), "物品权杖");
        this.add(ItemList.FLAME_PIG_SPAWN_ITEM.get(), "烈焰猪刷怪蛋");
        this.add(ItemList.FLAME_CHICKEN_SPAWN_ITEM.get(), "烈焰鸡刷怪蛋");

        this.add(BlockList.FIRE_DIRT_BLOCK.get(),"火焰泥土");
        this.add(BlockList.TELEPORT_SCHEDULER_BLOCK.get(),"任意传送门");

        this.add("creative_tab.weapon", "GITDC武器");
        this.add("creative_tab.block","GITDC方块");
        this.add("creative_tab.item","GITDC物品");
        this.add("creative_tab.spawn_egg","GITDC刷怪蛋");

        this.addItemTranslate(ItemList.FIRE_LAUNCHER,"点击鼠标右键即可发射火焰");
        this.addItemTranslate(ItemList.FIRE_WORLD_TOKEN,"使用该物品去激活火焰世界维度吧");
        this.addBlockTranslate(BlockList.FIRE_DIRT_BLOCK,"看起来是岩浆和草方块的结合体。。。");
        this.addBlockTranslate(BlockList.TELEPORT_SCHEDULER_BLOCK,"可以使用该令牌去激活任意传送门");

        this.addAdvancement("root","由此开启了异世界的冒险故事","使用黑曜石、附魔台、烈焰棒、下界之星、末影之眼、龙息合成任意传送门。");
        this.addAdvancement("fire_world_root","浴火焚身","使用烈焰粉和纸合成火焰令牌。");
        this.addAdvancement("fire_world_activate_portal","火焰门","使用火焰令牌激活传送门框架");
        this.addAdvancement("fire_world_entrance","初入火焰世界","通过火焰传送门进入火焰维度");

        this.addItemContainerTitle(ItemList.ITEM_SCEPTER_ITEM,"物品列表(按E键退出)");

        this.addTreeTranslation(flameTreePackageInfo.FLAME_TREE_LOG_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_LEAVES_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_SAPLING_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_TRIPPED_LOG_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_WOOD_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_STAIR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_SLAB_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_BUTTON_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_PRESSURE_PLATE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_FENCE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_FENCE_GATE_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_WALL_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_DOOR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_TRAP_DOOR_BLOCK.get(),
                flameTreePackageInfo.FLAME_TREE_PLANKS_BLOCK.get(),flameTreePackageInfo.FLAME_TREE_STICK_ITEM.get(),"火焰树");
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
    private void addItemContainerTitle(RegistryObject<Item>itemRegistryObject,String description){
        this.add("container.item."+itemRegistryObject.getId().getPath()+".title",description);
    }
    private void addTreeTranslation(Block log_block,
                                    Block leaves_block,
                                    Block sapling_block,
                                    Block tripped_log_block,
                                    Block wood_block,
                                    Block stair_block,
                                    Block slab_block,
                                    Block button_block,
                                    Block pressure_plate_block,
                                    Block fence_block,
                                    Block fence_gate_block,
                                    Block wall_block,
                                    Block door_block,
                                    Block trap_door_block,
                                    Block planks_block,Item stick_item,String base_name){
        this.add(log_block,base_name+"原木");
        this.add(leaves_block,base_name+"树叶");
        this.add(sapling_block,base_name+"树苗");
        this.add(tripped_log_block,"削皮"+base_name+"原木");
        this.add(wood_block,base_name+"木头");
        this.add(stair_block,base_name+"楼梯");
        this.add(slab_block,base_name+"台阶");
        this.add(button_block,base_name+"按钮");
        this.add(pressure_plate_block,base_name+"压力板");
        this.add(fence_block,base_name+"围栏");
        this.add(fence_gate_block,base_name+"围栏门");
        this.add(wall_block,base_name+"围墙");
        this.add(door_block,base_name+"门");
        this.add(trap_door_block,base_name+"陷阱门");
        this.add(stick_item, base_name+"木棍");
        this.add(planks_block, base_name+"木板");
    }
}
