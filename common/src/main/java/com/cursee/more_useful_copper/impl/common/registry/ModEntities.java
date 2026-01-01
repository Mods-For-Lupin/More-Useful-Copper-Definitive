package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueSpider;
import com.cursee.more_useful_copper.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

  public static EntityType<CopperStatueSpider> COPPER_STATUE_SPIDER;

  public static void register(BiConsumer<EntityType<?>, ResourceLocation> consumer) {

    COPPER_STATUE_SPIDER = Services.PLATFORM.<CopperStatueSpider>createEntityType(CopperStatueSpider::new, MobCategory.MISC).sized(0.5F, 1.975F).clientTrackingRange(10).build(MoreUsefulCopper.identifier("copper_statue_spider").toString());

    consumer.accept(COPPER_STATUE_SPIDER, MoreUsefulCopper.identifier("copper_statue_spider"));
  }
}
