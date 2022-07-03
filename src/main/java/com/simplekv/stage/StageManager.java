package com.simplekv.stage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class StageManager {

    public BlockingQueue<StageObject> incomingReadDataStage;
    public BlockingQueue<StageObject> incomingWriteDataStage;
    public BlockingQueue<StageObject> incomingGossipStage;
    public BlockingQueue<StageObject> outgoingDataStage;

    private static StageManager stageManager;
    private static ReentrantLock lock = new ReentrantLock();

    private StageManager() {
        incomingReadDataStage = new LinkedBlockingQueue<>();
        incomingWriteDataStage = new LinkedBlockingQueue<>();
        incomingGossipStage = new LinkedBlockingQueue<>();
        outgoingDataStage = new LinkedBlockingQueue<>();
    }

    public static StageManager loadInstance() {
        if(stageManager == null) {
            try {
                lock.lock();
                if(stageManager == null)
                    stageManager = new StageManager();
            } finally {
                lock.unlock();
            }
        }
        return stageManager;
    }
}
