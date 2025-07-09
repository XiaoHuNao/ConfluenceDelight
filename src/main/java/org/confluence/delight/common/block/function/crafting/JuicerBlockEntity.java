package org.confluence.delight.common.block.function.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.delight.common.init.CDTags;
import org.confluence.delight.common.recipe.JuicerRecipe;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.lib.common.recipe.ItemStackHandlerRecipeInput;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public class JuicerBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    public static final int INPUT_SIZE = 3;
    public static final int CONTAINER_SIZE = 1;
    public static final int CONTAINER_SLOT = INPUT_SIZE;
    public static final int OUTPUT_SLOT = INPUT_SIZE + CONTAINER_SIZE;
    public static final int TOTAL_SLOTS = INPUT_SIZE + CONTAINER_SIZE + 1;

    protected final ItemStackHandlerRecipeInput itemHandler;
    public final FluidTank fluidTank;
    private final RecipeManager.CachedCheck<JuicerRecipe.Input, JuicerRecipe> cachedCheck;

    public int useCooldown;
    private int craftProgress = 0;
    private int craftTotalTime = 0;

    public JuicerBlockEntity(BlockPos pos, BlockState blockState) {
        super(CDBlocks.JUICER_BLOCK_ENTITY.get(), pos, blockState);
        this.itemHandler = new ItemStackHandlerRecipeInput(this, TOTAL_SLOTS);
        this.cachedCheck = RecipeManager.createCheck(CDRecipes.JUICER_TYPE.get());
        this.fluidTank = new FluidTank(StartupConfigs.FLUID_CAPACITY.get()) {
            @Override
            protected void onContentsChanged() {
                setChanged();
            }
        };
    }

    private ItemStack[] getAllInputStacks() {
        ItemStack[] inputStacks = new ItemStack[INPUT_SIZE + CONTAINER_SIZE];
        for (int i = 0; i < inputStacks.length; i++) {
            inputStacks[i] = this.itemHandler.getStackInSlot(i);
        }
        return inputStacks;
    }

    private static boolean canResultInsert(NonNullList<ItemStack> inventory, int maxStackSize, ItemStack newResult) {
        if (newResult.isEmpty()) {
            return false;
        }
        ItemStack oldResult = inventory.get(OUTPUT_SLOT);
        if (oldResult.isEmpty()) {
            return true;
        }
        if (!ItemStack.isSameItemSameComponents(oldResult, newResult)) {
            return false;
        }
        int combinedCount = oldResult.getCount() + newResult.getCount();
        return combinedCount <= maxStackSize && combinedCount <= oldResult.getMaxStackSize();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, JuicerBlockEntity blockEntity) {
        if (blockEntity.useCooldown > 0) {
            blockEntity.useCooldown--;
        }
    }

    public static void onEntityWork(Level level, BlockPos pos, BlockState state, JuicerBlockEntity blockEntity) {
        if (blockEntity.useCooldown > 0) {
            return;
        }
        ItemStack[] allInputs = blockEntity.getAllInputStacks();
        if (hasInputItems(allInputs) && hasFluid(blockEntity)) {
            ItemStack[] items = Arrays.copyOfRange(allInputs, 0, INPUT_SIZE);
            ItemStack container = allInputs[CONTAINER_SLOT];
            JuicerRecipe.Input input = new JuicerRecipe.Input(items, container, blockEntity.fluidTank.getFluid());
            Optional<RecipeHolder<JuicerRecipe>> optionalRecipe = blockEntity.cachedCheck.getRecipeFor(input, level);
            if (optionalRecipe.isPresent()) {
                JuicerRecipe recipe = optionalRecipe.get().value();
                blockEntity.craftTotalTime = recipe.getCycle();
                ItemStack resultItem = recipe.getResultItem(null);
                level.playSound(null, pos, SoundEvents.SLIME_HURT, SoundSource.BLOCKS, 0.75f, 0.5f);
                if (canResultInsert(blockEntity.itemHandler.getItems(), blockEntity.getMaxStackSize(), resultItem)) {
                    if (++blockEntity.craftProgress >= blockEntity.craftTotalTime) {
                        recipe.consumeFluids(blockEntity.fluidTank);
                        ItemStack newResult = recipe.assemble(input, level.registryAccess());
                        ItemStack currentResult = blockEntity.itemHandler.getStackInSlot(OUTPUT_SLOT);
                        if (currentResult.isEmpty()) {
                            blockEntity.itemHandler.setStackInSlot(OUTPUT_SLOT, newResult.copy());
                        } else if (ItemStack.isSameItemSameComponents(currentResult, newResult)) {
                            currentResult.grow(newResult.getCount());
                        }
                        for (int i = 0; i < recipe.ingredients.size(); i++) {
                            AmountIngredient amountIngredient = new AmountIngredient(recipe.ingredients.get(i), AmountIngredient.getAmount(recipe.ingredients.get(i)));
                            ItemStack stack = blockEntity.itemHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                stack.shrink(amountIngredient.amount());
                                if (stack.isEmpty()) {
                                    blockEntity.itemHandler.setStackInSlot(i, ItemStack.EMPTY);
                                }
                            }
                        }
                        ItemStack containerStack = blockEntity.itemHandler.getStackInSlot(CONTAINER_SLOT);
                        if (!containerStack.isEmpty()) {
                            containerStack.shrink(1);
                            if (containerStack.isEmpty()) {
                                blockEntity.itemHandler.setStackInSlot(CONTAINER_SLOT, ItemStack.EMPTY);
                            }
                        }
                        blockEntity.craftProgress = 0;
                        blockEntity.setChanged();
                    }
                    return;
                }
            }
        }
        blockEntity.craftProgress = 0;
    }

    private static boolean hasInputItems(ItemStack[] inputStacks) {
        for (ItemStack stack : inputStacks) {
            if (!stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasFluid(JuicerBlockEntity blockEntity) {
        return !blockEntity.fluidTank.getFluid().isEmpty();
    }

    private boolean isContainerItem(ItemStack stack) {
        return stack.is(CDTags.Items.JUICER_CONTAINER);
    }

    public ItemStack addItem(ItemStack toAdd) {
        if (toAdd.isEmpty()) {
            return ItemStack.EMPTY;
        }
        int firstEmptySlot = -1;
        for (int i = 0; i < INPUT_SIZE; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (firstEmptySlot == -1 && stack.isEmpty()) {
                firstEmptySlot = i;
            }
            if (ItemStack.isSameItemSameComponents(stack, toAdd)) {
                ItemStack remainder = itemHandler.insertItem(i, toAdd, false);
                setChanged();
                return remainder;
            }
        }
        if (isContainerItem(toAdd)) {
            ItemStack containerStack = itemHandler.getStackInSlot(CONTAINER_SLOT);
            if (containerStack.isEmpty()) {
                itemHandler.setStackInSlot(CONTAINER_SLOT, toAdd);
                setChanged();
                return ItemStack.EMPTY;
            }
            if (ItemStack.isSameItemSameComponents(containerStack, toAdd)) {
                ItemStack remainder = itemHandler.insertItem(CONTAINER_SLOT, toAdd, false);
                setChanged();
                return remainder;
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
            for (int i = 0; i < INPUT_SIZE + CONTAINER_SIZE; i++) {
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
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return new int[]{OUTPUT_SLOT};
        } else {
            return new int[]{0, 1, 2, CONTAINER_SLOT};
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return slot >= 0 && slot < OUTPUT_SLOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return slot == OUTPUT_SLOT && direction == Direction.DOWN;
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Juicer");
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
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        throw new UnsupportedOperationException("JuicerBlockEntity does not support container menu");
    }

    @Override
    public int getContainerSize() {
        return itemHandler.getSlots();
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        itemHandler.setItems(NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY));
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
        ContainerHelper.saveAllItems(nbt, itemHandler.getItems(), registries);
        nbt.putInt("CraftTime", this.craftProgress);
        nbt.putInt("CraftTotalTime", this.craftTotalTime);
        nbt.put("FluidTank", fluidTag);
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }
}

