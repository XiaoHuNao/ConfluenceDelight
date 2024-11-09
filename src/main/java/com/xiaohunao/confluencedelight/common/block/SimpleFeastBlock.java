package com.xiaohunao.confluencedelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class SimpleFeastBlock extends FeastBlock {
    private SimpleGetVoxelShape shape;
    private SimpleServingItemStack servingItemStack;
    private IntegerProperty rollServings;

    public SimpleFeastBlock(Properties properties, Supplier<Item> servingItem, boolean hasLeftovers) {
        super(properties, servingItem, hasLeftovers);
    }

    @Override
    @NotNull
    public IntegerProperty getServingsProperty() {
        return rollServings != null ? rollServings : super.getServingsProperty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        if (rollServings != null) {
            builder.add(FACING, rollServings);
        }
        super.createBlockStateDefinition(builder);
    }

    @Override
    public int getMaxServings() {
        if (rollServings != null) {
            return rollServings.getValue("servings").orElseThrow(() -> new IllegalStateException("No servings property"));
        }
        return super.getMaxServings();
    }

    @Override
    @NotNull
    public ItemStack getServingItem(BlockState state) {
        return servingItemStack != null ? servingItemStack.getServingItem(state) : super.getServingItem(state);
    }
    @Override
    @NotNull
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return shape != null ? shape.getShape(state, level, pos, context) : super.getShape(state, level, pos, context);
    }

    public SimpleFeastBlock setShape(SimpleGetVoxelShape shape) {
        this.shape = shape;
        return this;
    }

    public SimpleFeastBlock setRollServings(IntegerProperty rollServings) {
        this.rollServings = rollServings;
        return this;
    }
    public SimpleFeastBlock setItemStack(SimpleServingItemStack servingItemStack) {
        this.servingItemStack = servingItemStack;
        return this;
    }

    @FunctionalInterface
    public interface SimpleGetVoxelShape {
        VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);
    }
    @FunctionalInterface
    public interface SimpleServingItemStack {
        ItemStack getServingItem(BlockState state);
    }
}
