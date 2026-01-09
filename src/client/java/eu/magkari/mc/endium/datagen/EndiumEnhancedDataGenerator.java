package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.world.EndiumConfiguredFeatures;
import eu.magkari.mc.endium.world.EndiumPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class EndiumEnhancedDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EndiumEnhancedModelProvider::new);
        pack.addProvider(EndiumEnhancedBlockTagProvider::new);
        pack.addProvider(EndiumEnhancedBlockLootTableProvider::new);
        pack.addProvider(EndiumEnhancedRecipeProvider::new);
        pack.addProvider(EndiumEnhancedItemTagProvider::new);
        pack.addProvider(EndiumEnhancedRegistryDataGenerator::new);
	}

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        DataGeneratorEntrypoint.super.buildRegistry(registryBuilder);

        registryBuilder.add(Registries.CONFIGURED_FEATURE, EndiumConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, EndiumPlacedFeatures::bootstrap);
    }
}
