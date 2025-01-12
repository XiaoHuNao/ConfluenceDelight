package com.xiaohunao.confluencedelight.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xiaohunao.confluencedelight.common.init.ModMaterialItems;
import com.xiaohunao.confluencedelight.common.init.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.Optional;


public class FridgeRecipe extends CookingPotRecipe {
    public int consumeFuel;

    public FridgeRecipe(String group, @Nullable CookingPotRecipeBookTab tab, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime, int consumeFuel) {
        super(group, tab, inputItems, output, container, experience, cookTime);
        this.consumeFuel = consumeFuel;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FRIDGE_RECIPE_SERIALIZER.get();
    }
    public int getConsumeFuel() {
        return consumeFuel;
    }

    public RecipeType<?> getType() {
        return ModRecipes.FRIDGE_RECIPE_TYPE.get() ;
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModMaterialItems.SUN_PLATE_BOWL);
    }


    public static class Serializer implements RecipeSerializer<FridgeRecipe> {

        private static final MapCodec<FridgeRecipe> CODEC = RecordCodecBuilder.mapCodec((inst) -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(FridgeRecipe::getGroup),
                CookingPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab").xmap((optional) -> optional.orElse(null), Optional::of).forGetter(FridgeRecipe::getRecipeBookTab),
                Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap((ingredients) -> {
                    NonNullList<Ingredient> nonNullList = NonNullList.create();
                    nonNullList.addAll(ingredients);
                    return nonNullList;
                    }, (ingredients) -> ingredients).forGetter(FridgeRecipe::getIngredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(CookingPotRecipe::getOutputContainer),
                ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(FridgeRecipe::getContainerOverride),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(FridgeRecipe::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(FridgeRecipe::getCookTime),
                Codec.INT.optionalFieldOf("consume_fuel", 0).forGetter(FridgeRecipe::getConsumeFuel)
                ).apply(inst, FridgeRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FridgeRecipe> STREAM_CODEC = StreamCodec.of(FridgeRecipe.Serializer::toNetwork, FridgeRecipe.Serializer::fromNetwork);

        public Serializer() {
        }

        public MapCodec<FridgeRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, FridgeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static FridgeRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf();
            CookingPotRecipeBookTab tabIn = CookingPotRecipeBookTab.findByName(buffer.readUtf());
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);
            inputItemsIn.replaceAll((ignored) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            float experienceIn = buffer.readFloat();
            int cookTimeIn = buffer.readVarInt();
            int consumeFuelIn = buffer.readVarInt();
            return new FridgeRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn, consumeFuelIn);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, FridgeRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeUtf(recipe.getRecipeBookTab() != null ? recipe.getRecipeBookTab().toString() : "");
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.getResultItem(null));
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.getOutputContainer());
            buffer.writeFloat(recipe.getExperience());
            buffer.writeVarInt(recipe.getCookTime());
            buffer.writeVarInt(recipe.getConsumeFuel());
        }


    }
}
