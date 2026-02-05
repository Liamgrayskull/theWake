package net.grayskull.theWake.entity;


import net.grayskull.theWake.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

public interface WokenEntities {

    static void tickEntity(LivingEntity entity) {
        WokenEntities wokenEntities = (WokenEntities) entity;
    }

    MobEffect wokenEntities = ModEffects.COUGH.get();
}
