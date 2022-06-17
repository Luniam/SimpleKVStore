package com.simplekv.db;

import com.simplekv.utils.DataRecord;
import com.simplekv.utils.KeyRecord;
import com.simplekv.utils.ValueRecord;

import java.util.Map;

/**
 * The in memory MemTable, Users can implement this with their data structure of choice.
 */
public interface MemTableMBean {

    void putDataRecord(DataRecord dataRecord);
    boolean exists(KeyRecord keyRecord);
    ValueRecord getValueRecord(KeyRecord keyRecord);
    Map<KeyRecord,ValueRecord> getMemData();
    long getKeyCount();

    void refreshMemTable();
}
