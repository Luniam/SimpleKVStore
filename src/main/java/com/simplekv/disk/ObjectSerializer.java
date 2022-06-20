package com.simplekv.disk;

import java.io.IOException;
import java.io.Serializable;

public interface ObjectSerializer {

    void write() throws IOException;
}
