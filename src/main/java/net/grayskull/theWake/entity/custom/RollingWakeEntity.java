package net.grayskull.theWake.entity.custom;

import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.particle.ModParticles;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

// Try to take some goals from bee pollination so they stick near the floor
public class RollingWakeEntity extends Vex {
    public RollingWakeEntity(EntityType<? extends Vex> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void registerGoals() {
        this.setPersistenceRequired();
        this.setInvulnerable(true);
        this.targetSelector.removeGoal((new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        super.registerGoals();
    }

    @Override
    public boolean isInWall() {
        return super.isInWall();
    }


    public boolean isAttackable() { return false; }

    protected float getSoundVolume() { return 40.0F; }

    public void aiStep() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 1; ++i) {
                this.level().addParticle(ModParticles.MASS_PARTICLES.get(), this.getRandomX(1.5D),
                        this.getRandomY() + 1.0D, this.getRandomZ(1.0D), (this.random.nextDouble() - 1.5D) * 1.5D,
                        -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 1.5D);

            }

        }

        super.aiStep();
    }
public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 5D)
                .add(Attributes.ATTACK_DAMAGE, -10.0D)
                .add(Attributes.FOLLOW_RANGE, 10D)
                .add(Attributes.FLYING_SPEED, 10D);
    }


    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel) this.level();
        serverlevel.getProfiler().push("rollingBrain");
        this.level().getProfiler().pop();
        super.customServerAiStep();
        if ((this.tickCount + this.getId()) % 120 == 0) {
            RollingWakeFogControl.applyVeilAround(serverlevel, this.position(), this, 60);
        }
    }



    static class RollingWakeFogControl extends MoveControl {
        public RollingWakeFogControl(RollingWakeEntity pRollingWake) {
            super(pRollingWake);
        }

        public static void applyVeilAround(ServerLevel pLevel, Vec3 pPos, @javax.annotation.Nullable Entity pSource, int pRadius) {
            MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.VEILED.get(), 260, 0, false, false);
            MobEffectUtil.addEffectToPlayersAround(pLevel, pSource, pPos, pRadius, mobeffectinstance, 200);
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



