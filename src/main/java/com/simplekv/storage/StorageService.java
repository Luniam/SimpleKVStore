package com.simplekv.storage;

import com.simplekv.db.MemTableMBean;
import com.simplekv.db.TreeMapMemTable;

public class StorageService {

    public void writeSSTable(MemTableMBean memTable) {

    }

    public static void main(String[] args) {
        StorageService storageService = new StorageService();
        TreeMapMemTable memTable = TreeMapMemTable.loadInstance();
        storageService.writeSSTable(memTable);
    }
}
