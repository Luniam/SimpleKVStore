package com.simplekv.stage;

import com.simplekv.grpc.GossipMessage;
import io.grpc.stub.StreamObserver;

public class GossipStageObject extends StageObject {

    private GossipMessage gossipMessage;

    public GossipStageObject(GossipMessage gossipMessage, StreamObserver streamObserver) {
        super(streamObserver);
        this.gossipMessage = gossipMessage;
    }

    public GossipMessage getGossipMessage() {
        return gossipMessage;
    }
}
