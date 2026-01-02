package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.block.BellBlock;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

  public static Block COPPER_BELL;

  public static void register(BiConsumer<Block, ResourceLocation> consumer) {

    COPPER_BELL = new BellBlock(Properties.of().mapColor(MapColor.GOLD).forceSolidOn().requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.ANVIL).pushReaction(PushReaction.DESTROY));

    consumer.accept(COPPER_BELL, MoreUsefulCopper.identifier("copper_bell"));
  }
}
