package org.confluence.delight.common.block.function.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
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
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.delight.common.init.CDSoundEvents;
import org.confluence.delight.common.recipe.MillStoneRecipe;
import org.confluence.lib.common.recipe.ItemStackHandlerRecipeInput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MillStoneBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    public static final int INPUT_SIZE = 2;
    public static final int OUTPUT_SIZE = 1;
    public static final int TOTAL_SIZE = INPUT_SIZE + OUTPUT_SIZE;
    public static final int OUTPUT_SLOT = TOTAL_SIZE - 1;

    protected NonNullList<ItemStack> items = NonNullList.withSize(TOTAL_SIZE, ItemStack.EMPTY);

    private int craftProgress = 0;
    private int craftTotalTime = 0;
    public int useCooldown = 30;

    public final ItemStackHandlerRecipeInput itemHandler;
    private final RecipeManager.CachedCheck<MillStoneRecipe.Input, MillStoneRecipe> cachedCheck;

    public MillStoneBlockEntity(BlockPos pos, BlockState blockState) {
        super(CDBlocks.MILLSTONE_BLOCK_ENTITY.get(), pos, blockState);
        this.itemHandler = new ItemStackHandlerRecipeInput(this, TOTAL_SIZE);
        this.cachedCheck = RecipeManager.createCheck(CDRecipes.MILLSTONE_TYPE.get());
    }

    private ItemStack[] getInputStacks() {
        ItemStack[] inputStacks = new ItemStack[INPUT_SIZE];
        for (int i = 0; i < INPUT_SIZE; i++) {
            inputStacks[i] = this.itemHandler.getStackInSlot(i);
        }
        return inputStacks;
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
                int combinedCount = oldResult.getCount() + newResult.getCount();
                return combinedCount <= maxStackSize && (combinedCount <= oldResult.getMaxStackSize() || combinedCount <= newResult.getMaxStackSize());
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MillStoneBlockEntity blockEntity) {
        if (blockEntity.useCooldown > 0) {
            blockEntity.useCooldown--;
        }
    }


    public static void onEntityWork(Level level, BlockPos pos, BlockState state, MillStoneBlockEntity blockEntity) {
        if (blockEntity.useCooldown > 0) {
            return;
        }
        ItemStack[] inputStacks = blockEntity.getInputStacks();
        if (hasInputItems(inputStacks) && isInputValidForRecipe(inputStacks, level, blockEntity.cachedCheck)) {
            MillStoneRecipe.Input input = new MillStoneRecipe.Input(inputStacks);
            Optional<RecipeHolder<MillStoneRecipe>> optionalRecipe = blockEntity.cachedCheck.getRecipeFor(input, level);
            if (optionalRecipe.isPresent()) {
                MillStoneRecipe recipe = optionalRecipe.get().value();
                blockEntity.craftTotalTime = recipe.getWorkCircles();
                ItemStack resultItem = recipe.getResultItem(null);
                level.playSound(null, pos, CDSoundEvents.MILLSTONE_WORK.get(), SoundSource.BLOCKS, 0.5f, 0.5f);
                blockEntity.spawnInputItemParticles(level, pos);
                if (canResultInsert(blockEntity.items, blockEntity.getMaxStackSize(), resultItem)) {
                    if (++blockEntity.craftProgress >= blockEntity.craftTotalTime) {
                        ItemStack newResult = recipe.assemble(input, level.registryAccess());
                        ItemStack currentResult = blockEntity.itemHandler.getStackInSlot(INPUT_SIZE);
                        if (currentResult.isEmpty()) {
                            blockEntity.itemHandler.setStackInSlot(INPUT_SIZE, newResult.copy());
                        } else if (ItemStack.isSameItemSameComponents(currentResult, newResult)) {
                            currentResult.grow(newResult.getCount());
                        }
                        for (int i = 0; i < INPUT_SIZE; i++) {
                            ItemStack stack = blockEntity.itemHandler.getStackInSlot(i);
                            if (!stack.isEmpty()) {
                                ItemStack newStack = stack.copy();
                                newStack.shrink(1);
                                blockEntity.itemHandler.setStackInSlot(i, newStack.isEmpty() ? ItemStack.EMPTY : newStack);
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

    public void spawnInputItemParticles(Level level, BlockPos pos) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        ItemStack[] inputStacks = getInputStacks();
        for (ItemStack stack : inputStacks) {
            if (!stack.isEmpty()) {
                serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), pos.getX() + 0.5F, pos.getY() + 0.75F, pos.getZ() + 0.5F, 20, 0F, 0.0625F, 0F, 0.15F);
                break;
            }
        }
    }

    private static boolean hasInputItems(ItemStack[] stacks) {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInputValidForRecipe(ItemStack[] inputStacks, Level level, RecipeManager.CachedCheck<MillStoneRecipe.Input, MillStoneRecipe> cachedCheck) {
        MillStoneRecipe.Input input = new MillStoneRecipe.Input(inputStacks);
        Optional<RecipeHolder<MillStoneRecipe>> optionalRecipe = cachedCheck.getRecipeFor(input, level);
        if (optionalRecipe.isEmpty()) {
            return false;
        }
        MillStoneRecipe recipe = optionalRecipe.get().value();
        for (ItemStack stack : inputStacks) {
            if (!stack.isEmpty() && !recipe.isValidInput(stack)) {
                return false;
            }
        }
        return true;
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
        itemHandler.setItems(NonNullList.withSize(TOTAL_SIZE, ItemStack.EMPTY));
        ContainerHelper.loadAllItems(nbt, itemHandler.getItems(), registries);
        this.craftProgress = nbt.getInt("CraftTime");
        this.craftTotalTime = nbt.getInt("CraftTotalTime");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        ContainerHelper.saveAllItems(nbt, itemHandler.getItems(), registries);
        nbt.putInt("CraftTime", this.craftProgress);
        nbt.putInt("CraftTotalTime", this.craftTotalTime);
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Millstone");
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
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public int getContainerSize() {
        return itemHandler.getSlots();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP) {
            return new int[]{0, 1};
        } else {
            return new int[]{OUTPUT_SLOT};
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return index >= 0 && index <= 2 && direction == Direction.UP;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == OUTPUT_SLOT && direction == Direction.DOWN;
    }
}
