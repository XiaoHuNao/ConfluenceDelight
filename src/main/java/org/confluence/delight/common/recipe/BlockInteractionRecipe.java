package org.confluence.delight.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDRecipes;

import java.util.List;

public record BlockInteractionRecipe(Ingredient inputItem, Block[] sourceBlocks, Block resultBlock) implements Recipe<BlockInteractionRecipe.Inventory> {

    public boolean matchesBlock(BlockState state) {
        for (Block block : sourceBlocks) {
            if (state.getBlock() == block) return true;
        }
        return false;
    }

    public boolean matchesItem(ItemStack stack) {
        return inputItem.test(stack);
    }

    public boolean transformBlock(Level level, BlockPos pos, ItemStack usedItem, Player player) {
        if (level.isClientSide) return false;
        BlockState currentState = level.getBlockState(pos);
        if (matchesBlock(currentState)) {
            if (!player.isCreative()) {
                usedItem.shrink(1);
            }
            level.setBlockAndUpdate(pos, resultBlock.defaultBlockState());
            return true;
        }
        return false;
    }

    @Override
    public boolean matches(Inventory inv, Level level) {
        return !inv.isEmpty() && matchesItem(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(Inventory inventory, HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CDRecipes.BLOCK_INTERACTION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CDRecipes.BLOCK_INTERACTION_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<BlockInteractionRecipe> {
        public static final MapCodec<BlockInteractionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("input").forGetter(BlockInteractionRecipe::inputItem),
                BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("source_blocks").forGetter(recipe -> List.of(recipe.sourceBlocks)),
                BuiltInRegistries.BLOCK.byNameCodec().fieldOf("result_block").forGetter(BlockInteractionRecipe::resultBlock)
                ).apply(instance, (input, sourceBlocks, resultBlock) -> new BlockInteractionRecipe(input, sourceBlocks.toArray(new Block[0]), resultBlock))
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, BlockInteractionRecipe> STREAM_CODEC = StreamCodec.of(BlockInteractionRecipe.Serializer::toNetwork, BlockInteractionRecipe.Serializer::fromNetwork);

        @Override
        public MapCodec<BlockInteractionRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BlockInteractionRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static BlockInteractionRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient inputItem = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            int count = buffer.readVarInt();
            Block[] sourceBlocks = new Block[count];
            for (int i = 0; i < count; i++) {
                sourceBlocks[i] = BuiltInRegistries.BLOCK.byId(buffer.readVarInt());
            }
            Block resultBlock = BuiltInRegistries.BLOCK.byId(buffer.readVarInt());
            return new BlockInteractionRecipe(inputItem, sourceBlocks, resultBlock);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, BlockInteractionRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.inputItem);
            buffer.writeVarInt(recipe.sourceBlocks.length);
            for (Block block : recipe.sourceBlocks) {
                buffer.writeVarInt(BuiltInRegistries.BLOCK.getId(block));
            }
            buffer.writeVarInt(BuiltInRegistries.BLOCK.getId(recipe.resultBlock));
        }
    }

    public static class Inventory implements RecipeInput {
        private final ItemStack[] items;

        public Inventory(ItemStack[] items) {
            this.items = items;
        }

        @Override
        public ItemStack getItem(int index) {
            return items[index];
        }

        @Override
        public int size() {
            return items.length;
        }

        public boolean isEmpty() {
            return items.length == 0 || (items.length == 1 && items[0].isEmpty());
        }
    }
}
