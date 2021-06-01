package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.ImpModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.ImpEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpRender extends OldPVZZombieRender<ImpEntity> {

	private static final ResourceLocation IMP_TEX = StringUtil.prefix("textures/entity/zombie/roof/imp.png");
	
	public ImpRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ImpModel(), 0.3F);
	}

	@Override
	protected float getScaleByEntity(ImpEntity entity) {
		if(entity.isMiniZombie()) return 0.2F;
		return 0.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(ImpEntity entity) {
		return IMP_TEX;
	}

}
