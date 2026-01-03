package com.cursee.more_useful_copper.mixin.client;

import com.cursee.more_useful_copper.impl.client.api_fabric.BuiltinItemRendererRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
abstract class BlockEntityWithoutLevelRendererMixin {

  @Inject(method = "renderByItem", at = @At("HEAD"), cancellable = true)
  private void fabric_onRender(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, CallbackInfo info) {
    BuiltinItemRendererRegistry.DynamicItemRenderer renderer = BuiltinItemRendererRegistry.INSTANCE.get(stack.getItem());

    if (renderer != null) {
      renderer.render(stack, mode, matrices, vertexConsumers, light, overlay);
      info.cancel();
    }
  }
}
