package me.squiddew.consoly;

import me.squiddew.consoly.platform.Services;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ConsolyMod {

    private static final Logger LOGGER = LoggerFactory.getLogger("Consoly");
    public static final String MOD_ID = "consoly";

    public static void init() {
        if (Services.PLATFORM.isModLoaded(MOD_ID)){
            LOGGER.info("Consoly loaded on {}", Services.PLATFORM.getPlatformName());
        }
    }
}