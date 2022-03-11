package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.api.interfaces.ICollectible;
import com.hungteen.pvz.common.event.events.LivingCollectDropEvent;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 08:58
 **/
public abstract class DropEntity extends Mob implements ICollectible {

    /**
     * how much does it have when being collected.
     */
    private static final EntityDataAccessor<Integer> AMOUNT = SynchedEntityData.defineId(DropEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(DropEntity.class, EntityDataSerializers.INT);

    public DropEntity(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
        this.setInvulnerable(true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AMOUNT, 1);
        this.entityData.define(STATE, DropStates.NORMAL.ordinal());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        this.onDropped();
        return super.finalizeSpawn(accessor, difficultyInstance, spawnType, groupData, tag);
    }

    @Override
    public void tick() {
        super.tick();
        //when being stolen or collected, it can move through blocks.
        this.noPhysics = this.getDropState() != DropStates.NORMAL;

        if (! level.isClientSide) {
            if (this.getDropState() == DropStates.NORMAL) {
                ++ this.tickCount;
            }
            //max exist time reach, it will disappear.
            if (this.tickCount >= this.getMaxLiveTick()) {
                this.discard();
            }
        }
    }

    /**
     * collect by colliding with entity.
     */
    @Override
    public void playerTouch(Player entityIn) {
        if (this.canCollectBy(entityIn)) {
            this.onCollect(entityIn);
        }
    }

    @Override
    public boolean canCollectBy(LivingEntity living) {
        return EntityUtil.isEntityValid(this) && this.getDropState() != DropStates.STEAL && ! MinecraftForge.EVENT_BUS.post(new LivingCollectDropEvent(living, this));
    }

    /**
     * drop live tick,read from config file
     */
    protected abstract int getMaxLiveTick();

    /**
     * called when first join to world.
     */
    protected void onDropped() {

    }

    @Override
    protected void doPush(Entity entityIn) {//can not push by entity.
    }

    @Override
    protected void pushEntities() {//can not push by entity.
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (AMOUNT.equals(key)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(key);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void playBlockFallSound() {
        return;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("drop_amount")) {
            this.setAmount(compound.getInt("drop_amount"));
        }
        if (compound.contains("drop_state")) {
            this.setDropState(DropStates.values()[compound.getInt("drop_state")]);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("drop_amount", this.getAmount());
        compound.putInt("drop_state", this.getDropState().ordinal());
    }

    public int getAmount() {
        return this.entityData.get(AMOUNT);
    }

    public void setAmount(int num) {
        this.entityData.set(AMOUNT, num);
    }

    public DropStates getDropState() {
        return DropStates.values()[this.entityData.get(STATE)];
    }

    public void setDropState(DropStates state) {
        this.entityData.set(STATE, state.ordinal());
    }

    public enum DropStates {
        NORMAL,
        ABSORB,
        STEAL
    }
}