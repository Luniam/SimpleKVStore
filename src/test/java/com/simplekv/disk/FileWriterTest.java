package com.simplekv.disk;

import com.simplekv.TestUtils;
import com.simplekv.disk.FileManager;
import com.simplekv.disk.FileWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class FileWriterTest {

    @Test
    public void testAppendBytes() {
        long timestamp = System.currentTimeMillis();
        String filename = TestUtils.testDataDirectory + "test-data-" + timestamp + ".db";
        try {
            FileWriter writer = FileManager.getFileWriter(filename);
            byte[] bytes = new byte[1024*128];
            SecureRandom.getInstanceStrong().nextBytes(bytes);
            writer.appendBytes(bytes);
        } catch (IOException | NoSuchAlgorithmException exception) {
            Assert.fail(exception.getMessage());
        } finally {
            File file = new File(filename);
            file.delete();
        }
    }
}
