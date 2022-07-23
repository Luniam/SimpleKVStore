package com.simplekv.stage;

import com.simplekv.grpc.ReadResponse;
import io.grpc.stub.StreamObserver;

public class ReadResponseStageObject extends StageObject {

    private ReadResponse readResponse;

    public ReadResponseStageObject(ReadResponse readResponse, StreamObserver streamObserver) {
        super(streamObserver);
        this.readResponse = readResponse;
    }

    public ReadResponse getReadResponse() {
        return readResponse;
    }
}
