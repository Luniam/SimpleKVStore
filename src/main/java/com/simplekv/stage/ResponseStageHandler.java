package com.simplekv.stage;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseStageHandler extends StageHandler {

    private final StageManager stageManager = StageManager.loadInstance();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void startHandler() {
        Thread responseHandlerThread = new Thread(() -> {
            while (true) {
                try {
                    StageObject stageObject = stageManager.outgoingDataStage.take();
                    StreamObserver streamObserver = stageObject.getStreamObserver();
                    if(stageObject instanceof ReadResponseStageObject) {
                        streamObserver.onNext(((ReadResponseStageObject) stageObject).getReadResponse());
                    }
                    else if(stageObject instanceof GossipResponseStageObject) {
                        streamObserver.onNext(((GossipResponseStageObject) stageObject).getGossipResponse());
                    }
                    streamObserver.onCompleted();
                } catch (InterruptedException interruptedException) {
                    logger.error(interruptedException.getMessage());
                }
            }
        });
        responseHandlerThread.start();
    }
}
