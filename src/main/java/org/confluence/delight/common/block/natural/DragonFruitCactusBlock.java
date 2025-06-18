package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.confluence.mod.common.init.item.FoodItems;


public class DragonFruitCactusBlock extends CactusBlock {

    public DragonFruitCactusBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate = level.getBlockState(pos.relative(direction));
            if (blockstate.isSolid() || level.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) {
                return false;
            }
        }
        BlockState blockstate1 = level.getBlockState(pos.below());
        net.neoforged.neoforge.common.util.TriState soilDecision = blockstate1.canSustainPlant(level, pos.below(), Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return (blockstate1.is(this) || blockstate1.is(BlockTags.SAND)) && !level.getBlockState(pos.above()).liquid();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos abovePos = pos.above();
        int age = state.getValue(AGE);

        if (age < MAX_AGE) {
            if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, true)) {
                level.setBlock(pos, state.setValue(AGE, age + 1), 3);
                net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
            }
        } else if (age == MAX_AGE) {
            if (level.isEmptyBlock(abovePos)) {
                int height = 1;
                while (level.getBlockState(pos.below(height)).is(this)) {
                    height++;
                }
                if (height < 5) {
                    if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, true)) {
                        level.setBlockAndUpdate(abovePos, this.defaultBlockState().setValue(AGE, 0));
                        net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                    }
                }
            }
        }
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int currentAge = state.getValue(AGE);
        if (currentAge >= 13) {
            if (!level.isClientSide()) {
                ItemStack fruitStack = new ItemStack(FoodItems.DRAGON_FRUIT.get(), 1);
                player.getInventory().add(fruitStack);
                int newAge = Math.max(12, currentAge - 1);
                level.setBlockAndUpdate(pos, state.setValue(AGE, newAge));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }
}

