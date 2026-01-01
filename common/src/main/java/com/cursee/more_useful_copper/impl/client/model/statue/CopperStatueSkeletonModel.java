package com.cursee.more_useful_copper.impl.client.model.statue;

import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.impl.common.entity.CopperStatueSkeleton;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraft.world.entity.Entity;

public class CopperStatueSkeletonModel extends EntityModel<CopperStatueSkeleton> {

  // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "skeleton"), "main");

  private final ModelPart skeleton_statue;
  private final ModelPart legs;
  private final ModelPart RightLeg;
  private final ModelPart LeftLeg;
  private final ModelPart arms;
  private final ModelPart LeftArm;
  private final ModelPart leftItem;
  private final ModelPart RightArm;
  private final ModelPart Head;
  private final ModelPart Body;

  public CopperStatueSkeletonModel(ModelPart root) {
    this.skeleton_statue = root.getChild("skeleton_statue");
    this.legs = this.skeleton_statue.getChild("legs");
    this.RightLeg = this.legs.getChild("RightLeg");
    this.LeftLeg = this.legs.getChild("LeftLeg");
    this.arms = this.skeleton_statue.getChild("arms");
    this.LeftArm = this.arms.getChild("LeftArm");
    this.leftItem = this.LeftArm.getChild("leftItem");
    this.RightArm = this.arms.getChild("RightArm");
    this.Head = this.skeleton_statue.getChild("Head");
    this.Body = this.skeleton_statue.getChild("Body");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition skeleton_statue = partdefinition.addOrReplaceChild("skeleton_statue", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

    PartDefinition legs = skeleton_statue.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

    PartDefinition RightLeg = legs.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(24, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    PartDefinition LeftLeg = legs.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(24, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 0.0F));

    PartDefinition arms = skeleton_statue.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(7.0F, -10.0F, 0.0F));

    PartDefinition LeftArm = arms.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    PartDefinition leftItem = LeftArm.addOrReplaceChild("leftItem", CubeListBuilder.create(), PartPose.offset(1.0F, 7.0F, 1.0F));

    PartDefinition RightArm = arms.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 0.0F, 0.0F));

    PartDefinition Head = skeleton_statue.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

    PartDefinition Body = skeleton_statue.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 64, 64);
  }

  @Override
  public void setupAnim(CopperStatueSkeleton entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

  }

  @Override
  public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    skeleton_statue.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }
}