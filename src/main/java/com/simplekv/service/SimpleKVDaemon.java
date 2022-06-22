package com.simplekv.service;

import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.db.MemTableManager;
import com.simplekv.disk.CommitLogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleKVDaemon {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    static final SimpleKVDaemon instance = new SimpleKVDaemon();

    private void applyConfig() {
        DatabaseDescriptor.daemonInitialization();
    }

    private void memTableSequence() {
        MemTableManager.loadMemTable();
        CommitLogManager.startCommitLogAppenderWorker();
    }

    /**
     * Activating the instance
     */
    public void activate() {
        applyConfig();
        memTableSequence();
        logger.info("Started Simple KV Store...yaay");
    }

    public static void main(String[] args) {
        instance.activate();
    }
}
