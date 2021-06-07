package com.hungteen.pvz.common.capability.player;


import java.util.EnumMap;
import java.util.HashMap;

import com.hungteen.pvz.common.advancement.trigger.MoneyTrigger;
import com.hungteen.pvz.common.advancement.trigger.PlantLevelTrigger;
import com.hungteen.pvz.common.advancement.trigger.SunAmountTrigger;
import com.hungteen.pvz.common.advancement.trigger.TreeLevelTrigger;
import com.hungteen.pvz.common.container.shop.MysteryShopContainer;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.PlantLevelUpEvent;
import com.hungteen.pvz.common.event.events.PlayerLevelUpEvent.TreeLevelUpEvent;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.PlantStatsPacket;
import com.hungteen.pvz.common.network.PlayerStatsPacket;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;


public class PlayerDataManager {

	private final PlayerEntity player;
	private final PlayerStats playerStats;
	private final PlantStats plantStats;
	private final ItemCDStats itemCDStats;
	private final OtherStats otherStats;
	
	public PlayerDataManager(PlayerEntity player) {
		this.player = player;
		this.playerStats = new PlayerStats();
		this.plantStats = new PlantStats();
		this.itemCDStats = new ItemCDStats(this);
		this.otherStats = new OtherStats(this);
	}
	
	public CompoundNBT saveToNBT() {
		CompoundNBT baseTag = new CompoundNBT();
		playerStats.saveToNBT(baseTag);
		plantStats.saveToNBT(baseTag);
		itemCDStats.saveToNBT(baseTag);
		otherStats.saveToNBT(baseTag);
		return baseTag;
	}

	public void loadFromNBT(CompoundNBT baseTag) {
		playerStats.loadFromNBT(baseTag);
		plantStats.loadFromNBT(baseTag);
		itemCDStats.loadFromNBT(baseTag);
		otherStats.loadFromNBT(baseTag);
	}
//	
	public void cloneFromExistingPlayerData(PlayerDataManager data) {
		//Resources
		for(Resources res:Resources.values()) {
			this.playerStats.resources.put(res,data.playerStats.resources.get(res));
		}
		//Plants
		for (Plants plant : Plants.values()) {
			this.plantStats.plantLevel.put(plant, data.plantStats.plantLevel.get(plant));
			this.plantStats.plantXp.put(plant, data.plantStats.plantXp.get(plant));
		}
		for(Plants p : Plants.values()) {
			this.itemCDStats.setPlantCardCD(p, data.itemCDStats.getPlantCardCoolDown(p));
			this.itemCDStats.setPlantCardBar(p, data.itemCDStats.getPlantCardBarLength(p));
		}
		//other
		for(int i = 0; i < WaveManager.MAX_WAVE_NUM; ++ i) {
			this.otherStats.zombieWaveTime[i] = data.otherStats.zombieWaveTime[i];
		}
		this.otherStats.totalWaveCount = data.otherStats.totalWaveCount;
		this.otherStats.playSoundTick = data.otherStats.playSoundTick;
		for(int i = 0; i < MysteryShopContainer.MAX_MYSTERY_GOOD; ++ i) {
			this.otherStats.mysteryGoods[i] = data.otherStats.mysteryGoods[i];
		}
		this.otherStats.updateGoodTick = data.otherStats.updateGoodTick;
	}

	public final class PlayerStats{
		
		private HashMap<Resources, Integer> resources = new HashMap<>(Resources.values().length);
		
		private PlayerStats() {
			for(Resources res : Resources.values()) {
				if(res == Resources.SUN_NUM) resources.put(res, 50);
				else if(res == Resources.LOTTERY_CHANCE) resources.put(res, 10);
				else if(res == Resources.GROUP_TYPE) resources.put(res, ConfigUtil.getPlayerInitialGroup());
				else if(res == Resources.NO_FOG_TICK) resources.put(res, 0);
				else resources.put(res, res.min);
			}
		}
		
		public int getPlayerStats(Resources res){
			return resources.get(res);
		}
		
		public void setPlayerStats(Resources res, int num) {
			resources.put(res, num);
			this.addPlayerStats(res, 0);//check (min max) and sync send packet.
		}
		
		public void addPlayerStats(Resources res, int num){
			switch (res) {
			case TREE_LVL:{
				int now = MathHelper.clamp(resources.get(Resources.TREE_LVL) + num, 1, PlayerUtil.MAX_TREE_LVL);
				resources.put(Resources.TREE_LVL, now);
				this.addPlayerStats(Resources.SUN_NUM, 0);
				TreeLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
				break;
			}
			case TREE_XP:{
				addTreeXp(num);
				break;
			}
			case SUN_NUM:{
				int now = MathHelper.clamp(resources.get(Resources.SUN_NUM) + num, 0, PlayerUtil.getPlayerMaxSunNum(resources.get(Resources.TREE_LVL)));
				resources.put(Resources.SUN_NUM, now);
				SunAmountTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
				break;
			}
			case ENERGY_NUM:{
				int now = MathHelper.clamp(resources.get(Resources.ENERGY_NUM) + num, 0, resources.get(Resources.MAX_ENERGY_NUM));
				resources.put(Resources.ENERGY_NUM, now);
				break;
			}
			default:
				int now = MathHelper.clamp(resources.get(res) + num, res.min, res.max);
				resources.put(res, now);
				if(res == Resources.MONEY) {
					MoneyTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
				} 
				break;
			}
			this.sendPacket(player, res);
		}
		
		/**
		 * add tree xp and level up.
		 * {@link #addPlayerStats(Resources, int)}
		 */
		private void addTreeXp(int num) {
			int lvl = resources.get(Resources.TREE_LVL);
			int now = resources.get(Resources.TREE_XP);
			if(num > 0) {
				int req = PlayerUtil.getPlayerLevelUpXp(lvl);
				while(lvl < PlayerUtil.MAX_TREE_LVL && num + now >= req) {
					num -= req - now;
					this.addPlayerStats(Resources.TREE_LVL, 1);
					MinecraftForge.EVENT_BUS.post(new TreeLevelUpEvent(player, ++ lvl));
					now = 0;
					req = PlayerUtil.getPlayerLevelUpXp(lvl);
				}
				resources.put(Resources.TREE_XP, num + now);
			} else {
				num = - num;
				while(lvl > 1 && num > now) {
					num -= now;
					-- lvl;
					now = PlayerUtil.getPlayerLevelUpXp(lvl);
					this.addPlayerStats(Resources.TREE_LVL, - 1);
				}
				resources.put(Resources.TREE_XP, now - num);
			}
		}
		
		public void sendPacket(PlayerEntity player, Resources res){
			if (player instanceof ServerPlayerEntity) {
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new PlayerStatsPacket(res.ordinal(), resources.get(res))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(Resources res : Resources.values()) {
				statsNBT.putInt("player_" + res.toString(), resources.get(res));
			}
			baseTag.put("player_stats", statsNBT);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("player_stats")) {
				CompoundNBT statsTag = baseTag.getCompound("player_stats");
			    for(Resources res:Resources.values()) {
			    	if(statsTag.contains("player_"+res.toString())) {
				        resources.put(res, statsTag.getInt("player_"+res.toString()));
			    	}
			    }
			}
		}
		
	}
	
	public final class PlantStats {
		
		private HashMap<Plants, Integer> plantXp = new HashMap<Plants, Integer>(Plants.values().length);
		private HashMap<Plants, Integer> plantLevel = new HashMap<Plants, Integer>(Plants.values().length);
		
		private PlantStats() {
			for (Plants plant : Plants.values()) {
				plantXp.put(plant, 0);
				plantLevel.put(plant, 1);
			}
		}
		
		//get		
		public int getPlantLevel(Plants plant){
			return this.plantLevel.get(plant);
		}
		
		public int getPlantXp(Plants plant){
			return this.plantXp.get(plant);
		}
		
		//add
		public void addPlantLevel(Plants plant, int lvl){
			int now = this.getPlantLevel(plant) + lvl;
			int maxLvl = PlantUtil.getPlantMaxLvl(plant);
			now = MathHelper.clamp(now, 1, maxLvl);
			this.plantLevel.put(plant, now);
			PlantLevelTrigger.INSTANCE.trigger((ServerPlayerEntity) player, now);
			this.sendPlantPacket(player, plant);
		}
		
		public void addPlantXp(Plants plant, int num) {
			int lvl = this.getPlantLevel(plant);
			int xp = this.getPlantXp(plant) + num;
			if(num > 0) {
				int needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
				int maxLvl = PlantUtil.getPlantMaxLvl(plant);
				while(lvl < maxLvl && xp >= needXp) {
					xp -= needXp;
					this.addPlantLevel(plant, 1);
					MinecraftForge.EVENT_BUS.post(new PlantLevelUpEvent(player, plant, ++ lvl));
					needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
				}
				if(lvl == maxLvl) xp = 0;
			} else {
				while(lvl > 1 && xp < 0) {
					this.addPlantLevel(plant, - 1);
					lvl = this.getPlantLevel(plant);
					int needXp = PlantUtil.getPlantLevelUpXp(plant, lvl);
					xp += needXp;
				}
				if(lvl == 1 && xp < 0) xp = 0;
			}
			this.plantXp.put(plant, xp);
			this.sendPlantPacket(player, plant);
		}
		
		public void sendPlantPacket(PlayerEntity player, Plants plant){
			if (player instanceof ServerPlayerEntity) {
				PVZPacketHandler.CHANNEL.send(
					PacketDistributor.PLAYER.with(()->{
						return (ServerPlayerEntity) player;
					}),
					new PlantStatsPacket(plant.ordinal(), plantLevel.get(plant), plantXp.get(plant))
				);
			}
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			for (Plants plant : Plants.values()) {
				CompoundNBT plantNBT = new CompoundNBT();
				plantNBT.putInt("player_plant_lvl", this.getPlantLevel(plant));
				plantNBT.putInt("player_plant_exp", this.getPlantXp(plant));
				baseTag.put(plant.toString(), plantNBT);
			}
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			for (Plants plant : Plants.values()) {
				CompoundNBT plantTag = (CompoundNBT) baseTag.get(plant.toString());
				if(plantTag == null) continue;
				if(plantTag.contains("player_plant_lvl")) {
					this.plantLevel.put(plant, plantTag.getInt("player_plant_lvl"));
				}
				if(plantTag.contains("player_plant_exp")) {
					this.plantXp.put(plant, plantTag.getInt("player_plant_exp"));
				}
			}
		}
	}
	
	public final class ItemCDStats{
		@SuppressWarnings("unused")
		private final PlayerDataManager manager;
		private EnumMap<Plants, Integer> plantCardCD = new EnumMap<>(Plants.class);
		private EnumMap<Plants, Float> plantCardBar = new EnumMap<>(Plants.class);
		
		public ItemCDStats(PlayerDataManager manager) {
			this.manager = manager;
			for(Plants plant : Plants.values()) {
				plantCardCD.put(plant, 0);
				plantCardBar.put(plant, 0f);
			}
		}
		
		public void setPlantCardCD(Plants plant, int tick) {
			this.plantCardCD.put(plant, tick);
		}
		
		public int getPlantCardCoolDown(Plants plant) {
			return this.plantCardCD.get(plant);
		}
		
		public void setPlantCardBar(Plants plant, float bar) {
			this.plantCardBar.put(plant, bar);
		}
		
		public float getPlantCardBarLength(Plants plant) {
			return this.plantCardBar.get(plant);
		}
		
		public int getPlantCardCD(Plants plant) {
			return (int) (this.plantCardBar.get(plant) * this.plantCardCD.get(plant));
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(Plants p : Plants.values()) {
				statsNBT.putInt(p.toString().toLowerCase() + "_plant_card_cd", this.plantCardCD.get(p));
				statsNBT.putFloat(p.toString().toLowerCase() + "_plant_card_bar", this.plantCardBar.get(p));
			}
			baseTag.put("plant_card_item_cd", statsNBT);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("plant_card_item_cd")) {
			    CompoundNBT statsTag = baseTag.getCompound("plant_card_item_cd");
			    for(Plants p : Plants.values()) {
			    	if(statsTag.contains(p.toString().toLowerCase() + "_plant_card_cd")) {
				        this.setPlantCardCD(p, statsTag.getInt(p.toString().toLowerCase() + "_plant_card_cd"));
			    	}
			    	if(statsTag.contains(p.toString().toLowerCase() + "_plant_card_bar")) {
				        this.setPlantCardBar(p, statsTag.getFloat(p.toString().toLowerCase() + "_plant_card_bar"));
			    	}
			    }
			}
		}
	}
	
	public final class OtherStats{
		
		@SuppressWarnings("unused")
		private final PlayerDataManager manager;
		public int[] zombieWaveTime = new int[WaveManager.MAX_WAVE_NUM];
		public int[] mysteryGoods = new int[MysteryShopContainer.MAX_MYSTERY_GOOD];
		public int totalWaveCount;
		public int playSoundTick;
		public int updateGoodTick;
		public int lightLevel;
		
		public OtherStats(PlayerDataManager manager) {
			this.manager = manager;
			for(int i = 0; i < zombieWaveTime.length; ++ i) {
				zombieWaveTime[i] = 0;
			}
			this.totalWaveCount = 0;
			this.playSoundTick = 0;
			this.lightLevel = 0;
			MysteryShopContainer.genNextGoods(player);
		}
		
		private void saveToNBT(CompoundNBT baseTag) {
			CompoundNBT statsNBT = new CompoundNBT();
			for(int i = 0; i < zombieWaveTime.length; ++ i) {
				statsNBT.putInt("zombieWaveTime_" + i, zombieWaveTime[i]);
			}
			baseTag.put("zombie_wave_time", statsNBT);
			baseTag.putInt("total_wave_cnt", this.totalWaveCount);
			baseTag.putInt("base_sound_tick", this.playSoundTick);
			CompoundNBT tmp = new CompoundNBT();
			for(int i = 0; i < mysteryGoods.length; ++ i) {
				tmp.putInt("mystery_good_" + i, mysteryGoods[i]);
			}
			baseTag.put("mystery_goods", tmp);
			baseTag.putInt("update_good_tick", this.updateGoodTick);
		}

		private void loadFromNBT(CompoundNBT baseTag) {
			if(baseTag.contains("zombie_wave_time")) {
				CompoundNBT nbt = baseTag.getCompound("zombie_wave_time");
				for(int i = 0; i < zombieWaveTime.length; ++ i) {
					zombieWaveTime[i] = nbt.getInt("zombieWaveTime_" + i);
				}
			}
			if(baseTag.contains("total_wave_cnt")) {
				this.totalWaveCount = baseTag.getInt("total_wave_cnt");
			}
			if(baseTag.contains("base_sound_tick")) {
				this.playSoundTick = baseTag.getInt("base_sound_tick");
			}
			if(baseTag.contains("mystery_goods")) {
				CompoundNBT nbt = baseTag.getCompound("mystery_goods");
				for(int i = 0; i < mysteryGoods.length; ++ i) {
					mysteryGoods[i] = nbt.getInt("mystery_good_" + i);
				}
			}
			if(baseTag.contains("update_good_tick")) {
				this.updateGoodTick = baseTag.getInt("update_good_tick");
			}
		}
	}
	
	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}
	
	public PlantStats getPlantStats(){
		return this.plantStats;
	}
	
//	public AlmanacStats getAlmanacStats() {
//		return this.almanacStats;
//	}
	
	public ItemCDStats getItemCDStats() {
		return this.itemCDStats;
	}
	
	public OtherStats getOtherStats() {
		return this.otherStats;
	}
	
}
