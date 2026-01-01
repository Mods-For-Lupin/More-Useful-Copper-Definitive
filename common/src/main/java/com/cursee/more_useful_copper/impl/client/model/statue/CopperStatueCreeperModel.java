package com.cursee.more_useful_copper.impl.client.model.statue;

import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueCreeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CopperStatueCreeperModel extends EntityModel<CopperStatueCreeper> {

  // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "creeper"), "main");

  private final ModelPart creeper_statue;
  private final ModelPart head;
  private final ModelPart legs;

  public CopperStatueCreeperModel(ModelPart root) {
    this.creeper_statue = root.getChild("creeper_statue");
    this.head = this.creeper_statue.getChild("head");
    this.legs = this.creeper_statue.getChild("legs");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition creeper_statue = partdefinition.addOrReplaceChild("creeper_statue", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -18.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

    PartDefinition head = creeper_statue.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -26.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    PartDefinition legs = creeper_statue.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(24, 16).addBox(-4.0F, -6.0F, 2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(24, 16).addBox(0.0F, -6.0F, 2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(24, 16).addBox(-4.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(24, 16).addBox(0.0F, -6.0F, -6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 64, 64);
  }

  @Override
  public void setupAnim(CopperStatueCreeper entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

//    poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
//    float f = (float)(entity.level().getGameTime() - entity.lastHit) + partialTicks;
//    if (f < 5.0F) {
//      poseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(f / 1.5F * (float)Math.PI) * 3.0F));
//    }
  }

  @Override
  public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    creeper_statue.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }
}