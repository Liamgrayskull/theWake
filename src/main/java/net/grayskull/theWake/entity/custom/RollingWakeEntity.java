package net.grayskull.theWake.entity.custom;

import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.particle.ModParticles;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

// Try to take some goals from bee pollination so they stick near the floor
public class RollingWakeEntity extends Vex {
    public RollingWakeEntity(EntityType<? extends Vex> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void registerGoals() {
        super.registerGoals();
        this.setPersistenceRequired();
        this.setInvulnerable(true);
        this.targetSelector.removeGoal((new HurtByTargetGoal(this, Raider.class)).setAlertOthers());

    }

    public void checkDespawn() { }

    public boolean isAttackable() { return false; }

    protected float getSoundVolume() { return 40.0F; }

    public void aiStep() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 1; ++i) {
                this.level().addParticle(ModParticles.MOTE_PARTICLES.get(), this.getRandomX(15.5D),
                        this.getRandomY() + 2.25D, this.getRandomZ(15.5D), (this.random.nextDouble() - 1.5D) * 1.5D,
                        -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 1.5D);

            }

        }

        super.aiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 10D)
                .add(Attributes.ATTACK_DAMAGE, -1.0D)
                .add(Attributes.FOLLOW_RANGE, 1000D)
                .add(Attributes.FLYING_SPEED, 10D);
    }

    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel) this.level();
        serverlevel.getProfiler().push("rollingBrain");
        this.level().getProfiler().pop();
        super.customServerAiStep();
        if ((this.tickCount + this.getId()) % 120 == 0) {
            RollingWakeFogControl.applyCoughAround(serverlevel, this.position(), this, 60);
        }
    }



    static class RollingWakeFogControl extends MoveControl {
        public RollingWakeFogControl(RollingWakeEntity pRollingWake) {
            super(pRollingWake);
        }

        public static void applyCoughAround(ServerLevel pLevel, Vec3 pPos, @javax.annotation.Nullable Entity pSource, int pRadius) {
            MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.COUGH.get(), 260, 0, false, false);
            MobEffectUtil.addEffectToPlayersAround(pLevel, pSource, pPos, (double) pRadius, mobeffectinstance, 200);
        }

        public SoundSource getSoundSource() {
            return SoundSource.AMBIENT;
        }

        }


    }





/*
  @Nullable
   public ServerPlayer getCause() {
      return this.cause;
   }

   public void setCause(@Nullable ServerPlayer pCause) {
      this.cause = pCause;
   }

   private void powerLightningRod() {
      BlockPos blockpos = this.getStrikePosition();
      BlockState blockstate = this.level().getBlockState(blockpos);
      if (blockstate.is(Blocks.LIGHTNING_ROD)) {
         ((LightningRodBlock)blockstate.getBlock()).onLightningStrike(blockstate, this.level(), blockpos);
      }

   }

//REFERENCE LATER FOR BLOCKS WHICH SUMMON FOG




*/



