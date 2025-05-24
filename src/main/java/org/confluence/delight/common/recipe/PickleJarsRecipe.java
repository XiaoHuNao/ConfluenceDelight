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
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.confluence.delight.common.init.ModBlocks;
import org.confluence.delight.common.init.ModRecipes;
import org.confluence.lib.common.recipe.AbstractAmountRecipe;

public class PickleJarsRecipe extends AbstractAmountRecipe<PickleJarsRecipe.Input> {
    private final FluidStack requiredFluid;
    private final int craftTime;

    public PickleJarsRecipe(ItemStack result, NonNullList<Ingredient> ingredients, FluidStack requiredFluid, int craftTime) {
        super(result, ingredients);
        this.requiredFluid = requiredFluid;
        this.craftTime = craftTime;
    }

    @Override
    public boolean matches(Input input, Level level) {
        return matchesFluid(input.fluid) && super.matches(input, level);
    }

    private boolean matchesFluid(FluidStack inputFluid) {
        if (inputFluid.isEmpty()) return false;
        return inputFluid.getFluid() == requiredFluid.getFluid() && inputFluid.getAmount() >= requiredFluid.getAmount();
    }

    public FluidStack getRequiredFluid() {
        return requiredFluid;
    }

    public int getCraftTime() {
        return craftTime;
    }

    public void consumeFluids(FluidTank fluidTank) {
        FluidStack fluidInTank = fluidTank.getFluid();
        if (fluidInTank.getFluid() == requiredFluid.getFluid() && fluidInTank.getAmount() >= requiredFluid.getAmount()) {
            fluidTank.drain(requiredFluid.getAmount(), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    @Override
    protected int maxIngredientSize() {
        return 4;
    }

    @Override
    public String getGroup() {
        return "pickle_jars";
    }

    @Override
    public ItemStack getToastSymbol() {
        return ModBlocks.PICKLE_JARS_BLOCK.toStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.PICKLE_JARS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.PICKLE_JARS_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<PickleJarsRecipe> {
        public static final MapCodec<PickleJarsRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                INGREDIENTS_CODEC.forGetter(recipe -> recipe.ingredients),
                FluidStack.CODEC.fieldOf("fluid").forGetter(recipe -> recipe.requiredFluid),
                Codec.INT.fieldOf("crafttime").forGetter(recipe -> recipe.craftTime)
        ).apply(instance, PickleJarsRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, PickleJarsRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<PickleJarsRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PickleJarsRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static PickleJarsRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            FluidStack requiredFluid = FluidStack.STREAM_CODEC.decode(buffer);
            int craftTime = buffer.readVarInt();
            return new PickleJarsRecipe(result, ingredients, requiredFluid, craftTime);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, PickleJarsRecipe recipe) {
            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            FluidStack.STREAM_CODEC.encode(buffer, recipe.requiredFluid);
            buffer.writeVarInt(recipe.craftTime);
        }
    }

    public static class Input implements RecipeInput {
        private final ItemStack[] items;
        final FluidStack fluid;

        public Input(ItemStack[] items, FluidStack fluid) {
            this.items = items;
            this.fluid = fluid;
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


