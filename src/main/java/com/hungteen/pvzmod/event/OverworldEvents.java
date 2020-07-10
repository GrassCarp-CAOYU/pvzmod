package com.hungteen.pvzmod.event;

import java.util.HashSet;
import java.util.Random;

import javax.annotation.Nonnull;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityBucketHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityFlagZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.registry.EntitySpawnRegister;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.StringUtil;
import com.hungteen.pvzmod.util.enums.SpecialEvents;
import com.hungteen.pvzmod.world.data.OverworldData;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OverworldEvents{

	private static final Random rand = new Random();
	
	public static void doTickCheck(TickEvent.WorldTickEvent ev)
	{
		long totalTime = ev.world.getTotalWorldTime();
		if(totalTime<=24000l*getSafeDayLength()) return ; //���簲ȫʱ��
		int time = (int)(totalTime % 24000L);//�����ʱ��
		switch(time) {
		case 10:{//10tick����ʼ���������¼�
			OverworldData data=OverworldData.getGlobalData(ev.world);
			if(!data.hasChanged()) {//��û�ı䣬��ֹ����ʱ��ֹͣ������ͣˢ��
				data.setChanged(true);
				rand.setSeed(ev.world.getSeed() + totalTime);
				if(data.isZombossDefeated()||rand.nextInt(100)<getAttackChance(totalTime)) {//���Թ���
					data.setAttack(true);
					activateEvent(ev.world, SpecialEvents.NORMAL_ZOMBIE);
					activateEvent(ev.world, SpecialEvents.DAY_ZOMBIE);
					if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.plantZombieEventChance)==0) {
						activateEvent(ev.world, SpecialEvents.PLANT_ZOMBIE);
				    }
			        if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.miniZombieEventChance)==0) {
				        activateEvent(ev.world, SpecialEvents.MINI_ZOMBIE);
			        }
			        if (rand.nextInt(ConfigurationUtil.MainConfig.eventSettings.invisZombieEventChance)==0) {
				        activateEvent(ev.world, SpecialEvents.INVIS_ZOMBIE);
			        }
				}
			}
			break;
		}
		case 11800:{//11800 tick:������� ���콩ʬ��ʧ
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			deactivateEvent(ev.world, SpecialEvents.DAY_ZOMBIE);
			break;
		}
		case 12020:{//12020 tick:ҹ��ʼ ҹ��ʬ����
			OverworldData data=OverworldData.getGlobalData(ev.world);
			if(!data.hasChanged()) {
				data.setChanged(true);
				if(data.isAttack()) {
					activateEvent(ev.world, SpecialEvents.NIGHT_ZOMBIE);
				}
			}
		}
		case 23800:{//23800 tick:һ�������ȡ����������
			OverworldData data=OverworldData.getGlobalData(ev.world);
			data.setChanged(false);
			deactivateEvent(ev.world, SpecialEvents.NORMAL_ZOMBIE);
			deactivateEvent(ev.world, SpecialEvents.NIGHT_ZOMBIE);
			deactivateEvent(ev.world, SpecialEvents.PLANT_ZOMBIE);
			deactivateEvent(ev.world, SpecialEvents.MINI_ZOMBIE);
			deactivateEvent(ev.world, SpecialEvents.INVIS_ZOMBIE);
			break;
		}
		}
	}
	
	public static void activateEvent(World world, @Nonnull SpecialEvents event) {
		OverworldData data=OverworldData.getGlobalData(world);
		if (!data.hasEvent(event)) {
			data.add(event);
			switch(event) {
			case NORMAL_ZOMBIE:
			case DAY_ZOMBIE:
			case NIGHT_ZOMBIE:
			case PLANT_ZOMBIE:{
				EntitySpawnRegister.addEventSpawns(event);
				break;
			}
			default:break;
			}
			
			ITextComponent message = getEventMessage(event, false);
			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);
				world.playSound(null, pl.posX, pl.posY, pl.posZ, SoundsHandler.HUGE_WAVE, SoundCategory.AMBIENT, 1.0f, 1.0f);
			}
		}
	}
	
	public static void deactivateEvent(World world,@Nonnull SpecialEvents event)
	{
		OverworldData data=OverworldData.getGlobalData(world);
		if(data.hasEvent(event)) {
			data.remove(event);
			switch(event) {
			case NORMAL_ZOMBIE:
			case DAY_ZOMBIE:
			case NIGHT_ZOMBIE:
			case PLANT_ZOMBIE:{
				EntitySpawnRegister.removeEventSpawns(event);
				break;
			}
			default:break;
			}
			ITextComponent message = getEventMessage(event, true);
			for (EntityPlayer pl : world.playerEntities) {
				pl.sendMessage(message);
			}
		}
	}
	
	private static ITextComponent getEventMessage(SpecialEvents event, boolean isEnding) {
		switch (event) {
			case PLANT_ZOMBIE:
				return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".plantZombieDay", TextFormatting.DARK_GREEN);
			case MINI_ZOMBIE:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".smallZombieDay", TextFormatting.DARK_GRAY);
			case INVIS_ZOMBIE:
			    return StringUtil.getColourLocale("message.event." + (isEnding ? "end" : "start") + ".invisZombieDay", TextFormatting.DARK_BLUE);
			default:
				return null;
		}
	}

	private static void hugeWave(World world,int type)
	{
		for(EntityPlayer player:world.playerEntities) {
			player.sendMessage(StringUtil.getColourLocale("message.huge_wave", TextFormatting.DARK_RED));
			double x=player.posX;
			double y=player.posY;
			double z=player.posZ;
			world.playSound(null, x, y, z, SoundsHandler.HUGE_WAVE, SoundCategory.AMBIENT, 4f, 1f);
			if(!player.isCreative()&&!player.isSpectator()) {//һ�󲨽�ʬ�ɽ��������
				BlockPos block = getBlock(world, x, y, z);
				summonZombie(world, block, 10, 1);
				summonZombie(world, block, 3, 2);
				summonZombie(world, block, 1, 3);
				summonZombie(world, block, 1, 4);
			}
		}
	}
	
	private static void summonZombie(World world,BlockPos block,int num,int type)
	{
		for(int i=1;i<=num;i++) {
			EntityNormalZombie zombie=null;
	        if(type==1) zombie=new EntityNormalZombie(world);
	        else if(type==2) zombie=new EntityConeHeadZombie(world);
	        else if(type==3) zombie=new EntityBucketHeadZombie(world);
	        else if(type==4) zombie=new EntityFlagZombie(world);
	        if(zombie!=null) {
	        	zombie.setPosition(block.getX(), block.getY(), block.getZ());
			    world.spawnEntity(zombie);
	        }
		}
	}
	
	private static BlockPos getBlock(World world,double x,double y,double z)
	{
		Random rand=new Random();
		while(true) {
			int dx=(rand.nextInt(20)+0)*((rand.nextInt(1)==1)?1:-1);//40 - 80
			int dz=(rand.nextInt(20)+0)*((rand.nextInt(1)==1)?1:-1);
			int nowX=MathHelper.floor(x)+dx;
			int nowZ=MathHelper.floor(z)+dz;
			int nowY=world.getHeight();
			
			if(Math.abs(dx)+Math.abs(dz)<=80) {
				while(nowY-->=0) {
				    Block block = world.getBlockState(new BlockPos(nowX,nowY,nowZ)).getBlock();
				    if(block!=Blocks.AIR) break;
			    }
				return new BlockPos(nowX,nowY+1,nowZ);
			}
		}
	}
	
	/**
	 * ��ȡ��������
	 */
	public static int getAttackChance(long time)
	{
		int dif=ConfigurationUtil.getPVZDifficulty();
		int chance=10+dif*10;
		return chance;
	}
	
	public static int getSafeDayLength()
	{
		int dif=ConfigurationUtil.getPVZDifficulty();
		return 3-dif;
	}
}
