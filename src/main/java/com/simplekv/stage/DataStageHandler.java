package com.simplekv.stage;

import com.google.protobuf.ByteString;
import com.simplekv.grpc.ReadRequest;
import com.simplekv.grpc.ReadResponse;
import com.simplekv.service.StorageProxy;
import com.simplekv.storage.Command;
import com.simplekv.storage.ReadCommand;
import com.simplekv.utils.DataRecord;
import com.simplekv.utils.DataReturnRecord;
import com.simplekv.utils.KeyRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class DataStageHandler extends StageHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static DataStageHandler dataStageHandler;
    private static ReentrantLock lock = new ReentrantLock();
    private ThreadPoolExecutor threadPoolExecutor;
    private final StageManager stageManager = StageManager.loadInstance();

    static class ReadRequestHandler implements Supplier<DataReturnRecord> {

        private ReadDataStageObject readDataStageObject;

        public ReadRequestHandler(ReadDataStageObject readDataStageObject) {
            this.readDataStageObject = readDataStageObject;
        }
        @Override
        public DataReturnRecord get() {
            ReadRequest readRequest = readDataStageObject.getRequest();
            KeyRecord keyRecord = new KeyRecord(readRequest.getKey());
            DataRecord dataRecord = new DataRecord(keyRecord);
            String enumName = readRequest.getRequestType().name();
            ReadCommand command = new ReadCommand(dataRecord, Command.getCommandTypeFromName(enumName));
            return StorageProxy.get(command);

        }
    }

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
        Thread readStageHandlerThread = new Thread(() -> {
            while(true) {
                try {
                    ReadDataStageObject readDataStageObject =
                            (ReadDataStageObject) this.stageManager.incomingReadDataStage.take();
                    String key = readDataStageObject.getRequest().getKey();
                    CompletableFuture.supplyAsync(new ReadRequestHandler(readDataStageObject), this.threadPoolExecutor)
                                    .thenAccept((dataReturnRecord -> {
                                        if(dataReturnRecord == null) return;
                                        byte[] digestValue = null;
                                        if(dataReturnRecord.getDigest() != null) {
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            byteArrayOutputStream.write(dataReturnRecord.getDigest());
                                            digestValue = byteArrayOutputStream.toByteArray();
                                        }
                                        ReadResponse readResponse = ReadResponse.newBuilder()
                                                .setKey(key)
                                                .setDigest(ByteString.copyFrom(digestValue))
                                                .setVale(ByteString.copyFrom(dataReturnRecord.getData()))
                                                .build();
                                        ReadResponseStageObject readResponseStageObject =
                                                new ReadResponseStageObject(readResponse, readDataStageObject.getStreamObserver());
                                        stageManager.outgoingDataStage.add(readResponseStageObject);
                                    }));
                } catch (InterruptedException interruptedException) {
                    logger.error(interruptedException.getMessage());
                }
            }
        });
        readStageHandlerThread.start();
    }
}
