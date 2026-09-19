package me.squiddew.consoly.neoforge;

import me.squiddew.consoly.ConsolyMod;
import me.squiddew.consoly.screen.ConsolyScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ConsolyMod.MOD_ID, dist = Dist.CLIENT)
public class ConsolyForgeMod {

    public ConsolyForgeMod(ModContainer modContainer) {

        modContainer.registerExtensionPoint(IConfigScreenFactory.class,
                (_, _) -> new ConsolyScreen(Component.literal("Consoly"))
        );
        ConsolyMod.onInitializeCommon();
    }
}