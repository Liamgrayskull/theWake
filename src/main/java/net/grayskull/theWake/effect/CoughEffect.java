package net.grayskull.theWake.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CoughEffect extends MobEffect {
    protected CoughEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) { // applyEffectTick is applied every tick AS LONG AS isDurationEffectTick = true below
        if (!pLivingEntity.level().isClientSide()) {



        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }



    public void coughRate() {
  // I don't know how to write methods oh god
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
