package com.cursee.more_useful_copper.impl.common.block;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;

public class GardenStakeBlock extends Block {

  public static final BooleanProperty LIT = BlockStateProperties.LIT;

  public GardenStakeBlock(Properties properties) {
    super(properties.randomTicks());
    this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(LIT);
  }

  @Override
  public void tick(BlockState selfState, ServerLevel level, BlockPos selfPos, RandomSource random) {

    if (!selfState.getValue(LIT)) {
      return;
    }

    AABB box = new AABB(selfPos).inflate(5, 3, 5);
    List<BlockPos> alreadyChecked = new ArrayList<>();

    for (double x = box.minX; x < box.maxX; x++) {
      for (double z = box.minZ; z < box.maxZ; z++) {
        for (double y = box.minY; y < box.maxY; y++) {

          BlockPos pos = BlockPos.containing(x, y, z);
          BlockState state = level.getBlockState(pos);

          if (!alreadyChecked.contains(pos) && state.getBlock() instanceof BonemealableBlock block) {

            // Blocks.WHEAT -> CropBlock#performBonemeal -> CropBlock#growCrops
            block.performBonemeal(level, random, pos, state);

            alreadyChecked.add(pos);
          }
        }
      }
    }
  }
}
