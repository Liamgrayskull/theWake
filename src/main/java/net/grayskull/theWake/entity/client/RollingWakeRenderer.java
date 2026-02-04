package net.grayskull.theWake.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.grayskull.theWake.entity.custom.RollingWakeEntity;

import net.grayskull.theWake.theWake;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RollingWakeRenderer extends MobRenderer<RollingWakeEntity, RollingWakeModel<RollingWakeEntity>> {
    public RollingWakeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RollingWakeModel<>(pContext.bakeLayer(ModModelLayers.ROLLING_WAKE_LAYER)), 10f);
    }

    @Override
    public ResourceLocation getTextureLocation(RollingWakeEntity pEntity) {
        return new ResourceLocation(theWake.MOD_ID, "textures/entity/rolling_wake.png");
    }

    @Override
    public void render(RollingWakeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isAggressive()) {
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
