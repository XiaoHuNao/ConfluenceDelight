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
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.lib.common.recipe.AbstractAmountRecipe;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.common.recipe.CookingPotRecipe;
import org.confluence.mod.common.recipe.SimpleRecipeSerializer;

public class ShimmerTransmutationPotRecipe extends AbstractAmountRecipe<ShimmerTransmutationPotRecipe.Pot> {
    private final Ingredient container;
    private final CookingPotRecipe.HeatSourcePredicate heatSource;
    private final int cookingTime;

    public ShimmerTransmutationPotRecipe(ItemStack result, NonNullList<Ingredient> ingredients, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime) {
        super(result, ingredients);
        this.container = container;
        this.heatSource = heatSource;
        this.cookingTime = cookingTime;
    }

    public CookingPotRecipe.HeatSourcePredicate getHeatSource() {
        return heatSource;
    }

    public Ingredient getContainer() {
        return container;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    protected int maxIngredientSize() {
        return 7;
    }

    @Override
    public String getGroup() {
        return "shimmer_transmutation_pot";
    }

    @Override
    public ItemStack getToastSymbol() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CDRecipes.SHIMMER_TRANSMUTATION_POT_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CDRecipes.SHIMMER_TRANSMUTATION_POT_TYPE.get();
    }

    public static class Serializer extends SimpleRecipeSerializer<ShimmerTransmutationPotRecipe> {
        @Override
        protected MapCodec<ShimmerTransmutationPotRecipe> getCodec() {
            return RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                Ingredient.CODEC.fieldOf("container").forGetter(recipe -> recipe.container),
                CookingPotRecipe.HeatSourcePredicate.CODEC.fieldOf("heat_source").forGetter(recipe -> recipe.heatSource),
                Codec.INT.fieldOf("cooking_time").forGetter(recipe -> recipe.cookingTime)
            ).apply(instance, ShimmerTransmutationPotRecipe::new));
        }

        @Override
        protected StreamCodec<RegistryFriendlyByteBuf, ShimmerTransmutationPotRecipe> getStreamCodec() {
            return new StreamCodec<>() {
                @Override
                public ShimmerTransmutationPotRecipe decode(RegistryFriendlyByteBuf buffer) {
                    int size = buffer.readVarInt();
                    ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
                    NonNullList<Ingredient> nonnulllist = NonNullList.withSize(size, AmountIngredient.EMPTY);
                    nonnulllist.replaceAll(ignore -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
                    Ingredient container = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                    CookingPotRecipe.HeatSourcePredicate heatSource = CookingPotRecipe.HeatSourcePredicate.STREAM_CODEC.decode(buffer);
                    return new ShimmerTransmutationPotRecipe(result, nonnulllist, container, heatSource, buffer.readVarInt());
                }

                @Override
                public void encode(RegistryFriendlyByteBuf buffer, ShimmerTransmutationPotRecipe recipe) {
                    buffer.writeVarInt(recipe.ingredients.size());
                    ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
                    for (Ingredient ingredient : recipe.ingredients) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
                    }
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.container);
                    CookingPotRecipe.HeatSourcePredicate.STREAM_CODEC.encode(buffer, recipe.heatSource);
                    buffer.writeVarInt(recipe.cookingTime);
                }
            };
        }
    }

    public record Pot(ItemStack[] items, ItemStack container, BlockInWorld heatSource) implements RecipeInput {

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
