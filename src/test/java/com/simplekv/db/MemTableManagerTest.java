package com.simplekv.db;

import org.junit.Assert;
import org.junit.Test;

public class MemTableManagerTest {

    @Test
    public void testLoadMemTable() {
        MemTableManager.loadMemTable();
        MemTableMBean memTable = MemTableManager.getMemTable();
        Assert.assertNotNull(memTable);
    }
}
