package com.cursee.more_useful_copper.impl.client.render.blockentity;

import com.cursee.more_useful_copper.MoreUsefulCopper;
import com.cursee.more_useful_copper.impl.common.block.entity.BellBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BellRenderer implements BlockEntityRenderer<BellBlockEntity> {

  public static final Material BELL_RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, MoreUsefulCopper.identifier("entity/bell/bell_body"));
  private static final String BELL_BODY = "bell_body";

  private final ModelPart bellBody;

  public BellRenderer(BlockEntityRendererProvider.Context context) {
    ModelPart modelpart = context.bakeLayer(ModelLayers.BELL);
    this.bellBody = modelpart.getChild(BELL_BODY);
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();
    PartDefinition partdefinition1 = partdefinition.addOrReplaceChild(BELL_BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 7.0F, 6.0F),
        PartPose.offset(8.0F, 12.0F, 8.0F));
    partdefinition1.addOrReplaceChild("bell_base", CubeListBuilder.create().texOffs(0, 13).addBox(4.0F, 4.0F, 4.0F, 8.0F, 2.0F, 8.0F), PartPose.offset(-8.0F, -12.0F, -8.0F));
    return LayerDefinition.create(meshdefinition, 32, 32);
  }

  public void render(BellBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
    float f = (float) blockEntity.ticks + partialTick;
    float f1 = 0.0F;
    float f2 = 0.0F;
    if (blockEntity.shaking) {
      float f3 = Mth.sin(f / (float) Math.PI) / (4.0F + f / 3.0F);
      if (blockEntity.clickDirection == Direction.NORTH) {
        f1 = -f3;
      } else if (blockEntity.clickDirection == Direction.SOUTH) {
        f1 = f3;
      } else if (blockEntity.clickDirection == Direction.EAST) {
        f2 = -f3;
      } else if (blockEntity.clickDirection == Direction.WEST) {
        f2 = f3;
      }
    }

    this.bellBody.xRot = f1;
    this.bellBody.zRot = f2;
    // VertexConsumer vertexconsumer = BELL_RESOURCE_LOCATION.buffer(buffer, RenderType::entitySolid);
    VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(MoreUsefulCopper.identifier("textures/entity/bell/bell_body.png")));
    this.bellBody.render(poseStack, consumer, packedLight, packedOverlay);
  }
}

