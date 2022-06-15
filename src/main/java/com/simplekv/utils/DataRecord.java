package com.simplekv.utils;

import java.io.Serializable;

public class DataRecord implements Serializable {
    private KeyRecord key;
    private ValueRecord value;

    public DataRecord(KeyRecord key, ValueRecord value) {
        this.key = key;
        this.value = value;
    }

    public KeyRecord getKey() {
        return key;
    }
    public ValueRecord getValue() {
        return value;
    }
}
