package com.simplekv.disk;

import java.io.IOException;

public interface FileWriter {


    public void appendBytes(byte[] bytes) throws IOException;
    public void writeBytes(byte[] bytes, Long position) throws IOException;
}
