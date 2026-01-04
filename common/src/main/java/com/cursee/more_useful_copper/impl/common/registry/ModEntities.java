package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.entity.CopperBottomBoat;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueCreeper;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueSkeleton;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueSpider;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueZombie;
import com.cursee.more_useful_copper.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

  public static EntityType<CopperStatueCreeper> COPPER_STATUE_CREEPER;
  public static EntityType<CopperStatueSkeleton> COPPER_STATUE_SKELETON;
  public static EntityType<CopperStatueSpider> COPPER_STATUE_SPIDER;
  public static EntityType<CopperStatueZombie> COPPER_STATUE_ZOMBIE;

  public static EntityType<CopperBottomBoat> COPPER_BOTTOM_BOAT;

  public static void register(BiConsumer<EntityType<?>, ResourceLocation> consumer) {

    COPPER_STATUE_CREEPER = Services.PLATFORM.<CopperStatueCreeper>createEntityType(CopperStatueCreeper::new, MobCategory.MISC).sized(0.5F, 1.975F).clientTrackingRange(10)
        .build(MoreUsefulCopper.identifier("copper_statue_creeper").toString());

    COPPER_STATUE_SKELETON = Services.PLATFORM.<CopperStatueSkeleton>createEntityType(CopperStatueSkeleton::new, MobCategory.MISC).sized(0.5F, 1.975F).clientTrackingRange(10)
        .build(MoreUsefulCopper.identifier("copper_statue_skeleton").toString());

    COPPER_STATUE_SPIDER = Services.PLATFORM.<CopperStatueSpider>createEntityType(CopperStatueSpider::new, MobCategory.MISC).sized(0.5F, 1.975F).clientTrackingRange(10)
        .build(MoreUsefulCopper.identifier("copper_statue_spider").toString());

    COPPER_STATUE_ZOMBIE = Services.PLATFORM.<CopperStatueZombie>createEntityType(CopperStatueZombie::new, MobCategory.MISC).sized(0.5F, 1.975F).clientTrackingRange(10)
        .build(MoreUsefulCopper.identifier("copper_statue_zombie").toString());

    COPPER_BOTTOM_BOAT = Services.PLATFORM.<CopperBottomBoat>createEntityType(CopperBottomBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10)
        .build(MoreUsefulCopper.identifier("copper_bottom_boat").toString());

    consumer.accept(COPPER_STATUE_CREEPER, MoreUsefulCopper.identifier("copper_statue_creeper"));
    consumer.accept(COPPER_STATUE_SKELETON, MoreUsefulCopper.identifier("copper_statue_skeleton"));
    consumer.accept(COPPER_STATUE_SPIDER, MoreUsefulCopper.identifier("copper_statue_spider"));
    consumer.accept(COPPER_STATUE_ZOMBIE, MoreUsefulCopper.identifier("copper_statue_zombie"));

    consumer.accept(COPPER_BOTTOM_BOAT, MoreUsefulCopper.identifier("copper_bottom_boat"));
  }
}
