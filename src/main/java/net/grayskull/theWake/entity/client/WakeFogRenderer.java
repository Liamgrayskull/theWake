package net.grayskull.theWake.entity.client;


import net.grayskull.theWake.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class WakeFogRenderer {

    private static MobEffectInstance coughEffect;

    public static void clientTick() {
        if (!(Minecraft.getInstance().gameRenderer.getMainCamera().getEntity() instanceof Player player)) return;
        coughEffect = player.getEffect(ModEffects.COUGH.get()); // REFACTOR THIS WHEN YOU KNOW WHAT TF YOU'RE DOING PLEASE

    }
}
