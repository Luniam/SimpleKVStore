package com.simplekv.db;

import com.simplekv.disk.SSTable;

import java.util.Map;
import java.util.TreeMap;

public class IndexManager {

    private static final Map<String, IndexBloomFilter>
            indexFileNameToBloomFilter = IndexManager.loadIndexBloomFilterMapInstance();
    private static final Map<String, SSTable>
            indexFileNameToSSTable = IndexManager.loadIndexFilenameToSSTableMapInstance();

    public static Map<String, IndexBloomFilter> loadIndexBloomFilterMapInstance() {
        return new TreeMap<>();
    }

    public static Map<String, SSTable> loadIndexFilenameToSSTableMapInstance() {
        return new TreeMap<>();
    }

    public static void putSSTableBloomFilter(String indexFileName, IndexBloomFilter bloomFilter) {
        indexFileNameToBloomFilter.put(indexFileName, bloomFilter);
    }
}
