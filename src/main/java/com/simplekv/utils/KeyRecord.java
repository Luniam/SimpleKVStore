package com.simplekv.utils;

import java.io.Serializable;

public class KeyRecord implements Serializable {

    private int keySizeInBytes;
    private String key;

    @Override
    public boolean equals(Object that) {
        return (that instanceof KeyRecord) &&
                this.key.equals(((KeyRecord)that).key);
    }
}
