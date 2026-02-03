//package org.confluence.delight.common.block.function.crafting;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.core.NonNullList;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.ContainerHelper;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.inventory.AbstractContainerMenu;
//import net.minecraft.world.inventory.ContainerData;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.RecipeHolder;
//import net.minecraft.world.item.crafting.RecipeManager;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.pattern.BlockInWorld;
//import org.confluence.delight.common.init.CDRecipes;
//import org.confluence.delight.common.recipe.ShimmerTransmutationPotRecipe;
//import org.confluence.delight.common.recipe.ShimmerTransmutationRecipe;
//import org.confluence.mod.common.block.functional.crafting.CookingPotBlock;
//
//import java.util.Optional;
//
//public class ShimmerTransmutationPotEntity extends BaseContainerBlockEntity {
//    protected NonNullList<ItemStack> potNonNullList = NonNullList.withSize(.SLOT_COUNT, ItemStack.EMPTY);
//    protected NonNullList<ItemStack> transmutationNonNullList  = NonNullList.withSize(.SLOT_COUNT, ItemStack.EMPTY);
//    int potTimeProgress;
//    int potTimeTotalTime;
//    int transmutationTimeProgress;
//    int transmutationTimeTotal;
//    int heatSourceItem = CookingPotBlock.BItem.getId(Items.AIR);
//    ItemStack[] itemStacks = new ItemStack[6];
//    protected final ContainerData dataAccess = new ContainerData() {
//        @Override
//        public int get(int data) {
//            return switch (data) {
//                case 0 -> potTimeProgress;
//                case 1 -> potTimeTotalTime;
//                case 2 -> transmutationTimeProgress;
//                case 3 -> transmutationTimeTotal;
//                case 4 -> heatSourceItem;
//                default -> 0;
//            };
//        }
//
//        @Override
//        public void set(int data, int value) {
//            switch (data) {
//                case 0:
//                    potTimeProgress = value;
//                    break;
//                case 1:
//                    potTimeTotalTime = value;
//                    break;
//                case 2:
//                    transmutationTimeProgress = value;
//                    break;
//                case 3:
//                    transmutationTimeTotal = value;
//                    break;
//                case 4:
//                    heatSourceItem = value;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return CookingPotMenu.DATA_COUNT;
//        }
//    };
//
//    private final RecipeManager.CachedCheck<ShimmerTransmutationPotRecipe.Pot, ShimmerTransmutationPotRecipe> stprCachedCheck; //锅配方缓存
//    private final RecipeManager.CachedCheck<ShimmerTransmutationRecipe.Transmutation, ShimmerTransmutationRecipe>  strCachedCheck; //嬗变配方缓存
//
//    private RecipeType currentRecipeType = RecipeType.NONE;
//
//    public ShimmerTransmutationPotEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
//        super(type, pos, blockState);
//        this.stprCachedCheck = RecipeManager.createCheck(CDRecipes.SHIMMER_TRANSMUTATION_POT_TYPE.get());
//        this.strCachedCheck = RecipeManager.createCheck(CDRecipes.SHIMMER_TRANSMUTATION_TYPE.get());
//    }
//
//    private void checkAndProcessRecipe(Level level, BlockPos pos, BlockState state, ShimmerTransmutationPotEntity blockEntity) {
//        BlockInWorld heatSource = new BlockInWorld(level, pos.below(), true);
//        if (level.getGameTime() % 20 == 1) { // 每秒获取一次
//            blockEntity.heatSourceItem = Item.getId(heatSource.getState().getBlock().asItem());
//        }
//        ItemStack[] potItemStacks = blockEntity.getPotItemStacks();
//        boolean hasPotInputItem = !potItemStacks[0].isEmpty() || !potItemStacks[1].isEmpty() || !potItemStacks[2].isEmpty() || !potItemStacks[3].isEmpty() || !potItemStacks[4].isEmpty() || !potItemStacks[5].isEmpty();
//        if (hasPotInputItem) {
//            ShimmerTransmutationPotRecipe.Pot pot = new ShimmerTransmutationPotRecipe.Pot(potItemStacks, , heatSource);
//            Optional<RecipeHolder<ShimmerTransmutationPotRecipe>> potRecipe = stprCachedCheck.getRecipeFor(pot, level);
//            if (potRecipe.isPresent()) {
//                currentRecipeType = RecipeType.POT;
//                ShimmerTransmutationPotRecipe recipe = potRecipe.get().value();
//                if (canResultInPotInsert(blockEntity.potNonNullList, blockEntity.getMaxStackSize(), recipe.getResultItem(null))) {
//                    blockEntity.potTimeTotalTime = recipe.getCookingTime();
//                    if (++blockEntity.potTimeProgress >= blockEntity.potTimeTotalTime) {
//                        blockEntity.potNonNullList.get(.CONTAINER_SLOT).shrink(1);
//                        ItemStack neoResult = recipe.assembleAndExtract(pot, level.registryAccess());
//                        ItemStack oldResult = blockEntity.potNonNullList.get();
//                        if (oldResult.isEmpty()) {
//                            blockEntity.potNonNullList.set(, neoResult.copy());
//                        } else if (ItemStack.isSameItemSameComponents(oldResult, neoResult)) {
//                            oldResult.grow(neoResult.getCount());
//                        }
//                    } else {
//                        return;
//                    }
//                }
//            }
//        }
//        blockEntity.potTimeProgress = 0;
//        ItemStack[] transmutationItemStacks = blockEntity.getTransmutationItemStacks();
//        boolean hasTransmutationInputItem = !transmutationItemStacks[0].isEmpty() || !transmutationItemStacks[1].isEmpty() || !transmutationItemStacks[2].isEmpty() || !transmutationItemStacks[3].isEmpty();
//        if (hasTransmutationInputItem) {
//            ShimmerTransmutationRecipe.Transmutation transmutation = new ShimmerTransmutationRecipe.Transmutation(transmutationItemStacks);
//            Optional<RecipeHolder<ShimmerTransmutationRecipe>> transmutationRecipe = strCachedCheck.getRecipeFor(transmutation, level);
//            if (transmutationRecipe.isPresent()) {
//                currentRecipeType = RecipeType.TRANSMUTATION;
//                ShimmerTransmutationRecipe recipe = transmutationRecipe.get().value();
//                if (canResultInPotInsert(blockEntity.transmutationNonNullList, blockEntity.getMaxStackSize(), recipe.getResultItem(null))) {
//                    blockEntity.transmutationTimeTotal = recipe.getCookingTime();
//                    if (++blockEntity.transmutationTimeProgress >= blockEntity.transmutationTimeTotal) {
//                        ItemStack neoResult = recipe.assembleAndExtract(transmutation, level.registryAccess());
//                        ItemStack oldResult = blockEntity.transmutationNonNullList.get();
//                        if (oldResult.isEmpty()) {
//                            blockEntity.transmutationNonNullList.set(, neoResult.copy());
//                        } else if (ItemStack.isSameItemSameComponents(oldResult, neoResult)) {
//                            oldResult.grow(neoResult.getCount());
//                        }
//                    } else {
//                        return;
//                    }
//                }
//            }
//        }
//        blockEntity.transmutationTimeProgress = 0;
//    }
//
//    private static boolean canResultInPotInsert(NonNullList<ItemStack> inventory, int maxStackSize, ItemStack neoResult) {
//        if (neoResult.isEmpty()) {
//            return false;
//        } else {
//            ItemStack oldResult = inventory.get(.RESULT_SLOT);
//            if (oldResult.isEmpty()) {
//                return true;
//            } else if (!ItemStack.isSameItemSameComponents(oldResult, neoResult)) {
//                return false;
//            } else {
//                return oldResult.getCount() + neoResult.getCount() <= maxStackSize && oldResult.getCount() + neoResult.getCount() <= oldResult.getMaxStackSize() || oldResult.getCount() + neoResult.getCount() <= neoResult.getMaxStackSize();
//            }
//        }
//    }
//
//    private ItemStack[] getPotItemStacks() {
//        itemStacks[0] = potNonNullList.get(0);
//        itemStacks[1] = potNonNullList.get(1);
//        itemStacks[2] = potNonNullList.get(2);
//        itemStacks[3] = potNonNullList.get(3);
//        itemStacks[4] = potNonNullList.get(4);
//        itemStacks[5] = potNonNullList.get(5);
//        return itemStacks;
//    }
//
//    private ItemStack[] getTransmutationItemStacks() {
//        itemStacks[0] = transmutationNonNullList.get(0);
//        itemStacks[1] = transmutationNonNullList.get(1);
//        itemStacks[2] = transmutationNonNullList.get(2);
//        itemStacks[3] = transmutationNonNullList.get(3);
//        return itemStacks;
//    }
//
//    @Override
//    protected Component getDefaultName() {
//        return Component.translatable("container.shimmer_transmutation_pot");
//    }
//
//    @Override
//    protected NonNullList<ItemStack> getItems() {
//        if (currentRecipeType == RecipeType.POT) {
//            return potNonNullList;
//        } else if (currentRecipeType == RecipeType.TRANSMUTATION) {
//            return transmutationNonNullList;
//        }
//        return potNonNullList;
//    }
//
//    @Override
//    protected void setItems(NonNullList<ItemStack> nonNullList) {
//        if (currentRecipeType == RecipeType.POT) {
//            potNonNullList = nonNullList;
//        } else if (currentRecipeType == RecipeType.TRANSMUTATION) {
//            transmutationNonNullList = nonNullList;
//        }
//    }
//
//    @Override
//    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
//        super.loadAdditional(tag, registries);
//        this.potNonNullList = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
//        this.transmutationNonNullList = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
//        ContainerHelper.loadAllItems(tag, potNonNullList, registries);
//        ContainerHelper.loadAllItems(tag, transmutationNonNullList, registries);
//        this.potTimeProgress = tag.getInt("potTimeProgress");
//        this.potTimeTotalTime = tag.getInt("potTimeTotalTime");
//        this.transmutationTimeProgress = tag.getInt("transmutationTimeProgress");
//        this.transmutationTimeTotal = tag.getInt("transmutationTimeTotal");
//        this.currentRecipeType = RecipeType.values()[tag.getInt("currentRecipeType")];
//    }
//
//    @Override
//    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
//        super.saveAdditional(tag, registries);
//        ContainerHelper.saveAllItems(tag, potNonNullList, registries);
//        ContainerHelper.saveAllItems(tag, transmutationNonNullList, registries);
//        tag.putInt("potTimeProgress", potTimeProgress);
//        tag.putInt("potTimeTotalTime", potTimeTotalTime);
//        tag.putInt("transmutationTimeProgress", transmutationTimeProgress);
//        tag.putInt("transmutationTimeTotal", transmutationTimeTotal);
//        tag.putInt("currentRecipeType", currentRecipeType.ordinal());
//    }
//
//
//    @Override
//    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
//        return null;
//    }
//
//    @Override
//    public int getContainerSize() {
//        return 0;
//    }
//
//    private enum RecipeType {
//        NONE, POT, TRANSMUTATION
//    }
//}
