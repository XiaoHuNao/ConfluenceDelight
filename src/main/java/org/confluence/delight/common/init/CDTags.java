package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.confluence.delight.ConfluenceDelight;


public final class CDTags {
    public static class Fluids {
        public static final TagKey<Fluid> WINE = c("wine");

        private static TagKey<Fluid> register(String id) {
            return FluidTags.create(ConfluenceDelight.asResource(id));
        }

        private static TagKey<Fluid> c(String id) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath("c", id));
        }
    }

    public static class Blocks {
        private static TagKey<Block> register(String id) {
            return BlockTags.create(ConfluenceDelight.asResource(id));
        }
    }

    public static class Items {
        public static final TagKey<Item> FRUIT_MANGO = register("mango");
        public static final TagKey<Item> FRUIT_PINEAPPLE = register("pineapple");
        public static final TagKey<Item> FRUIT_LEMON = register("lemon");

        private static TagKey<Item> c(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static TagKey<Item> register(String id) {
            return ItemTags.create(ConfluenceDelight.asResource(id));
        }
    }
}
