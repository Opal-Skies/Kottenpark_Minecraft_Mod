package net.kottenpark_mod;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.block.entity.ModBlockEntities;
import net.kottenpark_mod.block.entity.renderer.WhiteboardBlockRenderer;
import net.kottenpark_mod.entity.ModEntities;
import net.kottenpark_mod.entity.client.ChairRenderer;
import net.kottenpark_mod.screen.ModScreenHandlers;
import net.kottenpark_mod.screen.custom.CustomScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.EntityRendererFactories;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ModRenderFactories {
    public static void render(){

        BlockRenderLayerMap.putBlock(ModBlocks.TEMP_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.TEMP_TRAP_DOOR, BlockRenderLayer.CUTOUT);

        BlockEntityRendererFactories.register(ModBlockEntities.WHITEBOARD_BLOCK_ENTITY, WhiteboardBlockRenderer::new);

        EntityRendererFactories.register(ModEntities.DUCK, context -> new GeoEntityRenderer<>(context, ModEntities.DUCK));
        EntityRendererFactories.register(ModEntities.CHAIR, ChairRenderer::new);

        HandledScreens.register(ModScreenHandlers.CUSTOM_SCREEN_HANDLER, CustomScreen::new);
    }
}
