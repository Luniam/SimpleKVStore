package com.simplekv.disk;

import java.io.IOException;

public interface FileReader {

    byte readByte() throws IOException;
    byte readByte(long position) throws IOException;
    byte[] readBytes(long position, int len) throws IOException;
}
