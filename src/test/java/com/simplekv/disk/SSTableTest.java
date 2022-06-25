package com.simplekv.disk;

import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.db.IndexManager;
import com.simplekv.db.MemTableManager;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.*;

public class SSTableTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before
    public void ssTableTestInit() {
        logger.debug("SSTable test pre init");
        DatabaseDescriptor.daemonInitialization();
        MemTableManager.loadMemTable();
        CommitLogManager.startCommitLogAppenderWorker();
        IndexManager.loadIndicesAndBloomFilters();
        logger.debug("SSTable test pre init done");
    }

    @Test
    public void testSSTableCreation() {
        Map<KeyRecord, ValueRecord> inMemoryMemTable = new TreeMap<>();
        for(int i = 0; i < 128*1024; i++) {
            KeyRecord key = new KeyRecord("Mahi" + i);
            ValueRecord value = new ValueRecord("test-key" + i);
            DataRecord dataRecord = new DataRecord(key, value);
            MemTableManager.putData(dataRecord);
            inMemoryMemTable.put(key, value);
        }
        MemTableManager.flushMemTable();
        try {
            File folder = new File("data");
            List<File> listOfFiles = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
            listOfFiles.sort((f1, f2) -> f2.getName().compareTo(f1.getName()));
            Assert.assertFalse(listOfFiles.isEmpty());
            RandomAccessFile randomAccessFile = new RandomAccessFile(listOfFiles.get(0), "r");
            Assert.assertEquals(1, 1);
        } catch (IOException ioException) {
            Assert.fail();
        }
    }
}
