package com.simplekv.disk;

import com.simplekv.config.DatabaseDescriptor;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class BlockIndex {

    public static class BlockIndexBuilder {

        private Map<String, BlockMetaData> blockMetaDataMap;
        private String ssTablename;

        public BlockIndexBuilder() {}

        public BlockIndexBuilder ssTablename(String baseFilename) {
            this.ssTablename = baseFilename;
            return this;
        }

        public BlockIndexBuilder blockMetadataMap(Map<String, BlockMetaData> blockMetaDataMap) {
            this.blockMetaDataMap = blockMetaDataMap;
            return this;
        }

        public BlockIndex build() {
            return new BlockIndex(this);
        }
    }

    private final Map<String, BlockMetaData> blockMetaDataMap;
    private String filePath;
    private String ssTablename;
    private final String filenamePrefix = "index-";
    private final String filenameExtension = ".db";
    private String finalFilename;

    public BlockIndex(BlockIndexBuilder blockIndexBuilder) {
        this.blockMetaDataMap = Objects.requireNonNullElseGet(blockIndexBuilder.blockMetaDataMap, TreeMap::new);
        this.ssTablename = blockIndexBuilder.ssTablename;
        this.finalFilename = DatabaseDescriptor.getConfig().data_directory +
                                        filenamePrefix +
                                        ssTablename +
                                        filenameExtension;
    }

    public BlockIndex() {
        blockMetaDataMap = new TreeMap<>();
    }

    public String getFinalFilename() {
        return this.finalFilename;
    }

    public Map<String, BlockMetaData> getBlockMetaDataMap() {
        return this.blockMetaDataMap;
    }

    public BlockMetaData getBlockMetaData(String key) {
        return this.blockMetaDataMap.get(key);
    }

    public void putBlockMetaData(String key, BlockMetaData blockMetaData) {
        this.blockMetaDataMap.put(key, blockMetaData);
    }
}
