package net.grayskull.theWake.events;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.grayskull.theWake.effect.ModEffects;
import net.grayskull.theWake.theWake;
import net.minecraft.Util;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ClientEvents {

    void onClientTick(Minecraft minecraft) {

        Player player = minecraft.player;
        applyShader(player, minecraft);
    }
        private static String lastAppliedShader = null;

    private static void applyShader(Player player, Minecraft mc) {
        if (lastAppliedShader == null) {
            GameRenderer renderer = Minecraft.getInstance().gameRenderer;

            String rendererShader = renderer.currentEffect() == null ? null : renderer.currentEffect().getName(); //IMPORTANT! check if posteffect = currenteffect


            ItemStack stack =
                    player.getItemBySlot(EquipmentSlot.HEAD).isEmpty() ? null : player.getItemBySlot(EquipmentSlot.HEAD);

            Item item = stack.getItem();

            if (mc.options.getCameraType() == CameraType.FIRST_PERSON) {};



            if (rendererShader != null ) {}
        }
    }

    private static final Map<ModEffects, String> SHADER_ON_EFFECT = Util.make(() -> { var map = new
        Object2ObjectOpenHashMap<RegistryObject<MobEffect>, String>(); map = new Object2ObjectOpenHashMap<>();
        map.put(ModEffects.VEILED, "minecraft:shaders/post/phosphor.json");
        map.put(ModEffects.COUGH, "minecraft:shaders/post/wobble.json");
        map.put(ModEffects.WOKEN, "minecraft:shaders/post/invert.json");


                return map;
    });


    private static boolean hasVeiledEffect(Player player, MobEffect mobEffect) {
        return player.hasEffect(ModEffects.VEILED.get());
    }



}

