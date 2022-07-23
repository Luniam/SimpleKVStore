package com.simplekv.utils;

import java.io.Serializable;

public class DataRecord implements Serializable {
    private final KeyRecord key;
    private final ValueRecord value;

    public DataRecord(KeyRecord key, ValueRecord value) {
        this.key = key;
        this.value = value;
    }

    public DataRecord(KeyRecord key) {
        this.key = key;
        this.value = new ValueRecord();
    }

    public KeyRecord getKey() {
        return key;
    }
    public ValueRecord getValue() {
        return value;
    }
}
