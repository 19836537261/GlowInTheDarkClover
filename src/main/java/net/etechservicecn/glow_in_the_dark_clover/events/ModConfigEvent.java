package net.etechservicecn.glow_in_the_dark_clover.events;

import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = StartModApplication.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigEvent
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), ModConfigEvent::validateItemName);
    public static final ForgeConfigSpec.ConfigValue<String>MOD_WEAPON_CREATIVE_TAB_NAME=BUILDER
            .comment("GlowInTheDarkClover默认命名的武器标签名称")
            .define("mod_weapon_creative_name","GITDC武器");
    public static final ForgeConfigSpec.ConfigValue<Integer>FIRE_LAUNCHER_ITEM_MAX_USE_TIME=BUILDER
            .comment("火焰权杖的最大使用时间")
            .define("fire_launcher_item_max_use_time",36000);
    public static final ForgeConfigSpec.ConfigValue<Integer>FIRE_DIRT_BLOCK_EFFECT_MAX_HEIGHT=BUILDER
            .comment("设置火焰泥土的烟雾和火焰粒子特效生成最大高度。默认值为3")
            .define("fire_dirt_block_effect_max_height",3);
    public static final ForgeConfigSpec.ConfigValue<Integer>FIRE_DIRT_BLOCK_FLAME_SUMMON_CHANCE=BUILDER
            .comment("设置火焰泥土的火焰粒子特效生成几率。数值范围为0到10.默认值为6.值越大生成几率越小")
            .define("fire_dirt_block_flame_summon_chance",6);
    public static final ForgeConfigSpec.ConfigValue<Double>FIRE_DIRT_BLOCK_EFFECT_SPEED=BUILDER
            .comment("设置火焰泥土的粒子特效垂直运动速率。默认值为0.1")
            .define("fire_dirt_block_effect_speed",0.1d);
    public static final ForgeConfigSpec.ConfigValue<Integer>FIRE_DIRT_BLOCK_SELF_BURN_EFFECT_TIME=BUILDER
            .comment("设置玩家受火焰泥土debuff的影响时间。默认值为10s")
            .define("fire_dirt_block_self_burn_effect_time",10);
    public static final ForgeConfigSpec.ConfigValue<List<? extends Double>> TELEPORT_SCHEDULER_PARAMETERS=BUILDER
            .comment("设置任意传送门的粒子特效高度和生成半径。高度默认为0.5，半径默认为2,粒子密集程度值默认为32")
            .defineList("teleport_scheduler_parameters", List.of(0.5d,2.0d,32.0D),p->p instanceof Double);
    public static final ForgeConfigSpec.ConfigValue<Integer>FLAME_TREE_SAPLING_BLOCK_PARTICLES=BUILDER
            .comment("火焰树生长阶段的粒子生成数量")
            .defineInRange("flame_tree_sapling_block_particles",12,6,20);
    public static final ForgeConfigSpec.ConfigValue<Double>FLAME_TREE_SAPLING_BLOCK_PARTICLE_SPEED=BUILDER
            .comment("火焰树生长阶段的粒子运动速度")
            .defineInRange("flame_tree_sapling_block_particle_speed",0.1d,0.05d,1.0d);
    public static final ForgeConfigSpec SPEC = BUILDER.build();
    public static int magicNumber;
    public static Set<Item> items;
    public static String mod_weapon_tab_name;
    public static int fire_launcher_item_max_use_time;
    public static int fire_dirt_block_effect_max_height;
    public static int fire_dirt_block_flame_summon_chance;
    public static double fire_dirt_block_effect_speed;
    public static Integer fire_dirt_block_self_burn_effect_time;
    public static List<? extends Double>teleport_scheduler_parameters;
    public static Integer flame_tree_sapling_block_particles;
    public static Double flame_tree_sapling_block_particle_speed;


    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final net.minecraftforge.fml.event.config.ModConfigEvent event)
    {
        magicNumber = MAGIC_NUMBER.get();
        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());

        mod_weapon_tab_name=MOD_WEAPON_CREATIVE_TAB_NAME.get();
        fire_launcher_item_max_use_time=FIRE_LAUNCHER_ITEM_MAX_USE_TIME.get();
        fire_dirt_block_effect_max_height=FIRE_DIRT_BLOCK_EFFECT_MAX_HEIGHT.get();
        fire_dirt_block_flame_summon_chance=FIRE_DIRT_BLOCK_FLAME_SUMMON_CHANCE.get();
        fire_dirt_block_effect_speed=FIRE_DIRT_BLOCK_EFFECT_SPEED.get();
        fire_dirt_block_self_burn_effect_time=FIRE_DIRT_BLOCK_SELF_BURN_EFFECT_TIME.get();
        teleport_scheduler_parameters=TELEPORT_SCHEDULER_PARAMETERS.get();
        flame_tree_sapling_block_particles= FLAME_TREE_SAPLING_BLOCK_PARTICLES.get();
        flame_tree_sapling_block_particle_speed=FLAME_TREE_SAPLING_BLOCK_PARTICLE_SPEED.get();
    }
}
