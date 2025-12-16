package com.cursee.more_useful_copper.impl.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import java.util.function.BiConsumer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ArmorStandItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;

public class ModItems {

//  public static Item MOD_STICK;
  public static Item COPPER_STATUE_SPIDER;

  public static void register(BiConsumer<Item, Identifier> consumer) {

//    MOD_STICK = new Item(new Item.Properties().stacksTo(1));
//    consumer.accept(MOD_STICK, MoreUsefulCopper.identifier("mod_stick"));

    // supply entity type like spawn egg?
    COPPER_STATUE_SPIDER = new ArmorStandItem(new Properties());
    consumer.accept(COPPER_STATUE_SPIDER, MoreUsefulCopper.identifier("copper_statue_spider"));
  }
}
