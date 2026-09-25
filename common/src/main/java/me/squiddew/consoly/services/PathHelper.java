package me.squiddew.consoly.services;

import java.nio.file.Path;

public interface PathHelper {

    static PathHelper getInstance() {
        return Services.load(PathHelper.class);
    }
    Path getConfigDir();
}
