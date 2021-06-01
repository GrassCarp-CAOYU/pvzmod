package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.ZomboniModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZomboniRender extends OldPVZZombieRender<ZomboniEntity>{

	public ZomboniRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZomboniModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(ZomboniEntity entity) {
		if(entity.isMiniZombie()) return 0.25F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(ZomboniEntity entity) {
		float life = entity.getHealth();
		float max = entity.getMaxHealth();
		if(life>=max*2/3) return StringUtil.prefix("textures/entity/zombie/poolday/zomboni1.png");
		if(life>=max/3) return StringUtil.prefix("textures/entity/zombie/poolday/zomboni2.png");
		return StringUtil.prefix("textures/entity/zombie/poolday/zomboni3.png");
	}

}
