package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.client.api_fabric.BuiltinItemRendererRegistry;
import com.cursee.more_useful_copper.impl.client.model.boat.CopperBottomBoatModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueCreeperModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSkeletonModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueZombieModel;
import com.cursee.more_useful_copper.impl.client.render.blockentity.CopperBellRenderer;
import com.cursee.more_useful_copper.impl.client.render.boat.CopperBottomBoatRenderer;
import com.cursee.more_useful_copper.impl.client.render.item.MoistureCompassItemRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueCreeperRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSkeletonRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSpiderRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueZombieRenderer;
import com.cursee.more_useful_copper.impl.common.item.MoistureCompassItem;
import com.cursee.more_useful_copper.impl.common.registry.ModBlockEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CompassItem;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class MoreUsefulCopperClientForge {

  public MoreUsefulCopperClientForge() {

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::clientSetup);

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::registerEntityModels);

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::registerEntityRenderers);
  }

  private void clientSetup(FMLClientSetupEvent event) {

    // CopperBottomBoatModel.createBodyModel();

    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COPPER_STATUE_SPIDER, (stack, mode, matrices, vertexConsumers, light, overlay) -> MoistureCompassItemRenderer.INSTANCE.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay));

    ItemProperties.register(ModItems.MOISTURE_COMPASS, new ResourceLocation("angle"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
      return !MoistureCompassItem.isMoistureCompass(itemStack) ? CompassItem.getSpawnPosition(clientLevel) : MoistureCompassItem.getMoisturePosition(itemStack.getOrCreateTag());
    }));

//    event.enqueueWork(() -> {
//
//    });
  }

//  private void attachCapabilities(IClientItemExtensions event) {
//
//    event.addCapability();
//  }

  private void registerEntityModels(EntityRenderersEvent.RegisterLayerDefinitions event) {

    event.registerLayerDefinition(CopperBottomBoatModel.LAYER_LOCATION, CopperBottomBoatModel::createBodyModel);

    event.registerLayerDefinition(CopperStatueSpiderModel.LAYER_LOCATION, CopperStatueSpiderModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueCreeperModel.LAYER_LOCATION, CopperStatueCreeperModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueSkeletonModel.LAYER_LOCATION, CopperStatueSkeletonModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueZombieModel.LAYER_LOCATION, CopperStatueZombieModel::createBodyLayer);
  }

  private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    event.registerBlockEntityRenderer(ModBlockEntities.COPPER_BELL, CopperBellRenderer::new);

    event.registerEntityRenderer(ModEntities.COPPER_BOTTOM_BOAT, CopperBottomBoatRenderer::new);

    event.registerEntityRenderer(ModEntities.COPPER_STATUE_SPIDER, CopperStatueSpiderRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_CREEPER, CopperStatueCreeperRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_SKELETON, CopperStatueSkeletonRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_ZOMBIE, CopperStatueZombieRenderer::new);

    event.registerEntityRenderer(ModEntities.LIGHTNING_BOTTLE, ThrownItemRenderer::new);
  }

//  private void registerItemColorHandlers(ItemEvent event) {
//    event.addModifier()
//  }
}
