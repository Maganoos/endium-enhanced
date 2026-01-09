package eu.magkari.mc.endium.armor;

import eu.magkari.mc.endium.EndiumEnhanced;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class EndiumArmorMaterial {
    public static final int BASE_DURABILITY = 36;
    public static final ResourceKey<EquipmentAsset> ENDIUM_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, EndiumEnhanced.id("endium"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            5,
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.ENDERMAN_TELEPORT),
            0.0F,
            0.0F,
            EndiumEnhanced.REPAIRS_ENDIUM_EQUIPMENT,
            ENDIUM_ARMOR_MATERIAL_KEY
    );
}
