package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.item.CopperStatueItem;
import com.cursee.more_useful_copper.impl.common.item.MoistureCompassItem;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems {

  public static Item COPPER_STATUE_CREEPER;
  public static Item COPPER_STATUE_SKELETON;
  public static Item COPPER_STATUE_SPIDER;
  public static Item COPPER_STATUE_ZOMBIE;

  public static Item MOISTURE_COMPASS;
  public static Item COPPER_BELL;

  public static Item COPPER_BUTTON;
  public static Item EXPOSED_COPPER_BUTTON;
  public static Item WEATHERED_COPPER_BUTTON;
  public static Item OXIDIZED_COPPER_BUTTON;

  public static void register(BiConsumer<Item, ResourceLocation> consumer) {

    COPPER_STATUE_CREEPER = new CopperStatueItem(new Properties(), "creeper");
    COPPER_STATUE_SKELETON = new CopperStatueItem(new Properties(), "skeleton");
    COPPER_STATUE_SPIDER = new CopperStatueItem(new Properties(), "spider");
    COPPER_STATUE_ZOMBIE = new CopperStatueItem(new Properties(), "zombie");

    MOISTURE_COMPASS = new MoistureCompassItem(new Properties());
    COPPER_BELL = new BlockItem(ModBlocks.COPPER_BELL, new Properties());

    COPPER_BUTTON = new BlockItem(ModBlocks.COPPER_BUTTON, new Properties());
    EXPOSED_COPPER_BUTTON = new BlockItem(ModBlocks.EXPOSED_COPPER_BUTTON, new Properties());
    WEATHERED_COPPER_BUTTON = new BlockItem(ModBlocks.WEATHERED_COPPER_BUTTON, new Properties());
    OXIDIZED_COPPER_BUTTON = new BlockItem(ModBlocks.OXIDIZED_COPPER_BUTTON, new Properties());

    consumer.accept(COPPER_STATUE_CREEPER, MoreUsefulCopper.identifier("copper_statue_creeper"));
    consumer.accept(COPPER_STATUE_SKELETON, MoreUsefulCopper.identifier("copper_statue_skeleton"));
    consumer.accept(COPPER_STATUE_SPIDER, MoreUsefulCopper.identifier("copper_statue_spider"));
    consumer.accept(COPPER_STATUE_ZOMBIE, MoreUsefulCopper.identifier("copper_statue_zombie"));

    consumer.accept(MOISTURE_COMPASS, MoreUsefulCopper.identifier("moisture_compass"));
    consumer.accept(COPPER_BELL, MoreUsefulCopper.identifier("copper_bell"));

    consumer.accept(COPPER_BUTTON, MoreUsefulCopper.identifier("copper_button"));
    consumer.accept(EXPOSED_COPPER_BUTTON, MoreUsefulCopper.identifier("exposed_copper_button"));
    consumer.accept(WEATHERED_COPPER_BUTTON, MoreUsefulCopper.identifier("weathered_copper_button"));
    consumer.accept(OXIDIZED_COPPER_BUTTON, MoreUsefulCopper.identifier("oxidized_copper_button"));
  }
}
