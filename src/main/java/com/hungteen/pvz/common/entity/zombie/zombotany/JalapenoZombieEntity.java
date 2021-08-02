package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class JalapenoZombieEntity extends AbstractZombotanyEntity {

	public JalapenoZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_FAST);
	}
	
	@Override
	public void die(DamageSource cause) {
		if(! EntityUtil.isEntityCold(this) && ! EntityUtil.isEntityFrozen(this)) {
			this.startBomb();
		}
		super.die(cause);
	}
	
	public void startBomb() {
		if(! level.isClientSide) {
			JalapenoEntity jalapeno = EntityRegister.JALAPENO.get().create(level);
			jalapeno.setImmunneToWeak(true);
			jalapeno.setCharmed(! this.isCharmed());
			jalapeno.setPlantLvl(this.getZombieLevel());
			EntityUtil.onEntitySpawn(level, jalapeno, this.blockPosition().above());
		}
	}
	
	public int getFireRange() {
		return 20;
	}

	@Override
	public float getLife() {
		return 44;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.JALAPENO_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.JALAPENO_ZOMBIE;
	}
	
}
