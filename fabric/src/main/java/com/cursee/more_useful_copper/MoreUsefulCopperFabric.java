package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.common.registry.ModBlocks;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import com.cursee.more_useful_copper.impl.common.registry.ModTabs;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class MoreUsefulCopperFabric implements ModInitializer {

  public static <T> void bind(Registry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
    source.accept((t, rl) -> Registry.register(registry, rl, t));
  }

  @Override
  public void onInitialize() {

    // bind before init
    bind(BuiltInRegistries.BLOCK, ModBlocks::register);
    bind(BuiltInRegistries.ITEM, ModItems::register);
    bind(BuiltInRegistries.CREATIVE_MODE_TAB, ModTabs::register);

    bind(BuiltInRegistries.ENTITY_TYPE, ModEntities::register);

    FabricDefaultAttributeRegistry.register(ModEntities.COPPER_STATUE_SPIDER, LivingEntity.createLivingAttributes()); // give em life or something
    FabricDefaultAttributeRegistry.register(ModEntities.COPPER_STATUE_CREEPER, LivingEntity.createLivingAttributes());
    FabricDefaultAttributeRegistry.register(ModEntities.COPPER_STATUE_SKELETON, LivingEntity.createLivingAttributes());
    FabricDefaultAttributeRegistry.register(ModEntities.COPPER_STATUE_ZOMBIE, LivingEntity.createLivingAttributes());

    MoreUsefulCopper.init();
  }
}
