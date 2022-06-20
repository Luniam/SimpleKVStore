package com.simplekv.disk;

import com.simplekv.storage.Command;
import com.simplekv.utils.DataRecord;

public class CommitLogManager {

    private static final CommitLog commitLog = CommitLog.instance();

    public static CommitLog getCommitLog() {
        return commitLog;
    }

    public static void append(DataRecord dataRecord) {
        commitLog.append(dataRecord);
    }
}
