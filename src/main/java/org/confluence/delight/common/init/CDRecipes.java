package org.confluence.delight.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.recipe.BlockAndItemInteractionRecipe;
import org.confluence.delight.common.recipe.JuicerRecipe;
import org.confluence.delight.common.recipe.MillStoneRecipe;
import org.confluence.delight.common.recipe.PickleJarsRecipe;

import java.util.function.Supplier;

public class CDRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ConfluenceDelight.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ConfluenceDelight.MODID);

    public static final Supplier<RecipeSerializer<?>> PICKLE_JARS_SERIALIZER = RECIPE_SERIALIZERS.register("pickle_jars", PickleJarsRecipe.Serializer::new);
    public static final Supplier<RecipeType<PickleJarsRecipe>> PICKLE_JARS_TYPE = registerRecipeType("pickle_jars");
    public static final Supplier<RecipeSerializer<?>> MILLSTONE_SERIALIZER = RECIPE_SERIALIZERS.register("millstone", MillStoneRecipe.Serializer::new);
    public static final Supplier<RecipeType<MillStoneRecipe>> MILLSTONE_TYPE = registerRecipeType("millstone");
    public static final Supplier<RecipeSerializer<?>> JUICER_SERIALIZER = RECIPE_SERIALIZERS.register("juicer", JuicerRecipe.Serializer::new);
    public static final Supplier<RecipeType<JuicerRecipe>> JUICER_TYPE = registerRecipeType("juicer");
    public static final Supplier<RecipeSerializer<?>> BLOCK_AND_ITEM_INTERACTION_SERIALIZER = RECIPE_SERIALIZERS.register("block_and_item_interaction", BlockAndItemInteractionRecipe.Serializer::new);
    public static final Supplier<RecipeType<BlockAndItemInteractionRecipe>> BLOCK_AND_ITEM_INTERACTION_TYPE = registerRecipeType("block_and_item_interaction");


    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(final String identifier) {
        return RECIPE_TYPES.register(identifier, () -> new RecipeType<T>() {
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
