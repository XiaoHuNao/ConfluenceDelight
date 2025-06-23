package org.confluence.delight.common.init;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.food.BlackCurrantDarkChocolatePieBlock;
import org.confluence.delight.common.block.food.ChickenHotPotBlock;

import java.util.function.Supplier;

public class CDFoodBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);

    //food
    public static final DeferredBlock<ChickenHotPotBlock> CHICKEN_HOT_POT = registerWithoutItem("chicken_hot_pot", ChickenHotPotBlock::new);
    public static final DeferredBlock<BlackCurrantDarkChocolatePieBlock> BLACKCURRANT_DARK_CHOCOLATE_PIE = registerWithoutItem("blackcurrant_dark_chocolate_pie", BlackCurrantDarkChocolatePieBlock::new);

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, Supplier<B> bl) {
        return BLOCKS.register(en, bl);
    }

}
