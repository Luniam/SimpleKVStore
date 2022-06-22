package com.simplekv.disk;

import com.simplekv.storage.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CommitLog {

    private static ReentrantLock lock = new ReentrantLock();
    private static CommitLog instance;

    private List<Command> dataCommandList;

    private CommitLog() {

    }

    public static CommitLog loadInstance() {
        if(instance == null) {
            lock.lock();
            try {
                if(instance == null) {
                    instance = new CommitLog();
                    instance.dataCommandList = new ArrayList<>();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void loadDataCommandList() {

    }

    public List<Command> getDataCommandList() {
        return this.dataCommandList;
    }

    public void append(Command command) {

    }

    public void clearCommitLog() {

    }
}
