package com.simplekv.disk;

import com.simplekv.storage.Command;
import com.simplekv.utils.DataRecord;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CommitLog {

    private static ReentrantLock lock = new ReentrantLock();
    private static CommitLog instance;

    private List<Command> dataCommandList;

    private CommitLog() {

    }

    public static CommitLog instance() {
        if(instance == null) {
            lock.lock();
            try {
                if(instance == null) instance = new CommitLog();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void append(DataRecord dataRecord) {

    }

    public void clearCommitLog() {

    }
}
