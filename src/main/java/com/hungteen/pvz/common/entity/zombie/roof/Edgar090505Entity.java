package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.misc.drop.JewelEntity;
import com.hungteen.pvz.common.entity.zombie.base.EdgarRobotEntity;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class Edgar090505Entity extends EdgarRobotEntity {

    private static final DataParameter<Mth> ORIGIN_POS = EntityDataManager.defineId(Edgar090505Entity.class, DataSerializers.BLOCK_POS);

    public Edgar090505Entity(EntityType<? extends CreatureEntity> type, Level worldIn) {
        super(type, worldIn);
        this.refreshCountCD = 10;
        this.maxZombieSurround = 60;
        this.maxPlantSurround = 50;
        this.kickRange = 6;
        this.setIsWholeBody();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ORIGIN_POS, Mth.ZERO);
    }

    @Override
    public void kill() {
        super.kill();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void zombieTick() {
        super.zombieTick();
        if (!level.isClientSide) {
            if (this.getOriginPos() == Mth.ZERO) {
                this.setOriginPos(this.blockPosition());
            } else {
                if (MathUtil.getPosDisToVec(getOriginPos(), position()) >= 10) {
                    final int range = 4;
                    for (int i = -range; i <= range; ++i) {
                        for (int j = -range; j <= range; ++j) {
                            final Mth tmp = getOriginPos().offset(i, -1, j);
                            if (level.getBlockState(tmp).isAir()) {
                                level.setBlockAndUpdate(tmp, Blocks.GRASS_BLOCK.defaultBlockState());
                            }
                            for (int k = 0; k <= 10; ++k) {
                                level.setBlockAndUpdate(getOriginPos().offset(i, k, j), Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                    this.setPos(getOriginPos().getX(), getOriginPos().getY() + 1, getOriginPos().getZ());
                }
            }
        }
    }

    @Override
    public int getBossStage() {
        final float percent = this.bossInfo.getPercent();
        return percent > 3F / 4 ? 1 :
                percent > 2F / 4 ? 2 :
                        percent > 1F / 4 ? 3 : 4;
    }

    @Override
    protected void spawnSpecialDrops() {
        final int playerCnt = this.bossInfo.getPlayers().size();
        for (int i = 0; i < 2 + 2 * playerCnt; ++i) {
            JewelEntity jewel = EntityRegister.JEWEL.get().create(level);
            EntityUtil.onEntityRandomPosSpawn(level, jewel, blockPosition().above(5), 4);
        }
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(2F, 7.5F);
    }

    @Override
    public int getSpawnCount() {
        return (this.bossInfo.getPlayers().size() + 1) / 2 + 1;
    }

    @Override
    public float getWalkSpeed() {
        return 0;
    }

    @Override
    public float getEatDamage() {
        return ZombieUtil.NORMAL_DAMAGE;
    }

    @Override
    public float getLife() {
        return 1000;
    }

    @Override
    public float getInnerLife() {
        return 4000;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("origin_pos")) {
            CompoundTag nbt = compound.getCompound("origin_pos");
            this.setOriginPos(new Mth(nbt.getInt("origin_pos_x"), nbt.getInt("origin_pos_y"), nbt.getInt("origin_pos_z")));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("origin_pos_x", this.getOriginPos().getX());
        nbt.putInt("origin_pos_y", this.getOriginPos().getY());
        nbt.putInt("origin_pos_z", this.getOriginPos().getZ());
        compound.put("origin_pos", nbt);
    }

    public Mth getOriginPos() {
        return this.entityData.get(ORIGIN_POS);
    }

    public void setOriginPos(Mth pos) {
        this.entityData.set(ORIGIN_POS, pos);
    }

    @Override
    public ZombieType getZombieType() {
        return RoofZombies.EDGAR_090505;
    }

}
