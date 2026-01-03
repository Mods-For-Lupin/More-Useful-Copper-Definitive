package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueCreeperModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSkeletonModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueZombieModel;
import com.cursee.more_useful_copper.impl.client.render.blockentity.BellRenderer;
import com.cursee.more_useful_copper.impl.client.render.item.MoistureCompassItemRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueCreeperRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSkeletonRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSpiderRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueZombieRenderer;
import com.cursee.more_useful_copper.impl.common.item.MoistureCompassItem;
import com.cursee.more_useful_copper.impl.common.registry.ModBlockEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CompassItem;

public class MoreUsefulCopperClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    this.registerEntityModelsAndRenderers();

    ItemProperties.register(ModItems.MOISTURE_COMPASS, new ResourceLocation("angle"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
      return !MoistureCompassItem.isMoistureCompass(itemStack) ? CompassItem.getSpawnPosition(clientLevel) : MoistureCompassItem.getMoisturePosition(itemStack.getOrCreateTag());
    }));

//    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COPPER_STATUE_SPIDER, (stack, mode, matrices, vertexConsumers, light, overlay) -> new DynamicItemRenderer() {
//
//      @Override
//      public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
//        JustDireItemRenderer.INSTANCE.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay);
//      }
//    });

    // BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COPPER_STATUE_SPIDER, JustDireItemRenderer.INSTANCE::renderByItem);
    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COPPER_STATUE_SPIDER, (stack, mode, matrices, vertexConsumers, light, overlay) -> MoistureCompassItemRenderer.INSTANCE.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay));
  }

  private void registerEntityModelsAndRenderers() {

    BlockEntityRenderers.register(ModBlockEntities.COPPER_BELL, BellRenderer::new);

    EntityModelLayerRegistry.registerModelLayer(CopperStatueSpiderModel.LAYER_LOCATION, CopperStatueSpiderModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.COPPER_STATUE_SPIDER, CopperStatueSpiderRenderer::new);

    // Creeper
    EntityModelLayerRegistry.registerModelLayer(CopperStatueCreeperModel.LAYER_LOCATION, CopperStatueCreeperModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.COPPER_STATUE_CREEPER, CopperStatueCreeperRenderer::new);

    // Skeleton
    EntityModelLayerRegistry.registerModelLayer(CopperStatueSkeletonModel.LAYER_LOCATION, CopperStatueSkeletonModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.COPPER_STATUE_SKELETON, CopperStatueSkeletonRenderer::new);

    // Zombie
    EntityModelLayerRegistry.registerModelLayer(CopperStatueZombieModel.LAYER_LOCATION, CopperStatueZombieModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.COPPER_STATUE_ZOMBIE, CopperStatueZombieRenderer::new);
  }
}
