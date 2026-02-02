package net.grayskull.theWake.event;

import net.grayskull.theWake.entity.ModEntities;
import net.grayskull.theWake.entity.custom.RollingWakeEntity;
import net.grayskull.theWake.theWake;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = theWake.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ROLLING_WAKE.get(), RollingWakeEntity.createAttributes().build());
    }
}
