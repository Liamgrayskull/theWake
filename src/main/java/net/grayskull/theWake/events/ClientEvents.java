package net.grayskull.theWake.events;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.grayskull.theWake.effect.ModEffects;
import net.minecraft.Util;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class ClientEvents {

    private static String lastShader = null;

    public static void applyShader(Minecraft minecraft) {
        GameRenderer renderer = Minecraft.getInstance().gameRenderer;
        String ShaderRenderer = null;
        Player player = minecraft.player;
        if (player == null) return;

        ShaderRenderer = renderer.currentEffect() == null ? null :
                    renderer.currentEffect().getName();

            if (ShaderRenderer != null &&
                    WAKE_SHADERS.contains(ShaderRenderer)) {
                return;
            }


        String newShader = null;
            if(player.hasEffect(ModEffects.VEILED.get())) {
                newShader = SHADER_EFFECTS.get(ModEffects.VEILED);
            } else if (player.hasEffect(ModEffects.WOKEN.get())) {
                newShader = SHADER_EFFECTS.get(ModEffects.WOKEN);
            } else if (player.hasEffect(ModEffects.COUGH.get())) {
                newShader = SHADER_EFFECTS.get(ModEffects.COUGH);
            }



        if (newShader != null &&
                !newShader.equals(ShaderRenderer)) {
            renderer.loadEffect(new ResourceLocation(newShader));
            lastShader = newShader;
        } else if (newShader == null && lastShader != null) {
            renderer.shutdownEffect();
            lastShader = null;
        }
    }

private static final Object2ObjectOpenHashMap<RegistryObject<MobEffect>, String> SHADER_EFFECTS = Util.make(() -> {
    Object2ObjectOpenHashMap<RegistryObject<MobEffect>, String> map = new Object2ObjectOpenHashMap<>();
    map.put(ModEffects.VEILED, "minecraft:shaders/post/creeper.json");
    map.put(ModEffects.WOKEN, "minecraft:shaders/post/wobble.json");
    map.put(ModEffects.COUGH, "minecraft:shaders/post/phosphor.json");
    return map;
});

    private static final Set<String> WAKE_SHADERS;

    static {
        WAKE_SHADERS = new HashSet<>(SHADER_EFFECTS.values());
    }

    static void onClientTick(Minecraft minecraft) {
        ClientEvents.applyShader(minecraft);
    }
        @SubscribeEvent
        public static void onClientEndTick (TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                ClientEvents.onClientTick(Minecraft.getInstance());
            }
        }
}
