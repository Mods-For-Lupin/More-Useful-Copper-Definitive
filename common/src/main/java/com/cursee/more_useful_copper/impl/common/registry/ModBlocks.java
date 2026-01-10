package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.block.CopperBellBlock;
import com.cursee.more_useful_copper.impl.common.block.CopperButtonBlock;
import com.cursee.more_useful_copper.impl.common.block.GardenStakeBlock;
import com.cursee.more_useful_copper.impl.common.block.CopperPoweredRailBlock;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

  public static Block COPPER_BELL;

  public static Block COPPER_BUTTON;
  public static Block EXPOSED_COPPER_BUTTON;
  public static Block WEATHERED_COPPER_BUTTON;
  public static Block OXIDIZED_COPPER_BUTTON;

  public static Block COPPER_POWERED_RAIL;

  public static Block GARDEN_STAKE;

  public static void register(BiConsumer<Block, ResourceLocation> consumer) {

    COPPER_BELL = new CopperBellBlock(Properties.of().mapColor(MapColor.GOLD).forceSolidOn().requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.ANVIL).pushReaction(PushReaction.DESTROY));

    COPPER_BUTTON = new CopperButtonBlock(Properties.of().randomTicks().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON, 10, true);
    EXPOSED_COPPER_BUTTON = new CopperButtonBlock(Properties.of().randomTicks().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON, 20, true);
    WEATHERED_COPPER_BUTTON = new CopperButtonBlock(Properties.of().randomTicks().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON, 40, false);
    OXIDIZED_COPPER_BUTTON = new CopperButtonBlock(Properties.of().randomTicks().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON, 80, false);

    COPPER_POWERED_RAIL = new CopperPoweredRailBlock(Properties.of().noCollission().strength(0.7F).sound(SoundType.METAL));
    GARDEN_STAKE = new GardenStakeBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission().lightLevel(s -> s.getValue(GardenStakeBlock.LIT) ? 7 : 2));

    consumer.accept(COPPER_BELL, MoreUsefulCopper.identifier("copper_bell"));

    consumer.accept(COPPER_BUTTON, MoreUsefulCopper.identifier("copper_button"));
    consumer.accept(EXPOSED_COPPER_BUTTON, MoreUsefulCopper.identifier("exposed_copper_button"));
    consumer.accept(WEATHERED_COPPER_BUTTON, MoreUsefulCopper.identifier("weathered_copper_button"));
    consumer.accept(OXIDIZED_COPPER_BUTTON, MoreUsefulCopper.identifier("oxidized_copper_button"));

    consumer.accept(COPPER_POWERED_RAIL, MoreUsefulCopper.identifier("copper_powered_rail"));
    consumer.accept(GARDEN_STAKE, MoreUsefulCopper.identifier("garden_stake"));
  }
}
