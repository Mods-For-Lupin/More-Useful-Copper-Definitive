package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSpiderRenderer;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MoreUsefulCopperClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    EntityModelLayerRegistry.registerModelLayer(CopperStatueSpiderModel.LAYER_LOCATION, CopperStatueSpiderModel::createBodyLayer);
    EntityRendererRegistry.register(ModEntities.COPPER_STATUE_SPIDER, CopperStatueSpiderRenderer::new);
  }
}
