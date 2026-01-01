package com.cursee.more_useful_copper.impl.common.entity;

import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecart.Type;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class AbstractStatue extends LivingEntity {

  public static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(AbstractStatue.class, EntityDataSerializers.STRING);

  private static final Predicate<Entity> RIDEABLE_MINECARTS = (entity) -> entity instanceof AbstractMinecart cart && cart.getMinecartType() == Type.RIDEABLE;

  private final NonNullList<ItemStack> handItems;
  private final NonNullList<ItemStack> armorItems;

  public long lastHit; // mimicking armor stand

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

  public void handleEntityEvent(byte id) {
    if (id == EntityEvent.ARMORSTAND_WOBBLE) {
      if (this.level().isClientSide) {
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3F, 1.0F, false);
        this.lastHit = this.level().getGameTime();
      }
    } else {
      super.handleEntityEvent(id);
    }

  }

  @Override
  public boolean isCustomNameVisible() {
    return false;
  }

  public boolean hurt(DamageSource source, float amount) {
    if (!this.level().isClientSide && !this.isRemoved()) {
      if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
        this.kill();
        return false;
      } else if (!this.isInvulnerableTo(source)) {
        if (source.is(DamageTypeTags.IS_EXPLOSION)) {
          this.brokenByAnything(source);
          this.kill();
          return false;
        } else if (source.is(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
          if (this.isOnFire()) {
            this.causeDamage(source, 0.15F);
          } else {
            this.setSecondsOnFire(5);
          }

          return false;
        } else if (source.is(DamageTypeTags.BURNS_ARMOR_STANDS) && this.getHealth() > 0.5F) {
          this.causeDamage(source, 4.0F);
          return false;
        } else {
          boolean flag = source.getDirectEntity() instanceof AbstractArrow;
          boolean flag1 = flag && ((AbstractArrow) source.getDirectEntity()).getPierceLevel() > 0;
          boolean flag2 = "player".equals(source.getMsgId());
          if (!flag2 && !flag) {
            return false;
          } else {
            Entity entity = source.getEntity();
            if (entity instanceof Player player) {
              if (!player.getAbilities().mayBuild) {
                return false;
              }
            }

            if (source.isCreativePlayer()) {
              this.playBrokenSound();
              this.showBreakingParticles();
              this.kill();
              return flag1;
            } else {
              long i = this.level().getGameTime();
              if (i - this.lastHit > 5L && !flag) {
                this.level().broadcastEntityEvent(this, EntityEvent.ARMORSTAND_WOBBLE);
                this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
                this.lastHit = i;
              } else {
                this.brokenByPlayer(source);
                this.showBreakingParticles();
                this.kill();
              }

              return true;
            }
          }
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  private void causeDamage(DamageSource damageSource, float amount) {
    float f = this.getHealth();
    f -= amount;
    if (f <= 0.5F) {
      this.brokenByAnything(damageSource);
      this.kill();
    } else {
      this.setHealth(f);
      this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());
    }

  }

  private void showBreakingParticles() {
    if (this.level() instanceof ServerLevel) {
      ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666), this.getZ(), 10,
          this.getBbWidth() / 4.0F, this.getBbHeight() / 4.0F, this.getBbWidth() / 4.0F, 0.05);
    }

  }

  private void brokenByPlayer(DamageSource damageSource) {

    Item typed = switch (this.entityData.get(VARIANT)) {
      case "creeper" -> ModItems.COPPER_STATUE_CREEPER;
      case "skeleton" -> ModItems.COPPER_STATUE_SKELETON;
      case "spider" -> ModItems.COPPER_STATUE_SPIDER;
      default -> ModItems.COPPER_STATUE_ZOMBIE;
    };

    ItemStack itemstack = new ItemStack(typed);
    if (this.hasCustomName()) {
      itemstack.setHoverName(this.getCustomName());
    }

    Block.popResource(this.level(), this.blockPosition(), itemstack);
    this.brokenByAnything(damageSource);
  }

  public void setVariant(String variant) {
    this.entityData.set(VARIANT, variant);
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

  @Override
  public ItemStack getPickResult() {
    return new ItemStack(this.getStatueItem());
  }

  private void brokenByAnything(DamageSource damageSource) {
    this.playBrokenSound();
    this.dropAllDeathLoot(damageSource);

    for (int i = 0; i < this.handItems.size(); ++i) {
      ItemStack itemstack = this.handItems.get(i);
      if (!itemstack.isEmpty()) {
        Block.popResource(this.level(), this.blockPosition().above(), itemstack);
        this.handItems.set(i, ItemStack.EMPTY);
      }
    }

    for (int j = 0; j < this.armorItems.size(); ++j) {
      ItemStack itemstack1 = this.armorItems.get(j);
      if (!itemstack1.isEmpty()) {
        Block.popResource(this.level(), this.blockPosition().above(), itemstack1);
        this.armorItems.set(j, ItemStack.EMPTY);
      }
    }

  }

  private void playBrokenSound() {
    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_BREAK, this.getSoundSource(), 1.0F, 1.0F);
  }

  protected abstract Item getStatueItem();
}
