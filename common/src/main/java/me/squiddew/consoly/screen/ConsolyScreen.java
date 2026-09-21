package me.squiddew.consoly.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;
import me.squiddew.consoly.options.ConsolyOptions;

public class ConsolyScreen extends Screen {

    private int page = 0;
    public ConsolyScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.done"),
                _ -> super.onClose()
        ).bounds(this.width - 60 - 15, this.height - 20 - 15, 60, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.done")))
                .build());

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.general"),
                _ -> {
                    page = 0;
                    this.rebuildWidgets();
                }
        ).bounds(5, 34, 70, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.tooltip.general")))
                .build());

        switch (page){
            case 0 -> drawGeneral();
        }
    }

    public void drawGeneral(){

        Component theme = Component.translatable(ConsolyOptions.dark
                ? "gui.consoly.screen.button.theme.dark"
                : "gui.consoly.screen.button.theme.light"
        );

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.theme", theme),
                _ -> {
                    ConsolyOptions.dark = !ConsolyOptions.dark;
                    this.rebuildWidgets();
                }
        ).bounds((this.width / 2) - (36 / 2), 36, 70, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.tooltip.theme")))
                .build());
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY, float a) {
        GuiUtils.drawBg(graphicsExtractor, 0x60000000);

        super.extractRenderState(graphicsExtractor, mouseX, mouseY, a);

        GuiUtils.drawTitle(graphicsExtractor, 0xFFFFFFFF, true);
    }
}
