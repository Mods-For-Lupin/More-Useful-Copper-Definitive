package com.cursee.more_useful_copper.impl.common.item;

import com.cursee.more_useful_copper.impl.common.entity.AbstractStatue;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CopperStatueItem extends Item {

  private final String variant;

  public CopperStatueItem(Properties properties, String variant) {
    super(properties);
    this.variant = variant;
  }

  @Override
  public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
    tooltipComponents.add(Component.literal("Very delicate; this block oxidizes quickly."));
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Direction direction = context.getClickedFace();
    if (direction == Direction.DOWN) {
      return InteractionResult.FAIL;
    } else {
      Level level = context.getLevel();
      BlockPlaceContext blockplacecontext = new BlockPlaceContext(context);
      BlockPos blockpos = blockplacecontext.getClickedPos();
      ItemStack itemstack = context.getItemInHand();
      Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
      AABB aabb = EntityType.ARMOR_STAND.getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());
      if (level.noCollision(null, aabb) && level.getEntities(null, aabb).isEmpty()) {
        if (level instanceof ServerLevel serverlevel) {

          EntityType<? extends AbstractStatue> entityType = switch (this.variant) {
            case "creeper" -> ModEntities.COPPER_STATUE_CREEPER;
            case "skeleton" -> ModEntities.COPPER_STATUE_SKELETON;
            case "spider" -> ModEntities.COPPER_STATUE_SPIDER;
            default -> ModEntities.COPPER_STATUE_ZOMBIE;
          };

          // Consumer<AbstractStatue> consumer = EntityType.createDefaultStackConfig(serverlevel, itemstack, context.getPlayer());
          AbstractStatue armorstand = entityType.create(serverlevel, itemstack.getTag(), null, blockpos, MobSpawnType.SPAWN_EGG, true, true);
          if (armorstand == null) {
            return InteractionResult.FAIL;
          }

          float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
          armorstand.moveTo(armorstand.getX(), armorstand.getY(), armorstand.getZ(), f, 0.0F);
          armorstand.setYBodyRot(f); // ensure that rotation gets synced
          serverlevel.addFreshEntityWithPassengers(armorstand);
          level.playSound(null, armorstand.getX(), armorstand.getY(), armorstand.getZ(), SoundEvents.ARMOR_STAND_PLACE, SoundSource.BLOCKS, 0.75F, 0.8F);
          armorstand.gameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
        }

        itemstack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
      } else {
        return InteractionResult.FAIL;
      }
    }
  }

//  @Override
//  public InteractionResult useOn(UseOnContext context) {
//    Direction direction = context.getClickedFace();
//    if (direction == Direction.DOWN) {
//      return InteractionResult.FAIL;
//    } else {
//      Level level = context.getLevel();
//      BlockPlaceContext blockplacecontext = new BlockPlaceContext(context);
//      BlockPos blockpos = blockplacecontext.getClickedPos();
//      ItemStack itemstack = context.getItemInHand();
//      Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
//
//
//      EntityType<? extends AbstractStatue> statueType = switch (this.variant) {
//        case "skeleton" -> ModEntities.COPPER_STATUE_SKELETON;
//        case "spider" -> ModEntities.COPPER_STATUE_SPIDER;
//        case "zombie" -> ModEntities.COPPER_STATUE_ZOMBIE;
//        default -> ModEntities.COPPER_STATUE_CREEPER;
//      };
//
//      AABB aabb = statueType.getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());
//      if (level.noCollision(null, aabb) && level.getEntities(null, aabb).isEmpty()) {
//        if (level instanceof ServerLevel serverlevel) {
//         //  Consumer<CopperStatueSpider> consumer = EntityType.createDefaultStackConfig(serverlevel, itemstack, context.getPlayer());
//          // CopperStatue statue = ModEntities.COPPER_STATUE.create(serverlevel, itemstack.getTag(), consumer, blockpos, MobSpawnType.SPAWN_EGG, true, true);
//
//          // CopperStatue statue = new CopperStatue(level, blockpos.getX(), blockpos.getY(), blockpos.getZ());
//          // AbstractStatue statue = statueType.create(serverlevel);
//
//          AbstractStatue statue = (AbstractStatue)statueType.create(serverlevel, itemstack.getTag(), null, blockpos, MobSpawnType.SPAWN_EGG, true, true);
//
//          if (statue == null) {
//            return InteractionResult.FAIL;
//          }
//
//          float f = (float) Mth.floor((Mth.wrapDegrees(context.getRotation() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
//          statue.moveTo(statue.getX(), statue.getY(), statue.getZ(), f, 0.0F);
//          serverlevel.addFreshEntityWithPassengers(statue);
//          level.playSound(null, statue.getX(), statue.getY(), statue.getZ(), SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 0.75F, 0.8F);
//          statue.gameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
//        }
//
//        itemstack.shrink(1);
//        return InteractionResult.sidedSuccess(level.isClientSide);
//      } else {
//        return InteractionResult.FAIL;
//      }
//    }
//  }
}
