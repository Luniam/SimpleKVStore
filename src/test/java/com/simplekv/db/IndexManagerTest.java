package com.simplekv.db;

import org.junit.Test;

public class IndexManagerTest {

    @Test
    public void testLoadIndexBloomFilters() {
        IndexManager.loadIndicesAndBloomFilters();
    }
}
