package com.simplekv.disk;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface ObjectDeSerializer {

    Object read() throws IOException, ClassNotFoundException;
    List<Object> readAll() throws IOException, ClassNotFoundException;
}
