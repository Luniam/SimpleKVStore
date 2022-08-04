package com.simplekv.stage;

import com.simplekv.grpc.GossipResponse;
import io.grpc.stub.StreamObserver;

public class GossipResponseStageObject extends StageObject {

    private GossipResponse gossipResponse;

    public GossipResponseStageObject(GossipResponse gossipResponse, StreamObserver streamObserver) {
        super(streamObserver);
        this.gossipResponse = gossipResponse;
    }

    public GossipResponse getGossipResponse() {
        return gossipResponse;
    }
}
