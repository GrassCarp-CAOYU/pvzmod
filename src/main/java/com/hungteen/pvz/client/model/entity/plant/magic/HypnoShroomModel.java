package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class HypnoShroomModel extends PVZPlantModel<HypnoShroomEntity> {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public HypnoShroomModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(63, 86).addBox(-8.0F, -25.0F, -8.0F, 16.0F, 25.0F, 16.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, -26.0F, 0.0F);
		body.addChild(hat);
		setRotationAngle(hat, -0.0873F, 0.0F, 0.0F);
		hat.texOffs(1, 1).addBox(-12.0F, -6.0F, -12.0F, 24.0F, 8.0F, 24.0F, 0.0F, false);
		hat.texOffs(1, 66).addBox(-7.0F, -20.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		hat.texOffs(1, 35).addBox(-10.0F, -14.0F, -10.0F, 20.0F, 8.0F, 20.0F, 0.0F, false);
		hat.texOffs(2, 90).addBox(-4.0F, -24.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);
		hat.texOffs(4, 107).addBox(-2.0F, -26.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(HypnoShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.hat.yRot = ageInTicks / 30;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.body;
	}

	@Override
	public EntityModel<HypnoShroomEntity> getPlantModel() {
		return this;
	}
}