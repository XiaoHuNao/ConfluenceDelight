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
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.lib.common.recipe.AbstractAmountRecipe;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.common.recipe.SimpleRecipeSerializer;

public class ShimmerTransmutationRecipe extends AbstractAmountRecipe<ShimmerTransmutationRecipe.Transmutation> {
    private final int cookingTime;


    public ShimmerTransmutationRecipe(ItemStack result, NonNullList<Ingredient> ingredients, int cookingTime) {
        super(result, ingredients);
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    protected int maxIngredientSize() {
        return 4;
    }

    @Override
    public String getGroup() {
        return "shimmer_transmutation";
    }

    @Override
    public ItemStack getToastSymbol() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CDRecipes.SIMMER_TRANSMUTATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CDRecipes.SHIMMER_TRANSMUTATION_TYPE.get();
    }

    public static class Serializer extends SimpleRecipeSerializer<ShimmerTransmutationRecipe> {
        @Override
        protected MapCodec<ShimmerTransmutationRecipe> getCodec() {
            return RecordCodecBuilder.mapCodec(instance -> instance.group(
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                    INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                    Codec.INT.fieldOf("cooking_time").forGetter(recipe -> recipe.cookingTime)
            ).apply(instance, ShimmerTransmutationRecipe::new));
        }

        @Override
        protected StreamCodec<RegistryFriendlyByteBuf, ShimmerTransmutationRecipe> getStreamCodec() {
            return new StreamCodec<>() {
                @Override
                public ShimmerTransmutationRecipe decode(RegistryFriendlyByteBuf buffer) {
                    int size = buffer.readVarInt();
                    NonNullList<Ingredient> nonnulllist = NonNullList.withSize(size, AmountIngredient.EMPTY);
                    nonnulllist.replaceAll(ignore -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
                    ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
                    return new ShimmerTransmutationRecipe(itemstack, nonnulllist, buffer.readVarInt());
                }

                @Override
                public void encode(RegistryFriendlyByteBuf buffer, ShimmerTransmutationRecipe recipe) {
                    buffer.writeVarInt(recipe.ingredients.size());
                    for (Ingredient ingredient : recipe.ingredients) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
                    }
                    ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
                    buffer.writeVarInt(recipe.cookingTime);
                }
            };
        }
    }

    public record Transmutation(ItemStack... items) implements RecipeInput {

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
