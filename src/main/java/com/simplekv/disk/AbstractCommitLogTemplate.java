package com.simplekv.disk;

import com.simplekv.storage.Command;
import com.simplekv.storage.MutateCommand;

import java.io.IOException;

public abstract class AbstractCommitLogTemplate {

    protected int timestampSizeInBytes = 8;
    protected int dataHeaderSizeInBytes = 4;
    protected int keyHeaderSizeInBytes = 4;

    public void appendToCommitLog(FileWriter fileWriter, Command mutateCommand) throws IOException {
         byte[] byteData = getBlockData(mutateCommand);
         fileWriter.appendBytes(byteData);
    }

    public abstract byte[] getBlockData(Command mutateCommand) throws IOException;
}
