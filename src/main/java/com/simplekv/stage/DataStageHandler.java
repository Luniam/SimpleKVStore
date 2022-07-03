package com.simplekv.stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

public class DataStageHandler extends StageHandler {

    private static DataStageHandler dataStageHandler;
    private static ReentrantLock lock = new ReentrantLock();
    private ThreadPoolExecutor threadPoolExecutor;
    private final StageManager stageManager = StageManager.loadInstance();

    private DataStageHandler() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    }

    public static DataStageHandler loadInstance() {
        if(dataStageHandler == null) {
            try {
                lock.lock();
                if(dataStageHandler == null) {
                    dataStageHandler = new DataStageHandler();
                }
            } finally {
                lock.unlock();
            }
        }
        return dataStageHandler;
    }

    public void addToIncomingDataStage(ReadDataStageObject dataStageObject) {
        this.stageManager.incomingReadDataStage.add(dataStageObject);
    }

    @Override
    public void startHandler() {
        while(true) {
            break;
        }
    }
}
