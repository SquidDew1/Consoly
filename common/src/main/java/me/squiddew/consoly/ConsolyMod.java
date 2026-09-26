package me.squiddew.consoly;

import me.squiddew.consoly.console.ConsoleReader;

public class ConsolyMod {

    public static final String MOD_ID = "consoly";

    public static void onInitializeCommon() {
        ConsoleReader.read();
        ConsolyOptions.load();
    }
}