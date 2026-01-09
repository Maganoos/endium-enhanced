package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.EndiumEnhanced;
import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class EndiumEnhancedItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public EndiumEnhancedItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(EndiumEnhanced.REPAIRS_ENDIUM_EQUIPMENT)
                .add(EndiumItems.ENDIUM_INGOT);
        valueLookupBuilder(ConventionalItemTags.INGOTS)
                .add(EndiumItems.ENDIUM_INGOT);
        valueLookupBuilder(ConventionalItemTags.SHEAR_TOOLS)
                .add(EndiumItems.ENDIUM_SHEARS);
        valueLookupBuilder(ConventionalItemTags.FISHING_ROD_TOOLS)
                .add(EndiumItems.ENDIUM_ROD);
        valueLookupBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(EndiumItems.ENDIUM_BOW);
        valueLookupBuilder(ConventionalItemTags.NETHERITE_SCRAP_ORES)
                .add(EndiumItems.ENDIUM_SCRAP);

        valueLookupBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .add(EndiumBlocks.ENDIUM_BLOCK.asItem(), EndiumBlocks.ENDIUM_SCRAP_BLOCK.asItem());
        valueLookupBuilder(ConventionalItemTags.ORES)
                .add(EndiumBlocks.ENDIUM_ORE.asItem());
        valueLookupBuilder(ConventionalItemTags.ORE_RATES_SINGULAR)
                .add(EndiumBlocks.ENDIUM_ORE.asItem());
    }
}
