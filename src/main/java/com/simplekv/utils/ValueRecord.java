package com.simplekv.utils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ValueRecord implements Serializable {
    private boolean isTombStone;
    private byte[] data;
    private byte[] checkSum;

    public ValueRecord() {}

    public ValueRecord(String data) {
        this.data = data.getBytes(StandardCharsets.UTF_8);
    }

    public boolean isTombStone() {
        return this.isTombStone;
    }

    public byte[] getData() {
        return data;
    }

    public int getDataSizeInBytes() {
        return data.length;
    }
}
