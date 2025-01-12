package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.recipe.FridgeRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES= DeferredRegister.create(Registries.RECIPE_TYPE, ConfluenceDelight.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ConfluenceDelight.MODID);


    public static final Supplier<RecipeType<FridgeRecipe>> FRIDGE_RECIPE_TYPE = registerRecipeType("fridge");
    public static final Supplier<RecipeSerializer<?>> FRIDGE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("fridge", FridgeRecipe.Serializer::new);


    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(final String identifier) {
        return RECIPE_TYPES.register(identifier, ()-> new RecipeType<T>() {
            public String toString() {
                return ConfluenceDelight.MODID + ":" + identifier;
            }
        });
    }

    public static void register(IEventBus modEventBus) {
        RECIPE_TYPES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
}
