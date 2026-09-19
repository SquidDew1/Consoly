package me.squiddew.consoly.mixin;

import me.squiddew.consoly.Window;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "stop", at = @At("HEAD"))
    private void consoly$close(CallbackInfo ci){
        Window.close();
    }
}
