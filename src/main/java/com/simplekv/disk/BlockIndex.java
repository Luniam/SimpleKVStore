package com.simplekv.disk;

import java.util.Map;
import java.util.TreeMap;

public class BlockIndex {

    public static class BlockIndexBuilder {

        public BlockIndexBuilder() {

        }
    }

    public static class BlockMetaData {
        public String key;
        public long offset;
    }

    private Map<String, BlockMetaData> blockMetaDataMap;
    private String filename;
    private final String filenamePrefix = "index-";
    private final String filenameExtension = ".db";

    public BlockIndex() {
        blockMetaDataMap = new TreeMap<>();
    }

    public void putBlockMetaData(String key, BlockMetaData blockMetaData) {
        this.blockMetaDataMap.put(key, blockMetaData);
    }
}
