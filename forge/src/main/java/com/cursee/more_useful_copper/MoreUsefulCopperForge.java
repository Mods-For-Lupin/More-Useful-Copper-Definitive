package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.common.registry.ModBlockEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModBlocks;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import com.cursee.more_useful_copper.impl.common.registry.ModTabs;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class MoreUsefulCopperForge {

  public static IEventBus EVENT_BUS;

  public MoreUsefulCopperForge(FMLJavaModLoadingContext context) {

    EVENT_BUS = context.getModEventBus();

    // bind before init
    bind(Registries.BLOCK, ModBlocks::register);
    bind(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities::register);
    bind(Registries.ITEM, ModItems::register);
    bind(Registries.CREATIVE_MODE_TAB, ModTabs::register);

    bind(Registries.ENTITY_TYPE, ModEntities::register);

    EVENT_BUS.addListener((Consumer<EntityAttributeCreationEvent>) event -> {
      event.put(ModEntities.COPPER_STATUE_SPIDER, LivingEntity.createLivingAttributes().build()); // give em life or something
      event.put(ModEntities.COPPER_STATUE_CREEPER, LivingEntity.createLivingAttributes().build());
      event.put(ModEntities.COPPER_STATUE_SKELETON, LivingEntity.createLivingAttributes().build());
      event.put(ModEntities.COPPER_STATUE_ZOMBIE, LivingEntity.createLivingAttributes().build());
    });

    MoreUsefulCopper.init();

    if (FMLLoader.getDist() == Dist.CLIENT) {
      new MoreUsefulCopperClientForge();
    }
  }

  @Deprecated
  @SuppressWarnings("all")
  public MoreUsefulCopperForge() {
    this(FMLJavaModLoadingContext.get());
  }

  public static <T> void bind(ResourceKey<Registry<T>> registryKey, Consumer<BiConsumer<T, ResourceLocation>> source) {
    EVENT_BUS.addListener((Consumer<RegisterEvent>) event -> {
      if (registryKey.equals(event.getRegistryKey())) {
        source.accept((t, rl) -> event.register(registryKey, rl, () -> t));
      }
    });
  }
}