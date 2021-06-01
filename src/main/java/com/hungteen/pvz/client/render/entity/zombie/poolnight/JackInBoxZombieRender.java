package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.JackInBoxZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.JackInBoxZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackInBoxZombieRender extends OldPVZZombieRender<JackInBoxZombieEntity>{

	public JackInBoxZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JackInBoxZombieModel(), 0.45f);
	}

	@Override
	protected float getScaleByEntity(JackInBoxZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(JackInBoxZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/jack_in_box_zombie.png");
	}

}
