package com.hungteen.pvz.utils.enums;

public enum Zombies {
	// 1
	NORMAL_ZOMBIE, FLAG_ZOMBIE, CONEHEAD_ZOMBIE, POLE_ZOMBIE, BUCKETHEAD_ZOMBIE,
	// 2
	TOMB_STONE, NEWSPAPER_ZOMBIE, OLD_ZOMBIE, SUNDAY_EDITION_ZOMBIE, SCREENDOOR_ZOMBIE, FOOTBALL_ZOMBIE,
	GIGA_FOOTBALL_ZOMBIE, DANCING_ZOMBIE, BACKUP_DANCER,
	// 3
	SNORKEL_ZOMBIE, ZOMBONI, BOBSLE_TEAM, BOBSLE_ZOMBIE, ZOMBIE_DOLPHIN, DOLPHIN_RIDER, DOLPHIN_RIDER_ZOMBIE,
	LAVA_ZOMBIE,
	// 4
	JACK_IN_BOX_ZOMBIE, BALLON_ZOMBIE, DIGGER_ZOMBIE, POGO_ZOMBIE, YETI_ZOMBIE,
	// 5
	CATAPULT_ZOMBIE, GARGANTUAR, IMP, SAD_GARGANTUAR,
	// boss
	ZOMBOSS,
	// plant_zombie
	PEASHOOTER_ZOMBIE, NUTWALL_ZOMBIE, GATLINGPEA_ZOMBIE, TALLNUT_ZOMBIE, SQUASH_ZOMBIE, JALAPENO_ZOMBIE, PUMPKIN_ZOMBIE,
	//other
	TRICK_ZOMBIE;

	public static Zombies getZombieByName(String name) {
		for (Zombies zombie : Zombies.values()) {
			if (name.equals(zombie.toString().toLowerCase())) {
				return zombie;
			}
		}
		return null;
	}
}
