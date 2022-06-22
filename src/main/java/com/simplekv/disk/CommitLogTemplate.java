package com.simplekv.disk;

import com.simplekv.storage.Command;
import com.simplekv.storage.MutateCommand;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CommitLogTemplate extends AbstractCommitLogTemplate {
    /**
     * Commit log structure
     * | 8 byte timestamp | 1 byte command type 1 for put, 0 for delete | 4 bytes key size | n bytes key
     * | 4 bytes value size | n bytes value
     */
    @Override
    public byte[] getBlockData(Command mutateCommand) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        DataRecord dataRecord = mutateCommand.dataRecord;
        byte commandType = (byte) (mutateCommand.command == Command.CommandType.PUT? 1 : 0);
        KeyRecord key = dataRecord.getKey();
        ValueRecord value = dataRecord.getValue();
        //writing timestamp
        byte[] timestamp = ByteBuffer.allocate(this.timestampSizeInBytes).putLong(mutateCommand.timestamp).array();
        byteBuilder.write(timestamp);
        //writing command type
        byteBuilder.write(commandType);
        //writing keyheader and key
        byte[] keyHeader = ByteBuffer.allocate(this.keyHeaderSizeInBytes).putInt(key.getKeySizeInBytes()).array();
        byteBuilder.write(keyHeader);
        byteBuilder.write(key.getKey().getBytes());
        //writing dataheader and data
        byte[] dataHeader = ByteBuffer.allocate(this.dataHeaderSizeInBytes).putInt(value.getDataSizeInBytes()).array();
        byteBuilder.write(dataHeader);
        byteBuilder.write(value.getData());

        return byteBuilder.toByteArray();
    }

    @Override
    public void populateDataCommandList(List<Command> dataCommandList, FileReader commitLogReader)
            throws IOException {
        long filePosition = 0;
        while (true) {
            try {
                //read 8 bytes timestamp
                byte[] timestampArray = commitLogReader.readBytes(filePosition, this.timestampSizeInBytes);
                filePosition+=this.timestampSizeInBytes;
                //read 1 byte command type
                long timestamp = ByteBuffer.wrap(timestampArray).getLong();
                byte commandType = commitLogReader.readByte();
                filePosition+=1;
                // read 4 bytes key size
                byte[] keyHeaderSizeArray = commitLogReader.readBytes(filePosition, this.keyHeaderSizeInBytes);
                filePosition+=this.keyHeaderSizeInBytes;
                int keySize = ByteBuffer.wrap(keyHeaderSizeArray).getInt();
                // read keySize bytes key
                byte[] keyArray;
                keyArray = commitLogReader.readBytes(filePosition, keySize);
                filePosition+=keySize;
                String key = new String(keyArray, StandardCharsets.UTF_8);
                // read 4 bytes data size
                byte[] dataHeaderSizeArray = commitLogReader.readBytes(filePosition, this.dataHeaderSizeInBytes);
                filePosition+=this.dataHeaderSizeInBytes;
                int dataSize = ByteBuffer.wrap(dataHeaderSizeArray).getInt();
                //read dataSize bytes data
                byte[] dataArray;
                dataArray = commitLogReader.readBytes(filePosition, dataSize);
                filePosition+=dataSize;
                //build the Command
                KeyRecord keyRecord = new KeyRecord(key);
                ValueRecord valueRecord = new ValueRecord(dataArray);
                valueRecord.setTombStone(commandType == 0);
                DataRecord dataRecord = new DataRecord(keyRecord, valueRecord);
                MutateCommand command = new MutateCommand(dataRecord, commandType == 0?
                        Command.CommandType.DELETE : Command.CommandType.PUT);
                dataCommandList.add(command);
            } catch (EOFException eofException) {
                break;
            }
        }
    }
}
