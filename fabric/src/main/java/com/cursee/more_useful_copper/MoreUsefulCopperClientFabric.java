package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueCreeperModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSkeletonModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueZombieModel;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueCreeperRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSkeletonRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSpiderRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueZombieRenderer;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class MoreUsefulCopperClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
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
