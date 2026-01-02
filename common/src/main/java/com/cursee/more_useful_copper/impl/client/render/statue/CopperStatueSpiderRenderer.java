package com.cursee.more_useful_copper.impl.client.render.statue;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueSpiderModel;
import com.cursee.more_useful_copper.impl.common.entity.AbstractOxidizingCopperStatue;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueCreeper;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueSpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CopperStatueSpiderRenderer extends LivingEntityRenderer<CopperStatueSpider, CopperStatueSpiderModel> {

  // private final CopperStatueSpiderModel<CopperStatueSpider> model;

  public CopperStatueSpiderRenderer(Context context) {
    super(context, new CopperStatueSpiderModel(context.bakeLayer(CopperStatueSpiderModel.LAYER_LOCATION)), 0.0f);
    // this.model = new CopperStatueSpiderModel<>(context.bakeLayer(CopperStatueSpiderModel.LAYER_LOCATION));
  }

  @Override
  protected boolean shouldShowName(CopperStatueSpider entity) {
    return false;
  }

  @Override
  public void render(CopperStatueSpider entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    // this.model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
  }

  @Override
  public ResourceLocation getTextureLocation(CopperStatueSpider statue) {

    // boolean waxed = statue.getEntityData().get(AbstractOxidizingCopperStatue.WAXED);
    int oxiLevel = statue.getEntityData().get(AbstractOxidizingCopperStatue.OXIDIZATION_LEVEL);

    final String oxiString = (oxiLevel == 0) ? "" : String.valueOf(oxiLevel);

    return MoreUsefulCopper.identifier("textures/entity/copper_statue/" + statue.getVariantId().getPath() + oxiString + ".png");
  }
}
