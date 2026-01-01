package com.cursee.more_useful_copper.impl.common.statues;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;

public class StatueVariantRegistry {

  private static final Map<ResourceLocation, StatueVariant> VARIANT_BY_ID = new HashMap<>();

  public static StatueVariant register(ResourceLocation id, StatueVariant variant) {
    VARIANT_BY_ID.put(id, variant);
    return VARIANT_BY_ID.get(id);
  }

  public static StatueVariant registerInternal(String id, StatueVariant variant) {
    ResourceLocation rl = MoreUsefulCopper.identifier(id);
    VARIANT_BY_ID.put(rl, variant);
    return VARIANT_BY_ID.get(rl);
  }

  public static StatueVariant get(String stringifiedId) {
    return VARIANT_BY_ID.get(ResourceLocation.tryParse(stringifiedId));
  }

  public static void registerAll() {
    registerInternal("spider", createVariant("spider"));
    registerInternal("exposed_spider", createVariant("exposed_spider"));
    registerInternal("weathered_spider", createVariant("weathered_spider"));
    registerInternal("oxidized_spider", createVariant("oxidized_spider"));

    // Creeper
    registerInternal("creeper", createVariant("creeper"));
    registerInternal("exposed_creeper", createVariant("exposed_creeper"));
    registerInternal("weathered_creeper", createVariant("weathered_creeper"));
    registerInternal("oxidized_creeper", createVariant("oxidized_creeper"));

    // Skeleton
    registerInternal("skeleton", createVariant("skeleton"));
    registerInternal("exposed_skeleton", createVariant("exposed_skeleton"));
    registerInternal("weathered_skeleton", createVariant("weathered_skeleton"));
    registerInternal("oxidized_skeleton", createVariant("oxidized_skeleton"));

    // Zombie
    registerInternal("zombie", createVariant("zombie"));
    registerInternal("exposed_zombie", createVariant("exposed_zombie"));
    registerInternal("weathered_zombie", createVariant("weathered_zombie"));
    registerInternal("oxidized_zombie", createVariant("oxidized_zombie"));
  }

  private static StatueVariant createVariant(String id) {
    return () -> MoreUsefulCopper.identifier(id);
  }

  @FunctionalInterface
  public interface StatueVariant {

    ResourceLocation getVariantId();

    default String getStringifiedVariantId() {
      return getVariantId().toString();
    }
  }
}
