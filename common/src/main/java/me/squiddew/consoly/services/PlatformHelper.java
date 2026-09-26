package me.squiddew.consoly.services;

import java.nio.file.Path;

public interface PlatformHelper {

    static PlatformHelper getInstance() {
        return Services.load(PlatformHelper.class);
    }
    Path getConfigDir();
}
