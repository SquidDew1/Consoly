package me.squiddew.consoly.fabric;

import me.squiddew.consoly.ConsolyMod;
import net.fabricmc.api.ClientModInitializer;

public class ConsolyFabricMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ConsolyMod.init();
    }
}
