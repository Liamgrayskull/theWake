package net.grayskull.theWake.entity.custom;

import net.minecraft.sounds.SoundSource;
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

public class RollingWakeEntity extends Mob implements TraceableEntity {
    public RollingWakeEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // makes em float
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class,3000f)); // fog faces towards player,,, always? test, prob not ideal




    } // consult more goals to add at package net.minecraft.world.entity.ai.goal;


    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    public void move(MoverType pType, Vec3 pPos) {
        super.move(pType, pPos);
        this.checkInsideBlocks();
    }
    public boolean isAttackable() {
        return false;
    }

    public SoundSource getSoundSource() {
        return SoundSource.WEATHER;
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

    @Override
    public @Nullable Entity getOwner() {
        return null; // USE THIS LATER FOR COUGH SYMPTOM LOGIC
    }
}