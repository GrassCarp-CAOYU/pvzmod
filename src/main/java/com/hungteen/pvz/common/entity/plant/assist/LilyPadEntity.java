package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class LilyPadEntity extends PVZPlantEntity{

	public LilyPadEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.LILY_PAD;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
