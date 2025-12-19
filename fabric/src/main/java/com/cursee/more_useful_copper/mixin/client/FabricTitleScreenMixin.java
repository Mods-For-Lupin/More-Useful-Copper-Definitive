package com.cursee.more_useful_copper.mixin.client;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class FabricTitleScreenMixin {

  @Inject(at = @At("HEAD"), method = "init()V")
  private void init(CallbackInfo info) {

    if (Services.PLATFORM.isDevelopmentEnvironment()) {
      MoreUsefulCopper.LOG.info("This line is printed by a More Useful Copper Fabric mixin!");
      MoreUsefulCopper.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
  }
}