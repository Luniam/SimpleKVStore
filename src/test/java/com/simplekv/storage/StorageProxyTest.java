package com.simplekv.storage;

import com.simplekv.db.IndexManager;
import com.simplekv.db.MemTableManager;
import com.simplekv.disk.CommitLogManager;
import com.simplekv.service.SimpleKVDaemon;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class StorageProxyTest {


    @Before
    public void storageProxyInit() {
        CommitLogManager.startCommitLogAppenderWorker();
        IndexManager.loadIndexBloomFilters();
    }
    @Test
    public void testMutate() throws InterruptedException {
        MemTableManager.loadMemTable();
        Map<KeyRecord, ValueRecord> dummyMemTable = new TreeMap<>();
        for(int i = 10000; i < 99999; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("start-working-out" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            MutateCommand putCommand = new MutateCommand(dataRecord);
            if(!StorageProxy.mutate(putCommand)) Assert.fail();
            dummyMemTable.put(key, value);
        }
        Thread.sleep(8*1000);
    }
}
