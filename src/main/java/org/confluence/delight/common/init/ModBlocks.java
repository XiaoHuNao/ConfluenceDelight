package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.crafting.PickleJarsBlock;
import org.confluence.delight.common.block.crafting.PickleJarsBlockEntity;
import org.confluence.delight.common.block.food.ChickenHotPotBlock;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ConfluenceDelight.MODID);

    public static final DeferredBlock<ChickenHotPotBlock> CHICKEN_HOT_POT = registerWithoutItem("chicken_hot_pot", ChickenHotPotBlock::new);
    public static final DeferredBlock<PickleJarsBlock> PICKLE_JARS_BLOCK = registerBlockItem("pickle_jars_block", "泡菜罐", () -> new PickleJarsBlock(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<BlockEntityType<PickleJarsBlockEntity>>  PICKLE_JARS_BLOCK_ENTITY = BLOCK_ENTITIES.register("pickle_jars_block", () -> BlockEntityType.Builder.of(PickleJarsBlockEntity::new, PICKLE_JARS_BLOCK.get()).build(null));

    //流体
    public static final DeferredBlock<LiquidBlock> WINE = registerWithoutItem("wine", "酒", () -> new LiquidBlock(ModFluids.WINE.fluid().get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(DyeColor.byId(0xFFFFFF))));

    public static DeferredHolder<Block, Block> register(final String en, final String zh) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, () -> new Block(BlockBehaviour.Properties.of()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, Supplier<B> bl) {
        return BLOCKS.register(en, bl);
    }

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, final String zh, Supplier<B> bl) {
        DeferredBlock<B> block = BLOCKS.register(en, bl);
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerBlockItem(final String en, final String zh, Supplier<B> bl) {
        DeferredBlock<B> block = BLOCKS.register(en, bl);
        ModMaterialItems.register(en, zh, () -> new BlockItem(block.get(), new Item.Properties()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }
}
