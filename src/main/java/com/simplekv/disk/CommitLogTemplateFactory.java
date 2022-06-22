package com.simplekv.disk;

public class CommitLogTemplateFactory {

    private static AbstractCommitLogTemplate abstractCommitLogTemplate;

    public static AbstractCommitLogTemplate getDefaultCommitLogTemplate() {
        abstractCommitLogTemplate = new CommitLogTemplate();
        return abstractCommitLogTemplate;
    }
}
