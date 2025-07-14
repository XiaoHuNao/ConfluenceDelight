package org.confluence.delight.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.lib.common.recipe.AbstractAmountRecipe;

import java.util.ArrayList;
import java.util.List;

public class JuicerRecipe extends AbstractAmountRecipe<JuicerRecipe.Input> {
    private final int cycle;
    private final ItemLike container;
    private final FluidStack fluid;

    public JuicerRecipe(ItemStack result, ItemLike container, int cycle, FluidStack fluid, NonNullList<Ingredient> ingredients) {
        super(result, ingredients);
        this.cycle = cycle;
        this.container = container;
        this.fluid = fluid;
    }

    @Override
    public boolean matches(Input input, Level level) {
        if (input.getContainer().isEmpty() || input.getContainer().getItem() != container.asItem()) {
            return false;
        }
        if (!matchesFluid(input.fluid)) {
            return false;
        }
        List<ItemStack> inputs = new ArrayList<>();
        for (ItemStack stack : input.items) {
            if (!stack.isEmpty()) inputs.add(stack);
        }
        if (inputs.size() != ingredients.size()) return false;

        boolean[] matched = new boolean[inputs.size()];
        for (Ingredient ingredient : ingredients) {
            boolean found = false;
            for (int i = 0; i < inputs.size(); i++) {
                if (!matched[i] && ingredient.test(inputs.get(i))) {
                    matched[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    private boolean matchesFluid(FluidStack inputFluid) {
        if (inputFluid.isEmpty()) return false;
        return inputFluid.getFluid() == fluid.getFluid() && inputFluid.getAmount() >= fluid.getAmount();
    }

    public void consumeFluids(FluidTank fluidTank) {
        FluidStack fluidInTank = fluidTank.getFluid();
        if (fluidInTank.getFluid() == fluid.getFluid() && fluidInTank.getAmount() >= fluid.getAmount()) {
            fluidTank.drain(fluid.getAmount(), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    public FluidStack getFluid() {
        return fluid;
    }

    public int getCycle() {
        return cycle;
    }

    public NonNullList<Ingredient> getIngredient() {
        return ingredients;
    }

    public ItemLike getContainer() {
        return container;
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
                BuiltInRegistries.ITEM.byNameCodec().fieldOf("container").forGetter(recipe -> recipe.container.asItem()),
                Codec.INT.fieldOf("work_circles").forGetter(recipe -> recipe.cycle),
                FluidStack.CODEC.fieldOf("fluid").forGetter(recipe -> recipe.fluid),
                INGREDIENTS_CODEC.forGetter(recipe -> recipe.ingredients)
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
            int size = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }
            Item container = BuiltInRegistries.ITEM.byId(buffer.readVarInt());
            FluidStack fluid = FluidStack.STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            int cycle = buffer.readVarInt();
            return new JuicerRecipe(result, container, cycle, fluid, ingredients);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, JuicerRecipe recipe) {
            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            buffer.writeVarInt(BuiltInRegistries.ITEM.getId(recipe.container.asItem()));
            FluidStack.STREAM_CODEC.encode(buffer, recipe.fluid);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeVarInt(recipe.getCycle());
        }
    }

    public static class Input implements RecipeInput {
        private final ItemStack[] items;
        private final ItemStack container;
        final FluidStack fluid;

        public Input(ItemStack[] items, ItemStack container, FluidStack fluid) {
            this.items = items;
            this.container = container;
            this.fluid = fluid;
        }

        public ItemStack getContainer() {
            return container;
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
