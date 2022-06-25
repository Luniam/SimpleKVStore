package com.simplekv.disk;

import com.simplekv.config.DatabaseDescriptor;
import org.junit.Assert;
import org.junit.Test;

public class CommitLogTest {

    @Test
    public void testLoadInstance() {
        DatabaseDescriptor.daemonInitialization();
        CommitLog commitLog = CommitLog.loadInstance();
        Assert.assertNotNull(commitLog);
        Assert.assertNotNull(commitLog.getDataCommandList());
    }
}
