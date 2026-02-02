package net.grayskull.theWake.block;

import net.grayskull.theWake.item.ModItems;
import net.grayskull.theWake.theWake;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, theWake.MOD_ID);


    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.BASALT))); // Creates a block. .copy copies block behavior, .of is OG. any property can be overridden!

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block); // registers the block
        registerBlockItem(name, toReturn); // registers the blockItem
        return toReturn; // returns block for particular method
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) { // T HAS to be some sort of block
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())); // Registers an item and associates it w/ a block!
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
