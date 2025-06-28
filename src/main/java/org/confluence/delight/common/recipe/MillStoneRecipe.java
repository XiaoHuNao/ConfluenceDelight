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
    private final Ingredient ingredient1;
    private final Ingredient ingredient2;
    private final int workCircles;


    public MillStoneRecipe(ItemStack result, Ingredient ingredient, int workCircles) {
        super(result, NonNullList.of(Ingredient.EMPTY, ingredient));
        this.ingredient1 = ingredient;
        this.ingredient2 = Ingredient.EMPTY;
        this.workCircles = workCircles;
    }

    public MillStoneRecipe(ItemStack result, Ingredient ingredient1, Ingredient ingredient2, int workCircles) {
        super(result, NonNullList.of(Ingredient.EMPTY, ingredient1, ingredient2));
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.workCircles = workCircles;
    }

    @Override
    public boolean matches(Input input, Level level) {
        ItemStack[] items = input.items;
        if (ingredient2 == Ingredient.EMPTY) {
            for (ItemStack item : items) {
                if (ingredient1.test(item)) {
                    return true;
                }
            }
            return false;
        }
        if (items.length != 2) {
            return false;
        }
        ItemStack input0 = items[0];
        ItemStack input1 = items[1];
        return (ingredient1.test(input0) && ingredient2.test(input1)) || (ingredient1.test(input1) && ingredient2.test(input0));
    }

    public boolean isValidInput(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (ingredient2 == Ingredient.EMPTY) {
            return ingredient1.test(stack);
        } else {
            return ingredient1.test(stack) || ingredient2.test(stack);
        }
    }


    public int getWorkCircles() {
        return workCircles;
    }

    public Ingredient getIngredient1() {
        return ingredient1;
    }

    public Ingredient getIngredient2() {
        return ingredient2;
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
                Ingredient.CODEC.fieldOf("ingredient1").forGetter(recipe -> recipe.ingredient1),
                Ingredient.CODEC.fieldOf("ingredient2").forGetter(recipe -> recipe.ingredient2),
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
            Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient ingredient2 = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int workCircles = buffer.readVarInt();
            return new MillStoneRecipe(result, ingredient1, ingredient2, workCircles);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, MillStoneRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient1);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient2);
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
