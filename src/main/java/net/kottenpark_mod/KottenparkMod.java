package net.kottenpark_mod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.block.entity.ModBlockEntities;
import net.kottenpark_mod.entity.ModEntities;
import net.kottenpark_mod.entity.custom.DuckEntity;
import net.kottenpark_mod.item.ModItemGroups;
import net.kottenpark_mod.item.ModItems;
import net.kottenpark_mod.screen.ModScreenHandlers;
import net.kottenpark_mod.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class KottenparkMod implements ModInitializer {
	public static final String MOD_ID = "kottenpark_mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

		ModEntities.registerModEntities();

		ModBlockEntities.registerModBlockEntities();

		ModScreenHandlers.registerModScreenHandlers();

        ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.DUCK, DuckEntity.createAttributes());

	}
}