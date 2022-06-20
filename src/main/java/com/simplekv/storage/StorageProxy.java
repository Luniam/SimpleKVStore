package com.simplekv.storage;

import com.simplekv.db.MemTableManager;
import com.simplekv.disk.CommitLogManager;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;

public class StorageProxy {

    /**
     * This is the entrypoint towards writing a data to the database
     * At first data will be written to the commit log and then to the memtable
     * If the memtable reaches its fixed size then it will be flushed to disk
     * The sequence of operations is not atomic. e.g. Commit logs cannot be rolled back if in memory memtable write fails
     * This method is blocking
     * @param command instance of type Command
     */
    public synchronized static boolean mutate(Command command) {
        CommitLogManager.append(command.dataRecord);
        MemTableManager.putData(command.dataRecord);
        if(MemTableManager.shouldFlushMemTable())
            return MemTableManager.flushMemTable();
        return true;
    }

    /**
     * This is the get method to get the data associated with a key
     */
    public DataRecord get(KeyRecord key) {
        return null;
    }
}
