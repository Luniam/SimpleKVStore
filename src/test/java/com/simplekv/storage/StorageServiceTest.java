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

public class StorageServiceTest {


    @Before
    public void storageProxyInit() {
        DatabaseDescriptor.daemonInitialization();
        MemTableManager.loadMemTable();
        CommitLogManager.startCommitLogAppenderWorker();
        IndexManager.loadIndicesAndBloomFilters();
    }
    public void testAppend() {
        Map<KeyRecord, ValueRecord> dummyMemTable = new TreeMap<>();
        for(int i = 10000; i < 80000; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("start-working-out" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            MutateCommand putCommand = new MutateCommand(dataRecord);
            if(!StorageService.append(putCommand)) Assert.fail();
            dummyMemTable.put(key, value);
        }
    }

    @Test
    public void testGet() {
        testAppend();
        KeyRecord qKey = new KeyRecord("quote");
        String originalQuote = "Happiness can be found, even in the darkest of times, " +
                "if one only remembers to turn on the light";
        ValueRecord vR = new ValueRecord(originalQuote);
        DataRecord dr = new DataRecord(qKey, vR);
        MutateCommand mc = new MutateCommand(dr);
        StorageService.append(mc);

        DataReturnRecord returnQuote = StorageService.get(qKey, false);
        Assert.assertNotNull(returnQuote);
        String quote = new String(returnQuote.getData(), StandardCharsets.UTF_8);
        Assert.assertEquals(originalQuote, quote);
        for(int i = 10000; i < 80000; i++) {
            KeyRecord keyRecord = new KeyRecord("Mahi" + i);
            DataReturnRecord returnRecord = StorageService.get(keyRecord, false);
            Assert.assertNotNull(returnRecord);
            String dataString = new String(returnRecord.getData(), StandardCharsets.UTF_8);
            Assert.assertEquals("start-working-out" + i, dataString);
            LoggerFactory.getLogger(StorageServiceTest.class).debug("Found Mahi" + i);
        }
    }
}
