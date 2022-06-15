package com.simplekv.utils;

import java.io.Serializable;

public class ValueRecord implements Serializable {
    private boolean isTombStone;
    private int dataSizeInBytes;
    private byte[] data;
    private byte[] checkSum;
}
