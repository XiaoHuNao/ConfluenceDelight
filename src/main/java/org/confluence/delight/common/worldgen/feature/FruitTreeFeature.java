package org.confluence.delight.common.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FruitTreeFeature extends Feature<FruitTreeFeature.Config> {
    public FruitTreeFeature(Codec<Config> pCodec) {
        super(pCodec);
    }

    public static void setLeaves(BoundingBox box, BlockState leaves, BlockState fruitedLeaves, float fruitedPC, boolean up, RandomSource random, WorldGenLevel level) {
        int xStart = box.minX();
        int yStart = box.minY();
        int zStart = box.minZ();
        int xEnd = box.maxX();
        int yEnd = box.maxY();
        int zEnd = box.maxZ();
        boolean set;
        BlockPos posPlace;
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                for (int z = zStart; z <= zEnd; z++) {
                    posPlace = new BlockPos(x, y, z);
                    set = (!((x == xStart || x == xEnd) && (z == zStart || z == zEnd)) || ((y == yStart || up) && random.nextInt(3) == 0)) && (level.getBlockState(posPlace).isAir());
                    if (set) {
                        level.setBlock(posPlace, (fruitedPC > random.nextFloat()) ? fruitedLeaves : leaves, 3);
                    }
                }
            }
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<Config> pContext) {
        RandomSource random = pContext.random();
        Config config = pContext.config();
        WorldGenLevel level = pContext.level();
        BlockPos baseBlockPos = pContext.origin();
        BlockState trunkBlockState = config.trunk().getState(random, baseBlockPos);
        BlockState leavesBlockState = config.leaves().getState(random, baseBlockPos);
        BlockState fruitedLeavesBlockState = config.fruited_leaves().getState(random, baseBlockPos);
        int height = config.height + random.nextInt(config.height_more + 1);
        float fruitedPercent = config.fruited_percent;
        List<BlockPos> trunkPosList = new ArrayList<>();
        Set<BlockPos> trunkPos = new HashSet<>();
        Set<BlockPos> leavesPos = new HashSet<>();
        Set<BlockPos> rootPos = new HashSet<>();
        BoundingBox box = new BoundingBox(baseBlockPos.getX() - 2, baseBlockPos.getY(), baseBlockPos.getZ() - 2, baseBlockPos.getX() + 2, baseBlockPos.getY() + height + 4, baseBlockPos.getZ() + 2);
        for (int i = 0; i < height + 3; i++) {
            trunkPosList.add(baseBlockPos.offset(0, i, 0));
        }

        boolean placed = true;

        for (BlockPos blockPos : trunkPosList) {
            if (!(level.getBlockState(blockPos).isAir() || level.getBlockState(blockPos).is(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("minecraft", "leaves"))))) {
                placed = false;
            }
        }

        if (placed) {
            for (BlockPos pos : trunkPosList) {
                level.setBlock(pos, trunkBlockState, 3);
                rootPos.add(pos);
            }
            setLeaves(new BoundingBox(baseBlockPos.getX() - 2, baseBlockPos.getY() + height, baseBlockPos.getZ() - 2, baseBlockPos.getX() + 2, baseBlockPos.getY() + height + 1, baseBlockPos.getZ() + 2), leavesBlockState, fruitedLeavesBlockState, fruitedPercent, true, random, level);
            setLeaves(new BoundingBox(baseBlockPos.getX() - 1, baseBlockPos.getY() + height + 2, baseBlockPos.getZ() - 1, baseBlockPos.getX() + 1, baseBlockPos.getY() + height + 3, baseBlockPos.getZ() + 1), leavesBlockState, fruitedLeavesBlockState, fruitedPercent, false, random, level);
            TreeFeature.updateLeaves(level, box, rootPos, trunkPos, leavesPos);
            return true;
        }
        return false;
    }

    public record Config(
        BlockStateProvider trunk,
        BlockStateProvider leaves,
        BlockStateProvider fruited_leaves,
        int height,
        int height_more,
        float fruited_percent
    ) implements FeatureConfiguration {
        public static final Codec<Config> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("trunk_block").forGetter(Config::trunk),
            BlockStateProvider.CODEC.fieldOf("leaves_block").forGetter(Config::leaves),
            BlockStateProvider.CODEC.fieldOf("fruited_leaves_block").forGetter(Config::fruited_leaves),
            Codec.INT.fieldOf("height").forGetter(FruitTreeFeature.Config::height),
            Codec.INT.fieldOf("height_more").forGetter(FruitTreeFeature.Config::height_more),
            Codec.FLOAT.fieldOf("fruited_percent").forGetter(FruitTreeFeature.Config::fruited_percent)
        ).apply(instance, Config::new));
    }
}
