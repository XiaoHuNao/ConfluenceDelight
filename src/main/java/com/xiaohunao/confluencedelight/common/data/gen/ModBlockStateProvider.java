package com.xiaohunao.confluencedelight.common.data.gen;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.block.SimpleFeastBlock;
import com.xiaohunao.confluencedelight.common.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Map;
import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final Map<Direction, Integer> DIRECTION_WITH_ROTATION =
            Map.of(Direction.NORTH, 0, Direction.EAST, 90,
                    Direction.SOUTH, 180, Direction.WEST, 270);

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ConfluenceDelight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerSimpleFeastBlock(ModBlocks.SIMPLE_FEAST_BLOCK.get());
    }

    private void registerSimpleFeastBlock(SimpleFeastBlock simpleFeastBlock) {
        String name = this.name(simpleFeastBlock);
        VariantBlockStateBuilder variantBuilder = this.getVariantBuilder(simpleFeastBlock);

        for (int i = 0; i <= simpleFeastBlock.getMaxServings(); i++) {
            for (Direction direction : FeastBlock.FACING.getPossibleValues()) {
                int rotation = DIRECTION_WITH_ROTATION.get(direction);

                ModelFile.ExistingModelFile modelFile = this.models().getExistingFile(
                        ConfluenceDelight.asResource("block/" + name + "_serving_" + (i == simpleFeastBlock.getMaxServings() ? 0 : i))
                );

                variantBuilder.partialState()
                        .with(FeastBlock.FACING, direction)
                        .with(FeastBlock.SERVINGS, i)
                        .modelForState()
                        .rotationY(rotation)
                        .modelFile(modelFile)
                        .addModel();
            }
        }
    }

    private String name(Block block) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block)).getPath();
    }

}
