package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class MutateCommand extends Command {

    public MutateCommand(DataRecord dataRecord, CommandType commandType) {
        super(dataRecord);
        this.command = commandType;
    }
    public MutateCommand(DataRecord dataRecord) {
        super(dataRecord);
        this.command = CommandType.PUT;
    }
}
