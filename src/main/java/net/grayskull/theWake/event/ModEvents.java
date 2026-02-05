package net.grayskull.theWake.event;



import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.entity.ModEntities;
import net.grayskull.theWake.entity.WokenEntities;
import net.grayskull.theWake.entity.client.WakeFogRenderer;
import net.grayskull.theWake.entity.custom.RollingWakeEntity;
import net.grayskull.theWake.theWake;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.logging.Level;

@Mod.EventBusSubscriber(modid = theWake.MOD_ID)
public class ModEvents {



    @SubscribeEvent
    public static void fogOnPlayerEffect(ViewportEvent.RenderFog event) {


        if (event instanceof WokenEntities wokenEntities) {
                 {
                    event.setNearPlaneDistance(6.0f);
                    event.setFarPlaneDistance(16.0f);
                    event.setCanceled(true);
                WakeFogRenderer.clientTick();

            }
        }
}



    @SubscribeEvent
    public static void PlayWakeAmbienceOne(PlayLevelSoundEvent.AtEntity event) {
    }



}

