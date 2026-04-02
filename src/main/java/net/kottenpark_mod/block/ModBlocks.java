package net.kottenpark_mod.block;

import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.block.custom.*;
import net.kottenpark_mod.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {


    public static final Block TEMP_BLOCK = registerBlock("temp_block",
            properties -> new Block(properties.strength(4f).requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CHAIR = registerBlock("chair",
            properties -> new ChairBlock(properties.nonOpaque()));

    public static final Block TABLE = registerBlock("table",
            properties -> new TableBlock(properties.nonOpaque()));
    public static final Block WHITEBOARD = registerBlock("whiteboard",
            properties -> new WhiteboardBlock(properties.nonOpaque()));
    public static final Block INFORMATICA_COUCH = registerBlock("informatica_couch",
            properties -> new InformaticaCouchBlock(properties.nonOpaque()));
    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(1f).requiresTool().sounds(ModSounds.TEST_BLOCK_SOUNDS)));

    public static final Block TEMP_LAMP_BLOCK = registerBlock("temp_lamp_block",
            properties -> new TempLampBlock(properties.strength(1f).requiresTool().sounds(BlockSoundGroup.ANVIL).luminance(state -> state.get(TempLampBlock.TEXTURE_STATE)*3)));

    public static final Block TEMP_STAIRS = registerBlock("temp_stairs",
            properties -> new StairsBlock(ModBlocks.TEMP_BLOCK.getDefaultState()
                    ,properties.strength(2f).requiresTool()));
    public static final Block TEMP_SLAB = registerBlock("temp_slab",
            properties -> new SlabBlock(properties.strength(2f).requiresTool()));
    public static final Block TEMP_BUTTON = registerBlock("temp_button",
            properties -> new ButtonBlock(BlockSetType.IRON, 20, properties.strength(2f).requiresTool().noCollision()));
    public static final Block TEMP_PRESSURE_PLATE = registerBlock("temp_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties.strength(2f).requiresTool()));
    public static final Block TEMP_FENCE = registerBlock("temp_fence",
            properties -> new FenceBlock(properties.strength(2f).requiresTool()));
    public static final Block TEMP_FENCE_GATE = registerBlock("temp_fence_gate",
            properties -> new FenceGateBlock(WoodType.BAMBOO,properties.strength(2f).requiresTool()));
    public static final Block TEMP_WALL = registerBlock("temp_wall",
            properties -> new WallBlock(properties.strength(2f).requiresTool()));
    public static final Block TEMP_DOOR = registerBlock("temp_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.strength(2f).requiresTool().nonOpaque()));
    public static final Block TEMP_TRAP_DOOR = registerBlock("temp_trap_door",
            properties -> new TrapdoorBlock(BlockSetType.IRON, properties.strength(2f).requiresTool().nonOpaque()));


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(KottenparkMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(KottenparkMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(KottenparkMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KottenparkMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        KottenparkMod.LOGGER.info("Registering Mod Blocks for " + KottenparkMod.MOD_ID);

    }

}
