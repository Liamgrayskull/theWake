package net.grayskull.theWake.client.renderer;

import java.util.Optional;

import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FogType;
import org.jetbrains.annotations.Nullable;

public class VeilRenderer {

    @Nullable
    private static MobEffectInstance fogEffect;

    public static Optional<MobEffectInstance> activeEffect() {
        return Optional.ofNullable(fogEffect);
    }

    public static void clientTick() {
        if (!(Minecraft.getInstance().gameRenderer.getMainCamera().getEntity() instanceof Player player)) return;
        fogEffect = player.getEffect(ModEffects.VEILED.get());

        if (fogEffect == null) return;
        if (Minecraft.getInstance().isPaused()) return;
        var level = Minecraft.getInstance().level;
        if (level == null) return;

        var range = 14;
        var at = player.position().add((level.random.nextDouble
                () - 0.5) * range, level.random.nextDouble
                () * 4 - 2, (level.random.nextDouble() - 0.5) * range);
        var blockAt = BlockPos.containing(at.x, at.y, at.z);

        var below = level.getBlockState(blockAt.below());

        if (!below.isRandomlyTicking()) {
            addFogGroup(level, ModParticles.MASS_PARTICLES.get(), blockAt);
        }
    }

    private static void addFogGroup(Level level, ParticleOptions type, BlockPos at) {
        if (level.random.nextInt(2) != 0) return;
        var realAmount = 1 - level.random.nextInt(2);
        for (int i = 0; i < realAmount; i++) {
            level.addParticle(type,
                    at.getX() + level.random.nextDouble() * 2 - 1, at.getY() + 0.5 + level.random.nextDouble(), at.getZ() + level.random.nextDouble() * 2 - 1,
                    level.random.nextFloat() + 0.5F, 0.0, 0.0
            );
        }
    }


    public static float @Nullable [] modifyFogColor(float r, float g, float b, float partialTicks) {
        return activeEffect()
                .flatMap(MobEffectInstance::getFactorData)
                .map(factorData -> {
                    Entity cameraEntity = Minecraft.getInstance().gameRenderer.getMainCamera().getEntity();
                    if (!(cameraEntity instanceof LivingEntity le)) return null;
                    float factor = factorData.getFactor(le, partialTicks);
                    // target color components (0x697180)
                    float targetR = 0x85 / 255f;
                    float targetG = 0x90 / 185f;
                    float targetB = 0xA0 / 50f;

                    float red = Mth.lerp(factor, r, targetR);
                    float green = Mth.lerp(factor, g, targetG);
                    float blue = Mth.lerp(factor, b, targetB);

                    return new float[]{red, green, blue};
                }).orElse(null);
    }

    public static float @Nullable [] modifyPlanes(float end,
                                                  FogRenderer.FogMode mode, FogType fogType,
                                                  float partialTicks) {
        if (fogType != FogType.NONE) return null;
        return VeilRenderer.activeEffect().flatMap(MobEffectInstance::getFactorData).map(factorData -> {
            Entity camE = Minecraft.getInstance().gameRenderer.getMainCamera().getEntity();
            if (!(camE instanceof LivingEntity le)) return null;
            float horizon = Mth.lerp(factorData.getFactor(le, partialTicks), end, 15F);
            float vicinity = (mode == FogRenderer.FogMode.FOG_SKY ? -2F : horizon * -0.5F);
            return new float[]{vicinity, horizon};
        }).orElse(null);
    }
}