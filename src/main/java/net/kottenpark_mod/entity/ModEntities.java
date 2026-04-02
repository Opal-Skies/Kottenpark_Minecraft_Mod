package net.kottenpark_mod.entity;

import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.entity.custom.ChairEntity;
import net.kottenpark_mod.entity.custom.DuckEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    private static final RegistryKey<EntityType<?>> DUCK_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(KottenparkMod.MOD_ID, "duck"));
    private static final RegistryKey<EntityType<?>> CHAIR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(KottenparkMod.MOD_ID, "chair"));

    public static final EntityType<DuckEntity> DUCK = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(KottenparkMod.MOD_ID, "duck"),
            EntityType.Builder.create(DuckEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 1f).build(DUCK_KEY));


    public static final EntityType<ChairEntity> CHAIR = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(KottenparkMod.MOD_ID, "chair"),
            EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.65f).build(CHAIR_KEY));

    public static void registerModEntities() {
        KottenparkMod.LOGGER.info("Registering Mod Entities for " + KottenparkMod.MOD_ID);
    }
}
