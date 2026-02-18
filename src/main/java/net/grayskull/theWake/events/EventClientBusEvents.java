package net.grayskull.theWake.events;

import net.grayskull.theWake.entity.client.ModModelLayers;
import net.grayskull.theWake.entity.client.RollingWakeModel;
import net.grayskull.theWake.theWake;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = theWake.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventClientBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ROLLING_WAKE_LAYER, RollingWakeModel::createBodyLayer);
    }
}
