package com.simplekv.service;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import com.simplekv.grpc.*;
import com.simplekv.stage.DataStageHandler;
import com.simplekv.stage.ReadDataStageObject;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MessagingService extends GrpcMessagingServiceGrpc.GrpcMessagingServiceImplBase {

    private DataStageHandler dataStageHandler = DataStageHandler.loadInstance();

    private static Logger logger = LoggerFactory.getLogger(MessagingService.class);

    @Override
    public void get(ReadRequest request, StreamObserver<ReadResponse> responseObserver) {
        ReadDataStageObject dataStageObject = new ReadDataStageObject(responseObserver, request);
        dataStageHandler.addToIncomingDataStage(dataStageObject);
    }

    @Override
    public void put(WriteRequest request, StreamObserver<WriteResponse> responseObserver) {
        super.put(request, responseObserver);
    }

    @Override
    public void gossip(GossipMessage request, StreamObserver<GossipResponse> responseObserver) {
        super.gossip(request, responseObserver);
    }

    public static void startGrpcServer() {
        Config config = DatabaseDescriptor.getConfig();
        Thread serverThread = new Thread(() -> {
            try {
                Server server = ServerBuilder.forPort(config.node_port)
                        .addService(new MessagingService())
                        .build();
                server.start();
                server.awaitTermination();
            } catch (IOException ioException) {
                logger.error("IO exception occurred while starting the server");
            } catch (InterruptedException interruptedException) {
                logger.error("Interrupted while awaiting termination");
            }
        });
        serverThread.start();
    }
}
