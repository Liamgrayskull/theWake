package net.grayskull.theWake.item;

import net.grayskull.theWake.block.ModBlocks;
import net.grayskull.theWake.theWake;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, theWake.MOD_ID);


    public static final RegistryObject<CreativeModeTab> WAKE_TAB = CREATIVE_MODE_TABS.register("wake_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ICON.get()))
            .title(Component.translatable("creativetab.wake_tab"))
            .displayItems((pParameters, pOutput) -> {
                //Items go here
                pOutput.accept(ModItems.TEST_ITEM.get()); //.get is used ONLY for custom items


                pOutput.accept(ModBlocks.TEST_BLOCK.get());
            })
            .build());

    public static void  register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
