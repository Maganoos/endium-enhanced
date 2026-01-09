package eu.magkari.mc.endium;

import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import eu.magkari.mc.endium.world.gen.EndiumOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndiumEnhanced implements ModInitializer {
	public static final String MOD_ID = "endium-enhanced";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final TagKey<Item> REPAIRS_ENDIUM_EQUIPMENT = TagKey.create(Registries.ITEM, EndiumEnhanced.id("repairs_endium_equipment"));

    @Override
	public void onInitialize() {
        EndiumItems.register();
        EndiumBlocks.register();
        EndiumOreGeneration.generateOres();

        LootTableEvents.MODIFY.register((resourceKey, builder, source, provider) -> {
            if (resourceKey.equals(BuiltInLootTables.END_CITY_TREASURE) && source.isBuiltin()) {
                LootPool pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(EndiumItems.ENDIUM_UPGRADE_SMITHING_TEMPLATE).setWeight(1))
                        .build();

                builder.pool(pool);
            }
            if (resourceKey.equals(BuiltInLootTables.SHEAR_SHEEP_BY_DYE) || resourceKey.equals(BuiltInLootTables.SHEAR_SHEEP) && source.isBuiltin()) {
                LootPool pool = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(0F, 1F))
                        .add(LootItem.lootTableItem(EndiumItems.ENDIUM_SCRAP))
                        .build();

                builder.pool(pool);
            }
        });
	}

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}