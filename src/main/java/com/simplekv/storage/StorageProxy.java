package com.simplekv.storage;

import com.simplekv.utils.DataRecord;

public class StorageProxy {

    /**
     * This is the entrypoint towards writing a data to the database
     * At first data will be written to the commit log and then to the memtable
     * If the memtable reaches its fixed size then it will be flushed to disk
     * @param dataRecord instance of type DataRecord
     */
    public static void put(DataRecord dataRecord) {

    }
}
