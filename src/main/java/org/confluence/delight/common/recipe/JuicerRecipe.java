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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class JuicerRecipe extends AbstractAmountRecipe<JuicerRecipe.Input> {
    private final NonNullList<Ingredient> ingredients;
    private final int workCircles;

    public JuicerRecipe(ItemStack result, NonNullList<Ingredient> ingredients, int workCircles) {
        super(result, ingredients);
        this.ingredients = ingredients;
        this.workCircles = workCircles;
    }

    @Override
    public boolean matches(Input input, Level level) {
        List<ItemStack> inputs = Arrays.stream(input.items)
                .filter(stack -> !stack.isEmpty())
                .toList();

        if (inputs.size() != ingredients.size()) return false;

        List<Ingredient> remainingIngredients = new ArrayList<>(ingredients);

        for (ItemStack item : inputs) {
            boolean matched = false;
            Iterator<Ingredient> it = remainingIngredients.iterator();
            while (it.hasNext()) {
                Ingredient ing = it.next();
                if (ing.test(item)) {
                    it.remove();
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }

        return remainingIngredients.isEmpty();
    }

    public boolean isValidInput(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        for (Ingredient ingredient : ingredients) {
            if (ingredient.test(stack)) {
                return true;
            }
        }

        return false;
    }

    public int getWorkCircles() {
        return workCircles;
    }

    public NonNullList<Ingredient> getIngredient() {
        return ingredients;
    }


    @Override
    protected int maxIngredientSize() {
        return 4;
    }

    @Override
    public String getGroup() {
        return "juicer";
    }

    @Override
    public ItemStack getToastSymbol() {
        return CDBlocks.JUICER_BLOCK.toStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CDRecipes.JUICER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CDRecipes.JUICER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<JuicerRecipe> {
        public static final MapCodec<JuicerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                NonNullList.codecOf(Ingredient.CODEC).fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                Codec.INT.fieldOf("work_circles").forGetter(recipe -> recipe.workCircles)
        ).apply(instance, JuicerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, JuicerRecipe> STREAM_CODEC = StreamCodec.of(JuicerRecipe.Serializer::toNetwork, JuicerRecipe.Serializer::fromNetwork);

        @Override
        public MapCodec<JuicerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, JuicerRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static JuicerRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            int ingredientCount = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);
            for (int i = 0; i < ingredientCount; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int workCircles = buffer.readVarInt();
            return new JuicerRecipe(result, ingredients, workCircles);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, JuicerRecipe recipe) {
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
