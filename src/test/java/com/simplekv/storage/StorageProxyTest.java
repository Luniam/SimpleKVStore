package com.simplekv.storage;

import com.simplekv.db.MemTableManager;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class StorageProxyTest {

    @Test
    public void testPut() {
        Map<KeyRecord, ValueRecord> inMemoryMemTable = new TreeMap<>();
        for(int i = 0; i < 128*1024; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("start-working-out" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            if(!StorageProxy.put(dataRecord)) Assert.fail();
            inMemoryMemTable.put(key, value);
        }
    }
}
