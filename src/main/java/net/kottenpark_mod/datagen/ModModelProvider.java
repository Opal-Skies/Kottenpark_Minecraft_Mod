package net.kottenpark_mod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.block.custom.TempLampBlock;
import net.kottenpark_mod.item.ModItems;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);

        BlockStateModelGenerator.BlockTexturePool pinkGarnetPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TEMP_BLOCK);
            pinkGarnetPool.stairs(ModBlocks.TEMP_STAIRS);
            pinkGarnetPool.slab(ModBlocks.TEMP_SLAB);

            pinkGarnetPool.button(ModBlocks.TEMP_BUTTON);
            pinkGarnetPool.pressurePlate(ModBlocks.TEMP_PRESSURE_PLATE);

            pinkGarnetPool.fence(ModBlocks.TEMP_FENCE);
            pinkGarnetPool.fenceGate(ModBlocks.TEMP_FENCE_GATE);
            pinkGarnetPool.wall(ModBlocks.TEMP_WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.TEMP_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.TEMP_TRAP_DOOR);

        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.TEMP_LAMP_BLOCK, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.TEMP_LAMP_BLOCK, "_special", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.TEMP_LAMP_BLOCK)
                .with(BlockStateVariantMap.models(TempLampBlock.TEXTURE_STATE)
                        .register(0,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))
                        .register(1,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))
                        .register(2,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))
                        .register(3,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))
                        .register(4,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOffIdentifier)).build()))
                        .register(5,new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(lampOnIdentifier)).build()))
                ));

        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.CHAIR);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.TABLE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.INFORMATICA_COUCH);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PIG_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLIDER_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHITEBOARD_MARKER, Models.GENERATED);
    }
}
