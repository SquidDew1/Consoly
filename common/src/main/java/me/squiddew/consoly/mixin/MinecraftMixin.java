package me.squiddew.consoly.mixin;

import me.squiddew.consoly.ConsoleWindow;
import me.squiddew.consoly.ConsolyOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "stop", at = @At("HEAD"))
    private void consoly$close(CallbackInfo ci){
        ConsoleWindow.close();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void consoly$createWindow(GameConfig gameConfig, CallbackInfo ci){
        ConsoleWindow.createWindow(
                "Console",
                800,
                700,
                true,
                "/assets/consoly/icon.png",
                ConsolyOptions.dark ? 64 : 225);
    }
}
