package eu.magkari.mc.endium.datagen;

import eu.magkari.mc.endium.block.EndiumBlocks;
import eu.magkari.mc.endium.item.EndiumItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class EndiumEnhancedModelProvider extends FabricModelProvider {

    public EndiumEnhancedModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(EndiumBlocks.ENDIUM_ORE);
        generator.createTrivialCube(EndiumBlocks.ENDIUM_BLOCK);
        generator.createTrivialCube(EndiumBlocks.ENDIUM_SCRAP_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(EndiumItems.ENDIUM_INGOT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(EndiumItems.ENDIUM_SCRAP, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(EndiumItems.ENDIUM_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(EndiumItems.ENDIUM_NUGGET, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(EndiumItems.ENDIUM_SHEARS, ModelTemplates.FLAT_ITEM);
        generator.generateFishingRod(EndiumItems.ENDIUM_ROD);
        generator.generateElytra(EndiumItems.ENDIUM_ELYTRA);
        this.generateBow(generator, EndiumItems.ENDIUM_BOW);
        generator.generateTrimmableItem(EndiumItems.ENDIUM_TURTLE_HELMET, EquipmentAssets.TURTLE_SCUTE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
    }

    @Override
    public String getName() {
        return "EndiumEnhancedModelProvider";
    }

    private void generateBow(ItemModelGenerators generator, Item item) {
        ItemModel.Unbaked unbaked = ItemModelUtils.plainModel(generator.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked unbaked2 = ItemModelUtils.plainModel(generator.createFlatItemModel(item, "_pulling_0", ModelTemplates.BOW));
        ItemModel.Unbaked unbaked3 = ItemModelUtils.plainModel(generator.createFlatItemModel(item, "_pulling_1", ModelTemplates.BOW));
        ItemModel.Unbaked unbaked4 = ItemModelUtils.plainModel(generator.createFlatItemModel(item, "_pulling_2", ModelTemplates.BOW));
        generator.itemModelOutput
                .accept(
                        item,
                        ItemModelUtils.conditional(
                                ItemModelUtils.isUsingItem(),
                                ItemModelUtils.rangeSelect(new UseDuration(false), 0.05F, unbaked2, ItemModelUtils.override(unbaked3, 0.65F), ItemModelUtils.override(unbaked4, 0.9F)),
                                unbaked
                        )
                );
    }
}
