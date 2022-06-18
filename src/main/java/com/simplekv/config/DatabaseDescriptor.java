package com.simplekv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class DatabaseDescriptor {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseDescriptor.class);

    private static final String configFilename = "conf/simplekv.yaml";
    private static final Class<?> defaultLoader = YamlConfigurationLoader.class;

    private static Config config;

    public static void daemonInitialization() {
        daemonInitialization(DatabaseDescriptor::loadConfig);
    }

    private static void daemonInitialization(Supplier<Config> configSupplier) {
        config = configSupplier.get();
    }

    public static Config loadConfig() {
        Config config;
        try {
            logger.info("Loading configuration file " + configFilename);
            ConfigurationLoader configurationLoader =
                    (ConfigurationLoader) defaultLoader.getConstructor(String.class)
                            .newInstance(configFilename);
            config = configurationLoader.loadConfig();
            logger.debug("Config loaded successfully");
        } catch (IOException |
                NoSuchMethodException |
                 InvocationTargetException |
                 InstantiationException |
                 IllegalAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
        return config;
    }
}
