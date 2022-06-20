package com.simplekv.disk;

import com.simplekv.utils.DataRecord;

import java.io.IOException;
import java.util.List;

public abstract class AbstractSSTableTemplate {

    protected int keyHeaderSizeInBytes = 4;
    protected int timestampSizeInBytes = 8;
    protected int isTombstoneMarkerSizeInBytes = 1;
    protected int dataHeaderSizeInBytes = 4;

    public long dumpDataBlockAndGetIndex(FileWriter fileWriter,
                                         SSTable.TableMetaData tableMetaData,
                                         List<DataRecord> dataRecordList) throws IOException {
        byte[] blockData = getBlockData(dataRecordList);
        return dumpDataAndReturnIndex(fileWriter, blockData);
    }

    public void dumpBlockIndex(FileWriter fileWriter, BlockIndex blockIndex) throws IOException {
        byte[] blockData = getBlockIndexData(blockIndex);
        dumpDataAndReturnIndex(fileWriter, blockData);
    }

    public long dumpDataAndReturnIndex(FileWriter fileWriter, byte[] bytes) throws IOException {
        fileWriter.appendBytes(bytes);
        return fileWriter.getFilePointer();
    }

    protected abstract byte[] getBlockData(List<DataRecord> dataRecordList) throws IOException;
    protected abstract byte[] getBlockIndexData(BlockIndex blockIndex) throws IOException;
}
