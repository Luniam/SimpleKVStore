package com.simplekv.disk;

import com.simplekv.storage.Command;
import com.simplekv.storage.MutateCommand;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

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
}
