package net.grayskull.theWake.datagen;

import net.grayskull.theWake.block.ModBlocks;
import net.grayskull.theWake.theWake;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, theWake.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    //Replace BlockTags with ModTags for custom Tags
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TEST_BLOCK.get() );

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.TEST_BLOCK.get() );

    }
}
