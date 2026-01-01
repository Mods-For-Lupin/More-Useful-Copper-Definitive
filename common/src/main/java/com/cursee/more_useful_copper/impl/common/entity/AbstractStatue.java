package com.cursee.more_useful_copper.impl.common.entity;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecart.Type;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class AbstractStatue extends LivingEntity {

  private static final Predicate<Entity> RIDEABLE_MINECARTS = (entity) -> entity instanceof AbstractMinecart cart && cart.getMinecartType() == Type.RIDEABLE;

  private final NonNullList<ItemStack> handItems;
  private final NonNullList<ItemStack> armorItems;

  public AbstractStatue(EntityType<? extends AbstractStatue> entityType, Level level) {
    super(entityType, level);

    this.handItems = NonNullList.withSize(1, ItemStack.EMPTY);
    this.armorItems = NonNullList.withSize(1, ItemStack.EMPTY);

    this.setMaxUpStep(0.0f);
  }

  public AbstractStatue(EntityType<? extends AbstractStatue> entityType, Level level, double x, double y, double z) {
    this(entityType, level);
    this.setPos(x, y, z);
  }

  @Override
  public Iterable<ItemStack> getArmorSlots() {
    return this.armorItems;
  }

  @Override
  public Iterable<ItemStack> getHandSlots() {
    return this.handItems;
  }

  @Override
  public ItemStack getItemBySlot(EquipmentSlot slot) {
    return ItemStack.EMPTY;
  }

  @Override
  public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
  }

  @Override
  public HumanoidArm getMainArm() {
    return HumanoidArm.RIGHT;
  }

  public boolean isPushable() {
    return false;
  }

  protected void doPush(Entity entity) {
  }

  protected void pushEntities() {
    List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), RIDEABLE_MINECARTS);

    for (int i = 0; i < list.size(); ++i) {
      Entity entity = list.get(i);
      if (this.distanceToSqr(entity) <= 0.2) {
        entity.push(this);
      }
    }

  }

  public boolean shouldRenderAtSqrDistance(double distance) {
    double d0 = this.getBoundingBox().getSize() * (double) 4.0F;
    if (Double.isNaN(d0) || d0 == (double) 0.0F) {
      d0 = 4.0F;
    }

    d0 *= 64.0F;
    return distance < d0 * d0;
  }

  public void setYBodyRot(float offset) {
    this.yBodyRotO = this.yRotO = offset;
    this.yHeadRotO = this.yHeadRot = offset;
  }

  public void setYHeadRot(float rotation) {
    this.yBodyRotO = this.yRotO = rotation;
    this.yHeadRotO = this.yHeadRot = rotation;
  }

  public void kill() {
    this.remove(RemovalReason.KILLED);
    this.gameEvent(GameEvent.ENTITY_DIE);
  }

  public void thunderHit(ServerLevel level, LightningBolt lightning) {
  }

  public boolean isAffectedByPotions() {
    return false;
  }

  public boolean attackable() {
    return false;
  }

  public ItemStack getPickResult() {
    return new ItemStack(Items.ARMOR_STAND);
  }
}
