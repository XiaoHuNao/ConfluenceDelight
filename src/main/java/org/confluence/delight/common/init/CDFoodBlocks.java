package org.confluence.delight.common.init;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.food.BaseFoodBlock;
import org.confluence.delight.common.block.food.BlackCurrantDarkChocolatePieBlock;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.init.ModEffects;

import java.util.function.Supplier;

public class CDFoodBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);

    public static final DeferredBlock<BaseFoodBlock> WHITE_CHOCOLATE_BLOCK = registerWithoutItem("white_chocolate", () -> new BaseFoodBlock(2, 4,
            CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 200)));
    public static final DeferredBlock<BaseFoodBlock> BLACK_CHOCOLATE_BLOCK = registerWithoutItem("black_chocolate", () -> new BaseFoodBlock(2, 4,
            CDEffectData.of(ModEffects.RAGE, 200),
            CDEffectData.of(MobEffects.WEAKNESS, 200, 0.5f)));
    public static final DeferredBlock<BaseFoodBlock> FLAVORED_WHITE_CHOCOLATE_BLOCK = registerWithoutItem("flavored_white_chocolate", () -> new BaseFoodBlock(4, 6,
            CDEffectData.of(MobEffects.LUCK, 200),
            CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 200)));
    public static final DeferredBlock<BaseFoodBlock> CHICKEN_HOT_POT_BLOCK = registerWithoutItem("chicken_hot_pot", () -> new BaseFoodBlock(20, 20.0f,
            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 6000, 1),
            CDEffectData.of(ModEffects.HUNGER_DELAYED, 1000)));
    public static final DeferredBlock<BlackCurrantDarkChocolatePieBlock> BLACKCURRANT_DARK_CHOCOLATE_PIE_BLOCK = registerWithoutItem("blackcurrant_dark_chocolate_pie", BlackCurrantDarkChocolatePieBlock::new);
    public static final DeferredBlock<BaseFoodBlock> BRAISED_BEEF_RICE_BLOCK = registerWithoutItem("braised_beef_rice", () -> new BaseFoodBlock(18, 20.0f, Items.BOWL,
            CDEffectData.of(MobEffects.REGENERATION, 600),
            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
            CDEffectData.of(MobEffects.DAMAGE_BOOST, 600, 1)));
    public static final DeferredBlock<BaseFoodBlock> BRAISED_BEEF_NOODLES_BLOCK = registerWithoutItem("braised_beef_noodles", () -> new BaseFoodBlock(18, 20.0f, Items.BOWL,
            CDEffectData.of(MobEffects.REGENERATION, 600),
            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
            CDEffectData.of(MobEffects.DAMAGE_RESISTANCE, 600)));
    public static final DeferredBlock<BaseFoodBlock> THE_MEAL_OF_LIFE_BLOCK = registerWithoutItem("the_meal_of_life", () -> new BaseFoodBlock(18, 36.0f, Items.BOWL,
            CDEffectData.of(MobEffects.REGENERATION, 600, 1),
            CDEffectData.of(MobEffects.HEALTH_BOOST, 500, 4),
            CDEffectData.of(MobEffects.ABSORPTION, 2400),
            CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 18000)));
    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, Supplier<B> bl) {
        return BLOCKS.register(en, bl);
    }

}
