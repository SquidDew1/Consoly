package me.squiddew.consoly;

public class ConsolyMod {

    public static final String MOD_ID = "consoly";

    public static void onInitializeCommon() {
        Window.create(
                "Consoly",
                800,
                700,
                true,
                "/assets/consoly/icon.png"
        );
    }
}