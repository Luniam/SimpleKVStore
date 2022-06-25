package com.simplekv.utils;

public interface Hash {

    int hash(byte[] data, int length, int seed);
}
