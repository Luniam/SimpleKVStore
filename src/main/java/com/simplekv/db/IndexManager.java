package com.simplekv.db;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.disk.*;
import com.simplekv.utils.KeyRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class IndexManager {

    private static Logger logger = LoggerFactory.getLogger(IndexManager.class);
    public static Map<String, IndexBloomFilter> indexFileNameToBloomFilter;
    public static Map<String, String> indexFileNameToSSTableName;
    public static Map<String, BlockIndex> indexFileNameToBlockIndexMap;
    private static ReentrantLock lock = new ReentrantLock();

    public static void loadIndicesAndBloomFilters() {
        if(indexFileNameToBloomFilter == null) {
            try {
                lock.lock();
                if(indexFileNameToBloomFilter == null)
                    instantiateBloomFiltersAndIndicesFromDisk();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void instantiateBloomFiltersAndIndicesFromDisk() {
        indexFileNameToBloomFilter = new TreeMap<>(Comparator.reverseOrder());
        indexFileNameToSSTableName = new HashMap<>();
        indexFileNameToBlockIndexMap = new TreeMap<>(Comparator.reverseOrder());
        Config config = DatabaseDescriptor.getConfig();
        File[] allBloomFilterFiles = FileManager.getFilesWithPrefix(config.data_directory, IndexBloomFilter.getFilenamePrefix());
        if(allBloomFilterFiles == null || allBloomFilterFiles.length == 0) return;
        AbstractSSTableTemplate ssTableTemplate = SSTableTemplateFactory.getDefaultSSTableTemplate();
        for(File file : allBloomFilterFiles) {
            try {
                String filename = file.getAbsolutePath();
                logger.debug("Loading bloom filter " + filename);
                ObjectDeSerializer bloomFilterDeSerializer = FileManager.getObjectDeSerializer(filename);
                IndexBloomFilter indexBloomFilter = (IndexBloomFilter) bloomFilterDeSerializer.read();
                String indexFilename = indexBloomFilter.getIndexFilename();
                indexFileNameToBloomFilter.put(indexFilename, indexBloomFilter);
                indexFileNameToSSTableName.put(indexFilename, indexBloomFilter.getSsTableFileName());
                //load the index file in memory
                FileReader indexFileReader = FileManager.getFileReader(indexFilename);
                BlockIndex blockIndex = ssTableTemplate.getBlockIndexFromIndexFile(indexFileReader, indexBloomFilter.getSsTableName());
                indexFileNameToBlockIndexMap.put(indexFilename, blockIndex);
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

    public static void addBlockIndexInMemory(String indexFileName, BlockIndex blockIndex) {
        indexFileNameToBlockIndexMap.put(indexFileName, blockIndex);
    }

    /**
     * Do a binary search on the block metadata to check the correct starting position in SSTable
     */
    public static BlockMetaData getSsTableBlockIndexFromIndexFile(KeyRecord keyRecord, String indexFileName) {
        BlockIndex blockIndex = indexFileNameToBlockIndexMap.get(indexFileName);
        if (blockIndex == null) return null;
        Set<String> allKeySet = blockIndex.getBlockMetaDataMap().keySet();
        String[] allKeys = new String[allKeySet.size()];
        allKeySet.toArray(allKeys);
        int start = 0;
        int end = allKeys.length-1;
        int mid = 0;
        while(start <= end) {
            mid = start + (end-start)/2;
            String key = allKeys[mid];
            int compareResult = key.compareTo(keyRecord.getKey());
            if(compareResult < 0) start = mid+1;
            else if(compareResult > 0) end = mid-1;
            else break;
        }
        if(mid >= allKeys.length) return null;
        if(allKeys[mid].compareTo(keyRecord.getKey()) > 0) mid--;
        String searchedKey = allKeys[mid];
        return blockIndex.getBlockMetaData(searchedKey);
    }
}
