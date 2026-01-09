package eu.magkari.mc.endium.world;

import eu.magkari.mc.endium.EndiumEnhanced;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class EndiumPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ENDIUM_ORE_PLACED_KEY = registerKey("endium_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(
                context,
                ENDIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(EndiumConfiguredFeatures.ENDIUM_ORE_KEY),
                EndiumOrePlacement.modifiersWithCount(
                        14,
                        HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.top())
                )
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, EndiumEnhanced.id(name));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
