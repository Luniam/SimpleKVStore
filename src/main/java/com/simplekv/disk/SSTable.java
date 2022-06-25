package com.simplekv.disk;

import com.google.common.collect.Lists;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.db.MemTableMBean;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SSTable {

    public static class TableMetaData {

        private final String namePrefix = "data-";
        private final String tableName;
        private final String tableNameWithLocation;
        private final Long generationTimeStamp;

        public TableMetaData() {
            String dataFolder = DatabaseDescriptor.getConfig().data_directory;
            File folder = new File(dataFolder);
            if(!folder.exists()) folder.mkdir();
            generationTimeStamp = System.currentTimeMillis();
            tableName = namePrefix + generationTimeStamp;
            tableNameWithLocation = dataFolder + namePrefix + generationTimeStamp;
        }

        public String getTableName() {
            return tableName;
        }

        public Long getGenerationTimeStamp() {
            return generationTimeStamp;
        }

        public String getNamePrefix() {
            return namePrefix;
        }

        public String getTableFileName() {
            String tableFileExtension = ".db";
            return tableNameWithLocation + tableFileExtension;
        }
    }

    private final TableMetaData tableMetaData;
    private BlockIndex blockIndex;
    private FileManager fileManager;
    public static final int keysInEachBlock = 128;

    public SSTable() {
        tableMetaData = new TableMetaData();
        blockIndex = new BlockIndex.BlockIndexBuilder().ssTablename(tableMetaData.getTableName()).build();
    }

    public TableMetaData getTableMetaData() {
        return tableMetaData;
    }

    public BlockIndex getBlockIndex() {
        return blockIndex;
    }

    public static ValueRecord getValueRecordFromPosition(String ssTableName, KeyRecord key, BlockMetaData blockMetaData) {
        try {
            FileReader fileReader = FileManager.getFileReader(ssTableName);
            AbstractSSTableTemplate ssTableTemplate = SSTableTemplateFactory.getDefaultSSTableTemplate();
            return ssTableTemplate.getValueRecordFromPosition(fileReader, key, blockMetaData);
        } catch (IOException ioException) {
            return null;
        }
    }

    public void proceedToCreateSSTable(MemTableMBean memTable) throws IOException {
        FileWriter dataFileWriter = FileManager.getFileWriter(tableMetaData.getTableFileName());
        FileWriter indexFileWriter = FileManager.getFileWriter(this.blockIndex.getFinalFilename());
        AbstractSSTableTemplate tableTemplate = SSTableTemplateFactory.getDefaultSSTableTemplate();
        List<List<DataRecord>> chunkedData = splitMap(memTable.getMemData());
        long previousIndex = 0;
        for(List<DataRecord> dataRecordList : chunkedData) {
            long index = tableTemplate.dumpDataBlockAndGetIndex(dataFileWriter, tableMetaData, dataRecordList);
            BlockMetaData blockMetaData = new BlockMetaData();
            blockMetaData.key = dataRecordList.get(0).getKey().getKey();
            blockMetaData.offset = previousIndex;
            previousIndex = index;
            blockIndex.putBlockMetaData(blockMetaData.key, blockMetaData);
        }
        tableTemplate.dumpBlockIndex(indexFileWriter, blockIndex);
        dataFileWriter.closeWriter();
        indexFileWriter.closeWriter();
    }

    private List<List<DataRecord>> splitMap(Map<KeyRecord, ValueRecord> map) {
        List<DataRecord> dataRecordList = map.entrySet()
                                            .stream()
                                            .map(e -> new DataRecord(e.getKey(), e.getValue()))
                                            .toList();
        return Lists.partition(dataRecordList, keysInEachBlock);
    }

}
