package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class ReadCommand extends Command {

    public ReadCommand() {
        this.command = CommandType.READ;
    }
}
