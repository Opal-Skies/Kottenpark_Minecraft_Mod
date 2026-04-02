package net.kottenpark_mod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.screen.custom.CustomScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CustomScreenHandler> CUSTOM_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(KottenparkMod.MOD_ID, "custom_screen_handler"),
                    new ExtendedScreenHandlerType<>(CustomScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void registerModScreenHandlers() {
        KottenparkMod.LOGGER.info("Registering Screen Handlers for " + KottenparkMod.MOD_ID);
    }

}


