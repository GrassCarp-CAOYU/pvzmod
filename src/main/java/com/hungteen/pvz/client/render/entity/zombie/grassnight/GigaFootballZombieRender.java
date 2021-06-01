package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.model.entity.zombie.grassnight.GigaFootballZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassnight.GigaFootballZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GigaFootballZombieRender extends OldPVZZombieRender<GigaFootballZombieEntity>{

	public GigaFootballZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GigaFootballZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(GigaFootballZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.17F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(GigaFootballZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassnight/giga_football_zombie.png");
	}

}
