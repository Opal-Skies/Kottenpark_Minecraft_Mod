package net.kottenpark_mod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.block.entity.custom.WhiteboardBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<WhiteboardBlockEntity> WHITEBOARD_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(KottenparkMod.MOD_ID, "whiteboard_block_entity"),
                    FabricBlockEntityTypeBuilder.create(WhiteboardBlockEntity::new, ModBlocks.WHITEBOARD).build());

    public static void registerModBlockEntities() {
        KottenparkMod.LOGGER.info("Registering Block Entities for " + KottenparkMod.MOD_ID);
    }
}
