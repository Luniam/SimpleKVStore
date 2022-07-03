package com.simplekv.stage;

import io.grpc.stub.StreamObserver;

public abstract class StageObject {

    private StreamObserver streamObserver;

    public StageObject() {}

    public StageObject(StreamObserver streamObserver) {
        this.streamObserver = streamObserver;
    }
}
