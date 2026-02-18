package net.grayskull.theWake;

import com.mojang.logging.LogUtils;
import net.grayskull.theWake.block.ModBlocks;
import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.entity.ModEntities;
import net.grayskull.theWake.entity.client.RollingWakeRenderer;
import net.grayskull.theWake.item.ModCreativeModeTabs;
import net.grayskull.theWake.item.ModItems;
import net.grayskull.theWake.painting.ModPaintings;
import net.grayskull.theWake.particle.ModParticles;
import net.grayskull.theWake.particle.WakeMassParticles;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(theWake.MOD_ID)
public class theWake {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "wake";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public theWake(FMLJavaModLoadingContext context) {

        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModPaintings.register(modEventBus);
        ModEffects.register(modEventBus);

        ModEntities.register(modEventBus);
        ModParticles.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }


    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.SEARCH) {
            event.accept(ModItems.TEST_ITEM);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.ROLLING_WAKE.get(), RollingWakeRenderer::new);

        }
        @SubscribeEvent // always include these!
        public static void  registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.MASS_PARTICLES.get(), WakeMassParticles.Provider::new);
        }
    }
}
