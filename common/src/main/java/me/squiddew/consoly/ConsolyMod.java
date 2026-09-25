package me.squiddew.consoly;

public class ConsolyMod {

    public static final String MOD_ID = "consoly";

    public static void onInitializeCommon() {
        ConsoleReader.read();
        ConsolyOptions.load();
    }
}