package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class EndiumEnhancedBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected EndiumEnhancedBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.add(EndiumBlocks.ENDIUM_ORE, createOreDrop(EndiumBlocks.ENDIUM_ORE, EndiumItems.ENDIUM_SCRAP));

        dropSelf(EndiumBlocks.ENDIUM_BLOCK);
        dropSelf(EndiumBlocks.ENDIUM_SCRAP_BLOCK);
    }

    @Override
    public String getName() {
        return "EndiumEnhancedBlockLootTableProvider";
    }
}
