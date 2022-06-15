package com.simplekv.db;

import com.simplekv.disk.SSTable;

public class MemTableManager {

    private static TreeMapMemTable memTable = TreeMapMemTable.instance();

    public static void flushMemTable() {
        SSTable ssTable = new SSTable();
    }
}
