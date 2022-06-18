package com.simplekv.config;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseDescriptorTest {

    @Test
    public void daemonInitialization() {
        try {
            DatabaseDescriptor.daemonInitialization();
            Config loadedConfig = DatabaseDescriptor.getConfig();
            Assert.assertNotNull(loadedConfig);
            Assert.assertEquals(loadedConfig.cluster_name, "Cluster 1"); // Default cluster name
        } catch (Exception exception) {
            Assert.fail();
        }
    }
}
