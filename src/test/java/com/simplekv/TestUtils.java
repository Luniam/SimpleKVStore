package com.simplekv;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class TestUtils {

    public static final String testDataDirectory = "test-data/";

    @Rule
    public static final TemporaryFolder tempFolder = new TemporaryFolder();
}
