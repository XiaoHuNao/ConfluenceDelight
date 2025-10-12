package org.confluence.delight.common.init;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.item.food.*;
import org.confluence.delight.util.CDEffectData;
import org.confluence.lib.common.component.ModRarity;
import org.confluence.mod.common.init.ModEffects;
import org.confluence.mod.common.init.item.PotionItems;
import org.confluence.mod.common.item.food.BaseFoodItem;
import org.confluence.mod.common.item.food.ModFoodProperties;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;


public class CDFoodItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    //泡菜
    public static final DeferredItem<BaseFoodItem> JAR_CHILI_PEPPERS = registerNormalFood("jar_chili_peppers", "泡椒", () -> DelightFoodProperties.noEffectProperties(1, 1));
    public static final DeferredItem<BaseFoodItem> CHOP_BELL_PEPPER = registerNormalFood("chop_bell_pepper", "剁椒", () -> DelightFoodProperties.noEffectProperties(1, 0.5f));
    public static final DeferredItem<BaseFoodItem> SPICY_PICKLED_FISH = registerNormalFood("spicy_pickled_fish", "泡鱼辣子", () -> DelightFoodProperties.noEffectProperties(4, 4.8f));

    //食材
    public static final DeferredItem<BaseFoodItem> POTATO_PIECE = registerNormalFood("potato_piece", "马铃薯块", () -> DelightFoodProperties.noEffectProperties(1, 0.5f));
    public static final DeferredItem<BaseFoodItem> CRUSHED_CHILLI = registerNormalFood("crushed_chilli", "辣椒碎", () -> DelightFoodProperties.noEffectProperties(1, 0.5f));
    public static final DeferredItem<BaseFoodItem> RAW_DONKEY_MEAT = registerNormalFood("raw_donkey_meat", "生驴肉", () -> DelightFoodProperties.noEffectProperties(3, 1.5f));
    public static final DeferredItem<BaseFoodItem> RAW_HORSE_MEAT = registerNormalFood("raw_horse_meat", "生马肉", () -> DelightFoodProperties.noEffectProperties(3, 1.5f));
    public static final DeferredItem<BaseFoodItem> COOKED_DONKEY_MEAT = registerNormalFood("cooked_donkey_meat", "熟驴肉", () -> DelightFoodProperties.noEffectProperties(8, 12.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_HORSE_MEAT = registerNormalFood("cooked_horse_meat", "熟马肉", () -> DelightFoodProperties.noEffectProperties(8, 12.8f));
    public static final DeferredItem<BaseFoodItem> SQUIRREL_MEAT_CHUNKS = registerNormalFood("squirrel_meat_chunks", "松鼠肉块", () -> DelightFoodProperties.noEffectProperties(1, 0.9f));
    public static final DeferredItem<BaseFoodItem> BIRD_MEAT_CHUNKS = registerNormalFood("bird_meat_chunks", "鸟肉块", () -> DelightFoodProperties.noEffectProperties(1, 0.9f));
    public static final DeferredItem<BaseFoodItem> DUCK_MEAT_CHUNKS = registerNormalFood("duck_meat_chunks", "鸭肉块", () -> DelightFoodProperties.noEffectProperties(1, 1.3f));
    public static final DeferredItem<BaseFoodItem> FROG_MEAT_CHUNKS = registerNormalFood("frog_meat_chunks", "青蛙肉块", () -> DelightFoodProperties.noEffectProperties(1, 0.9f));
    public static final DeferredItem<BaseFoodItem> DONKEY_MEAT_CHUNKS = registerNormalFood("donkey_meat_chunks", "驴肉块", () -> DelightFoodProperties.noEffectProperties(2, 3.2f));
    public static final DeferredItem<BaseFoodItem> HORSE_MEAT_CHUNKS = registerNormalFood("horse_meat_chunks", "马肉块", () -> DelightFoodProperties.noEffectProperties(2, 3.2f));
    public static final DeferredItem<BaseFoodItem> COOKED_SQUIRREL_MEAT_CHUNKS = registerNormalFood("cooked_squirrel_meat_chunks", "熟松鼠肉块", () -> DelightFoodProperties.noEffectProperties(2, 1.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_BIRD_MEAT_CHUNKS = registerNormalFood("cooked_bird_meat_chunks", "熟鸟肉块", () -> DelightFoodProperties.noEffectProperties(2, 1.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_DUCK_MEAT_CHUNKS = registerNormalFood("cooked_duck_meat_chunks", "熟鸭肉块", () -> DelightFoodProperties.noEffectProperties(2, 2.6f));
    public static final DeferredItem<BaseFoodItem> COOKED_FROG_MEAT_CHUNKS = registerNormalFood("cooked_frog_meat_chunks", "熟青蛙肉块", () -> DelightFoodProperties.noEffectProperties(2, 1.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_DONKEY_MEAT_CHUNKS = registerNormalFood("cooked_donkey_meat_chunks", "熟驴肉块", () -> DelightFoodProperties.noEffectProperties(4, 6.4f));
    public static final DeferredItem<BaseFoodItem> COOKED_HORSE_MEAT_CHUNKS = registerNormalFood("cooked_horse_meat_chunks", "熟马肉块", () -> DelightFoodProperties.noEffectProperties(4, 6.4f));
    public static final DeferredItem<BaseFoodItem> SQUIRREL_CITATAP = registerNormalFood("squirrel_citatap", "松鼠齐塔塔普", () -> DelightFoodProperties.noEffectProperties(1, 0.9f));
    public static final DeferredItem<BaseFoodItem> FLYING_FISH_ROE = registerNormalFood("flying_fish_roe", "飞鱼籽", () -> DelightFoodProperties.noEffectProperties(1, 0.1f));
    public static final DeferredItem<BaseFoodItem> FLYING_FISH_CAVIAR = registerNormalFood("flying_fish_caviar", "飞鱼鱼子酱", () -> DelightFoodProperties.noEffectProperties(4, 2, Items.GLASS_BOTTLE));
    public static final DeferredItem<BaseFoodItem> RAW_FLYING_FISH_MEAT = registerNormalFood("raw_flying_fish_meat", "生飞鱼肉",
            () -> DelightFoodProperties.hasEffectProperties(2, 2,
                    CDEffectData.of(MobEffects.JUMP, 200)));
    public static final DeferredItem<BaseFoodItem> RAW_FLYING_FISH_SLICES = registerNormalFood("raw_flying_fish_slices", "生飞鱼片",
            () -> DelightFoodProperties.hasEffectProperties(1, 1,
                    CDEffectData.of(MobEffects.JUMP, 100)));
    public static final DeferredItem<BaseFoodItem> RAW_ROSEWOOD_MEAT = registerNormalFood("raw_rosewood_meat", "生紫檀肉",
            () -> DelightFoodProperties.hasEffectProperties(5, 2,
                    CDEffectData.of(MobEffects.HUNGER, 600),
                    CDEffectData.of(MobEffects.POISON, 100)));
    public static final DeferredItem<BaseFoodItem> COOKED_ROSEWOOD_MEAT = registerNormalFood("cooked_rosewood_meat", "熟紫檀肉",
            () -> DelightFoodProperties.hasEffectProperties(8, 4,
                    CDEffectData.of(ModEffects.CURSED_INFERNO, 40),
                    CDEffectData.of(ModEffects.WRATH, 2400)));
    public static final DeferredItem<BaseFoodItem> RAW_PROLIFERATING_FLESH_AND_BLOOD = registerNormalFood("raw_proliferating_flesh_and_blood", "生增生血肉",
            () -> DelightFoodProperties.hasEffectProperties(3, 4,
                    CDEffectData.of(MobEffects.HUNGER, 600),
                    CDEffectData.of(MobEffects.POISON, 100)));
    public static final DeferredItem<BaseFoodItem> COOKED_PROLIFERATING_FLESH_AND_BLOOD = registerNormalFood("cooked_proliferating_flesh_and_blood", "熟增生血肉",
            () -> DelightFoodProperties.hasEffectProperties(5, 7,
                    CDEffectData.of(MobEffects.POISON, 40, 1),
                    CDEffectData.of(ModEffects.RAGE, 2400)));
    public static final DeferredItem<BaseFoodItem> BLOOD_TUMOR_FRUIT = registerNormalFood("blood_tumor_fruit", "血瘤果",
            () -> DelightFoodProperties.hasEffectProperties(1, 1,
                    CDEffectData.of(CDEffects.PARASITIC, Integer.MAX_VALUE)));
    public static final DeferredItem<BaseFoodItem> BUTTER = registerNormalFood("butter", "黄油",
            () -> DelightFoodProperties.hasEffectProperties(3, 4.5f,
                    CDEffectData.of(MobEffects.CONFUSION, 400)));
    public static final DeferredItem<BaseFoodItem> CARROT_CUBES = registerNormalFood("carrot_cubes", "胡萝卜丁", () -> DelightFoodProperties.noEffectProperties(1, 0.6f));
    public static final DeferredItem<BaseFoodItem> SWEET_CARROT_CUBES = registerNormalFood("sweet_carrot_cubes", "甜胡萝卜丁", () -> DelightFoodProperties.noEffectProperties(3, 3f));//TODO 温暖效果
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM = registerNormalFood("blackcurrant_jam", "黑醋栗果酱", () -> DelightFoodProperties.noEffectProperties(1, 1f, Items.GLASS_BOTTLE));
    public static final DeferredItem<BaseFoodItem> CLOUD_BREAD_SLICE = registerNormalFood("cloud_bread_slice", "云朵面包片",
            () -> DelightFoodProperties.hasEffectProperties(2, 6,
                    CDEffectData.of(MobEffects.LEVITATION, 140),
                    CDEffectData.of(MobEffects.SLOW_FALLING, 300),
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3000)));
    public static final DeferredItem<BaseFoodItem.BItem> WHITE_CHOCOLATE = registerNormalBlockItemFood("white_chocolate", "白巧克力", CDFoodBlocks.WHITE_CHOCOLATE_BLOCK,
            DelightFoodProperties.hasEffectProperties(2, 4,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 200)));
    public static final DeferredItem<BaseFoodItem.BItem> BLACK_CHOCOLATE = registerNormalBlockItemFood("black_chocolate", "黑巧克力", CDFoodBlocks.BLACK_CHOCOLATE_BLOCK,
            DelightFoodProperties.hasEffectProperties(2, 4,
                    CDEffectData.of(ModEffects.RAGE, 200),
                    CDEffectData.of(MobEffects.WEAKNESS, 200, 0.5f)));
    public static final DeferredItem<BaseFoodItem.BItem> FLAVORED_WHITE_CHOCOLATE = registerNormalBlockItemFood("flavored_white_chocolate", "调味白巧克力", CDFoodBlocks.FLAVORED_WHITE_CHOCOLATE_BLOCK,
            DelightFoodProperties.hasEffectProperties(4, 6,
                    CDEffectData.of(MobEffects.LUCK, 200),
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 200)));
    public static final DeferredItem<BaseFoodItem> VANILLA_POD = registerNormalFood("vanilla_pod", "香草荚", () -> DelightFoodProperties.noEffectProperties(1, 1));

    //小型成品
    public static final DeferredItem<BaseFoodItem> BUBBLE_GUM = registerNormalFood("bubble_gum", "泡泡糖",
            () -> DelightFoodProperties.hasEffectProperties(1, 1,
                    CDEffectData.of(MobEffects.JUMP, 200),
                    CDEffectData.of(MobEffects.SLOW_FALLING, 200)));
    public static final DeferredItem<BaseFoodItem> SUPER_BUBBLE_GUM = registerNormalFood("super_bubble_gum", "超级泡泡糖",
            () -> DelightFoodProperties.hasEffectProperties(4, 4,
                    CDEffectData.of(MobEffects.LEVITATION, 600)));
    public static final DeferredItem<BaseFoodItem> FLYING_FISH_SASHIMI = registerNormalFood("flying_fish_sashimi", "飞鱼生鱼片",
            () -> DelightFoodProperties.hasEffectProperties(5, 4,
                    CDEffectData.of(MobEffects.JUMP, 600, 1)));
    public static final DeferredItem<BaseFoodItem> MANGO_PUDDING = registerNormalFood("mango_pudding", "芒果布丁",
            () -> DelightFoodProperties.hasEffectProperties(6, 8,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> BANANA_PUDDING = registerNormalFood("banana_pudding", "香蕉布丁",
            () -> DelightFoodProperties.hasEffectProperties(6, 8,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> SLIME_DRAGON_PUDDING = registerNormalFood("slime_dragon_pudding", "小史龙布丁",
            () -> DelightFoodProperties.hasEffectProperties(7, 7,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 1020)));
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM_MANGO_PUDDING = registerNormalFood("blackcurrant_jam_mango_pudding", "黑醋栗果酱芒果布丁",
            () -> DelightFoodProperties.hasEffectProperties(16, 20,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 800),
                    CDEffectData.of(ModEffects.DANGER_SENSE, 800),
                    CDEffectData.of(ModEffects.SPELUNKER, 800)));
    public static final DeferredItem<BaseFoodItem> CRISPY_RICE_WITH_POTATOES = registerNormalFood("crispy_rice_with_potatoes", "锅巴土豆", () -> DelightFoodProperties.noEffectProperties(3, 1.8f));
    public static final DeferredItem<BaseFoodItem> ROYAL_GUMMY = registerNormalFood("royal_gummy", "皇家软糖",
            () -> DelightFoodProperties.hasEffectProperties(1, 4,
                    CDEffectData.of(MobEffects.HEALTH_BOOST, 1200),
                    CDEffectData.of(MobEffects.REGENERATION, 5400)));
    public static final DeferredItem<BaseFoodItem> BLACK_LUCK = registerToolTipFood("black_luck", "黑色幸运", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(4, 4f, CDEffectData.of(MobEffects.LUCK, 1800))
    ).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> WHITE_DAWN = registerToolTipFood("white_dawn", "白色曙光", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(4, 4f, CDEffectData.of(ModEffects.MANA_REGENERATION, 1800))
    ).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> GEMINI_LANDING_STAR = registerNormalFood("gemini_landing_star", "双子落星",
            () -> DelightFoodProperties.hasEffectProperties(7, 6,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 900)));
    public static final DeferredItem<Item> SPECIAL_MUSHROOM_SOUP = normalItemRegister("special_mushroom_soup", "特色蘑菇汤", SpecialMushroomSoup::new);

    //大型成品
    public static final DeferredItem<Item> OVERLOADED_BREAD = normalItemRegister("overloaded_bread", "过载面包", OverloadedBreadItem::new);
    public static final DeferredItem<BaseFoodItem.BItem> BLACKCURRANT_DARK_CHOCOLATE_PIE = registerNormalBlockItemFood("blackcurrant_dark_chocolate_pie", "黑醋栗黑巧克力派", CDFoodBlocks.BLACKCURRANT_DARK_CHOCOLATE_PIE_BLOCK,
            DelightFoodProperties.hasEffectProperties(12, 12,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 600),
                    CDEffectData.of(MobEffects.LUCK, 600),
                    CDEffectData.of(ModEffects.MAGIC_POWER, 600)));
    public static final DeferredItem<BaseFoodItem> BUTTER_FRIED_CLOUD_BREAD_SLICES = registerFood("butter_fried_cloud_bread_slices", "黄油煎云朵面包片", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(5, 10f, CDEffectData.of(MobEffects.SLOW_FALLING, 300))
    ).duration(d -> 25).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT));
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM_BREAD = registerNormalFood("blackcurrant_jam_bread", "黑醋栗果酱面包",
            () -> DelightFoodProperties.hasEffectProperties(5, 8,
                    CDEffectData.of(MobEffects.LUCK, 300),
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> CLOUD_BACON_SANDWICH = registerNormalFood("cloud_bacon_sandwich", "云朵培根三明治",
            () -> DelightFoodProperties.hasEffectProperties(10, 12.5f,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 2400),
                    CDEffectData.of(MobEffects.SLOW_FALLING, 600)));
    public static final DeferredItem<BaseFoodItem> CLOUD_VEGETABLES_SANDWICH = registerNormalFood("cloud_vegetables_sandwich", "云朵蔬菜三明治",
            () -> DelightFoodProperties.hasEffectProperties(8, 9.2f,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 1200, 1),
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 1200, 1)));
    public static final DeferredItem<BaseFoodItem> CLOUD_GEL_SANDWICH = registerNormalFood("cloud_gel_sandwich", "云朵凝胶三明治",
            () -> DelightFoodProperties.hasEffectProperties(6, 8.2f,
                    CDEffectData.of(MobEffects.REGENERATION, 600),
                    CDEffectData.of(MobEffects.LEVITATION, 600),
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 600)));
    public static final DeferredItem<BaseFoodItem> CLOUD_FRIED_EGG_GEL_SANDWICH = registerNormalFood("cloud_fried_egg_gel_sandwich", "云朵煎蛋凝胶三明治",
            () -> DelightFoodProperties.hasEffectProperties(8, 10.5f,
                    CDEffectData.of(MobEffects.REGENERATION, 600),
                    CDEffectData.of(MobEffects.LEVITATION, 600),
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 600)));
    public static final DeferredItem<BaseFoodItem.BItem> BRAISED_BEEF_RICE = registerBlockItemFood("braised_beef_rice", "红烧牛肉饭", builder -> builder.stackTo(1).food(
            DelightFoodProperties.hasEffectProperties(18, 20, Items.BOWL,
                    CDEffectData.of(MobEffects.REGENERATION, 600),
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
                    CDEffectData.of(MobEffects.DAMAGE_BOOST, 600, 1))
    ).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), CDFoodBlocks.BRAISED_BEEF_RICE_BLOCK);
    public static final DeferredItem<BaseFoodItem.BItem> BRAISED_BEEF_NOODLES = registerBlockItemFood("braised_beef_noodles", "红烧牛肉面", builder -> builder.stackTo(1).food(
            DelightFoodProperties.hasEffectProperties(18, 20, Items.BOWL,
                    CDEffectData.of(MobEffects.REGENERATION, 600),
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
                    CDEffectData.of(MobEffects.DAMAGE_RESISTANCE, 600))
    ).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), CDFoodBlocks.BRAISED_BEEF_NOODLES_BLOCK);
    public static final DeferredItem<BaseFoodItem> SPICY_BOMB_FISH = registerNormalFood("spicy_bomb_fish", "香辣炸弹鱼", () -> DelightFoodProperties.noEffectProperties(10, 12));
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_COPPER_COIN = registerToolTipFood("luck_chocolate_copper_coin", "幸运巧克力铜币", builder -> builder.rarity(ModRarity.GREEN).food(
                    DelightFoodProperties.hasEffectProperties(4, 6,
                            CDEffectData.of(CDEffects.LUCK_COIN, 600)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_SILVER_COIN = registerToolTipFood("luck_chocolate_silver_coin", "幸运巧克力银币", builder -> builder.rarity(ModRarity.GREEN).food(
                    DelightFoodProperties.hasEffectProperties(4, 6,
                            CDEffectData.of(CDEffects.LUCK_COIN, 900, 1)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_GOLDEN_COIN = registerToolTipFood("luck_chocolate_golden_coin", "幸运巧克力金币", builder -> builder.rarity(ModRarity.GREEN).food(
                    DelightFoodProperties.hasEffectProperties(4, 6,
                            CDEffectData.of(CDEffects.LUCK_COIN, 1200, 2)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_PLATINUM_COIN = registerToolTipFood("luck_chocolate_platinum_coin", "幸运巧克力铂金币", builder -> builder.rarity(ModRarity.BLUE).food(
                    DelightFoodProperties.hasEffectProperties(4, 6,
                            CDEffectData.of(CDEffects.LUCK_COIN, 1800, 3)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_EMERALD_COIN = registerToolTipFood("luck_chocolate_emerald_coin", "幸运巧克力绿宝石币", builder -> builder.rarity(ModRarity.BLUE).food(
                    DelightFoodProperties.hasEffectProperties(4, 6,
                            CDEffectData.of(CDEffects.LUCK_COIN, 600),
                            CDEffectData.of(CDEffects.MASTER_TRADER, 1800)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> CHOCOLATE_LUCK_COIN_BOX = registerToolTipFood("chocolate_luck_coin_box", "巧克力幸运币礼盒", builder -> builder.rarity(ModRarity.ORANGE).food(
                    DelightFoodProperties.hasEffectProperties(20, 20,
                            CDEffectData.of(CDEffects.LUCK_COIN, 2400, 4)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GOLD);
    public static final DeferredItem<BaseFoodItem> GILDED_LUXURY_CHOCOLATE_LUCK_COIN_BOX = registerToolTipFood("gilded_luxury_chocolate_luck_coin_box", "镀金奢华巧克力幸运币礼盒", builder -> builder.rarity(ModRarity.EXPERT).food(
                    DelightFoodProperties.hasEffectProperties(20, 20,
                            CDEffectData.of(CDEffects.LUCK_COIN, 10800, 4),
                            CDEffectData.of(MobEffects.DAMAGE_RESISTANCE, 10800, 2),
                            CDEffectData.of(MobEffects.HEALTH_BOOST, 10800, 2),
                            CDEffectData.of(MobEffects.ABSORPTION, 10800, 2),
                            CDEffectData.of(MobEffects.DAMAGE_BOOST, 10800, 2),
                            CDEffectData.of(MobEffects.REGENERATION, 10800, 1)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GOLD);
    public static final DeferredItem<BaseFoodItem> HONEY_GLAZED_HAM = registerNormalFood("honey_glazed_ham", "蜜汁火腿",
            () -> DelightFoodProperties.hasEffectProperties(16, 12.6f,
                    CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 600),
                    CDEffectData.of(MobEffects.REGENERATION, 600),
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 300, 1)));
    public static final DeferredItem<BaseFoodItem> BRAISED_CHICKEN = registerToolTipFood("braised_chicken", "扒鸡", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(16, 20,
                            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 4600, 1),
                            CDEffectData.of(ModEffects.CHOKING, 4000)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> BIG_CHICKEN_CUTLET = registerToolTipFood("big_chicken_cutlet", "大鸡排", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(12, 16,
                            CDEffectData.of(ModEffects.CHOKING, 2000)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<Item> HOT_STAR_CHICKEN = normalItemRegister("hot_star_chicken", "豪大大鸡排", HotStarChickenItem::new);
    public static final DeferredItem<BaseFoodItem> FLYING_FISH_SOUP = registerNormalFood("flying_fish_soup", "飞鱼汤",
            () -> DelightFoodProperties.hasEffectProperties(12, 8, Items.BOWL,
                    CDEffectData.of(MobEffects.JUMP, 1200, 1),
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 1200)));
    public static final DeferredItem<BaseFoodItem> FLYING_FISH_SHARK_FIN_SOUP = registerNormalFood("flying_fish_shark_fin_soup", "飞鱼鱼翅汤",
            () -> DelightFoodProperties.hasEffectProperties(3, 1, Items.BOWL,
                    CDEffectData.of(MobEffects.SLOW_FALLING, 1800)));
    public static final DeferredItem<BaseFoodItem.BItem> CHICKEN_HOT_POT = registerNormalBlockItemFood("chicken_hot_pot", "鸡公煲", CDFoodBlocks.CHICKEN_HOT_POT_BLOCK,
            ModFoodProperties.PlentySatisfiedProperties(6000, 20, 20, Items.BOWL));
    public static final DeferredItem<BaseFoodItem> DONKEY_MEAT_FIRE = registerNormalFood("donkey_meat_fire", "驴肉火烧",
            () -> DelightFoodProperties.hasEffectProperties(10, 12f,
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 3600)));
    public static final DeferredItem<BaseFoodItem> HORSE_MEAT_SASHIMI = registerNormalFood("horse_meat_sashimi", "马肉刺身",
            () -> DelightFoodProperties.hasEffectProperties(8, 9.2f,
                    CDEffectData.of(MobEffects.MOVEMENT_SPEED, 3600, 1)));
    public static final DeferredItem<Item> HARVEST_STEW = normalItemRegister("harvest_stew", "丰收大锅炖", HarvestStewItem::new);
    public static final DeferredItem<Item> THE_MEAL_OF_LIFE = normalItemRegister("the_meal_of_life", "晶髓金菇膳", TheMealOfLifeItem::new);
    public static final DeferredItem<BaseFoodItem> GRASS_SEED_SOUP = registerToolTipFood("grass_seed_soup", "草籽汤", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(6, 6, Items.BOWL,
                            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 900)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> MUSHROOM_PLATTER = registerToolTipFood("mushroom_platter", "菌类拼盘", builder -> builder.food(
                    DelightFoodProperties.noEffectProperties(9, 8, Items.BOWL))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> BLOOD_RED_PORK_RIB_SOUP = registerToolTipFood("blood_red_pork_rib_soup", "血红排骨汤", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(10, 10, Items.BOWL,
                            CDEffectData.of(MobEffects.POISON, 200),
                            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 5200)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);

    //永久增益
    public static final DeferredItem<CDEverBeneficialItem> UTILITY_APPLE = normalItemRegister("utility_apple", "效用苹果", () -> new CDEverBeneficialItem(
            CDEverBeneficialItem.UTILITY_APPLE, () -> DelightFoodProperties.hasEffectProperties(5, 10,
            CDEffectData.of(MobEffects.DIG_SPEED, 600, 2)), CDEverBeneficialItem.getTooltipsFromString("utility_apple", 1, ChatFormatting.RED)));
    public static final DeferredItem<CDEverBeneficialItem> SPEEDY_COKE = normalItemRegister("speedy_coke", "疾行可乐", () -> new CDEverBeneficialItem(
            SoundEvents.GENERIC_DRINK, UseAnim.DRINK, CDEverBeneficialItem.SPEEDY_COKE, () -> DelightFoodProperties.hasEffectProperties(1, 1,
            CDEffectData.of(MobEffects.MOVEMENT_SPEED, 9600, 1)), CDEverBeneficialItem.getTooltipsFromString("speedy_coke", 2, ChatFormatting.WHITE)));
    public static final DeferredItem<CDEverBeneficialItem> EZConstant = normalItemRegister("ez_constant", "59160153", () -> new CDEverBeneficialItem(
            CDEverBeneficialItem.EZ_CONSTANT, () -> DelightFoodProperties.hasEffectProperties(9, 1,
            CDEffectData.of(CDEffects.LUCK_COIN, 12000, 4)), CDEverBeneficialItem.getTooltipsFromString("ez_constant", 2, ChatFormatting.GOLD)));

    //饮品
    public static final DeferredItem<BaseFoodItem> APRICOT_JUICE = registerDrinkingFood("apricot_juice", "杏汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(MobEffects.REGENERATION, 300),
                    CDEffectData.of(CDEffects.POISON_IMMUNE, 300)));
    public static final DeferredItem<BaseFoodItem> BANANA_JUICE = registerDrinkingFood("banana_juice", "香蕉汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 1.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 300)));
    public static final DeferredItem<BaseFoodItem> CHERRY_JUICE = registerDrinkingFood("cherry_juice", "樱桃汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(MobEffects.NIGHT_VISION, 300)));
    public static final DeferredItem<BaseFoodItem> COCONUT_JUICE = registerDrinkingFood("coconut_juice", "椰汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 1.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 300)));
    public static final DeferredItem<BaseFoodItem> DRAGON_FRUIT_JUICE = registerDrinkingFood("dragon_fruit_juice", "火龙果汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600, 1),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 600),
                    CDEffectData.of(MobEffects.DAMAGE_RESISTANCE, 300)));
    public static final DeferredItem<BaseFoodItem> GRAPEFRUIT_JUICE = registerDrinkingFood("grapefruit_juice", "葡萄柚汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600, 1),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 600),
                    CDEffectData.of(CDEffects.MINING_FATIGUE_IMMUNE, 300)));
    public static final DeferredItem<BaseFoodItem> LEMON_JUICE = registerDrinkingFood("lemon_juice", "柠檬汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.IMMUNITY, 1200)));
    public static final DeferredItem<BaseFoodItem> MANGO_JUICE = registerDrinkingFood("mango_juice", "芒果汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.IMMUNITY, 1200)));
    public static final DeferredItem<BaseFoodItem> PEACH_JUICE = registerDrinkingFood("peach_juice", "桃汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.POISON_IMMUNE, 1200)));
    public static final DeferredItem<BaseFoodItem> PINEAPPLE_JUICE = registerDrinkingFood("pineapple_juice", "菠萝汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600)));
    public static final DeferredItem<BaseFoodItem> PLUM_JUICE = registerDrinkingFood("plum_juice", "李子汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.IMMUNITY, 1200)));
    public static final DeferredItem<BaseFoodItem> GRAPE_JUICE = registerDrinkingFood("grape_juice", "葡萄汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600, 1),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 600),
                    CDEffectData.of(MobEffects.NIGHT_VISION, 1200)));
    public static final DeferredItem<BaseFoodItem> STAR_FRUIT_JUICE = registerDrinkingFood("star_fruit_juice", "杨桃汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600, 1),
                    CDEffectData.of(ModEffects.HUNGER_DELAYED, 600)));
    public static final DeferredItem<BaseFoodItem> POMEGRANATE_JUICE = registerDrinkingFood("pomegranate_juice", "番石榴汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.IMMUNITY, 1800)));
    public static final DeferredItem<BaseFoodItem> RAMBUTAN_JUICE = registerDrinkingFood("rambutan_juice", "红毛丹汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.BLINDNESS_IMMUNE, 1200)));
    public static final DeferredItem<BaseFoodItem> BLOOD_ORANGE_JUICE = registerDrinkingFood("blood_orange_juice", "血橙汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.BLEEDING_IMMUNE, 1200)));
    public static final DeferredItem<BaseFoodItem> ELDERBERRY_JUICE = registerDrinkingFood("elderberry_juice", "接骨木果汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 0.5f, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 3600),
                    CDEffectData.of(CDEffects.IMMUNITY, 1200)));
    public static final DeferredItem<BaseFoodItem> GEL_JUICE = registerDrinkingFood("gel_juice", "凝胶果汁",
            () -> DelightFoodProperties.hasEffectProperties(4, 6, Items.GLASS_BOTTLE,
                    CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 1200),
                    CDEffectData.of(ModEffects.WATER_WALKING, 1200)));
    public static final DeferredItem<BaseFoodItem> FRESHLY_SQUEEZED_VITALITY = registerToolTipFood("freshly_squeezed_vitality", "活力鲜榨", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(3, 5, Items.GLASS_BOTTLE,
                            CDEffectData.of(CDEffects.DINE, 1800)))
            .duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> ATLANTIS_TSUNAMI = registerToolTipFood("atlantis_tsunami", "大西洋冲击波", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(3, 0.5f, PotionItems.MUG,
                            CDEffectData.of(MobEffects.MOVEMENT_SPEED, 1200, 1),
                            CDEffectData.of(MobEffects.CONFUSION, 400)))
            .duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> ASH_TEA = registerDrinkingFood("ash_tea", "灰烬茶",
            () -> DelightFoodProperties.hasEffectProperties(4, 5,
                    CDEffectData.of(MobEffects.FIRE_RESISTANCE, 1200),
                    CDEffectData.of(CDEffects.SOUL_SAND_SLOWS_DOWN_IMMUNE, 1800)));

    public static <I extends Item> DeferredItem<I> normalItemRegister(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static <I extends BlockItem> DeferredItem<I> normalBItemRegister(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64);
            consumer.accept(builder);
            return new BaseFoodItem(builder);
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerToolTipFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer, int line, ChatFormatting chatFormatting) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).tooltip(en, line, chatFormatting);
            consumer.accept(builder);
            return new BaseFoodItem(builder);
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerNormalFood(String en, String zh, Supplier<FoodProperties> foodProperties) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties.get()).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT);
            return new BaseFoodItem(builder);
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem.BItem> registerNormalBlockItemFood(String en, String zh, Supplier<? extends Block> block, FoodProperties foodProperties) {
        DeferredItem<BaseFoodItem.BItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT);
            return new BaseFoodItem.BItem(block.get(), builder.getProperties());
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem.BItem> registerBlockItemFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer, Supplier<? extends Block> block) {
        DeferredItem<BaseFoodItem.BItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64);
            consumer.accept(builder);
            return new BaseFoodItem.BItem(block.get(), builder.getProperties());
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerDrinkingFood(String en, String zh, Supplier<FoodProperties> foodProperties) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties.get()).duration(d -> 15).useAnim(u -> UseAnim.DRINK).drinkingSound(s -> SoundEvents.GENERIC_DRINK);
            return new BaseFoodItem(builder);
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }
}
