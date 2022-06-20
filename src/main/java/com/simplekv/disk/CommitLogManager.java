package com.simplekv.disk;

import com.simplekv.storage.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

public class CommitLogManager {

    private static final Logger logger = LoggerFactory.getLogger(CommitLogManager.class);
    private static final ThreadPoolExecutor commitLogAppenderExecutors =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    private static final BlockingQueue<CommitLogAppender> commitLogAppenderQueue = new LinkedBlockingDeque<>();
    private static final int appenderThreadDelayMillis = 10000;


    static class CommitLogAppender implements Runnable {

        private Command command;
        public CommitLogAppender(Command command) {
            this.command = command;
        }

        public Command getCommand() {
            return this.command;
        }

        @Override
        public void run() {
            //todo do the actual append
        }
    }

    private static final CommitLog commitLog = CommitLog.instance();

    public static CommitLog getCommitLog() {
        return commitLog;
    }

    public static void append(Command command) {
        CommitLogAppender appender = new CommitLogAppender(command);
        commitLogAppenderQueue.add(appender);
    }

    public static void startCommitLogAppenderWorker() {
        Thread commitLogAppenderThread = new Thread(() -> {
            while (true) {
                try {
                    CommitLogAppender appender = commitLogAppenderQueue.poll();
                    if(appender != null) commitLogAppenderExecutors.submit(appender);
                    Thread.sleep(appenderThreadDelayMillis);
                } catch (InterruptedException exception) {
                    logger.error(exception.getMessage());
                }
            }
        });
        commitLogAppenderThread.start();
    }
}
