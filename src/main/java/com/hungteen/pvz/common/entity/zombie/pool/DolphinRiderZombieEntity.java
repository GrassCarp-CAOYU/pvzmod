package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.entity.zombie.base.SwimmerZombieEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class DolphinRiderZombieEntity extends SwimmerZombieEntity{

	public DolphinRiderZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_VERY_FAST;
	}

	@Override
	public float getLife() {
		return 30;
	}

	@Override
	public ZombieType getZombieType() {
		return PoolZombies.DOLPHIN_RIDER_ZOMBIE;
	}

}
