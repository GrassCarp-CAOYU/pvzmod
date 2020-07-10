package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.client.gui.render.ResourcesRenderer;
import com.hungteen.pvzmod.util.enums.Difficulty;

import net.minecraftforge.common.config.Config;

public class ConfigurationUtil {

	@Config(modid = "pvz", type = Config.Type.INSTANCE, name = "pvz/main_config")
	@Config.LangKey("gui.pvzconfig.title")
	public static class MainConfig {
		@Config.Comment("Configure the global settings")
		@Config.LangKey("gui.pvzconfig.globalSettings")
		public static final SubCategoryGlobalSettings globalSettings= new SubCategoryGlobalSettings();//ȫ������
		
		@Config.Comment("Configure damage settings")
		@Config.LangKey("gui.pvzconfig.damageSettings")
		public static final SubCategoryDamageSettings damageSettings =new SubCategoryDamageSettings();//�˺�����
		
		@Config.Comment("Configure random events")
		@Config.LangKey("gui.pvzconfig.eventSettings")
		public static final SubCategoryEventSettings eventSettings = new SubCategoryEventSettings();//�����¼�����
		
		@Config.Comment("Configure display")
		@Config.LangKey("gui.pvzconfig.displaySettings")
		public static final SubCategoryDisplaySettings displaySettings = new SubCategoryDisplaySettings();//�����¼�����
		
		public static class SubCategoryGlobalSettings{
			@Config.Comment("different with default difficulty")
			@Config.LangKey("gui.pvzconfig.difficulty")
			public Difficulty pvzDifficulty = Difficulty.NORMAL;//ֲ���ܷ��˺�ֲ��
		}
		
		public static class SubCategoryDamageSettings{
			
//			@Config.Comment("Can one hurt its friends by mistake.(eg:your PeaShooter's pea may hurt you by mistake")
//			@Config.LangKey("gui.pvzconfig.canHurtFriendByMistake")
//			public boolean canHurtFriendByAccident = false;//�Ƿ�������Ѿ�
			
			@Config.Comment("Plants can hurt the other teams when it's true)")
			@Config.LangKey("gui.pvzconfig.canPlantHurtTeams")
			public boolean canPlantHurtOtherTeams = false;//ֲ���ܷ��˺���������
			
			@Config.Comment("Can bullet pass through the entities that it can't hurt")
			@Config.LangKey("gui.pvzconfig.canBulletPassEntity")
			public boolean canBulletPassEntity = true;//�ӵ��ܷ񴩹������˺���ʵ��
		}
		
		public static class SubCategoryEventSettings{
			
			@Config.Comment("Chance for the Plant-Zombie-Event to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.plantZombieEventChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int plantZombieEventChance = 15;

			@Config.Comment("Chance for the Mini-Zombie-Event to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.miniZombieEventChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int miniZombieEventChance = 15;
			
			@Config.Comment("Chance for the Invis-Zombie-Event to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.invisZombieEventChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int invisZombieEventChance = 15;		
		}
		
		public static class SubCategoryDisplaySettings{
			@Config.Comment("Show sun amount bar")
			@Config.LangKey("gui.pvzconfig.showSun")
			public boolean showSunNum = true;//
			
			@Config.Comment("Show energy amount bar")
			@Config.LangKey("gui.pvzconfig.showEnergy")
			public boolean showEnergyNum = true;//
			
			@Config.Comment("Show money amount bar")
			@Config.LangKey("gui.pvzconfig.showMoney")
			public boolean showMoneyNum = true;//
		}
	}
	
	public static int getPVZDifficulty()
	{
		return MainConfig.globalSettings.pvzDifficulty.ordinal();
	}
}
