package com.simplekv.disk;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * SSTable format for each block
 * Each block will have 128 keys
 * |...4 byte key size...|...key...|...8 byte timestamp...|...1 byte tombstone if yes then 0...|...4 byte data size...|...data...|
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
        for(Map.Entry<String, BlockMetaData> entry : blockIndex.getBlockMetaDataMap().entrySet()) {
            byte[] keyHeader = ByteBuffer.allocate(this.blockKeyHeaderSizeInBytes).putInt(entry.getKey().length()).array();
            byteBuilder.write(keyHeader);
            byteBuilder.write(entry.getKey().getBytes());
            byte[] blockOffset = ByteBuffer.allocate(this.blockOffsetSizeInBytes).putLong(entry.getValue().offset).array();
            byteBuilder.write(blockOffset);
        }
        return byteBuilder.toByteArray();
    }

    @Override
    public BlockIndex getBlockIndexFromIndexFile(FileReader indexFileReader, String ssTableName) throws IOException {
        BlockIndex blockIndex = new BlockIndex.BlockIndexBuilder()
                                                .ssTablename(ssTableName)
                                                .build();
        Map<String, BlockMetaData> blockMetaDataMap = blockIndex.getBlockMetaDataMap();
        long filePosition = 0;
        long totalLength = indexFileReader.getTotalBytes();
        while(filePosition < totalLength) {
            try {
                //getting the 4 byte key size
                byte[] headerSizeArray = indexFileReader.readBytes(filePosition, this.blockKeyHeaderSizeInBytes);
                filePosition+=this.blockKeyHeaderSizeInBytes;
                int headerSize = ByteBuffer.wrap(headerSizeArray).getInt();
                //getting the headerSize bytes key
                byte[] keyArray = indexFileReader.readBytes(filePosition, headerSize);
                filePosition+=headerSize;
                String key = new String(keyArray, StandardCharsets.UTF_8);
                //getting the 8 byte block offset
                byte[] blockOffsetArray = indexFileReader.readBytes(filePosition, this.blockOffsetSizeInBytes);
                filePosition+=this.blockOffsetSizeInBytes;
                long blockOffset = ByteBuffer.wrap(blockOffsetArray).getLong();

                BlockMetaData blockMetaData = new BlockMetaData();
                blockMetaData.key = key;
                blockMetaData.offset = blockOffset;
                blockMetaDataMap.put(key, blockMetaData);
            } catch (EOFException eofException) {
                break;
            }
        }
        return blockIndex;
    }

    @Override
    public ValueRecord getValueRecordFromPosition(FileReader ssTableFileReader, KeyRecord keyRecord, BlockMetaData blockMetaData) throws IOException {
        long filePosition = blockMetaData.offset;
        long totalLength = ssTableFileReader.getTotalBytes();
        for(int j = 0; j < SSTable.keysInEachBlock; j++) {
            //getting the 4 byte key size
            byte[] headerSizeArray = ssTableFileReader.readBytes(filePosition, this.blockKeyHeaderSizeInBytes);
            filePosition+=this.blockKeyHeaderSizeInBytes;
            int headerSize = ByteBuffer.wrap(headerSizeArray).getInt();
            //getting the key
            byte[] keyArray = ssTableFileReader.readBytes(filePosition, headerSize);
            filePosition+=headerSize;
            String key = new String(keyArray, StandardCharsets.UTF_8);
            //getting the 8 byte timestamp
            byte[] timestampArray = ssTableFileReader.readBytes(filePosition, this.timestampSizeInBytes);
            filePosition+=this.timestampSizeInBytes;
            long timestamp = ByteBuffer.wrap(timestampArray).getLong();
            //getting the tombstone
            byte tombstone = ssTableFileReader.readByte();
            filePosition+=1;
            //getting the data size
            byte[] dataSizeArray = ssTableFileReader.readBytes(filePosition, this.dataHeaderSizeInBytes);
            filePosition+=this.dataHeaderSizeInBytes;
            int dataSize = ByteBuffer.wrap(dataSizeArray).getInt();
            //getting the data
            byte[] dataArray = ssTableFileReader.readBytes(filePosition, dataSize);
            filePosition+=dataSize;
            if(key.equals(keyRecord.getKey()) && tombstone == 1)
                return new ValueRecord(dataArray);
        }
        return null;
    }
}
