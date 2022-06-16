package com.simplekv.disk;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * SSTable format for each block
 * Each block will have 128 keys
 * |...4 byte key size...|...key...|...8 byte timestamp...|...1 byte tombstone...|...4 byte data size...|...data...|
 */
public class SSTableTemplate extends AbstractSSTableTemplate {

    protected int keyHeaderSizeInBytes = 4;
    protected int timestampSizeInBytes = 8;
    protected int isTombstoneMarkerSizeInBytes = 1;
    protected int dataHeaderSizeInBytes = 4;
    @Override
    protected byte[] getBlockData(List<DataRecord> dataRecordList) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        long currentTimeStamp = System.currentTimeMillis();
        for(DataRecord dataRecord : dataRecordList) {
            KeyRecord key = dataRecord.getKey();
            ValueRecord value = dataRecord.getValue();
            //writing the key header and data
            byte[] keyHeader = new byte[this.keyHeaderSizeInBytes];
            ByteBuffer.allocate(this.keyHeaderSizeInBytes).putInt(key.getKeySizeInBytes()).get(keyHeader);
            byteBuilder.write(keyHeader);
            byteBuilder.write(key.getKey().getBytes());
            //writing timestamp
            byte[] timestamp = new byte[this.timestampSizeInBytes];
            ByteBuffer.allocate(this.timestampSizeInBytes).putLong(currentTimeStamp).get(timestamp);
            byteBuilder.write(timestamp);
            byte isTombStone = value.isTombStone()? (byte) 0 : 1;
            byteBuilder.write(isTombStone);
            byte[] dataHeader = new byte[this.dataHeaderSizeInBytes];
            ByteBuffer.allocate(this.dataHeaderSizeInBytes).putInt(value.getDataSizeInBytes()).get(dataHeader);
            byteBuilder.write(dataHeader);
            byteBuilder.write(value.getData());
        }
        return byteBuilder.toByteArray();
    }
}
