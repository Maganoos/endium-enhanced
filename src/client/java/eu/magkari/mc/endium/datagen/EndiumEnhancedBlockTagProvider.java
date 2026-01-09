package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.block.EndiumBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class EndiumEnhancedBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public EndiumEnhancedBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(EndiumBlocks.ENDIUM_BLOCK)
                .add(EndiumBlocks.ENDIUM_ORE);

        valueLookupBuilder(ConventionalBlockTags.ORES)
                .add(EndiumBlocks.ENDIUM_ORE);
        valueLookupBuilder(ConventionalBlockTags.ORE_RATES_SINGULAR)
                .add(EndiumBlocks.ENDIUM_ORE);

        valueLookupBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
                .add(EndiumBlocks.ENDIUM_BLOCK, EndiumBlocks.ENDIUM_SCRAP_BLOCK);
    }

    @Override
    public String getName() {
        return "EndiumEnhancedBlockTagProvider";
    }
}
