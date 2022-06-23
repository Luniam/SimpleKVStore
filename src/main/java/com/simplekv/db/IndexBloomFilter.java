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
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * The bloom filter will give us a probabilistic answer on
 * whether the key resides in a sstable or not
 * Each sstable will have one bloom filter
 */
public class IndexBloomFilter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    static class IndexBloomFilterBuilder {

        private BloomFilter<String> bloomFilter;
        private String ssTableName;
        private String ssTableFileName;
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

        public IndexBloomFilterBuilder ssTableFileName(String ssTableFileName) {
            this.ssTableFileName = ssTableFileName;
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
    private String ssTableFileName;
    private String indexFilename;
    private static final String filenamePrefix = "filter-";
    private static final String filenameExtension = ".ser";

    public IndexBloomFilter() {}

    public IndexBloomFilter(IndexBloomFilterBuilder indexBloomFilterBuilder) {
        this.bloomFilter = indexBloomFilterBuilder.bloomFilter;
        this.ssTableName = indexBloomFilterBuilder.ssTableName;
        this.ssTableFileName = indexBloomFilterBuilder.ssTableFileName;
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

    public static String getFilenamePrefix() {
        return filenamePrefix;
    }

    public String getSsTableName() {
        return ssTableName;
    }

    public String getSsTableFileName() {
        return ssTableFileName;
    }

    public String getIndexFilename() {
        return indexFilename;
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
