package com.simplekv.disk;

import com.simplekv.storage.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CommitLog {

    private static Logger logger = LoggerFactory.getLogger(CommitLog.class);
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
                    FileReader commitLogReader = CommitLogManager.getCommitLogReader();
                    if(commitLogReader == null) return instance;
                    AbstractCommitLogTemplate commitLogTemplate =
                            CommitLogTemplateFactory.getDefaultCommitLogTemplate();
                    commitLogTemplate.populateDataCommandList(instance.dataCommandList, commitLogReader);
                }
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public List<Command> getDataCommandList() {
        return this.dataCommandList;
    }
}
