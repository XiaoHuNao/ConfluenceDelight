package org.confluence.delight.common.data.gen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.ModCreativeTabs;
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
        addCreativeTab(ModCreativeTabs.TAB_FARMERS_DELIGHT, "Terra Delight", "汇流乐事");

        //jei
        add("title.confluence_delight.pickle_jars", "PickleJars", "泡菜罐");
        add("jei.confluence_delight.info.pickle_jars.crafttime", "%d s", "%d 秒");

        add("config.jade.plugin_confluence_delight.jade_picklejars_info", "PickleJars Info", "泡菜罐信息");

        add("info.confluence_delight.fluid_capacity", "%s %dB / %dB", "%s %dB / %dB");
        add("info.confluence_delight.fluid_amount", "%s %dB", "%s %dB");
        add("info.confluence_delight.fluid_empty", "Empty %dB", "空 %dB");

        //config
        add("confluence_delight.configuration.machines", "Machines", "机器");
        add("confluence_delight.configuration.fluid_capacity", "PickleJars Fluid Capacity", "泡菜罐液体容量");

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

    public void addBlock(DeferredHolder<Block, ? extends Block> key, String cn) {
        String en = toTitleCase(key.get().getDescriptionId());
        this.add(key.get().getDescriptionId(), en, cn);
    }

    public void addItem(DeferredHolder<Item, ? extends Item> key, String cn) {
        String en = toTitleCase(key.get().getDescriptionId());
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
