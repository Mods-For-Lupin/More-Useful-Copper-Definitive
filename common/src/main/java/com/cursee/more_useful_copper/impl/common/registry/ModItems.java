package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.item.CopperStatueItem;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems {

  public static Item COPPER_STATUE;

  public static void register(BiConsumer<Item, ResourceLocation> consumer) {

    COPPER_STATUE = new CopperStatueItem(new Properties());

    consumer.accept(COPPER_STATUE, MoreUsefulCopper.identifier("copper_statue"));
  }
}
