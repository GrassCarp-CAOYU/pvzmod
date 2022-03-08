package com.hungteen.pvz.common.entity.plant.toxic;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.SwimGoal;
import net.minecraft.world.level.Level;

public class SeaShroomEntity extends PuffShroomEntity {

	public SeaShroomEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}
	
	@Override
	public double getMaxShootAngle() {
		return 5;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.5f, 0.8f);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.SEA_SHROOM;
	}

}
