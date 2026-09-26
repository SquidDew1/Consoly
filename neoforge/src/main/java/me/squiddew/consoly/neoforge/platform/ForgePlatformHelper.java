package me.squiddew.consoly.neoforge.platform;

import me.squiddew.consoly.services.PlatformHelper;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements PlatformHelper {

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
