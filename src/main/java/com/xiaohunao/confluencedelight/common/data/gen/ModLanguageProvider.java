package com.xiaohunao.confluencedelight.common.data.gen;

import com.google.gson.JsonObject;
import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.init.ModCreativeTabs;
import com.xiaohunao.confluencedelight.common.init.ModItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Blocking;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.chineseProviders;

public class ModLanguageProvider extends LanguageProvider {
    private final Map<String, String> enData = new TreeMap<>();
    private final Map<String, String> cnData = new TreeMap<>();
    private final PackOutput output;
    private final String locale;

    private static String toTitleCase(String raw) {
        return Arrays.stream(raw.split("_"))
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
        addCreativeTab(ModCreativeTabs.TAB_FARMERS_DELIGHT, "Terra Delight", "汇流乐事");

        chineseProviders.forEach(a->a.accept(this));

/*
        addItem(ModItems.BANANA_SPLIT, "Banana Split", "香蕉船");
        addItem(ModItems.BBQ_RIBS, "BBQ Ribs", "烧烤肋排");
        addItem(ModItems.FRIES, "Fries", "薯条");
        addItem(ModItems.PAD_THAI, "Pad Thai", "泰式炒面");
        addItem(ModItems.PHO, "Pho", "越南河粉");
        addItem(ModItems.POTATO_CHIPS, "Potato Chips", "薯片");
        addItem(ModItems.SAUTEED_FROG_LEGS, "Sauteed Frog Legs", "炒蛙腿");
        addItem(ModItems.FROGGLE_BUNWICH, "Froggle Bunwich", "青蛙三明治");
        addItem(ModItems.SEAFOOD_DINNER, "Seafood Dinner", "海鲜大餐");
        addItem(ModItems.SMOOTHIE_OF_DARKNESS, "Smoothie of Darkness", "黑暗奶昔");
        addItem(ModItems.SUGAR_COOKIE, "Sugar Cookie", "蜜糖饼干");
        addItem(ModItems.ICE_CREAM, "Ice Cream", "冰淇淋");
        addItem(ModItems.MILKSHAKE, "Milkshake", "奶昔");
        addItem(ModItems.SASHIMI, "Sashimi", "生鱼片");
        addItem(ModItems.CHRISTMAS_PUDDING, "Christmas Pudding", "圣诞布丁");
        addItem(ModItems.MONSTER_LASAGNA, "Monster Lasagna", "怪物千层面");
        addItem(ModItems.MARSHMALLOW, "Marshmallow", "棉花糖");
        addItem(ModItems.COOKED_MARSHMALLOW, "Cooked Marshmallow", "烤棉花糖");
        */
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

    public void addBlock(DeferredHolder<Block,? extends Block> key, String cn) {
        String en = toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, cn);
    }

    public void addItem(DeferredHolder<Item,Item> key,  String cn) {
        String en =toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, cn);
    }
    private void addCreativeTab(Supplier<CreativeModeTab> tab, String en, String cn) {
        this.add(tab.get().getDisplayName().getString(), en, cn);
    }
    private void add(String key, String en, String cn) {
        if (this.locale.equals("en_us") && !this.enData.containsKey(key)) {
            this.enData.put(key, en);
        } else if (this.locale.equals("zh_cn") && !this.cnData.containsKey(key)) {
            this.cnData.put(key, cn);
        }
    }
}
