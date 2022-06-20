package com.simplekv.disk;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * SSTable format for each block
 * Each block will have 128 keys
 * |...4 byte key size...|...key...|...8 byte timestamp...|...1 byte tombstone...|...4 byte data size...|...data...|
 *
 * SSTable index file structure
 * |...4 byte key size...|...key...|...8 byte block offset...|
 */
public class SSTableTemplate extends AbstractSSTableTemplate {

    protected final int keyHeaderSizeInBytes = 4;
    protected final int timestampSizeInBytes = 8;
    protected final int isTombstoneMarkerSizeInBytes = 1;
    protected final int dataHeaderSizeInBytes = 4;
    protected final int blockKeyHeaderSizeInBytes = 4;
    protected final int blockOffsetSizeInBytes = 8;

    @Override
    protected byte[] getBlockData(List<DataRecord> dataRecordList) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        long currentTimeStamp = System.currentTimeMillis();
        for(DataRecord dataRecord : dataRecordList) {
            KeyRecord key = dataRecord.getKey();
            ValueRecord value = dataRecord.getValue();
            //writing the key header and data
            byte[] keyHeader = ByteBuffer.allocate(this.keyHeaderSizeInBytes).putInt(key.getKeySizeInBytes()).array();
            byteBuilder.write(keyHeader);
            byteBuilder.write(key.getKey().getBytes());
            //writing timestamp
            byte[] timestamp = ByteBuffer.allocate(this.timestampSizeInBytes).putLong(currentTimeStamp).array();
            byteBuilder.write(timestamp);
            byte isTombStone = value.isTombStone()? (byte) 0 : 1;
            byteBuilder.write(isTombStone);
            byte[] dataHeader = ByteBuffer.allocate(this.dataHeaderSizeInBytes).putInt(value.getDataSizeInBytes()).array();
            byteBuilder.write(dataHeader);
            byteBuilder.write(value.getData());
        }
        return byteBuilder.toByteArray();
    }

    @Override
    protected byte[] getBlockIndexData(BlockIndex blockIndex) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        for(Map.Entry<String, BlockIndex.BlockMetaData> entry : blockIndex.getBlockMetaDataMap().entrySet()) {
            byte[] keyHeader = ByteBuffer.allocate(this.blockKeyHeaderSizeInBytes).putInt(entry.getKey().length()).array();
            byteBuilder.write(keyHeader);
            byteBuilder.write(entry.getKey().getBytes());
            byte[] blockOffset = ByteBuffer.allocate(this.blockOffsetSizeInBytes).putLong(entry.getValue().offset).array();
            byteBuilder.write(blockOffset);
        }
        return byteBuilder.toByteArray();
    }
}
