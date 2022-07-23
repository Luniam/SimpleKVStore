package com.simplekv.stage;

import com.simplekv.grpc.ReadRequest;
import io.grpc.stub.StreamObserver;

public class ReadDataStageObject extends StageObject {

    private ReadRequest request;

    public ReadDataStageObject(StreamObserver streamObserver, ReadRequest request) {
        super(streamObserver);
        this.request = request;
    }

    public ReadRequest getRequest() {
        return request;
    }
}
