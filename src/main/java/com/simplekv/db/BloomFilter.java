package com.simplekv.db;

import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.io.Serializable;

public class BloomFilter<T> implements Serializable {

    class SampleFunnel implements Funnel<T> {
        @Override
        public void funnel(T t, PrimitiveSink primitiveSink) {

        }
    }

    private BloomFilter<T> bloomFilter;
    private String ssTableName;

    public BloomFilter() {

    }
}
