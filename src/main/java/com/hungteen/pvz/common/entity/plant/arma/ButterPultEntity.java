package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.ButterEntity;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.impl.plant.CustomPlants;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class ButterPultEntity extends KernelPultEntity {

	public ButterPultEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setCurrentBullet(CornTypes.BUTTER);
	}
	
	@Override
	protected PultBulletEntity createBullet() {
		return new ButterEntity(level, this);
	}
	
	@Override
	protected void changeBullet() {
		this.setCurrentBullet(CornTypes.BUTTER);
	}

	@Override
	public float getAttackDamage() {
		return 0.1F;
	}
	
	@Override
	public int getButterDuration() {
		return 90;
	}
	
	@Override
	public IPlantType getPlantType() {
		return CustomPlants.BUTTER_PULT;
	}
	
}
