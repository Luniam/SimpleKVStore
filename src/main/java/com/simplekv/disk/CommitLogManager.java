package com.simplekv.disk;

import com.simplekv.storage.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

public class CommitLogManager {

    private static final Logger logger = LoggerFactory.getLogger(CommitLogManager.class);
    private static final ThreadPoolExecutor commitLogAppenderExecutors =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    private static final BlockingQueue<CommitLogAppender> commitLogAppenderQueue = new LinkedBlockingDeque<>();
    private static final int appenderThreadDelayMillis = 100;
    private static final String commitLogDataDirectory = "wal/";
    private static final String commitLogFileName = "wal.bin";
    private static final FileWriter commitLogWriter = getCommitLogWriter();

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
            try {
                AbstractCommitLogTemplate commitLogTemplate = CommitLogTemplateFactory.getDefaultCommitLogTemplate();
                if(commitLogWriter == null) return;
                commitLogTemplate.appendToCommitLog(commitLogWriter, command);
                //todo write commit log
            } catch (Exception exception) {
                logger.error(exception.getMessage());
                logger.error("Could not write commit log, appending to task quue once again");
                commitLogAppenderQueue.add(this);
            }
        }
    }

    private static final CommitLog commitLog = CommitLog.loadInstance();

    public static FileWriter getCommitLogWriter() {
        try {
            File folder = new File(commitLogDataDirectory);
            if(!folder.exists())
                folder.mkdir();
            return FileManager.getFileWriter(getFullCommitLogFileName());
        } catch (IOException ioException) {
            return null;
        }
    }

    public static String getFullCommitLogFileName() {
        return commitLogDataDirectory + commitLogFileName;
    }

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
