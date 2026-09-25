package me.squiddew.consoly.services;

import java.util.ServiceLoader;

public class Services {

    public static <T> T load(Class<T> clazz) {

        return ServiceLoader.load(clazz, Services.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}
