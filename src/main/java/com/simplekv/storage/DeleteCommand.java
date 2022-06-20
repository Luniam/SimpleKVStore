package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class DeleteCommand extends Command {

    public DeleteCommand(DataRecord dataRecord) {
        super(dataRecord);
        this.command = CommandType.DELETE;
    }
}
