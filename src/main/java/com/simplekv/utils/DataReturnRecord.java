package com.simplekv.utils;

public class DataReturnRecord {

    private byte[] data;
    private Integer digest;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getDigest() {
        return digest;
    }

    public void setDigest(Integer digest) {
        this.digest = digest;
    }
}
