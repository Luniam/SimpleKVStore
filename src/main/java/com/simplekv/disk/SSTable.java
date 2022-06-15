package com.simplekv.disk;

import com.simplekv.db.MemTableMBean;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.IOException;
import java.util.Map;

public class SSTable {



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

    private void dumpBlockIndex() {

    }

    public void dumpMemTableToDisk(MemTableMBean memTable) throws IOException {
        TableMetaData tableMetaData = new TableMetaData();
        FileWriter fileWriter = FileManager.getFileWriter(tableMetaData.getTableFileName());
        AbstractSSTableTemplate tableTemplate = SSTableTemplateManager.chooseDefaultSSTableTemplate();
        for(Map.Entry<KeyRecord, ValueRecord> entry : memTable.getMemData().entrySet()) {
            tableTemplate.appendToSSTable(fileWriter, tableMetaData, new DataRecord(entry.getKey(), entry.getValue()));
        }
    }

}
