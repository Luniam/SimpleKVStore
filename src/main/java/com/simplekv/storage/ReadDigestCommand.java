package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class ReadDigestCommand extends Command {

    public ReadDigestCommand() {
        this.command = CommandType.READDIGEST;
    }
}
