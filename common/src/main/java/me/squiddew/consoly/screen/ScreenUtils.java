package me.squiddew.consoly.screen;

import me.squiddew.consoly.ConsolyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ScreenUtils {

    public static void drawBg(GuiGraphicsExtractor graphicsExtractor, int bgColor){

        Screen screen = Minecraft.getInstance().gui.screen();
        if (screen != null){
            graphicsExtractor.fill(
                    0, 0,
                    screen.width, screen.height,
                    bgColor
            );
            graphicsExtractor.fill(
                    0, 30,
                    screen.width, 30 + 1,
                    0xFFFFFFFF
            );
            graphicsExtractor.blit(
                    RenderPipelines.GUI_TEXTURED,
                    Identifier.fromNamespaceAndPath(ConsolyMod.MOD_ID, "textures/gui/config-bg.png"),
                    0, 31,
                    0, 0,
                    100, screen.height - 31,
                    900, 1440,
                    900, 1440
            );
        }
    }
    public static void drawTitle(GuiGraphicsExtractor graphicsExtractor, int textColor, boolean dropShadow){

        Screen screen = Minecraft.getInstance().gui.screen();
        if (screen != null) {
            graphicsExtractor.text(
                        Minecraft.getInstance().font,
                        Component.literal("Consoly"),
                        (screen.width / 2) - (Minecraft.getInstance().font.width(Component.literal("Consoly")) / 2),
                        10,
                        textColor,
                        dropShadow
            );
        }
    }
}
