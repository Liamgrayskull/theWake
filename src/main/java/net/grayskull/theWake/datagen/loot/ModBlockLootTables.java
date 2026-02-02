package net.grayskull.theWake.datagen.loot;

import net.grayskull.theWake.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.TEST_BLOCK.get());
        //This is where Block drop loot tables are actually defines!
        /*
        this.add(ModBlocks.TESST_ORE.get(),
        block -> createOreDrops());
        //This creates an ore drop. drop! Used for more unique blockdrops
         */
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
    // High Level Overview,  All of our known blocks added via the above loot tables are listed under the ModBlocks DeferredRegister, unless it has the block property
    // .noLootTable() , which exempts that block from the datagen!
}
