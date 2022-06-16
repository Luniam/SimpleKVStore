package com.simplekv.disk;

import com.google.common.collect.Lists;
import com.simplekv.db.MemTableMBean;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SSTable {


    ReentrantLock lock = new ReentrantLock();

    protected static class TableMetaData {
        private String namePrefix = "data-";
        private String tableFileExtension = ".db";
        private String tableName;
        private Long generationTimeStamp;

        TableMetaData() {
            generationTimeStamp = System.currentTimeMillis();
            tableName = namePrefix + generationTimeStamp;
        }

        public Long getGenerationTimeStamp() {
            return generationTimeStamp;
        }

        public String getNamePrefix() {
            return namePrefix;
        }

        public String getTableName() {
            return tableName;
        }

        public String getTableFileName() {
            return tableName + tableFileExtension;
        }
    }

    protected static class BlockMetaData {

        public long position;
        public long size;
    }

    private TableMetaData tableMetaData;
    private BlockMetaData blockMetaData;
    private FileManager fileManager;

    public SSTable() {
        tableMetaData = new TableMetaData();
    }

    public void proceedToCreateSSTable(MemTableMBean memTable) throws IOException {
        TableMetaData tableMetaData = new TableMetaData();
        FileWriter fileWriter = FileManager.getFileWriter(tableMetaData.getTableFileName());
        AbstractSSTableTemplate tableTemplate = SSTableTemplateManager.chooseDefaultSSTableTemplate();
        List<List<DataRecord>> chunkedData = splitMap(memTable.getMemData());
        for(List<DataRecord> dataRecordList : chunkedData) {
            tableTemplate.dumpBlock(fileWriter, tableMetaData, dataRecordList);
        }
    }

    private List<List<DataRecord>> splitMap(Map<KeyRecord, ValueRecord> map) {
        List<DataRecord> dataRecordList = map.entrySet()
                                            .stream()
                                            .map(e -> new DataRecord(e.getKey(), e.getValue()))
                                            .toList();
        return Lists.partition(dataRecordList, 128);
    }

}
