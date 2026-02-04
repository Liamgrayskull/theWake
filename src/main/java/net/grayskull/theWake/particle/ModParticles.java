package net.grayskull.theWake.particle;

import net.grayskull.theWake.theWake;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, theWake.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MOTE_PARTICLES =
            PARTICLE_TYPES.register("mote", () -> new SimpleParticleType(true)); // if "pOverrideLimiter = true", particles SHOULD still show up even if players limit particles in options

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
