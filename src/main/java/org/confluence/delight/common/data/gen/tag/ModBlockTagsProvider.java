package org.confluence.delight.common.data.gen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.init.CDTags;
import org.confluence.mod.common.init.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.ConfluenceDelight.MODID;


public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get(),
                CDBlocks.MILLSTONE_BLOCK.get(),
                CDBlocks.JUICER_BLOCK.get()
        );
        tag(ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get(),
                CDBlocks.MILLSTONE_BLOCK.get(),
                CDBlocks.JUICER_BLOCK.get()
        ).add(crate);
        tag(BlockTags.MINEABLE_WITH_AXE).add(crate);
        tag(ModTags.Blocks.MINEABLE_WITH_HAMAXE).add(crate);
        tag(BlockTags.CAULDRONS).add(
                CDBlocks.WINE_CAULDRON.get()
        );
        tag(BlockTags.SAPLINGS).add(sapling);
        tag(ModTags.Blocks.MINEABLE_WITH_HOE_SHOVEL).add(leaves);
        tag(BlockTags.LEAVES).add(leaves);
        tag(BlockTags.MINEABLE_WITH_HOE).add(leaves);
        tag(BlockTags.LOGS).add(
                CDNaturalBlocks.COCONUT_TREE_TOP_BLOCK.get(),
                CDNaturalBlocks.BANANA_TRUNK_BLOCK.get()
        );
        tag(Tags.Blocks.STORAGE_BLOCKS).add(
                CDBlocks.BLACKCURRANT_CRATE.get(),
                CDBlocks.BLOOD_ORANGE_CRATE.get(),
                CDBlocks.ELDERBERRY_CRATE.get(),
                CDBlocks.APRICOT_CRATE.get(),
                CDBlocks.BANANA_CRATE.get(),
                CDBlocks.CHERRY_CRATE.get(),
                CDBlocks.COCONUT_CRATE.get(),
                CDBlocks.DRAGON_FRUIT_CRATE.get(),
                CDBlocks.GRAPE_FRUIT_CRATE.get(),
                CDBlocks.LEMON_CRATE.get(),
                CDBlocks.MANGO_CRATE.get(),
                CDBlocks.PEACH_CRATE.get(),
                CDBlocks.PINEAPPLE_CRATE.get(),
                CDBlocks.PLUM_CRATE.get(),
                CDBlocks.SPICY_PEPPER_CRATE.get(),
                CDBlocks.STAR_FRUIT_CRATE.get(),
                CDBlocks.POMEGRANATE_CRATE.get(),
                CDBlocks.RAMBUTAN_CRATE.get()
        );

        tag(CDTags.Blocks.BLACKCURRANT_CRATE).add(CDBlocks.BLACKCURRANT_CRATE.get());
        tag(CDTags.Blocks.BLOOD_ORANGE_CRATE).add(CDBlocks.BLOOD_ORANGE_CRATE.get());
        tag(CDTags.Blocks.ELDERBERRY_CRATE).add(CDBlocks.ELDERBERRY_CRATE.get());
        tag(CDTags.Blocks.APRICOT_CRATE).add(CDBlocks.APRICOT_CRATE.get());
        tag(CDTags.Blocks.BANANA_CRATE).add(CDBlocks.BANANA_CRATE.get());
        tag(CDTags.Blocks.CHERRY_CRATE).add(CDBlocks.CHERRY_CRATE.get());
        tag(CDTags.Blocks.COCONUT_CRATE).add(CDBlocks.COCONUT_CRATE.get());
        tag(CDTags.Blocks.DRAGON_FRUIT_CRATE).add(CDBlocks.DRAGON_FRUIT_CRATE.get());
        tag(CDTags.Blocks.GRAPE_FRUIT_CRATE).add(CDBlocks.GRAPE_FRUIT_CRATE.get());
        tag(CDTags.Blocks.LEMON_CRATE).add(CDBlocks.LEMON_CRATE.get());
        tag(CDTags.Blocks.MANGO_CRATE).add(CDBlocks.MANGO_CRATE.get());
        tag(CDTags.Blocks.PEACH_CRATE).add(CDBlocks.PEACH_CRATE.get());
        tag(CDTags.Blocks.PINEAPPLE_CRATE).add(CDBlocks.PINEAPPLE_CRATE.get());
        tag(CDTags.Blocks.PLUM_CRATE).add(CDBlocks.PLUM_CRATE.get());
        tag(CDTags.Blocks.SPICY_PEPPER_CRATE).add(CDBlocks.SPICY_PEPPER_CRATE.get());
        tag(CDTags.Blocks.STAR_FRUIT_CRATE).add(CDBlocks.STAR_FRUIT_CRATE.get());
        tag(CDTags.Blocks.POMEGRANATE_CRATE).add(CDBlocks.POMEGRANATE_CRATE.get());
        tag(CDTags.Blocks.RAMBUTAN_CRATE).add(CDBlocks.RAMBUTAN_CRATE.get());
    }

    Block[] leaves = new Block[]{
            CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get()
    };

    Block[] sapling = new Block[]{
            CDNaturalBlocks.APPLE_SAPLING.get(),
            CDNaturalBlocks.APRICOT_SAPLING.get(),
            CDNaturalBlocks.GRAPEFRUIT_SAPLING.get(),
            CDNaturalBlocks.LEMON_SAPLING.get(),
            CDNaturalBlocks.PEACH_SAPLING.get(),
            CDNaturalBlocks.CHERRY_SAPLING.get(),
            CDNaturalBlocks.PLUM_SAPLING.get(),
            CDNaturalBlocks.BLOOD_ORANGE_SAPLING.get(),
            CDNaturalBlocks.RAMBUTAN_SAPLING.get(),
            CDNaturalBlocks.MANGO_SAPLING.get(),
            CDNaturalBlocks.BANANA_SAPLING.get(),
            CDNaturalBlocks.COCONUT_SAPLING.get(),
            CDNaturalBlocks.STAR_FRUIT_SAPLING.get(),
            CDNaturalBlocks.POMEGRANATE_SAPLING.get()
    };

    Block[] crate = new Block[]{
            CDBlocks.BLACKCURRANT_CRATE.get(),
            CDBlocks.BLOOD_ORANGE_CRATE.get(),
            CDBlocks.ELDERBERRY_CRATE.get(),
            CDBlocks.APRICOT_CRATE.get(),
            CDBlocks.BANANA_CRATE.get(),
            CDBlocks.CHERRY_CRATE.get(),
            CDBlocks.COCONUT_CRATE.get(),
            CDBlocks.DRAGON_FRUIT_CRATE.get(),
            CDBlocks.GRAPE_FRUIT_CRATE.get(),
            CDBlocks.LEMON_CRATE.get(),
            CDBlocks.MANGO_CRATE.get(),
            CDBlocks.PEACH_CRATE.get(),
            CDBlocks.PINEAPPLE_CRATE.get(),
            CDBlocks.PLUM_CRATE.get(),
            CDBlocks.SPICY_PEPPER_CRATE.get(),
            CDBlocks.STAR_FRUIT_CRATE.get(),
            CDBlocks.POMEGRANATE_CRATE.get(),
            CDBlocks.RAMBUTAN_CRATE.get()
    };

    @Override
    public @NotNull IntrinsicTagAppender<Block> tag(@NotNull TagKey<Block> tag) {
        return super.tag(tag);
    }
}
