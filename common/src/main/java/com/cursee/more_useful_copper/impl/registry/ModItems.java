package com.cursee.more_useful_copper.impl.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import java.util.function.BiConsumer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModItems {

  public static Item MOD_STICK;

  public static void register(BiConsumer<Item, Identifier> consumer) {

    MOD_STICK = new Item(new Item.Properties().stacksTo(1));
    consumer.accept(MOD_STICK, MoreUsefulCopper.identifier("mod_stick"));
  }
}
