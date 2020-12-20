package com.hungteen.pvz.utils.enums;

public enum Plants {
	//grass day
	PEA_SHOOTER(0),
	SUN_FLOWER(0),
	CHERRY_BOMB(0),
	WALL_NUT(0),
	POTATO_MINE(0),
	SNOW_PEA(0),
	CHOMPER(0),
	REPEATER(0),
	//grass night
	PUFF_SHROOM(1),
	SUN_SHROOM(1),
	FUME_SHROOM(1),
	GRAVE_BUSTER(0),
	HYPNO_SHROOM(1),
	SCAREDY_SHROOM(1),
	ICE_SHROOM(1),
	DOOM_SHROOM(1),
	//pool day
	LILY_PAD(2),
	SQUASH(0),
	THREE_PEATER(0),
	TANGLE_KELP(4),
	JALAPENO(0),
	SPIKE_WEED(0),
	TORCH_WOOD(0),
	TALL_NUT(0),
	//pool night
	SEA_SHROOM(5),
	PLANTERN(0), 
//	CACTUS,
//	BLOVER,
	SPLIT_PEA(0),
//	STAR_FRUIT,
	PUMPKIN(16),
//	MAGNET_SHROOM,
	//roof 
//	CABBAGE_PULT,
//	FLOWER_POT, 
//	KERNEL_PULT,
	COFFEE_BEAN(0),
//	GARLIC, 
//	UMBRELLA_LEAF, 
	MARIGOLD(0), 
//	MELON_PULT,
	//upgrade
	GATLING_PEA(8),
	TWIN_SUNFLOWER(8),
//	GLOOM_SHROOM,
//	CAT_TAIL,
//	WINTER_MELON,
//	GOLD_MAGNET, 
//	SPIKE_ROCK,
//	COB_CANNON,
//	IMITATER,
	// other
	WATER_GUARD(4);

	/*
	 * 1 means ShroomPlant like PuffShroom,
	 * 2 means BlockPlant like LilyPad,
	 * 4 means WaterPlant like SeaShroom,
	 * 8 means UpgradePlant like GatlingPea,
	 * 16 means OuterPlants like Pumpkin,
	 */
	public final boolean isShroomPlant;
	public final boolean isBlockPlant;
	public final boolean isWaterPlant;
	public final boolean isUpgradePlant;
	public final boolean isOuterPlant;
	
	private Plants(int status) {
		this.isShroomPlant = (((status >> 0) & 1) == 1);
		this.isBlockPlant = (((status >> 1) & 1) == 1);
		this.isWaterPlant = (((status >> 2) & 1) == 1);
		this.isUpgradePlant = (((status >> 3) & 1) == 1);
		this.isOuterPlant = (((status >> 4) & 1) == 1);
	}
	
	public static Plants getPlantByName(String name) {
		for (Plants plant : Plants.values()) {
			if (name.equals(plant.toString().toLowerCase())) {
				return plant;
			}
		}
		return null;
	}
	
	public static boolean isEntityPlant(Plants p) {
		return ! p.isBlockPlant && ! p.isOuterPlant;
	}
	
}
