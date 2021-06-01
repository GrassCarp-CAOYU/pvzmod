package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.GargantuarModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GargantuarRender extends OldPVZZombieRender<GargantuarEntity> {

	public static final ResourceLocation GARGANTUAR_TEX = StringUtil.prefix("textures/entity/zombie/roof/gargantuar.png");
	
	public GargantuarRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GargantuarModel<>(), 1F);
	}

	@Override
	protected float getScaleByEntity(GargantuarEntity entity) {
		if(entity.isMiniZombie()) return 0.35F;
		return 0.75F;
	}

	@Override
	public ResourceLocation getTextureLocation(GargantuarEntity entity) {
		return GARGANTUAR_TEX;
	}

}
