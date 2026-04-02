package net.kottenpark_mod.component;

import net.kottenpark_mod.KottenparkMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector2d;

import java.util.Vector;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Vec3d> CLICK_COORDINATE = register("coordinates", builder -> builder.codec(Vec3d.CODEC));
    public static final ComponentType<Vec3d> PREF_CLICK_COORDINATE = register("coordinates", builder -> builder.codec(Vec3d.CODEC));


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(KottenparkMod.MOD_ID, name),
                builderOperator.apply(new ComponentType.Builder<>()).build());
    }
    public static void registerDataComponentTypes() {
        KottenparkMod.LOGGER.info("Registering data component types.");
    }
}
