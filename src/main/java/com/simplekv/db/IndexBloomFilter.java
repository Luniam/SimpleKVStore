package com.simplekv.db;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.simplekv.disk.FileManager;
import com.simplekv.disk.ObjectSerializer;
import com.simplekv.utils.Constants;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * The bloom filter will give us a probabilistic answer on
 * whether the key resides in a sstable or not
 * Each sstable will have one bloom filter
 */
public class IndexBloomFilter implements Serializable {

    static class IndexBloomFilterBuilder {

        private BloomFilter<String> bloomFilter;
        private String ssTableName;
        private String indexFilename;


        public IndexBloomFilterBuilder() {}

        public IndexBloomFilterBuilder memTable(MemTableMBean memTable) {
            int expectedInsertions = Math.toIntExact(memTable.getKeyCount()/2);
            this.bloomFilter = BloomFilter.create(
                    Funnels.stringFunnel(StandardCharsets.UTF_8),
                    expectedInsertions,
                    0.01);
            for(Map.Entry<KeyRecord, ValueRecord> entry : memTable.getMemData().entrySet()) {
                this.bloomFilter.put(entry.getKey().getKey());
            }
            return this;
        }

        public IndexBloomFilterBuilder ssTableName(String ssTableName) {
            this.ssTableName = ssTableName;
            return this;
        }

        public IndexBloomFilterBuilder indexFileName(String indexFilename) {
            this.indexFilename = indexFilename;
            return this;
        }

        public IndexBloomFilter build() {
            return new IndexBloomFilter(this);
        }
    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BloomFilter<String> bloomFilter;
    private String ssTableName;
    private String indexFilename;
    private final String filenamePrefix = "filter-";
    private final String filenameExtension = ".ser";

    public IndexBloomFilter() {}

    public IndexBloomFilter(IndexBloomFilterBuilder indexBloomFilterBuilder) {
        this.bloomFilter = indexBloomFilterBuilder.bloomFilter;
        this.ssTableName = indexBloomFilterBuilder.ssTableName;
        this.indexFilename = indexBloomFilterBuilder.indexFilename;
    }

    public void setFilter(BloomFilter<String> filter) {
        this.bloomFilter = filter;
    }

    public BloomFilter getFilter() {
        return this.bloomFilter;
    }

    public void put(KeyRecord key) {
        this.bloomFilter.put(key.getKey());
    }

    public boolean mightContain(KeyRecord key) {
        return this.bloomFilter.mightContain(key.getKey());
    }

    public void flushToDisk() {
        try {
            String objectFilenameWithLocation = Constants.dataDirectory +
                                                        this.filenamePrefix +
                                                        this.ssTableName +
                                                        filenameExtension;
            ObjectSerializer serializer = FileManager.getObjectSerializer(this, objectFilenameWithLocation);
            serializer.write();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
}
