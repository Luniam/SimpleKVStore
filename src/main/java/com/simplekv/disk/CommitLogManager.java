package com.simplekv.disk;

import com.simplekv.utils.DataRecord;

public class CommitLogManager {

    private static final CommitLog commitLog = CommitLog.instance();

    public static void append(DataRecord dataRecord) {
        commitLog.append(dataRecord);
    }
}
