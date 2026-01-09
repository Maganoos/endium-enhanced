package eu.magkari.mc.endium.block;

import eu.magkari.mc.endium.EndiumEnhanced;
import eu.magkari.mc.endium.item.EndiumItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class EndiumBlocks {
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, EndiumEnhanced.id(name));
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) EndiumItems.register(name, properties -> new BlockItem(block, properties), new Item.Properties().useBlockDescriptionPrefix());


        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void register() {}

    public static final Block ENDIUM_ORE = register(
            "endium_ore",
            Block::new,
            Blocks.END_STONE.properties().strength(4.0F, 10.0F),
            true
    );

    public static final Block ENDIUM_BLOCK = register(
            "endium_block",
            Block::new,
            Blocks.DIAMOND_BLOCK.properties().mapColor(MapColor.COLOR_PURPLE),
            true
    );

    public static final Block ENDIUM_SCRAP_BLOCK = register(
            "endium_scrap_block",
            Block::new,
            Blocks.RAW_IRON_BLOCK.properties().mapColor(MapColor.METAL),
            true
    );
}
