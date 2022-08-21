package com.simplekv.storage;

import com.simplekv.db.IndexBloomFilter;
import com.simplekv.db.IndexManager;
import com.simplekv.db.MemTableManager;
import com.simplekv.disk.BlockMetaData;
import com.simplekv.disk.CommitLogManager;
import com.simplekv.disk.SSTable;
import com.simplekv.utils.*;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StorageService {

    public static StorageService instance;
    public static ReentrantLock lock = new ReentrantLock();
    public static StorageService loadInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if(instance == null) instance = new StorageService();
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * This is the entrypoint towards writing a data to the database
     * At first data will be written to the commit log and then to the memtable
     * If the memtable reaches its fixed size then it will be flushed to disk
     * The sequence of operations is not atomic. e.g. Commit logs cannot be rolled back if in memory memtable write fails
     * This method is blocking
     * @param command instance of type Command
     */
    public synchronized boolean append(MutateCommand command) {
        CommitLogManager.append(command);
        MemTableManager.putData(command.dataRecord);
        if(MemTableManager.shouldFlushMemTable()) {
            CommitLogManager.append(new CommitLogFlushCommand());
            return MemTableManager.flushMemTable();
        }
        return true;
    }

    /**
     * This is the get method to get the data associated with a key
     */
    public DataReturnRecord get(KeyRecord key, boolean isDigest) {
        ValueRecord dataReturnRecordFromMemTable = MemTableManager.getData(key);
        if(dataReturnRecordFromMemTable != null) {
            return getReturnDataFromValueRecord(dataReturnRecordFromMemTable, isDigest);
        }
        for(Map.Entry<String, IndexBloomFilter> entry : IndexManager.indexFileNameToBloomFilter.entrySet()) {
            IndexBloomFilter bloomFilter = entry.getValue();
            if(bloomFilter.mightContain(key)) {
                String indexFileName = entry.getKey();
                BlockMetaData blockMetaData = IndexManager.getSsTableBlockIndexFromIndexFile(key, indexFileName);
                if(blockMetaData == null) continue;
                String ssTableName = IndexManager.indexFileNameToSSTableName.get(indexFileName);
                ValueRecord value = SSTable.getValueRecordFromPosition(ssTableName, key, blockMetaData);
                if(value == null) continue;
                return getReturnDataFromValueRecord(value, isDigest);
            }
        }
        return null;
    }

    private DataReturnRecord getReturnDataFromValueRecord(ValueRecord value, boolean isDigest) {
        DataReturnRecord dataReturnRecord = new DataReturnRecord();
        if(isDigest) {
            Hash hasher = new MurmurHash();
            int hashValue = hasher.hash(value.getData(), value.getDataSizeInBytes(), 0);
            dataReturnRecord.setDigest(hashValue);
            return dataReturnRecord;
        }
        dataReturnRecord.setData(value.getData());
        return dataReturnRecord;
    }
}
