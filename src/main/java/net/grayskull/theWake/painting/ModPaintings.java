package net.grayskull.theWake.painting;

import net.grayskull.theWake.theWake;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, theWake.MOD_ID);


    public static final RegistryObject<PaintingVariant> MORN = PAINTING_VARIANTS.register("morn", () -> new PaintingVariant(16,32)); // Defines painting "morn" as 2 block tall 1 wide

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
