package net.grayskull.theWake.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;


public class VeiledEffect extends MobEffect  {
    public VeiledEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);


        this.setFactorDataFactory(() -> new MobEffectInstance.FactorData(22));
    }



    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
