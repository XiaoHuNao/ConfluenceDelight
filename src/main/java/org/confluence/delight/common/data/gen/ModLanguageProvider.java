package org.confluence.delight.common.data.gen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDCreativeTabs;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class ModLanguageProvider extends LanguageProvider {
    private final Map<String, String> enData = new TreeMap<>();
    private final Map<String, String> cnData = new TreeMap<>();
    private final PackOutput output;
    private final String locale;

    private static String toTitleCase(String raw) {
        String name = raw.substring(raw.lastIndexOf('.') + 1);
        return Arrays.stream(name.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, ConfluenceDelight.MODID, locale);
        this.output = output;
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        addCreativeTab(CDCreativeTabs.TAB_FARMERS_DELIGHT, "Terra Delight", "汇流乐事");

        //jei
        add("title.confluence_delight.pickle_jars", "PickleJars", "泡菜罐");
        add("jei.confluence_delight.info.pickle_jars.crafttime", "%d s", "%d 秒");
        add("jei.confluence_delight.info.pickle_jars.fermented_item", "This formula can be accelerated by using fungal yeast", "该配方可以使用菌曲加速");
        add("jei.confluence_delight.info.pickle_jars.fermented_item", "Hold down shift to display the time after acceleration", "按住shift显示加速后时间");

        add("title.confluence_delight.millstone", "Millstone", "磨盘");
        add("jei.confluence_delight.info.millstone.work_circles", "%d circles", "%d 圈");

        add("title.confluence_delight.juicer", "Juicer", "榨汁机");
        add("jei.confluence_delight.info.juicer.cycle", "%d cycle", "%d 次");

        add("title.confluence_delight.block_interaction", "Block Interaction", "方块交互");
        //jade
        add("config.jade.plugin_confluence_delight.picklejars_info", "PickleJars Info", "泡菜罐信息");
        add("config.jade.plugin_confluence_delight.juicer_info", "Juicer Info", "榨汁机信息");

        //config
        add("confluence_delight.configuration.machines", "Machines", "机器");
        add("confluence_delight.configuration.fluid_capacity", "Container liquid capacity", "容器液体容量");

        //tooltip
        add("tooltip.item.confluence.atlantis_tsunami.0",
                "It feels like drinking iced fresh lemonade while your heel is pinned to the back of your head.",
                "据说喝它的感觉就像把脚后跟别在后脑勺上喝冰鲜柠檬水");
        add("tooltip.item.confluence.black_luck.0",
                "Black cat shaped ice cream, Who says black cats are a symbol of bad luck?",
                "黑色猫猫形状的冰淇淋，谁说黑猫是厄运象征的？");
        add("tooltip.item.confluence.white_dawn.0",
                "The white cat shaped ice cream will bring you the magic dawn.",
                "白色猫猫形状的冰淇淋，她会给你来带魔力曙光。");
        add("tooltip.item.confluence.luck_chocolate_copper_coin.0",
                "If I eat this, will it really drop the item I want?",
                "吃了这个，真的能爆出我想要的东西吗？");
        add("tooltip.item.confluence.luck_chocolate_silver_coin.0",
                "It seems... to have some use.",
                "似乎……有些用处。");
        add("tooltip.item.confluence.luck_chocolate_golden_coin.0",
                "A noticeable increase in luck has brought many benefits.",
                "明显提升的运气，带来了不少好处");
        add("tooltip.item.confluence.luck_chocolate_platinum_coin.0",
                "You feel the happiness that good luck brings!",
                "你体验到好运带来的快乐！");
        add("tooltip.item.confluence.luck_chocolate_emerald_coin.0",
                "Huge profits!!! This is pure profit!!! I—I'm richer than a kingdom! Hahahahaha!",
                "暴利！！！这简直是暴利！！！我——富可敌国！哈哈哈哈哈哈！");
        add("tooltip.item.confluence.chocolate_luck_coin_box.0",
                "So this is the blessing of luck. Now I can get any drop I want. The goddess of luck is always on my side!",
                "原来这就是幸运的加持，现在我可以爆出任何东西，幸运女神永远在我这边！");
        add("tooltip.item.confluence.gilded_luxury_chocolate_luck_coin_box.0",
                "A luxurious piece of art—should I consume it or keep it as a collectible?",
                "奢华的艺术品，是吃掉，亦或是收藏");
        add("tooltip.item.confluence.braised_chicken.0",
                "Chicken from the Orient, but it's a little overcooked and a bit mushy.",
                "这德克萨斯州的鸡武德充沛吗？");
        add("tooltip.item.confluence.big_chicken_cutlet.0",
                "LLLLLava CCCChicken",
                "特辣大鸡排");
        add("tooltip.item.confluence_delight.hot_star_chicken",
                "Hot Star Chicken, Everyone loves it",
                "豪大大鸡排，又香又嫩人人夸");
        add("jukebox_song.confluence_delight.hot_star_chicken",
                "The circus on the top floor - Hot Star Chicken",
                "顶楼的马戏团 - 豪大大鸡排");
        add("confluence_delight.harvest_stew.tooltip",
                "When feed to chicken/sheep :",
                "喂给鸡/羊时 ：");
        add("tooltip.item.confluence.freshly_squeezed_vitality.0",
                "Legendary explorers' special juice blend, designed to delight your taste buds and fill your belly.",
                "传说中的探险家们为了能满足味蕾需求和食物需求研发出的果汁特调");
        add("tooltip.item.confluence_delight.the_meal_of_life",
                "Products from the Goddess of Life are bound to be of high quality",
                "生命女神出品，必属精品");
        add("tooltip.item.confluence.grass_seed_soup.0",
                "It's all oil and very bitter, but it's healthy",
                "全是油，而且很苦涩，但是健康");
        add("tooltip.item.confluence.mushroom_platter.0",
                "A hodgepodge of mushrooms from another world",
                "异世蘑菇大杂烩");
        add("tooltip.item.confluence.blood_red_pork_rib_soup.0",
                "You are a monster.",
                "你是怪物");
        add("tooltip.item.confluence_delight.special_mushroom_soup",
                "only the simplest processing is needed",
                "只需要最简单的处理");

        add("tooltip.item.confluence_delight.apple_sapling",
                "It is obtained by right-clicking the oak sapling with an apple",
                "使用苹果右击橡树树苗获得");
        add("tooltip.item.confluence_delight.apricot_sapling",
                "It is obtained by right-clicking the birch sapling with an apricot",
                "使用杏右击白桦树苗获得");
        add("tooltip.item.confluence_delight.grapefruit_sapling",
                "It is obtained by right-clicking the jungle sapling with a grapefruit",
                "使用葡萄柚右击丛林树苗获得");
        add("tooltip.item.confluence_delight.lemon_sapling",
                "It is obtained by right-clicking the spruce sapling with a lemon",
                "使用柠檬右击云杉树苗获得");
        add("tooltip.item.confluence_delight.peach_sapling",
                "It is obtained by right-clicking the oak sapling with a peach",
                "使用桃子右击橡树树苗获得");
        add("tooltip.item.confluence_delight.cherry_sapling",
                "It is obtained by right-clicking the spruce sapling with a cherry",
                "使用樱桃右击云杉树苗获得");
        add("tooltip.item.confluence_delight.plum_sapling",
                "It is obtained by right-clicking the birch sapling with a plum",
                "使用李子右击白桦树苗获得");
        add("tooltip.item.confluence_delight.blood_orange_sapling",
                "It is obtained by right-clicking the jungle sapling with a blood orange",
                "使用血橙右击丛林树苗获得");
        add("tooltip.item.confluence_delight.rambutan_sapling",
                "It is obtained by right-clicking the jungle sapling with a rambutan",
                "使用红毛丹右击丛林树苗获得");
        add("tooltip.item.confluence_delight.mango_sapling",
                "It is obtained by right-clicking the jungle sapling with a mango",
                "使用芒果右击丛林树苗获得");
        add("tooltip.item.confluence_delight.banana_sapling",
                "It is obtained by right-clicking the jungle sapling with a banana",
                "使用香蕉右击丛林树苗获得");
        add("tooltip.item.confluence_delight.coconut_sapling",
                "It is obtained by right-clicking the palm sapling with a coconut",
                "使用椰子右击棕榈树苗获得");
        add("tooltip.item.confluence_delight.star_fruit_sapling",
                "It is obtained by right-clicking the oak sapling with a star fruit",
                "使用杨桃右击橡树树苗获得");
        add("tooltip.item.confluence_delight.pomegranate_sapling",
                "It is obtained by right-clicking the birch sapling with a pomegranate",
                "使用石榴右击白桦树苗获得");
        add("tooltip.item.confluence_delight.blackcurrant_shrub",
                "It can be obtained by right-clicking blackcurrant on azalea or flowering azalea",
                "使用黑醋栗右击杜鹃花丛或盛开的杜鹃花丛获得");
        add("tooltip.item.confluence_delight.elderberry_shrub",
                "It can be obtained by right-clicking elderberry on on azalea or flowering azalea",
                "使用接骨木右击杜鹃花丛或盛开的杜鹃花丛获得");

        //SoundsSubmit
        add("confluence_delight.subtitle.millstone_work",
                "MillStone: is working",
                "磨盘：工作");

        add("fluid_type.confluence_delight.wine", "Wine", "酒");
        add("fluid_type.confluence_delight.brine", "Brine", "卤水");

        chineseProviders.forEach(a -> a.accept(this));
    }

    @Override
    public @NotNull CompletableFuture<?> run(CachedOutput cache) {
        this.addTranslations();
        Path path = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                .resolve(ConfluenceDelight.MODID).resolve("lang");
        if (this.locale.equals("en_us") && !this.enData.isEmpty()) {
            return this.save(this.enData, cache, path.resolve("en_us.json"));
        }

        if (this.locale.equals("zh_cn") && !this.cnData.isEmpty()) {
            return this.save(this.cnData, cache, path.resolve("zh_cn.json"));
        }

        return CompletableFuture.allOf();
    }

    private CompletableFuture<?> save(Map<String, String> data, CachedOutput cache, Path target) {
        JsonObject json = new JsonObject();
        data.forEach(json::addProperty);
        return DataProvider.saveStable(cache, json, target);
    }

    public void addBlock(DeferredHolder<Block, ? extends Block> key, String zh) {
        String en = toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, zh);
    }

    public void addItem(DeferredHolder<Item, ? extends Item> key, String zh) {
        String en = toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, zh);
    }

    public void addEffect(DeferredHolder<MobEffect, ? extends MobEffect> key, String zh) {
        String en = toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, zh);
    }

    private void addCreativeTab(Supplier<CreativeModeTab> tab, String en, String zh) {
        this.add(tab.get().getDisplayName().getString(), en, zh);
    }

    private void add(String key, String en, String zh) {
        if (this.locale.equals("en_us") && !this.enData.containsKey(key)) {
            this.enData.put(key, en);
        } else if (this.locale.equals("zh_cn") && !this.cnData.containsKey(key)) {
            this.cnData.put(key, zh);
        }
    }
}
