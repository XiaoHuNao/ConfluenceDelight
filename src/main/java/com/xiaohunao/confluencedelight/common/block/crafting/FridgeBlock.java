package com.xiaohunao.confluencedelight.common.block.crafting;

import com.mojang.serialization.MapCodec;
import com.xiaohunao.confluencedelight.client.container.menu.FridgeMenu;
import com.xiaohunao.confluencedelight.common.init.ModBlocks;
import com.xiaohunao.confluencedelight.common.init.ModItems;
import com.xiaohunao.confluencedelight.common.init.ModRecipes;
import com.xiaohunao.confluencedelight.common.recipe.FridgeRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.SyncedBlockEntity;

import java.util.Optional;

public class FridgeBlock extends BaseEntityBlock {
    private static final VoxelShape SHAPE = Shapes.box(0.05, 0, 0.05,
            0.95, 2, 0.95);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<FridgeBlock> CODEC = simpleCodec(FridgeBlock::new);

    public FridgeBlock(Properties properties) {
        this();
    }

    public FridgeBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (p_52229_, p_52230_, p_52231_) -> new CraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(level, pos)),
                this.getName()
        );
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof Entity){
                Entity fridge = (Entity) entity;
                player.openMenu(fridge, pos);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            if (!player.isShiftKeyDown()){
                BlockEntity entity = level.getBlockEntity(pos);
                if (entity instanceof Entity) {
                    Entity fridge = (Entity) entity;
                    player.openMenu(fridge, pos);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ((BlockEntityType<?>) ModBlocks.FRIDGE_ENTITY.get()).create(blockPos, blockState);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof Entity) {
                Entity et = (Entity)tileEntity;
                Containers.dropContents(level, pos, et.getDroppableInventory());
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createFridgeTicker(level, blockEntityType, ModBlocks.FRIDGE_ENTITY.get());
    }

    @javax.annotation.Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createFridgeTicker(Level level, BlockEntityType<T> serverType, BlockEntityType<? extends Entity> clientType) {
        return level.isClientSide ? null : createTickerHelper(serverType, clientType, Entity::tick);
    }

    public static class Entity extends SyncedBlockEntity implements MenuProvider {
        ContainerData data;
        private final ItemStackHandler inventory = this.createHandler();

        private int iceTemperature;
        private int maxIceTemperature = 500;
        private MutableComponent customName;
        private int loadedTicks;
        private final RecipeWrapper input;
        private RecipeHolder<FridgeRecipe> chooseRecipe;
        private RecipeHolder<FridgeRecipe> curRecipe;
        private int cookTime;
        private int cookTimeTotal;

        public Entity(BlockPos pos, BlockState state) {
            super(ModBlocks.FRIDGE_ENTITY.get(), pos, state);
            data = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> iceTemperature;
                        case 1 -> maxIceTemperature;
                        case 2 -> cookTime;
                        case 3 -> cookTimeTotal;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int i1) {
                    switch (i) {
                        case 0 -> iceTemperature = i1;
                        case 1 -> maxIceTemperature = i1;
                        case 2 -> cookTime = i1;
                        case 3 -> cookTimeTotal = i1;
                    }
                }

                @Override
                public int getCount() {
                    return 4;
                }
            };

            input = new RecipeWrapper(inventory) {
                @Override
                public ItemStack getItem(int i) {
                    return inventory.getStackInSlot(i);
                }

                @Override
                public int size() {
                    return 6;
                }
            };
        }



        @Override
        public Component getDisplayName() {
            return customName != null ? customName : ModBlocks.FRIDGE.get().getName();
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
            return new FridgeMenu(i, inventory, this, data, ContainerLevelAccess.create(level, this.worldPosition));
        }

        public ItemStackHandler getInventory() {
            return inventory;
        }

        private ItemStackHandler createHandler() {
            return new ItemStackHandler(10) {
                protected void onContentsChanged(int slot) {
                    FridgeBlock.Entity.this.inventoryChanged();
                }
            };
        }

        public NonNullList<ItemStack> getDroppableInventory() {
            NonNullList<ItemStack> drops = NonNullList.create();

            for(int i = 0; i < 9; ++i) {
                if (i != 6) {
                    drops.add(this.inventory.getStackInSlot(i));
                }
            }

            return drops;
        }

        @Override
        protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
            super.loadAdditional(tag, registries);
            this.inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
            this.iceTemperature = tag.getInt("iceTemp");
            if (tag.contains("CustomName", 8)) {
                this.customName = Component.Serializer.fromJson(tag.getString("CustomName"), registries);
            }
            this.cookTime = tag.getInt("CookTime");
            this.cookTimeTotal = tag.getInt("CookTimeTotal");

            this.inventoryChanged();
        }

        @Override
        public void saveAdditional(CompoundTag compound, HolderLookup.Provider registries) {
            super.saveAdditional(compound, registries);
            compound.putInt("iceTemp", this.iceTemperature);
            this.inventory.serializeNBT(registries);
            if (this.customName != null) {
                compound.putString("CustomName", Component.Serializer.toJson(this.customName, registries));
            }

            compound.put("Inventory", this.inventory.serializeNBT(registries));
            compound.putInt("CookTime", this.cookTime);
            compound.putInt("CookTimeTotal", this.cookTimeTotal);
        }

        @Override
        protected void inventoryChanged() {
            super.inventoryChanged();
            if(!level.isClientSide){
                Optional<RecipeHolder<FridgeRecipe>> optRecipe = Minecraft.getInstance().level.getRecipeManager().getRecipeFor(ModRecipes.FRIDGE_RECIPE_TYPE.get(),input,Minecraft.getInstance().level);
                curRecipe = optRecipe.orElse(null);
            }
        }

        private static void tick(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
            if (!level.isClientSide){

                // 添加冰燃料
                if (entity.iceTemperature < entity.maxIceTemperature){
                    ItemStack fuel = entity.inventory.getStackInSlot(9);
                    if(entity.loadedTicks > 0 ){
                        entity.loadedTicks--;
                        entity.iceTemperature++;
                    } else if (ModItems.consumptionProbabilityMap.containsKey(fuel.getItem())) {
                        entity.loadedTicks = ModItems.consumptionProbabilityMap.get(fuel.getItem());
                        fuel.shrink(1);
                    }
                }

                // 消耗冰燃料

                if(entity.chooseRecipe != entity.curRecipe){
                    // 刷新配方
                    entity.chooseRecipe = entity.curRecipe;
                    if(entity.chooseRecipe!= null){
                        entity.cookTimeTotal = entity.chooseRecipe.value().getCookTime();
                    }else{
                        entity.cookTimeTotal = 0;
                    }

                }else{
                    if(entity.chooseRecipe == null || entity.iceTemperature < entity.chooseRecipe.value().getConsumeFuel()) return;
                    entity.cookTime++;
                    if(entity.cookTime >= entity.cookTimeTotal) {
                        entity.cookTime = 0;
                        FridgeRecipe recipe = entity.chooseRecipe.value();
                        entity.iceTemperature -= recipe.getConsumeFuel();
                        entity.cookTimeTotal = recipe.getCookTime();
                        if(entity.inventory.getStackInSlot(6).isEmpty()) entity.inventory.setStackInSlot(6, recipe.getResultItem(level.registryAccess()).copy());
                        else entity.inventory.getStackInSlot(6).grow(recipe.getResultItem(level.registryAccess()).getCount());
                        recipe.getIngredients().forEach(ing->{
                            for(int i=0;i<6;i++){
                                if(ing.test(entity.inventory.getStackInSlot(i))){
                                    entity.inventory.getStackInSlot(i).shrink(1);
                                    break;
                                }
                            }
                        });
                        entity.chooseRecipe = null;
                        entity.inventoryChanged();

                    }
                }
            }
        }
    }
}
