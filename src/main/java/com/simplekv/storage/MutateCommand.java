package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class PutCommand extends Command {

    public PutCommand(DataRecord dataRecord) {
        super(dataRecord);
        this.command = CommandType.PUT;
    }
}
