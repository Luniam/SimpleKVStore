package com.simplekv.db;

import com.simplekv.disk.FileManager;
import com.simplekv.disk.ObjectDeSerializer;
import com.simplekv.disk.SSTable;
import com.simplekv.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IndexManager {

    private static Logger logger = LoggerFactory.getLogger(IndexManager.class);
    private static Map<String, IndexBloomFilter> indexFileNameToBloomFilter;
    private static Map<String, String> indexFileNameToSSTableName;

    public static void loadIndexBloomFilters() {
        indexFileNameToBloomFilter = new TreeMap<>();
        indexFileNameToSSTableName = new HashMap<>();
        File[] allBloomFilterFiles = FileManager.getFilesWithPrefix(Constants.dataDirectory, IndexBloomFilter.getFilenamePrefix());
        for(File file : allBloomFilterFiles) {
            try {
                String filename = file.getAbsolutePath();
                logger.debug("Loading bloom filter " + filename);
                ObjectDeSerializer bloomFilterDeSerializer = FileManager.getObjectDeSerializer(filename);
                IndexBloomFilter indexBloomFilter = (IndexBloomFilter) bloomFilterDeSerializer.read();
                indexFileNameToBloomFilter.put(indexBloomFilter.getIndexFilename(), indexBloomFilter);
                indexFileNameToSSTableName.put(indexBloomFilter.getIndexFilename(), indexBloomFilter.getSsTableFileName());
                logger.debug(filename + " adding done");
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            } catch (ClassNotFoundException e) {
                logger.error("IndexBloomFilter class not found");
            }
        }
    }

    public static void addBloomFilterInMemory(String indexFileName, String ssTableName, IndexBloomFilter bloomFilter) {
        indexFileNameToBloomFilter.put(indexFileName, bloomFilter);
        indexFileNameToSSTableName.put(indexFileName, ssTableName);
    }
}
