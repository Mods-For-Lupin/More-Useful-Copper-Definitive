package com.cursee.more_useful_copper.impl.client.render.statue;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueZombieModel;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueZombie;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CopperStatueZombieRenderer extends LivingEntityRenderer<CopperStatueZombie, CopperStatueZombieModel> {

  // private final CopperStatueZombieModel<CopperStatueZombie> model;

  public CopperStatueZombieRenderer(Context context) {
    super(context, new CopperStatueZombieModel(context.bakeLayer(CopperStatueZombieModel.LAYER_LOCATION)), 0.0f);
    // this.model = new CopperStatueZombieModel<>(context.bakeLayer(CopperStatueZombieModel.LAYER_LOCATION));
  }

  @Override
  protected boolean shouldShowName(CopperStatueZombie entity) {
    return false;
  }

  @Override
  public void render(CopperStatueZombie entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    // this.model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
  }

  @Override
  public ResourceLocation getTextureLocation(CopperStatueZombie statue) {
    return MoreUsefulCopper.identifier("textures/entity/copper_statue/" + statue.getVariantId().getPath() + ".png");
  }
}
