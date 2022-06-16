package com.simplekv.db;

import com.simplekv.disk.SSTable;
import com.simplekv.utils.DataRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MemTableManager {

    private static Logger logger = LoggerFactory.getLogger(MemTableManager.class);
    private static TreeMapMemTable memTable = TreeMapMemTable.instance();

    public static void putData(DataRecord dataRecord) {
        memTable.putDataRecord(dataRecord);
    }

    public static void flushMemTable() {
        SSTable ssTable = new SSTable();
        try {
            ssTable.proceedToCreateSSTable(memTable);
        } catch (IOException ioException) {
            logger.error(ioException.toString());
        }
    }
}
