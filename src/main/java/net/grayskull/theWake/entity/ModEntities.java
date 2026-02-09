package net.grayskull.theWake.entity;

import net.grayskull.theWake.entity.custom.RollingWakeEntity;
import net.grayskull.theWake.theWake;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, theWake.MOD_ID);

    public static final RegistryObject<EntityType<RollingWakeEntity>> ROLLING_WAKE =
            ENTITY_TYPES.register("rolling_wake", () -> EntityType.Builder.of(RollingWakeEntity::new, MobCategory.MISC)
                    .sized(1.0f,1.0f).build("rolling_wake"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register((eventBus));
    }

}
