package com.simplekv.disk;

import com.simplekv.utils.DataRecord;

public abstract class AbstractSSTableTemplate {

    protected int keySizeInBytes = 4;
    protected int timestampSizeInBytes = 8;
    protected int isTombstoneMarkerSizeInBytes = 1;

    public void appendToSSTable(FileWriter fileWriter,
                                SSTable.TableMetaData tableMetaData,
                                DataRecord dataRecord) {
        writeKeyHeader();
        writeKey();
        writeDataHeader();
        writeData();
    }

    protected abstract void writeData();

    protected abstract void writeDataHeader();

    protected abstract void writeKey();

    protected abstract void writeKeyHeader();
}
