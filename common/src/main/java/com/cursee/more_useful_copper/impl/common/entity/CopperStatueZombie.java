package com.cursee.more_useful_copper.impl.common.entity;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import com.cursee.more_useful_copper.impl.common.statues.StatueVariantRegistry;
import com.cursee.more_useful_copper.impl.common.statues.StatueVariantRegistry.StatueVariant;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

// creeper, skeleton, spider, zombie variants
public class CopperStatueZombie extends AbstractStatue implements StatueVariant {

  // private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(CopperStatueZombie.class, EntityDataSerializers.STRING);

  private final String variant;

  public CopperStatueZombie(EntityType<? extends AbstractStatue> entityType, Level level) {
    super(entityType, level);
    this.variant = "zombie";
    this.setVariant("zombie");
  }

  @Override
  protected Item getStatueItem() {
    return ModItems.COPPER_STATUE_ZOMBIE;
  }

  public CopperStatueZombie(Level level, String variant) {
    super(ModEntities.COPPER_STATUE_ZOMBIE, level);
    this.variant = variant;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(VARIANT, this.getVariantId().toString());
  }

  public StatueVariant getVariant() {
    return StatueVariantRegistry.get(this.getVariantId().toString());
  }

  @Override
  public ResourceLocation getVariantId() {
    return MoreUsefulCopper.identifier("zombie");
  }
}
