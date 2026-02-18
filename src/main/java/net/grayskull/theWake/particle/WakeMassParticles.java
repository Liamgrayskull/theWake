package net.grayskull.theWake.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class WakeMassParticles extends TextureSheetParticle {
    protected WakeMassParticles(ClientLevel pLevel, double pX, double pY, double pZ,
                                SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.5F;
        this.lifetime = 380;
        this.hasPhysics = false;
        this.gravity = -0.01f;
        this.scale(60.0F);
        this.setSpriteFromAge(spriteSet);
        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
        this.alpha = 255;

    }

    public void fadeIn() {
        if (age < 20) {
            this.alpha = (age * 1.0f) / 40;
        }
    }
    @Override
    public void tick() {
        super.tick();
        fadeIn();
        fadeOut();
    }

    public void fadeOut() {
        if (this.alpha < 0.01) {
            remove();
        } else {
            this.alpha = this.alpha - 0.005F;
        }
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; //other options for opaque//lit sheets!
    }


    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            return new WakeMassParticles(pLevel,pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
