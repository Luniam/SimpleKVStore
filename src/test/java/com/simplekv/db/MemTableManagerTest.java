package com.simplekv.db;

import com.simplekv.config.DatabaseDescriptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MemTableManagerTest {


    @Before
    public void memTableManagerTestInit() {
        DatabaseDescriptor.daemonInitialization();
    }

    @Test
    public void testLoadMemTable() {
        MemTableManager.loadMemTable();
        MemTableMBean memTable = MemTableManager.getMemTable();
        Assert.assertNotNull(memTable);
    }
}
