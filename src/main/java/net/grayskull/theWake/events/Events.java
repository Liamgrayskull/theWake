package net.grayskull.theWake.events;



import net.grayskull.theWake.entity.WokenEntities;
import net.grayskull.theWake.entity.client.WakeFogRenderer;
import net.grayskull.theWake.theWake;

import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = theWake.MOD_ID)
public class Events {



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

