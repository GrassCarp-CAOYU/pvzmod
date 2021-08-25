package com.hungteen.pvz.common.impl.zombie;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.zombie.other.RaZombieModel;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;

public final class OtherZombies extends ZombieType {

	private static final List<ZombieType> LIST = new ArrayList<>();
	
	/*
	 * egypt.
	 */
	public static final ZombieType RA_ZOMBIE = new OtherZombies("ra_zombie", new ZombieFeatures()
		    .rank(Ranks.GREEN).xp(10)
			.entityType(() -> EntityRegister.RA_ZOMBIE.get())
			.zombieModel(() -> RaZombieModel::new).scale(0.5F)
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private OtherZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 70;
	}

	@Override
	public String getCategoryName() {
		return "other";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
