package com.simplekv.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;

public class YamlConfigurationLoader extends ConfigurationLoader {


    private String filename;

    public YamlConfigurationLoader() {}
    public YamlConfigurationLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public Config loadConfig() throws IOException {
        InputStream inputStream = new FileInputStream(filename);
        Yaml yaml = new Yaml(new Constructor(Config.class));
        Config config = yaml.load(inputStream);
        inputStream.close();
        return config;
    }
}
