package com.simplekv.db;

import com.simplekv.disk.CommitLogManager;
import com.simplekv.disk.SSTable;
import com.simplekv.utils.DataRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MemTableManager {

    private static final Logger logger = LoggerFactory.getLogger(MemTableManager.class);
    private static TreeMapMemTable memTable;

    public static void loadMemTable() {
        memTable = TreeMapMemTable.loadInstance(CommitLogManager.getCommitLog());
    }

    public static MemTableMBean getMemTable() {
        return memTable;
    }

    public static void putData(DataRecord dataRecord) {
        memTable.putDataRecord(dataRecord);
    }

    public static long getKeyCount() {
        return memTable.getKeyCount();
    }

    public static boolean shouldFlushMemTable() {
        return getKeyCount() > 64000;
    }

    public static boolean flushMemTable() {
        logger.debug("Flushing memtable");
        SSTable ssTable = new SSTable();
        try {
            ssTable.proceedToCreateSSTable(memTable);
            IndexBloomFilter bloomFilter =
                    new IndexBloomFilter.IndexBloomFilterBuilder()
                                        .memTable(memTable)
                                        .ssTableName(ssTable.getTableMetaData().getTableName())
                                        .ssTableFileName(ssTable.getTableMetaData().getTableFileName())
                                        .indexFileName(ssTable.getBlockIndex().getFinalFilename())
                                        .build();
            bloomFilter.flushToDisk();
            memTable.refreshMemTable();
            return true;
        } catch (IOException ioException) {
            logger.error(ioException.toString());
            return false;
        }
    }
}
