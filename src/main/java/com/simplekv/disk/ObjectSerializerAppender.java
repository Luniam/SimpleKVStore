package com.simplekv.disk;

import java.io.IOException;
import java.io.Serializable;

public interface ObjectSerializerAppender {

    void append(Serializable serializable) throws IOException;
}
