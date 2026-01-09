package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.EndiumEnhanced;
import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class EndiumEnhancedItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public EndiumEnhancedItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // --- INGOTS & REPAIR ---
        valueLookupBuilder(EndiumEnhanced.REPAIRS_ENDIUM_EQUIPMENT)
                .add(EndiumItems.ENDIUM_INGOT);

        valueLookupBuilder(ConventionalItemTags.INGOTS)
                .add(EndiumItems.ENDIUM_INGOT);

        // --- TOOLS & WEAPONS ---
        valueLookupBuilder(ConventionalItemTags.SHEAR_TOOLS)
                .add(EndiumItems.ENDIUM_SHEARS);
        valueLookupBuilder(ConventionalItemTags.FISHING_ROD_TOOLS)
                .add(EndiumItems.ENDIUM_ROD);
        valueLookupBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(EndiumItems.ENDIUM_BOW);

        // --- STORAGE BLOCKS ---
        valueLookupBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .add(EndiumBlocks.ENDIUM_BLOCK.asItem(), EndiumBlocks.ENDIUM_SCRAP_BLOCK.asItem());

        // --- ORES ---
        valueLookupBuilder(ConventionalItemTags.ORES)
                .add(EndiumBlocks.ENDIUM_ORE.asItem());
        valueLookupBuilder(ConventionalItemTags.ORE_RATES_SINGULAR)
                .add(EndiumBlocks.ENDIUM_ORE.asItem());

        valueLookupBuilder(ConventionalItemTags.NETHERITE_SCRAP_ORES)
                .add(EndiumItems.ENDIUM_SCRAP);

        // --- ARMOR ENCHANTABLE ---
        valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_ELYTRA, EndiumItems.ENDIUM_TURTLE_HELMET);
        valueLookupBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_TURTLE_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_ELYTRA);
        valueLookupBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(); // no leg armor yet
        valueLookupBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(); // no boots yet

        // --- ARMOR ITEMS ---
        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(EndiumItems.ENDIUM_TURTLE_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(EndiumItems.ENDIUM_ELYTRA);

        // --- DURABILITY ENCHANTABLE ---
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(
                        EndiumItems.ENDIUM_ELYTRA,
                        EndiumItems.ENDIUM_TURTLE_HELMET,
                        EndiumItems.ENDIUM_BOW,
                        EndiumItems.ENDIUM_SHEARS,
                        EndiumItems.ENDIUM_ROD
                );

        // --- SHEARS SPECIFIC ---
        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_SHEARS);
        // Fishing Rod specific
        valueLookupBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_ROD);

        // --- GENERAL WEAPON ENCHANTABLES ---
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_BOW);
        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(EndiumItems.ENDIUM_BOW);

    }
}
