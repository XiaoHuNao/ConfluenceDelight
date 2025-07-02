package org.confluence.delight.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.lib.common.recipe.AbstractAmountRecipe;

public class MillStoneRecipe extends AbstractAmountRecipe<MillStoneRecipe.Input> {
    private final int workCircles;


    public MillStoneRecipe(ItemStack result, NonNullList<Ingredient> ingredients, int workCircles) {
        super(result, ingredients);
        this.workCircles = workCircles;
    }

    @Override
    public boolean matches(Input input, Level level) {
        ItemStack[] items = input.items;
        for (ItemStack item : items) {
            for (Ingredient ingredient : ingredients) {
                if (ingredient.test(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getWorkCircles() {
        return workCircles;
    }

    @Override
    protected int maxIngredientSize() {
        return 2;
    }

    @Override
    public String getGroup() {
        return "millstone";
    }

    @Override
    public ItemStack getToastSymbol() {
        return CDBlocks.MILLSTONE_BLOCK.toStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CDRecipes.MILLSTONE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CDRecipes.MILLSTONE_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<MillStoneRecipe> {
        public static final MapCodec<MillStoneRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                INGREDIENTS_CODEC.forGetter(recipe -> recipe.ingredients),
                Codec.INT.fieldOf("work_circles").forGetter(recipe -> recipe.workCircles)
        ).apply(instance, MillStoneRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MillStoneRecipe> STREAM_CODEC = StreamCodec.of(MillStoneRecipe.Serializer::toNetwork, MillStoneRecipe.Serializer::fromNetwork);

        @Override
        public MapCodec<MillStoneRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MillStoneRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static MillStoneRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int workCircles = buffer.readVarInt();
            return new MillStoneRecipe(result, ingredients, workCircles);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, MillStoneRecipe recipe) {
            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeVarInt(recipe.getWorkCircles());
        }
    }

    public static class Input implements RecipeInput {
        private final ItemStack[] items;

        public Input(ItemStack[] items) {
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
    }
}
