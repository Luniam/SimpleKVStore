package com.simplekv.storage;

import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.db.IndexManager;
import com.simplekv.db.MemTableManager;
import com.simplekv.disk.CommitLogManager;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.DataReturnRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class StorageProxyTest {


    @Before
    public void storageProxyInit() {
        DatabaseDescriptor.daemonInitialization();
        MemTableManager.loadMemTable();
        CommitLogManager.startCommitLogAppenderWorker();
        IndexManager.loadIndicesAndBloomFilters();
    }
    @Test
    public void testAppend() {
        Map<KeyRecord, ValueRecord> dummyMemTable = new TreeMap<>();
        for(int i = 10000; i < 70000; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("start-working-out" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            MutateCommand putCommand = new MutateCommand(dataRecord);
            if(!StorageProxy.append(putCommand)) Assert.fail();
            dummyMemTable.put(key, value);
        }
    }

    @Test
    public void testGet() {
        testAppend();
        for(int i = 10000; i < 70000; i++) {
            KeyRecord keyRecord = new KeyRecord("Mahi" + i);
            DataReturnRecord returnRecord = StorageProxy.get(keyRecord, false);
            Assert.assertNotNull(returnRecord);
            String dataString = new String(returnRecord.getData(), StandardCharsets.UTF_8);
            Assert.assertEquals("start-working-out" + i, dataString);
            LoggerFactory.getLogger(StorageProxyTest.class).debug("Found Mahi" + i);
        }
    }
}
