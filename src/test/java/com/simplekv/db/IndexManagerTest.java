package com.simplekv.db;

import com.simplekv.config.DatabaseDescriptor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class IndexManagerTest {

    @Before
    public void indexManagerTestInit() {
        DatabaseDescriptor.daemonInitialization();
    }

    @Test
    public void testLoadIndexBloomFilters() {
        IndexManager.loadIndicesAndBloomFilters();
    }
}
