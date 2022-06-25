package com.simplekv.disk;

import com.simplekv.config.DatabaseDescriptor;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CommitLogTest {

    @Test
    public void testLoadInstance() {
        DatabaseDescriptor.daemonInitialization();
        CommitLog commitLog = CommitLog.loadInstance();
        Assert.assertNotNull(commitLog);
        Assert.assertNotNull(commitLog.getDataCommandList());
    }
}
