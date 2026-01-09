package eu.magkari.mc.endium.item;

import eu.magkari.mc.endium.EndiumEnhanced;
import eu.magkari.mc.endium.armor.EndiumArmorMaterial;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EndiumItems {
    public static final ResourceKey<CreativeModeTab> ENDIUM_ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), EndiumEnhanced.id("item_group"));
    public static final CreativeModeTab ENDIUM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(EndiumItems.ENDIUM_INGOT))
            .title(Component.translatable("itemGroup.endium-enhanced.group"))
            .build();

    private static final List<ItemStack> TO_ADD = new ArrayList<>();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ENDIUM_ITEM_GROUP_KEY, ENDIUM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(ENDIUM_ITEM_GROUP_KEY).register(group -> group.acceptAll(TO_ADD));
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, EndiumEnhanced.id(name));

        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        TO_ADD.add(item.getDefaultInstance());

        return item;
    }

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    public static Item register(String name) {
        return register(name, Item::new);
    }

    // ENDIUM
    public static final Item ENDIUM_INGOT = register("endium_ingot");
    public static final Item ENDIUM_NUGGET = register("endium_nugget");
    public static final Item ENDIUM_SCRAP = register("endium_scrap");
    public static final SmithingTemplateItem ENDIUM_UPGRADE_SMITHING_TEMPLATE =
            register("endium_upgrade_smithing_template", EndiumItems::createEndiumUpgradeTemplate);

    // ENDIUM MODS
    public static final Item ENDIUM_ELYTRA = register(
            "endium_elytra",
            Item::new,
            new Item.Properties()
                    .humanoidArmor(EndiumArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
    );
    public static final FishingRodItem ENDIUM_ROD = register("endium_rod", FishingRodItem::new, new Item.Properties().durability(128).enchantable(1).repairable(ENDIUM_INGOT).stacksTo(1));
    public static final BowItem ENDIUM_BOW = register(
            "endium_bow",
            BowItem::new,
            new Item.Properties()
                    .durability(768)
                    .enchantable(1)
                    .repairable(ENDIUM_INGOT).stacksTo(1)
    );
    public static final ShearsItem ENDIUM_SHEARS = register("endium_shears", ShearsItem::new, new Item.Properties().durability(476).component(DataComponents.TOOL, ShearsItem.createToolProperties()));
    public static final Item ENDIUM_TURTLE_HELMET = register(
            "endium_turtle_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(EndiumArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(EndiumArmorMaterial.BASE_DURABILITY)));


    // HELPER
    private static SmithingTemplateItem createEndiumUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(
                Component.translatable("item.endium-enhanced.endium_upgrade.applies_to"),
                Component.translatable("item.endium-enhanced.endium_upgrade.ingredients"),
                Component.translatable("item.endium-enhanced.endium_upgrade.base_slot_description"),
                Component.translatable("item.endium-enhanced.endium_upgrade.additions_slot_description"),
                List.of(

                ),
                List.of(
                        Identifier.withDefaultNamespace("container/slot/ingot")
                ),
                properties
        );
    }

}
