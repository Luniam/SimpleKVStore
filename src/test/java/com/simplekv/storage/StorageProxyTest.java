package com.simplekv.storage;

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

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class StorageProxyTest {


    @Before
    public void storageProxyInit() {
        MemTableManager.loadMemTable();
        CommitLogManager.startCommitLogAppenderWorker();
        IndexManager.loadIndicesAndBloomFilters();
    }
    @Test
    public void tesAppend() throws InterruptedException {
        Map<KeyRecord, ValueRecord> dummyMemTable = new TreeMap<>();
        for(int i = 10000; i < 99999; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("start-working-out" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            MutateCommand putCommand = new MutateCommand(dataRecord);
            if(!StorageProxy.append(putCommand)) Assert.fail();
            dummyMemTable.put(key, value);
        }
        Thread.sleep(180*1000);
    }

    @Test
    public void testGet() {
        KeyRecord keyRecord = new KeyRecord("Mahi99998");
        DataReturnRecord returnRecord = StorageProxy.get(keyRecord, false);
        String s = new String(returnRecord.getData(), StandardCharsets.UTF_8);
        Assert.assertNotNull(returnRecord);
    }
}
