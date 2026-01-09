package eu.magkari.mc.endium.world.gen;

import eu.magkari.mc.endium.world.EndiumPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class EndiumOreGeneration {

    public static void generateOres() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                EndiumPlacedFeatures.ENDIUM_ORE_PLACED_KEY
        );
    }
}
