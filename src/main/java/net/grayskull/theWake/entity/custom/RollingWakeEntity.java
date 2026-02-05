package net.grayskull.theWake.entity.custom;

import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.particle.ModParticles;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;


public class RollingWakeEntity extends Mob {
    public RollingWakeEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // makes em float
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class,3000f)); // fog faces towards player,,, always? test, prob not ideal
        this.noPhysics = false;
        this.setNoGravity(true);
        this.setPersistenceRequired();
        this.requiresCustomPersistence();
        this.setInvulnerable(true);








    }


    public boolean isAttackable() { return false; }
    protected boolean canRide(Entity pVehicle) { return false; }

    protected float getSoundVolume() {
        return 4.0F;
    } //Returns the volume for the sounds this mob makes.

    public void aiStep() {
        if (this.level().isClientSide) {
            for(int i = 0; i < 1; ++i) {
                this.level().addParticle(ModParticles.MOTE_PARTICLES.get(), this.getRandomX(15.5D),
                        this.getRandomY() + 2.25D, this.getRandomZ(15.5D), (this.random.nextDouble() - 1.5D) * 1.5D,
                        -this.random.nextDouble(), (this.random.nextDouble() - 0.2D) * 1.5D);
            }
        }

        super.aiStep();
    }




    protected void customServerAiStep() {
        ServerLevel serverlevel = (ServerLevel)this.level();
        serverlevel.getProfiler().push("rollingBrain");
        this.level().getProfiler().pop();
        super.customServerAiStep();
        if ((this.tickCount + this.getId()) % 120 == 0) {
            applyCoughAround(serverlevel, this.position(), this, 60);
        }
    }


    public static void applyCoughAround(ServerLevel pLevel, Vec3 pPos, @javax.annotation.Nullable Entity pSource, int pRadius) {
        MobEffectInstance mobeffectinstance = new MobEffectInstance(ModEffects.COUGH.get(), 260, 0, false, false);
        MobEffectUtil.addEffectToPlayersAround(pLevel, pSource, pPos, (double)pRadius, mobeffectinstance, 200);
    }

    public SoundSource getSoundSource() {
        return SoundSource.WEATHER;
    }

    @Override
    public void checkDespawn() {

        super.checkDespawn();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MOVEMENT_SPEED, 20D)
                .add(Attributes.FLYING_SPEED, 20D)
                .add(Attributes.FOLLOW_RANGE, 50D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 100D); // TINKER WITH THIS LATER !
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

REFERENCE LATER FOR BLOCKS WHICH SUMMON FOG

 */




}