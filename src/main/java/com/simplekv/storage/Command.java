package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

import java.io.Serializable;

public abstract class Command implements Serializable {

    public DataRecord dataRecord;
    public enum CommandType {
        READ,
        READDIGEST,
        PUT,
        DELETE
    };
    CommandType command;

    public Command() {}
    public Command(DataRecord dataRecord) {
        this.dataRecord = dataRecord;
    }
}
