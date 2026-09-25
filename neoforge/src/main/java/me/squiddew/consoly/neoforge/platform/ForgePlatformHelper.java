package me.squiddew.consoly.neoforge.platform;

import me.squiddew.consoly.services.PathHelper;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements PathHelper {

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }
}
