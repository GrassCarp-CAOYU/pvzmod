package com.hungteen.pvzmod.model.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.night.EntityBackupDancer;
import com.hungteen.pvzmod.entities.zombies.night.EntityDancingZombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBackupDancer extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer left_hand;
	private final ModelRenderer left_hand2;

	public ModelBackupDancer() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(5.0F, -30.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 218, 239, -4.0F, 24.0F, -6.0F, 8, 6, 11, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 226, -3.0F, 0.0F, -2.0F, 6, 24, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-5.0F, -30.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 36, 239, -4.0F, 24.0F, -6.0F, 8, 6, 11, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 96, 226, -3.0F, 0.0F, -2.0F, 6, 24, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -30.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 141, 224, -8.0F, -24.0F, -3.0F, 16, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -54.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -7.0F, -14.0F, -6.0F, 14, 14, 14, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(-9.0F, -52.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 6, 179, -5.0F, -2.0F, -2.0F, 6, 24, 6, 0.0F, false));

		left_hand2 = new ModelRenderer(this);
		left_hand2.setRotationPoint(8.0F, -51.0F, 1.0F);
		total.addChild(left_hand2);
		left_hand2.cubeList.add(new ModelBox(left_hand2, 60, 188, 0.0F, -3.0F, -3.0F, 6, 24, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		total.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		if(!(entityIn instanceof EntityBackupDancer)) return ;
		EntityBackupDancer dancer =(EntityBackupDancer) entityIn;
		if(dancer.getDanceTime()>0) {
			this.total.rotateAngleY=-MathHelper.sin(3.14159f*dancer.getDanceTime()/50);
			this.left_hand.rotateAngleX=-3*MathHelper.abs(MathHelper.sin(3.14159f*dancer.getDanceTime()/25));
			this.left_hand2.rotateAngleX=-3*MathHelper.abs(MathHelper.sin(3.14159f*dancer.getDanceTime()/25));
			return;
		}
		if(dancer.getWalkTime()>0) {
			this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
	        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
	        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing* 0.6662F) * 1.4F *limbSwingAmount;
	        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing* 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.left_hand2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
	}
}