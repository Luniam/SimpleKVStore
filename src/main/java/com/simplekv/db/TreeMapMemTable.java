package com.simplekv.db;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class TreeMapMemTable implements MemTableMBean {

    private Map<KeyRecord, ValueRecord> memData;
    private static TreeMapMemTable instance;
    private static ReentrantLock lock = new ReentrantLock();

    private TreeMapMemTable() {
        memData = new TreeMap<>();
    }

    public static TreeMapMemTable instance() {
        if(instance == null) {
            lock.lock();
            try {
                if(instance == null) instance = new TreeMapMemTable();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public synchronized void putDataRecord(DataRecord dataRecord) {
        this.memData.put(dataRecord.getKey(), dataRecord.getValue());
    }

    @Override
    public boolean exists(KeyRecord keyRecord) {
        return this.memData.containsKey(keyRecord);
    }

    @Override
    public ValueRecord getValueRecord(KeyRecord keyRecord) {
        return !this.exists(keyRecord)? null : this.memData.get(keyRecord);
    }

    @Override
    public Map<KeyRecord, ValueRecord> getMemData() {
        return memData;
    }

    @Override
    public long getKeyCount() {
        return this.memData.keySet().size();
    }

    @Override
    public void refreshMemTable() {
        this.memData.clear();
    }
}
