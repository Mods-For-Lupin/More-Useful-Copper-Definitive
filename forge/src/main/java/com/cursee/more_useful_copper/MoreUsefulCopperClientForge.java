package com.cursee.more_useful_copper;

import com.cursee.more_useful_copper.impl.client.api_fabric.BuiltinItemRendererRegistry;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueCreeperModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSkeletonModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueZombieModel;
import com.cursee.more_useful_copper.impl.client.render.blockentity.BellRenderer;
import com.cursee.more_useful_copper.impl.client.render.item.JustDireItemRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueCreeperRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSkeletonRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueSpiderRenderer;
import com.cursee.more_useful_copper.impl.client.render.statue.CopperStatueZombieRenderer;
import com.cursee.more_useful_copper.impl.common.item.MoistureCompassItem;
import com.cursee.more_useful_copper.impl.common.registry.ModBlockEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModEntities;
import com.cursee.more_useful_copper.impl.common.registry.ModItems;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class MoreUsefulCopperClientForge {

  public MoreUsefulCopperClientForge() {

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::clientSetup);

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::registerEntityModels);

    MoreUsefulCopperForge.EVENT_BUS.addListener(this::registerEntityRenderers);
  }

  private void clientSetup(FMLClientSetupEvent event) {

    BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COPPER_STATUE_SPIDER, (stack, mode, matrices, vertexConsumers, light, overlay) -> JustDireItemRenderer.INSTANCE.renderByItem(stack, mode, matrices, vertexConsumers, light, overlay));

    event.enqueueWork(() -> {
      ItemProperties.register(ModItems.MOISTURE_COMPASS, new ResourceLocation("angle"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
        return !MoistureCompassItem.isMoistureCompass(itemStack) ? CompassItem.getSpawnPosition(clientLevel) : MoistureCompassItem.getMoisturePosition(itemStack.getOrCreateTag());
      }));
    });
  }

//  private void attachCapabilities(IClientItemExtensions event) {
//
//    event.addCapability();
//  }

  private void registerEntityModels(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(CopperStatueSpiderModel.LAYER_LOCATION, CopperStatueSpiderModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueCreeperModel.LAYER_LOCATION, CopperStatueCreeperModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueSkeletonModel.LAYER_LOCATION, CopperStatueSkeletonModel::createBodyLayer);
    event.registerLayerDefinition(CopperStatueZombieModel.LAYER_LOCATION, CopperStatueZombieModel::createBodyLayer);
  }

  private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    event.registerBlockEntityRenderer(ModBlockEntities.COPPER_BELL, BellRenderer::new);

    event.registerEntityRenderer(ModEntities.COPPER_STATUE_SPIDER, CopperStatueSpiderRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_CREEPER, CopperStatueCreeperRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_SKELETON, CopperStatueSkeletonRenderer::new);
    event.registerEntityRenderer(ModEntities.COPPER_STATUE_ZOMBIE, CopperStatueZombieRenderer::new);
  }

//  private void registerItemColorHandlers(ItemEvent event) {
//    event.addModifier()
//  }
}
