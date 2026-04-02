package net.kottenpark_mod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.item.custom.GliderItem;
import net.kottenpark_mod.item.custom.WhiteboardMarkerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item PIG_HEART = registerItem("pig_heart", Item::new);

    public static final Item WHITEBOARD_MARKER = registerItem("whiteboard_marker",
            settings -> new WhiteboardMarkerItem(settings.maxCount(1)));

    public static final Item GLIDER_ITEM = registerItem("glider",
            settings -> new GliderItem(settings.maxCount(1)));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(KottenparkMod.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(KottenparkMod.MOD_ID, name)))));
    }

    public static void registerModItems() {
        KottenparkMod.LOGGER.info("registering for" + KottenparkMod.MOD_ID);

    }
}
