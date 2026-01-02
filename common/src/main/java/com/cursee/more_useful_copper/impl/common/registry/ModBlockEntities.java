package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.block.entity.BellBlockEntity;
import com.cursee.more_useful_copper.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

  public static BlockEntityType<BellBlockEntity> COPPER_BELL;

  public static void register(BiConsumer<BlockEntityType<?>, ResourceLocation> consumer) {

    COPPER_BELL = Services.PLATFORM.createBlockEntityType(BellBlockEntity::new, ModBlocks.COPPER_BELL).build(null);

    consumer.accept(COPPER_BELL, MoreUsefulCopper.identifier("copper_bell"));
  }
}
