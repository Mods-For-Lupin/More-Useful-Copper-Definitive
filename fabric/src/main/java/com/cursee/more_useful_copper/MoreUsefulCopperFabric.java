package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.registry.ModBlockEntities;
import com.cursee.more_useful_copper.impl.registry.ModBlocks;
import com.cursee.more_useful_copper.impl.registry.ModEntities;
import com.cursee.more_useful_copper.impl.registry.ModItems;
import com.cursee.more_useful_copper.impl.registry.ModMenus;
import com.cursee.more_useful_copper.impl.registry.ModTabs;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class MoreUsefulCopperFabric implements ModInitializer {

  @Override
  public void onInitialize() {

    // bind before init
    bind(BuiltInRegistries.BLOCK, ModBlocks::register);
    bind(BuiltInRegistries.BLOCK_ENTITY_TYPE, ModBlockEntities::register);
    bind(BuiltInRegistries.ITEM, ModItems::register);
    bind(BuiltInRegistries.ENTITY_TYPE, ModEntities::register);
    bind(BuiltInRegistries.CREATIVE_MODE_TAB, ModTabs::register);
    bind(BuiltInRegistries.MENU, ModMenus::register);

    MoreUsefulCopper.init();
  }

  public static <T> void bind(Registry<@NotNull T> registry, Consumer<BiConsumer<T, Identifier>> source) {
    source.accept((t, rl) -> Registry.register(registry, rl, t));
  }
}
