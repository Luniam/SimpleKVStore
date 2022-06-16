package com.simplekv.disk;

import com.simplekv.utils.DataRecord;

import java.io.IOException;
import java.util.List;

public abstract class AbstractSSTableTemplate {

    protected int keyHeaderSizeInBytes = 4;
    protected int timestampSizeInBytes = 8;
    protected int isTombstoneMarkerSizeInBytes = 1;
    protected int dataHeaderSizeInBytes = 4;

    public void dumpBlock(FileWriter fileWriter,
                          SSTable.TableMetaData tableMetaData,
                          List<DataRecord> dataRecordList) throws IOException {
        byte[] blockData = getBlockData(dataRecordList);
        fileWriter.appendBytes(blockData);
    }

    protected abstract byte[] getBlockData(List<DataRecord> dataRecordList) throws IOException;
}
