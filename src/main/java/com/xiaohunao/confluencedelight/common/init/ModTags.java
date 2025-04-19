package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public final class ModTags {
    public static class Blocks {

        private static TagKey<Block> tag(String id) {
            return BlockTags.create(ConfluenceDelight.asResource(id));
        }
    }

    public static class Items {
        private static TagKey<Item> tag(String id) {
            return ItemTags.create(ConfluenceDelight.asResource(id));
        }
    }
}
