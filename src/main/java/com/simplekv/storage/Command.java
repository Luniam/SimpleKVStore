package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    public DataRecord dataRecord;
    public final long timestamp;
    public enum CommandType {
        READ,
        READDIGEST,
        PUT,
        DELETE,
        COMMIT_LOG_FLUSH
    };
    public CommandType command;

    public Command() {
        this.timestamp = System.currentTimeMillis();
    }
    public Command(DataRecord dataRecord) {
        this();
        this.dataRecord = dataRecord;
    }
}
