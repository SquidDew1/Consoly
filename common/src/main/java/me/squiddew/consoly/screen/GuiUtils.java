package me.squiddew.consoly.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GuiUtils {

    private static final Screen screen = Minecraft.getInstance().gui.screen();

    public static void drawBg(GuiGraphicsExtractor graphicsExtractor, int bgColor){
        if (screen != null){
            graphicsExtractor.fill(
                    0, 0,
                    screen.width, screen.height,
                    bgColor
            );
        }
    }
    public static void drawTitle(GuiGraphicsExtractor graphicsExtractor, int textColor, boolean dropShadow){
        if (screen != null) {
            graphicsExtractor.text(
                    Minecraft.getInstance().font,
                    Component.translatable("gui.consoly.screen.text.title"),
                    (screen.width / 2) - (Minecraft.getInstance().font.width( Component.translatable("gui.consoly.screen.text.title"))),
                    10,
                    textColor,
                    dropShadow
            );
        }
    }
}
