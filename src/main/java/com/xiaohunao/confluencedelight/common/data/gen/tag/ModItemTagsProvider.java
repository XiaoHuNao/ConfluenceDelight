package com.xiaohunao.confluencedelight.common.data.gen.tag;

import com.xiaohunao.confluencedelight.common.init.ModMaterialItems;
import com.xiaohunao.confluencedelight.common.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.MODID;


public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> b, @Nullable ExistingFileHelper helper) {
        super(output, provider, b, MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.Items.ICE).add(Items.ICE, Items.BLUE_ICE, Items.SNOWBALL,Items.SNOW_BLOCK,Items.SNOW);
        tag(ModTags.Items.CONTAINER).add(ModMaterialItems.SUN_PLATE_BOWL.get());
    }
}
