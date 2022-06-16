package com.simplekv.utils;

import java.io.Serializable;

public class KeyRecord implements Serializable {

    private String key;

    public KeyRecord() {}

    public KeyRecord(String key) {
        this.key = key;
    }

    public int getKeySizeInBytes() {
        return key.length();
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public boolean equals(Object that) {
        return (that instanceof KeyRecord) &&
                this.key.equals(((KeyRecord)that).key);
    }
}
