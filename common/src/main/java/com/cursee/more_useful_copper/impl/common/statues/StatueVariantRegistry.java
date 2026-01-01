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

  public interface StatueVariant {

    ResourceLocation getVariantId();

    default String getStringifiedVariantId() {
      return getVariantId().toString();
    }
  }
}
