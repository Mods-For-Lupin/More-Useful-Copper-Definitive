package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.item.CopperStatueItem;
import com.cursee.more_useful_copper.impl.common.item.MoistureCompassItem;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems {

  public static Item COPPER_STATUE_CREEPER;
  public static Item COPPER_STATUE_SKELETON;
  public static Item COPPER_STATUE_SPIDER;
  public static Item COPPER_STATUE_ZOMBIE;

  public static Item MOISTURE_COMPASS;

  public static void register(BiConsumer<Item, ResourceLocation> consumer) {

    COPPER_STATUE_CREEPER = new CopperStatueItem(new Properties(), "creeper");
    COPPER_STATUE_SKELETON = new CopperStatueItem(new Properties(), "skeleton");
    COPPER_STATUE_SPIDER = new CopperStatueItem(new Properties(), "spider");
    COPPER_STATUE_ZOMBIE = new CopperStatueItem(new Properties(), "zombie");

    MOISTURE_COMPASS = new MoistureCompassItem(new Properties());

    consumer.accept(COPPER_STATUE_CREEPER, MoreUsefulCopper.identifier("copper_statue_creeper"));
    consumer.accept(COPPER_STATUE_SKELETON, MoreUsefulCopper.identifier("copper_statue_skeleton"));
    consumer.accept(COPPER_STATUE_SPIDER, MoreUsefulCopper.identifier("copper_statue_spider"));
    consumer.accept(COPPER_STATUE_ZOMBIE, MoreUsefulCopper.identifier("copper_statue_zombie"));

    consumer.accept(MOISTURE_COMPASS, MoreUsefulCopper.identifier("moisture_compass"));
  }
}
