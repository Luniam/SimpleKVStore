package com.simplekv.config;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ConfigurationLoader {

    abstract Config loadConfig() throws IOException;
}
