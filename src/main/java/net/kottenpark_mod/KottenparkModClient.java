package net.kottenpark_mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.ColorHelper;


public class KottenparkModClient implements ClientModInitializer {
    public float totalTickProgress = 0F;
    @Override
    public void onInitializeClient() {
        ModRenderFactories.render();


        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.of(KottenparkMod.MOD_ID, "before_chat"), KottenparkModClient::render);
    }


    private static void render(DrawContext graphics, RenderTickCounter tickCounter) {
        int color = 0xFFFF0000; // Red
        int targetColor = 0xFF00FF00; // Green

        // You can use the Util.getMillis() function to get the current time in milliseconds.
        // Divide by 1000 to get seconds.
        double currentTime = Util.getMeasuringTimeMs() / 1000.0;

        // "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
        float lerpedAmount = Math.abs(((float) Math.sin((currentTime))));
        int lerpedColor = ColorHelper.lerp(lerpedAmount,color,targetColor);

        // Draw a square with the lerped color.
        // x1, x2, y1, y2, color
        graphics.fill(0, 0, 10, 10, lerpedColor);
    }
}
