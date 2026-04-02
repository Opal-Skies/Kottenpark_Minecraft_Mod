package net.kottenpark_mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup KOTTENPARK_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(KottenparkMod.MOD_ID, "kottenpark_item_group"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.PIG_HEART))
                    .displayName(Text.translatable("itemgroup.kottenpark_mod.kottenpark_item_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PIG_HEART);
                        entries.add(ModItems.WHITEBOARD_MARKER);
                        entries.add(ModItems.GLIDER_ITEM);

                        entries.add(ModBlocks.CHAIR);
                        entries.add(ModBlocks.TABLE);
                        entries.add(ModBlocks.WHITEBOARD);
                        entries.add(ModBlocks.INFORMATICA_COUCH);
                        entries.add(ModBlocks.MAGIC_BLOCK);
                        entries.add(ModBlocks.TEMP_LAMP_BLOCK);

                        entries.add(ModBlocks.TEMP_BLOCK);
                        entries.add(ModBlocks.TEMP_STAIRS);
                        entries.add(ModBlocks.TEMP_SLAB);

                        entries.add(ModBlocks.TEMP_BUTTON);
                        entries.add(ModBlocks.TEMP_PRESSURE_PLATE);

                        entries.add(ModBlocks.TEMP_FENCE);
                        entries.add(ModBlocks.TEMP_FENCE_GATE);
                        entries.add(ModBlocks.TEMP_WALL);

                        entries.add(ModBlocks.TEMP_DOOR);
                        entries.add(ModBlocks.TEMP_TRAP_DOOR);

                    }).build());

    public static void registerItemGroups() {
        KottenparkMod.LOGGER.info("Registering Item Groups for " + KottenparkMod.MOD_ID);
    }
}
