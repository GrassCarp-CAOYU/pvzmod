package com.hungteen.pvzmod.util;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.defence.EntityPumpkin;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.roof.EntityImp;
import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.entities.zombies.special.EntityElementBall;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUtil {
	
	public static double getAttackRange(Entity a,Entity b,double r)
	{
		return (a.width/2+b.width+r)*(a.width/2+b.width+r);
	}
	
	public static boolean isOnGround(Entity entity)
	{
		BlockPos pos=new BlockPos(entity);
		pos=pos.add(0, -1, 0);
		if(entity.world.getBlockState(pos).getMaterial()!=Material.AIR&&(entity.posY-pos.getY())<=1.1) {
			return true;
		}
		return false;
	}
	
	public static List<EntityLivingBase> getEntityAttackableTarget(EntityLivingBase attacker,AxisAlignedBB aabb)
	{
		World world = attacker.world;
		List<EntityLivingBase> list=new ArrayList<>();
		for(EntityLivingBase entity:world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
			if(checkCanEntityAttack(attacker, entity)) {
				list.add(entity);
			}
		}
		return list;
	}
	
	public static boolean checkCanEntityAttack(Entity attacker,Entity target)
	{
		if(attacker==null||target==null) {
			return false;
		}
		World world=attacker.world;
		//�����о�һЩ������ζ����ܽ������ж�
		if(target instanceof EntityPlayer) {//������ҵ�Ȼ���ܹ���
			if(((EntityPlayer) target).isCreative()||((EntityPlayer) target).isSpectator()) {
				return false;
			}
		}
		if(target instanceof EntityZombieBase) {//��ʬ���߲��ܹ���
			if(target instanceof EntityDuckyTube) return false;
			if(target instanceof EntityDolphin) return false;
			if(target instanceof EntityImp) {
				if(target.isRiding()) return false;
			}
		}
		boolean flag = ConfigurationUtil.MainConfig.damageSettings.canPlantHurtOtherTeams;
		if(flag) {
			Team team1=getEntityTeam(world, attacker);
			Team team2=getEntityTeam(world, target);
			if(team1!=null&&team2!=null) {//���ж�������ж�
				if(getIsEntityCharmed(attacker)) return team1.isSameTeam(team2);//�Ȼ�Ļ������ö�����ʵ��
				return !team1.isSameTeam(team2);//��ͬ�����򲻹���
			}
		}
		
		//1Ϊֲ����Ӫ 0Ϊ���� -1Ϊ��ʬ��Ӫ
		int attackerGroup=getEntityGroup(attacker);
		int targetGroup=getEntityGroup(target);
		if(attackerGroup*targetGroup<0) {//��Ӫ�ж��� Ӧ�ÿɹ��� ���ź������������Ԥ��
			if(target instanceof EntityElementBall) {
				if(((EntityElementBall) target).getBallState()==EntityElementBall.State.FLAME&&attacker instanceof EntityIceShroom) {
					return true;
				}
				if(((EntityElementBall) target).getBallState()==EntityElementBall.State.ICE&&attacker instanceof EntityJalapeno) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * ��ȡʵ�����Ӫ
	 */
	public static int getEntityGroup(Entity entity)
	{
		int entityGroup=0;
		//2Ϊ�����Ӫ 1Ϊֲ����Ӫ 0Ϊ���� -1Ϊ��ʬ��Ӫ
		if(entity instanceof EntityPlayer) {
			entityGroup=2;
		}
		else if(entity instanceof EntityPlantBase) {
			entityGroup=((EntityPlantBase) entity).getIsCharmed()?-1:1;
		}else if(entity instanceof EntityMob) {
			entityGroup=-1;
			if(entity instanceof EntityZombieBase) {
				if(((EntityZombieBase) entity).getIsCharmed()) { 
					entityGroup=1;
				}
			}
		}else if(entity instanceof EntityNormalDefence) {
			entityGroup=-1;
			EntityZombieBase zombie = ((EntityNormalDefence) entity).getOwner();
			if(zombie instanceof EntityZombieBase) {
				if(zombie.getIsCharmed()) { 
					entityGroup=1;
				}
			}
		}
		return entityGroup;
	}
	
	/**
	 * �ж�ʵ���Ƿ��Ȼ�
	 */
	public static boolean getIsEntityCharmed(Entity entity)
	{
		if(entity instanceof EntityPlantBase) {
			return ((EntityPlantBase) entity).getIsCharmed();
		}
		if(entity instanceof EntityZombieBase) {
			return ((EntityZombieBase) entity).getIsCharmed();
		}
		return false;
	}
	
	public static Team getEntityTeam(World world,Entity entity)
	{
		if(entity instanceof EntityPlayer) {
			return ((EntityPlayer)entity).getTeam();
		}
	    else if(entity instanceof EntityPlantBase) {
			String name=((EntityPlantBase) entity).getOwnerName();
			if(name!="") {
				EntityPlayer player = world.getPlayerEntityByName(name);
				if(player==null) return null;
				return player.getTeam();
			}
		}
		else if(entity instanceof EntityZombieBase) {
			String name=((EntityZombieBase) entity).getOwnerName();
			if(name!="") {
				EntityPlayer player = world.getPlayerEntityByName(name);
				if(player==null) return null;
				return player.getTeam();
			}
		}
		return null;
	}
	
	public static boolean checkShouldApplyCollision(Entity base,Entity target)
	{
		if(base instanceof EntityPlantBase) {//������ֲ��
			if(target instanceof EntityPlayer) {//���������ײ
				return false;
			}
			if(target instanceof EntityPumpkin) {//ֲ�ﲻ���Ϲ�ͷ��ײ�����Ϲ�ͷ���Ϲ�ͷ��ײ
				if(!(base instanceof EntityPumpkin)) return false;
				return true;
			}
			if(base instanceof EntityPumpkin) {//�Ϲ�ͷ����ֲ����ײ�����Ϲ�ͷ���Ϲ�ͷ��ײ
				if(!(target instanceof EntityPumpkin)) return false;
				return true;
			}
			if(base instanceof EntitySquash) {
				if(EntityUtil.checkCanEntityAttack(base,target)) {
					if(((EntityPlantBase) base).getAttackTime()==0) {
						return true;
					}
				}
				if(target instanceof EntitySquash) return true; //�Լ���ѹ�Լ�
				return false;
			}
		}
		else if(base instanceof EntityZombieBase) {//�����ǽ�ʬ
			if(target instanceof EntitySpikeRock) return false;//����ײ�ش� �ش���
			if(target instanceof EntitySquash) return false;//�����ѹ� ���ᱻ�ѹ���
			if(target instanceof EntityZombieBase) return false;//������ʬ
		}
		return true;
	}
	
	public static double getDistanceSq(Entity owner,Entity target) {
		if(target==null) return 1000d;
		double d0 = owner.posX - target.posX;
        double d1 = owner.posY - target.posY;
        double d2 = owner.posZ - target.posZ;
        return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	}
}