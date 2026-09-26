package me.squiddew.consoly.screen;

import com.mojang.blaze3d.Blaze3D;
import me.squiddew.consoly.console.ConsoleWindow;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;
import me.squiddew.consoly.ConsolyOptions;

import java.net.URI;

public class ConsolyScreen extends Screen {

    private static final int y = 34;
    private static int page = 0;
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
        ).bounds(5, y, 70, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.tooltip.general")))
                .build());

        this.addRenderableWidget(Button.builder(
                Component.literal("Links"),
                _ -> {
                    page = 1;
                    this.rebuildWidgets();
                }
        ).bounds(5, y + 25, 70, 20)
                .tooltip(Tooltip.create(Component.literal("Links")))
                .build());

        switch (page){
            case 0 -> drawGeneral();
            case 1 -> drawLinks();
        }
    }

    public void drawLinks(){
        this.addRenderableWidget(Button.builder(
                Component.literal("Modrinth"),
                _ -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                        (modrinth) -> {
                            if (modrinth){
                                Blaze3D.openUri(URI.create("https://modrinth.com/project/consoly"));
                            }
                            this.minecraft.gui.setScreen(this);
                        },
                        URI.create("https://modrinth.com/project/consoly"),
                        true
                  ))
        ).bounds((this.width / 2) - (y / 2), y, 100, 20)
                .tooltip(Tooltip.create(Component.literal("Modrinth")))
                .build());

        this.addRenderableWidget(Button.builder(
                        Component.literal("GitHub"),
                        _ -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                                (modrinth) -> {
                                    if (modrinth){
                                        Blaze3D.openUri(URI.create("https://github.com/SquidDew1/Consoly"));
                                    }
                                    this.minecraft.gui.setScreen(this);
                                },
                                URI.create("https://github.com/SquidDew1/Consoly"),
                                true
                        ))
                ).bounds((this.width / 2) - (y / 2), y + 20, 100, 20)
                .tooltip(Tooltip.create(Component.literal("GitHub")))
                .build());

        this.addRenderableWidget(Button.builder(
                        Component.literal("Issues"),
                        _ -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                                (modrinth) -> {
                                    if (modrinth){
                                        Blaze3D.openUri(URI.create("https://github.com/SquidDew1/Consoly/issues"));
                                    }
                                    this.minecraft.gui.setScreen(this);
                                },
                                URI.create("https://github.com/SquidDew1/Consoly/issues"),
                                true
                        ))
                ).bounds((this.width / 2) - (y / 2), y + 20 + 20, 100, 20)
                .tooltip(Tooltip.create(Component.literal("Issues")))
                .build());

    }

    public void drawGeneral(){

        Component theme = Component.translatable(ConsolyOptions.dark
                ? "gui.consoly.screen.button.theme.dark"
                : "gui.consoly.screen.button.theme.light"
        );
        Component show = Component.translatable(ConsolyOptions.show
                ? "gui.consoly.screen.button.window.show"
                : "gui.consoly.screen.button.window.hide"
        );
        Component font = Component.translatable(ConsolyOptions.bold
                ? "gui.consoly.screen.button.font.bold"
                : "gui.consoly.screen.button.font.default"
        );
        Component style = Component.translatable(ConsolyOptions.style
                ? "gui.consoly.screen.button.style.nimbus"
                : "gui.consoly.screen.button.style.default"
        );

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.theme", theme),
                _ -> {
                    ConsolyOptions.dark = !ConsolyOptions.dark;
                    ConsolyOptions.save();
                    if (ConsolyOptions.dark){
                        ConsoleWindow.setTheme(64);
                    } else {
                        ConsoleWindow.setTheme(225);
                    }
                    this.rebuildWidgets();
                }
        ).bounds((this.width / 2) - (y / 2), y, 100, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.tooltip.theme")))
                .build());

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.window", show),
                _ -> {
                    ConsolyOptions.show = !ConsolyOptions.show;
                    ConsolyOptions.save();
                    ConsoleWindow.setWindowVisibility(ConsolyOptions.show);
                    this.rebuildWidgets();
                }
        ).bounds((this.width / 2) - (y / 2), y + 20, 100, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.window.tooltip")))
                .build());

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.font", font),
                _ -> {
                    ConsolyOptions.bold = !ConsolyOptions.bold;
                    ConsolyOptions.save();
                    ConsoleWindow.setFont(ConsolyOptions.bold);
                    this.rebuildWidgets();
                }
        ).bounds((this.width / 2) - (y / 2), y + 20 + 20, 100, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.font.tooltip")))
                .build());

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.consoly.screen.button.style", style),
                _ -> {
                    ConsolyOptions.style = !ConsolyOptions.style;
                    ConsolyOptions.save();
                    ConsoleWindow.setStyle(ConsolyOptions.style);
                    this.rebuildWidgets();
                }
        ).bounds((this.width / 2) - (y / 2), y + 20 + 20 + 20, 100, 20)
                .tooltip(Tooltip.create(Component.translatable("gui.consoly.screen.button.style.tooltip")))
                .build());
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphicsExtractor, int mouseX, int mouseY, float a) {
        ScreenUtils.drawBg(graphicsExtractor, 0x60000000);

        super.extractRenderState(graphicsExtractor, mouseX, mouseY, a);

        ScreenUtils.drawTitle(graphicsExtractor, 0xFFFFFFFF, true);
    }
}
