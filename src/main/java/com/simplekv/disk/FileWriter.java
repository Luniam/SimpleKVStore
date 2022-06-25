package com.simplekv.disk;

import java.io.IOException;

public interface FileWriter {


    void appendBytes(byte[] bytes) throws IOException;
    void writeBytes(byte[] bytes, long position) throws IOException;
    void closeWriter() throws IOException;
    long getFilePointer() throws IOException;
    void clearFile() throws IOException;
}
