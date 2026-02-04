package net.grayskull.theWake.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class WakeMoteParticles extends TextureSheetParticle {
    protected WakeMoteParticles(ClientLevel pLevel, double pX, double pY, double pZ,
                                SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8f;
        this.lifetime = 280;
        this.hasPhysics = false;
        this.gravity = -0.01f;
        this.scale(35.0F);



        this.setSpriteFromAge(spriteSet); // defines sprite

        this.rCol = 1f; // red
        this.gCol = 1f; // green
        this.bCol = 1f; //blue...?
        this.alpha = 255;
    }


    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime)* age + 1);
    }
    // above makes particles fade out



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
            return new WakeMoteParticles(pLevel,pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
