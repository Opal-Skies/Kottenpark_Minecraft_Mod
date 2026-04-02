package net.kottenpark_mod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.kottenpark_mod.block.ModBlocks;
import net.kottenpark_mod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.TEMP_BLOCK);
        addDrop(ModBlocks.MAGIC_BLOCK, multipleOreDrops(ModBlocks.MAGIC_BLOCK, ModItems.PIG_HEART, 1,4));

        addDrop(ModBlocks.TEMP_STAIRS);
        addDrop(ModBlocks.TEMP_SLAB, slabDrops(ModBlocks.TEMP_SLAB));

        addDrop(ModBlocks.TEMP_BUTTON);
        addDrop(ModBlocks.TEMP_PRESSURE_PLATE);

        addDrop(ModBlocks.TEMP_WALL);
        addDrop(ModBlocks.TEMP_FENCE);
        addDrop(ModBlocks.TEMP_FENCE_GATE);

        addDrop(ModBlocks.TEMP_DOOR, doorDrops(ModBlocks.TEMP_DOOR));
        addDrop(ModBlocks.TEMP_TRAP_DOOR);

    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
