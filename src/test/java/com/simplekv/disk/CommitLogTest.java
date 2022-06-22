package com.simplekv.disk;

import org.junit.Assert;
import org.junit.Test;

public class CommitLogTest {

    @Test
    public void testLoadInstance() {
        CommitLog commitLog = CommitLog.loadInstance();
        Assert.assertNotNull(commitLog);
        Assert.assertNotNull(commitLog.getDataCommandList());
    }
}
