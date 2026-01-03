package com.cursee.more_useful_copper.impl.common.block;

import com.cursee.more_useful_copper.impl.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CopperButtonBlock extends FaceAttachedHorizontalDirectionalBlock {

  public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
  public static final BooleanProperty WAXED = BooleanProperty.create("waxed");
  public static final IntegerProperty OXIDIZATION = IntegerProperty.create("oxidization", 0, 3);

  protected static final VoxelShape CEILING_AABB_X = Block.box(6.0F, 14.0F, 5.0F, 10.0F, 16.0F, 11.0F);
  protected static final VoxelShape CEILING_AABB_Z = Block.box(5.0F, 14.0F, 6.0F, 11.0F, 16.0F, 10.0F);
  protected static final VoxelShape FLOOR_AABB_X = Block.box(6.0F, 0.0F, 5.0F, 10.0F, 2.0F, 11.0F);
  protected static final VoxelShape FLOOR_AABB_Z = Block.box(5.0F, 0.0F, 6.0F, 11.0F, 2.0F, 10.0F);
  protected static final VoxelShape NORTH_AABB = Block.box(5.0F, 6.0F, 14.0F, 11.0F, 10.0F, 16.0F);
  protected static final VoxelShape SOUTH_AABB = Block.box(5.0F, 6.0F, 0.0F, 11.0F, 10.0F, 2.0F);
  protected static final VoxelShape WEST_AABB = Block.box(14.0F, 6.0F, 5.0F, 16.0F, 10.0F, 11.0F);
  protected static final VoxelShape EAST_AABB = Block.box(0.0F, 6.0F, 5.0F, 2.0F, 10.0F, 11.0F);
  protected static final VoxelShape PRESSED_CEILING_AABB_X = Block.box(6.0F, 15.0F, 5.0F, 10.0F, 16.0F, 11.0F);
  protected static final VoxelShape PRESSED_CEILING_AABB_Z = Block.box(5.0F, 15.0F, 6.0F, 11.0F, 16.0F, 10.0F);
  protected static final VoxelShape PRESSED_FLOOR_AABB_X = Block.box(6.0F, 0.0F, 5.0F, 10.0F, 1.0F, 11.0F);
  protected static final VoxelShape PRESSED_FLOOR_AABB_Z = Block.box(5.0F, 0.0F, 6.0F, 11.0F, 1.0F, 10.0F);
  protected static final VoxelShape PRESSED_NORTH_AABB = Block.box(5.0F, 6.0F, 15.0F, 11.0F, 10.0F, 16.0F);
  protected static final VoxelShape PRESSED_SOUTH_AABB = Block.box(5.0F, 6.0F, 0.0F, 11.0F, 10.0F, 1.0F);
  protected static final VoxelShape PRESSED_WEST_AABB = Block.box(15.0F, 6.0F, 5.0F, 16.0F, 10.0F, 11.0F);
  protected static final VoxelShape PRESSED_EAST_AABB = Block.box(0.0F, 6.0F, 5.0F, 1.0F, 10.0F, 11.0F);
  protected static final int HALF_AABB_HEIGHT = 2;
  protected static final int HALF_AABB_WIDTH = 3;
  private static final int PRESSED_DEPTH = 1;
  private static final int UNPRESSED_DEPTH = 2;

  private final BlockSetType type;
  private final int ticksToStayPressed;
  private final boolean arrowsCanPress;

  public CopperButtonBlock(BlockBehaviour.Properties properties, BlockSetType type, int ticksToStayPressed, boolean arrowsCanPress) {
    super(properties.sound(SoundType.COPPER));
    this.type = type;
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false).setValue(FACE, AttachFace.WALL).setValue(WAXED, false).setValue(OXIDIZATION, 0));
    this.ticksToStayPressed = ticksToStayPressed;
    this.arrowsCanPress = arrowsCanPress;
  }

  @Override
  public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

    if (state.getValue(WAXED) || state.is(ModBlocks.OXIDIZED_COPPER_BUTTON)) {
      return;
    }

    // int oxidization = state.getValue(OXIDIZATION);

    AttachFace face = state.getValue(FACE);
    Direction facing = state.getValue(FACING);
    boolean powered = state.getValue(POWERED);

    BlockState newState = null;

    if (state.is(ModBlocks.COPPER_BUTTON)) {
      newState = ModBlocks.EXPOSED_COPPER_BUTTON.defaultBlockState().setValue(FACE, face);
    } else if (state.is(ModBlocks.EXPOSED_COPPER_BUTTON)) {
      newState = ModBlocks.WEATHERED_COPPER_BUTTON.defaultBlockState().setValue(FACE, face);
    } else if (state.is(ModBlocks.WEATHERED_COPPER_BUTTON)) {
      newState = ModBlocks.OXIDIZED_COPPER_BUTTON.defaultBlockState().setValue(FACE, face);
    }

    if (newState == null) {
      return;
    }

    // System.out.println("copying facing, face, powered values from original state.");
    newState.setValue(FACING, facing);
    newState.setValue(FACE, face);
    newState.setValue(POWERED, powered);
    level.setBlock(pos, newState, 18);

    level.setBlocksDirty(pos, state, newState);
  }

  public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    Direction direction = state.getValue(FACING);
    boolean flag = state.getValue(POWERED);
    switch (state.getValue(FACE)) {
      case FLOOR:
        if (direction.getAxis() == Axis.X) {
          return flag ? PRESSED_FLOOR_AABB_X : FLOOR_AABB_X;
        }

        return flag ? PRESSED_FLOOR_AABB_Z : FLOOR_AABB_Z;
      case WALL:

        return switch (direction) {
          case EAST -> flag ? PRESSED_EAST_AABB : EAST_AABB;
          case WEST -> flag ? PRESSED_WEST_AABB : WEST_AABB;
          case SOUTH -> flag ? PRESSED_SOUTH_AABB : SOUTH_AABB;
          case NORTH, UP, DOWN -> flag ? PRESSED_NORTH_AABB : NORTH_AABB;
        };
      case CEILING:
      default:
        if (direction.getAxis() == Axis.X) {
          return flag ? PRESSED_CEILING_AABB_X : CEILING_AABB_X;
        } else {
          return flag ? PRESSED_CEILING_AABB_Z : CEILING_AABB_Z;
        }
    }
  }

  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
    if (state.getValue(POWERED)) {
      return InteractionResult.CONSUME;
    } else {
      this.press(state, level, pos);
      this.playSound(player, level, pos, true);
      level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
      return InteractionResult.sidedSuccess(level.isClientSide);
    }
  }

  public void press(BlockState state, Level level, BlockPos pos) {
    level.setBlock(pos, state.setValue(POWERED, true), 3);
    this.updateNeighbours(state, level, pos);
    level.scheduleTick(pos, this, this.ticksToStayPressed);
  }

  protected void playSound(Player player, LevelAccessor level, BlockPos pos, boolean hitByArrow) {
    level.playSound(hitByArrow ? player : null, pos, this.getSound(hitByArrow), SoundSource.BLOCKS);
  }

  protected SoundEvent getSound(boolean isOn) {
    return isOn ? this.type.buttonClickOn() : this.type.buttonClickOff();
  }

  public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    if (!isMoving && !state.is(newState.getBlock())) {
      if (state.getValue(POWERED)) {
        this.updateNeighbours(state, level, pos);
      }

      super.onRemove(state, level, pos, newState, isMoving);
    }

  }

  /**
   * @deprecated
   */
  public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
    return blockState.getValue(POWERED) ? 15 : 0;
  }

  /**
   * @deprecated
   */
  public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
    return blockState.getValue(POWERED) && getConnectedDirection(blockState) == side ? 15 : 0;
  }

  /**
   * @deprecated
   */
  public boolean isSignalSource(BlockState state) {
    return true;
  }

  public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
    if (state.getValue(POWERED)) {
      this.checkPressed(state, level, pos);
    }

  }

  public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
    if (!level.isClientSide && this.arrowsCanPress && !(Boolean) state.getValue(POWERED)) {
      this.checkPressed(state, level, pos);
    }

  }

  protected void checkPressed(BlockState state, Level level, BlockPos pos) {
    AbstractArrow abstractarrow = this.arrowsCanPress ? level.getEntitiesOfClass(AbstractArrow.class, state.getShape(level, pos).bounds().move(pos)).stream().findFirst().orElse(null) : null;
    boolean flag = abstractarrow != null;
    boolean flag1 = state.getValue(POWERED);
    if (flag != flag1) {
      level.setBlock(pos, state.setValue(POWERED, flag), 3);
      this.updateNeighbours(state, level, pos);
      this.playSound(null, level, pos, flag);
      level.gameEvent(abstractarrow, flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    if (flag) {
      level.scheduleTick(new BlockPos(pos), this, this.ticksToStayPressed);
    }

  }

  private void updateNeighbours(BlockState state, Level level, BlockPos pos) {
    level.updateNeighborsAt(pos, this);
    level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
  }

  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING, POWERED, FACE, WAXED, OXIDIZATION);
  }
}

