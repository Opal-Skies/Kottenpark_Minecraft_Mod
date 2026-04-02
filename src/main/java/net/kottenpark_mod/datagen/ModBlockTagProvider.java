package net.kottenpark_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kottenpark_mod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.TEMP_BLOCK)
                .add(ModBlocks.MAGIC_BLOCK);


        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.TEMP_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.TEMP_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.TEMP_WALL);
    }
}
