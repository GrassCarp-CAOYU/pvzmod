package com.hungteen.pvz.client.model.entity.zombie;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.client.model.entity.zombie.grassday.BucketHeadZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.FlagZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.NormalZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassday.PoleZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.BackupDancerModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.DancingZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.FootballZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.GigaFootballZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.NewspaperZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.OldZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.ScreenDoorZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.grassnight.SundayEditionZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.other.MournerZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.other.NobleZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.other.RaZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.other.TrickZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolday.BobsleZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolday.DolphinRiderZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolday.LavaZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolday.SnorkelZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolday.ZomboniModel;
import com.hungteen.pvz.client.model.entity.zombie.poolnight.BalloonZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolnight.DiggerZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolnight.JackInBoxZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolnight.PogoZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.poolnight.YetiZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.CatapultZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.GargantuarModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.ImpModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.LadderZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.ZomBossModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.GatlingPeaZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.JalapenoZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.PeaShooterZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.PumpkinZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.SquashZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.TallNutZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.WallNutZombieModel;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieModelHandler {

	public static final Map<Zombies, IZombieModel<? extends PVZZombieEntity>> ZOMBIE_PART1_MODEL = new EnumMap<>(Zombies.class);
	public static final Map<Zombies, IZombieModel<? extends PVZZombieEntity>> ZOMBIE_PART2_MODEL = new EnumMap<>(Zombies.class);
	
	static {
		//grass day
		putModel(Zombies.NORMAL_ZOMBIE, NormalZombieModel::new);
		putModel(Zombies.FLAG_ZOMBIE, FlagZombieModel::new);
		putModel(Zombies.CONEHEAD_ZOMBIE, ConeHeadZombieModel::new);
		putModel(Zombies.POLE_ZOMBIE, PoleZombieModel::new);
		putModel(Zombies.BUCKETHEAD_ZOMBIE, BucketHeadZombieModel::new);
		//grass night(no tomb stone)
		putModel(Zombies.NEWSPAPER_ZOMBIE, NewspaperZombieModel::new);
		putModel(Zombies.SCREENDOOR_ZOMBIE, ScreenDoorZombieModel::new);
		putModel(Zombies.FOOTBALL_ZOMBIE, FootballZombieModel::new);
		putModel(Zombies.DANCING_ZOMBIE, DancingZombieModel::new);
		putModel(Zombies.BACKUP_DANCER, BackupDancerModel::new);
		putModel(Zombies.GIGA_FOOTBALL_ZOMBIE, GigaFootballZombieModel::new);
		putModel(Zombies.OLD_ZOMBIE, OldZombieModel::new);
		putModel(Zombies.SUNDAY_EDITION_ZOMBIE, SundayEditionZombieModel::new);
		//pool day(no bobsle team, dolphin rider)
		putModel(Zombies.SNORKEL_ZOMBIE, SnorkelZombieModel::new);
		putModel(Zombies.ZOMBONI, ZomboniModel::new);
		putModel(Zombies.BOBSLE_ZOMBIE, BobsleZombieModel::new);
		putModel(Zombies.DOLPHIN_RIDER_ZOMBIE, DolphinRiderZombieModel::new);
		putModel(Zombies.LAVA_ZOMBIE, LavaZombieModel::new);
		//pool night
		putModel(Zombies.JACK_IN_BOX_ZOMBIE, JackInBoxZombieModel::new);
		putModel(Zombies.BALLOON_ZOMBIE, BalloonZombieModel::new);
		putModel(Zombies.DIGGER_ZOMBIE, DiggerZombieModel::new);
		putModel(Zombies.POGO_ZOMBIE, PogoZombieModel::new);
		putModel(Zombies.YETI_ZOMBIE, YetiZombieModel::new);
		//roof(no bungee)
		putModel(Zombies.LADDER_ZOMBIE, LadderZombieModel::new);
		putModel(Zombies.CATAPULT_ZOMBIE, CatapultZombieModel::new);
		putModel(Zombies.GARGANTUAR, GargantuarModel::new);
		putModel(Zombies.SAD_GARGANTUAR, GargantuarModel::new);
		putModel(Zombies.IMP, ImpModel::new);
		putModel(Zombies.ZOMBOSS, ZomBossModel::new);
		//zombotany
		putModel(Zombies.PEASHOOTER_ZOMBIE, PeaShooterZombieModel::new);
		putModel(Zombies.WALLNUT_ZOMBIE, WallNutZombieModel::new);
		putModel(Zombies.GATLINGPEA_ZOMBIE, GatlingPeaZombieModel::new);
		putModel(Zombies.TALLNUT_ZOMBIE, TallNutZombieModel::new);
		putModel(Zombies.JALAPENO_ZOMBIE, JalapenoZombieModel::new);
		putModel(Zombies.SQUASH_ZOMBIE, SquashZombieModel::new);
		putModel(Zombies.PUMPKIN_ZOMBIE, PumpkinZombieModel::new);
		//other
		putModel(Zombies.MOURNER_ZOMBIE, MournerZombieModel::new);
		putModel(Zombies.NOBLE_ZOMBIE, NobleZombieModel::new);
		putModel(Zombies.TRICK_ZOMBIE, TrickZombieModel::new);
		putModel(Zombies.RA_ZOMBIE, RaZombieModel::new);
	}
	
	public static Optional<IZombieModel<? extends PVZZombieEntity>> getPart1Model(Zombies zombie) {
		return Optional.ofNullable(ZOMBIE_PART1_MODEL.get(zombie));
	}
	
	public static Optional<IZombieModel<? extends PVZZombieEntity>> getPart2Model(Zombies zombie) {
		return Optional.ofNullable(ZOMBIE_PART2_MODEL.get(zombie));
	}
	
	private static void putModel(Zombies zombie, Supplier<? extends IZombieModel<? extends PVZZombieEntity>> sup) {
		ZOMBIE_PART1_MODEL.put(zombie, sup.get());
		ZOMBIE_PART2_MODEL.put(zombie, sup.get());
	}
}
