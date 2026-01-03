package com.cursee.more_useful_copper.impl.common.registry;

import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.platform.Services;
import java.util.function.BiConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

  public static CreativeModeTab MORE_USEFUL_COPPER;

  public static void register(BiConsumer<CreativeModeTab, ResourceLocation> consumer) {

    MORE_USEFUL_COPPER = Services.PLATFORM.createCreativeTabBuilder()
        .icon(() -> new ItemStack(ModItems.MOISTURE_COMPASS))
        .title(Component.translatable("itemGroup.moreUsefulCopper"))
        .displayItems((itemDisplayParameters, output) -> {

          output.accept(ModItems.COPPER_STATUE_CREEPER);
          output.accept(ModItems.COPPER_STATUE_SKELETON);
          output.accept(ModItems.COPPER_STATUE_SPIDER);
          output.accept(ModItems.COPPER_STATUE_ZOMBIE);

          output.accept(ModItems.MOISTURE_COMPASS);
          output.accept(ModItems.COPPER_BELL);
        })
        .build();

    consumer.accept(MORE_USEFUL_COPPER, MoreUsefulCopper.identifier(Constants.MOD_ID));

  }
}
