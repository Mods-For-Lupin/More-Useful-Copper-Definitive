package com.cursee.more_useful_copper.impl.client.render.statue;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.client.model.statue.CopperStatueCreeperModel;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.decoration.ArmorStand;

public class CopperStatueCreeperRenderer extends LivingEntityRenderer<CopperStatueCreeper, CopperStatueCreeperModel> {

  // private final CopperStatueCreeperModel model;

  public CopperStatueCreeperRenderer(Context context) {
    super(context, new CopperStatueCreeperModel(context.bakeLayer(CopperStatueCreeperModel.LAYER_LOCATION)), 0.0f);
    // this.model = new CopperStatueCreeperModel(context.bakeLayer(CopperStatueCreeperModel.LAYER_LOCATION));
  }

  @Override
  protected boolean shouldShowName(CopperStatueCreeper entity) {
    return false;
  }

  @Override
  public void render(CopperStatueCreeper entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    // this.model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(this.getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
  }

  @Override
  protected void setupRotations(CopperStatueCreeper entityLiving, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
    poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
    float f = (float)(entityLiving.level().getGameTime() - entityLiving.lastHit) + partialTicks;
    if (f < 5.0F) {
      poseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(f / 1.5F * (float)Math.PI) * 3.0F));
    }

  }

  @Override
  public ResourceLocation getTextureLocation(CopperStatueCreeper statue) {
    return MoreUsefulCopper.identifier("textures/entity/copper_statue/" + statue.getVariantId().getPath() + ".png");
  }
}
