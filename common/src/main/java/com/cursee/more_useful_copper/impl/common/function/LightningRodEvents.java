package com.cursee.more_useful_copper.impl.common.function;

import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LightningRodEvents {

  private static final Supplier<ItemStack> CREATED_STACK = () -> new ItemStack(ModItems.LIGHTNING_BOTTLE);

  public static void onLightningStrike(Level abstractLevel, BlockPos pos) {

    if (!(abstractLevel instanceof ServerLevel level)) {
      return;
    }

    BlockEntity tile = level.getBlockEntity(pos.below());

    if (!(tile instanceof Container container)) {
      return;
    }

    int size = container.getContainerSize();

    if (container.hasAnyMatching(stack -> stack.is(Items.GLASS_BOTTLE))) {
      for (int index = 0; index < size; index++) {
        ItemStack stack = container.getItem(index);

        if (!stack.is(Items.GLASS_BOTTLE)) {
          continue; // check next slot
        }

        if (stack.getCount() == 1) {
          container.setItem(index, CREATED_STACK.get());
          break; // only create one item
        } else {

          // shrink empty bottle stack
          stack.shrink(1);
          container.setItem(index, stack);

          // if there is an empty slot
          if (container.hasAnyMatching(check -> check == ItemStack.EMPTY)) {
            for (int emptyIndex = 0; emptyIndex < size; emptyIndex++) {
              if (container.getItem(emptyIndex).isEmpty()) {
                container.setItem(emptyIndex, CREATED_STACK.get());
                break; // only create one item
              }
            }
          } else {

            var player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 64.0D, true);

            if (player != null) {
              player.addItem(CREATED_STACK.get());
            }

            break; // only create one item
          }
        }

      }
    }
  }
}
