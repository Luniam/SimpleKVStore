package com.simplekv.storage;

public class CommitLogFlushCommand extends Command {

    public CommitLogFlushCommand() {
        super();
        this.command = CommandType.COMMIT_LOG_FLUSH;
    }
}
