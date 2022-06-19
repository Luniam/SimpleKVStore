package com.simplekv.db;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class IndexBloomFilter implements Serializable {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BloomFilter<String> bloomFilter;
    private String ssTableName;
    private String indexFilename;

    static class IndexBloomFilterBuilder {

        private IndexBloomFilter bloomFilter;

        public IndexBloomFilterBuilder() {
            this.bloomFilter = new IndexBloomFilter();
        }

        public IndexBloomFilter memTable(MemTableMBean memTable) {
            int expectedInsertions = Math.toIntExact(memTable.getKeyCount()/2);
            this.bloomFilter.setFilter(BloomFilter.create(
                    Funnels.stringFunnel(StandardCharsets.UTF_8),
                    expectedInsertions,
                    0.01));
            for(Map.Entry<KeyRecord, ValueRecord> entry : memTable.getMemData().entrySet()) {
                this.bloomFilter.getFilter().put(entry.getKey().getKey());
            }
            return this.bloomFilter;
        }

        public IndexBloomFilter sSTableName(String ssTableName) {
            this.bloomFilter.ssTableName = ssTableName;
            return this.bloomFilter;
        }

        public IndexBloomFilter indexFileName(String indexFilename) {
            this.bloomFilter.indexFilename = indexFilename;
            return this.bloomFilter;
        }

        public IndexBloomFilter build() {
            return new IndexBloomFilter(this);
        }
    }

    public IndexBloomFilter() {}

    public IndexBloomFilter(IndexBloomFilterBuilder indexBloomFilterBuilder) {
        this.bloomFilter = indexBloomFilterBuilder.bloomFilter.bloomFilter;
        this.ssTableName = indexBloomFilterBuilder.bloomFilter.ssTableName;
        this.indexFilename = indexBloomFilterBuilder.bloomFilter.indexFilename;
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
}
