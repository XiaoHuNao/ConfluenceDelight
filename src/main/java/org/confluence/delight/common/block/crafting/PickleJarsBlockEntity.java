package org.confluence.delight.common.block.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.init.ModBlocks;
import org.confluence.delight.common.init.ModRecipes;
import org.confluence.delight.common.recipe.PickleJarsRecipe;
import org.confluence.lib.common.recipe.ItemStackHandlerRecipeInput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PickleJarsBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    public static final int INPUT_SIZE = 3;
    public static final int OUTPUT_SIZE = 1;
    public static final int CONTAINER_SIZE = INPUT_SIZE + OUTPUT_SIZE;
    public static final int OUTPUT_SLOT = CONTAINER_SIZE - 1;
    public static final int FLUID_CAPACITY = StartupConfigs.FLUID_CAPACITY.getAsInt();
    protected NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    int craftProgress, craftTotalTime;
    public final ItemStackHandlerRecipeInput itemHandler;
    public final FluidTank fluidTank;
    private final RecipeManager.CachedCheck<PickleJarsRecipe.Input, PickleJarsRecipe> cachedCheck;

    public PickleJarsBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.PICKLE_JARS_BLOCK_ENTITY.get(), pos, blockState);
        this.itemHandler = new ItemStackHandlerRecipeInput(this, CONTAINER_SIZE);
        this.cachedCheck = RecipeManager.createCheck(ModRecipes.PICKLE_JARS_TYPE.get());
        this.fluidTank = new FluidTank(FLUID_CAPACITY) {
            @Override
            protected void onContentsChanged() {
                setChanged();
            }
        };
    }

    private ItemStack[] getInputStacks() {
        ItemStack[] inputStacks = new ItemStack[INPUT_SIZE];
        for (int i = 0; i < INPUT_SIZE; i++) {
            inputStacks[i] = this.itemHandler.getStackInSlot(i);
        }
        return inputStacks;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PickleJarsBlockEntity blockEntity) {
        ItemStack[] inputStacks = blockEntity.getInputStacks();
        boolean hasItem = false;
        for (ItemStack stack : inputStacks) {
            if (!stack.isEmpty()) {
                hasItem = true;
                break;
            }
        }
        boolean hasFluidInput = !blockEntity.fluidTank.getFluid().isEmpty();
        boolean blockCover = state.getValue(PickleJarsBlock.COVER);
        if (hasItem && hasFluidInput) {
            PickleJarsRecipe.Input input = new PickleJarsRecipe.Input(inputStacks, blockEntity.fluidTank.getFluid());
            Optional<RecipeHolder<PickleJarsRecipe>> optionalRecipe = blockEntity.cachedCheck.getRecipeFor(input, level);
            if (optionalRecipe.isPresent()) {
                PickleJarsRecipe recipe = optionalRecipe.get().value();
                if (recipe.getCover() == blockCover) {
                    ItemStack resultItem = recipe.getResultItem(null);
                    if (canResultInsert(blockEntity.items, blockEntity.getMaxStackSize(), resultItem)) {
                        blockEntity.craftTotalTime = recipe.getCraftTime();
                        if (++blockEntity.craftProgress >= blockEntity.craftTotalTime) {
                            recipe.consumeFluids(blockEntity.fluidTank);
                            ItemStack newResult = recipe.assembleAndExtract(input, level.registryAccess());
                            ItemStack currentResult = blockEntity.itemHandler.getStackInSlot(OUTPUT_SLOT);
                            if (currentResult.isEmpty()) {
                                blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, newResult.copy());
                            } else if (ItemStack.isSameItemSameComponents(currentResult, newResult)) {
                                currentResult.grow(newResult.getCount());
                            }
                            blockEntity.craftProgress = 0;
                            blockEntity.setChanged();
                        }
                        return;
                    }
                }
            }
        }
        blockEntity.craftProgress = 0;
    }

    private static boolean canResultInsert(NonNullList<ItemStack> inventory, int maxStackSize, ItemStack newResult) {
        if (newResult.isEmpty()) {
            return false;
        } else {
            ItemStack oldResult = inventory.get(OUTPUT_SLOT);
            if (oldResult.isEmpty()) {
                return true;
            } else if (!ItemStack.isSameItemSameComponents(oldResult, newResult)) {
                return false;
            } else {
                return oldResult.getCount() + newResult.getCount() <= maxStackSize && oldResult.getCount() + newResult.getCount() <= oldResult.getMaxStackSize() || oldResult.getCount() + newResult.getCount() <= newResult.getMaxStackSize();
            }
        }
    }

    public ItemStack addItem(ItemStack toAdd) {
        int firstEmptySlot = -1;
        for (int i = 0; i < INPUT_SIZE; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (firstEmptySlot == -1 && stack.isEmpty()) {
                firstEmptySlot = i;
            }
            if (ItemStack.isSameItemSameComponents(stack, toAdd)) {
                ItemStack result = itemHandler.insertItem(i, toAdd, false);
                setChanged();
                return result;
            }
        }
        if (firstEmptySlot != -1) {
            itemHandler.setStackInSlot(firstEmptySlot, toAdd);
            setChanged();
            return ItemStack.EMPTY;
        }
        return toAdd;
    }

    public ItemStack takeItem(int slot) {
        if (slot == -1) {
            ItemStack outputStack = itemHandler.getStackInSlot(OUTPUT_SLOT);
            if (!outputStack.isEmpty()) {
                ItemStack extracted = itemHandler.extractItem(OUTPUT_SLOT, outputStack.getCount(), false);
                setChanged();
                return extracted;
            }
            for (int i = 0; i < INPUT_SIZE; i++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    ItemStack extracted = itemHandler.extractItem(i, stack.getCount(), false);
                    setChanged();
                    return extracted;
                }
            }
            return ItemStack.EMPTY;
        } else {
            ItemStack stack = itemHandler.getStackInSlot(slot).copy();
            itemHandler.setStackInSlot(slot, ItemStack.EMPTY);
            setChanged();
            return stack;
        }
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        itemHandler.setItems(NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY));
        ContainerHelper.loadAllItems(nbt, itemHandler.getItems(), registries);
        this.craftProgress = nbt.getInt("CraftTime");
        this.craftTotalTime = nbt.getInt("CraftTotalTime");
        fluidTank.readFromNBT(registries, nbt.getCompound("FluidTank"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        CompoundTag fluidTag = new CompoundTag();
        fluidTank.writeToNBT(registries, fluidTag);
        nbt.putInt("CraftTime", this.craftProgress);
        nbt.putInt("CraftTotalTime", this.craftTotalTime);
        nbt.put("FluidTank", fluidTag);
        ContainerHelper.saveAllItems(nbt, itemHandler.getItems(), registries);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[] { OUTPUT_SLOT, 0, 1, 2 };
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return index < OUTPUT_SLOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == OUTPUT_SLOT;
    }


    @Override
    protected Component getDefaultName() {
        return Component.empty();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return itemHandler.getItems();
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        itemHandler.setItems(items);
    }

    @Override
    public boolean canOpen(Player player) {
        return false;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int getContainerSize() {
        return itemHandler.size();
    }
}

