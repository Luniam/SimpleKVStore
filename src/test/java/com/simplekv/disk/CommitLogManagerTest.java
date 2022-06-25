package com.simplekv.disk;

import com.simplekv.storage.Command;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Ignore
public class CommitLogManagerTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testGetCommitLogDeSerializer() {
    }
}
