package net.etechservicecn.glow_in_the_dark_clover.entities.mob_entity.flame_chicken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class FlameChickenModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "flamechicken"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart wing1;
	private final ModelPart wing0;
	private final ModelPart leg1;
	private final ModelPart leg0;
	private final ModelPart head;
	private final ModelPart comb;
	private final ModelPart beak;

	public FlameChickenModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.wing1 = this.root.getChild("wing1");
		this.wing0 = this.root.getChild("wing0");
		this.leg1 = this.root.getChild("leg1");
		this.leg0 = this.root.getChild("leg0");
		this.head = this.root.getChild("head");
		this.comb = this.head.getChild("comb");
		this.beak = this.head.getChild("beak");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition wing1 = root.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -11.0F, 0.0F));

		PartDefinition wing0 = root.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -11.0F, 0.0F));

		PartDefinition leg1 = root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(14, 24).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -5.0F, 1.0F));

		PartDefinition leg0 = root.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, 1.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -5.0F));

		PartDefinition comb = head.addOrReplaceChild("comb", CubeListBuilder.create().texOffs(26, 24).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(24, 8).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);
		this.head.xRot=headPitch*((float) Mth.PI/180f);
		this.head.yRot=netHeadYaw*((float) Mth.PI/180f);
		this.animateWalk(FlameChickenAnimations.walking,limbSwing,limbSwingAmount,2.0f,2.5f);
		this.animate(((FlameChickenEntity)entity).flyingAnimationState,FlameChickenAnimations.swing_rotate,ageInTicks,1.0f);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}