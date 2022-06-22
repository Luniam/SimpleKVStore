package com.simplekv.disk;

import com.simplekv.storage.Command;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommitLogManagerTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testGetCommitLogDeSerializer() {
        try {
            ObjectDeSerializer deSerializer = CommitLogManager.getCommitLogDeSerializer();
//            Command command = (Command) deSerializer.read();
//            Assert.assertNotNull(command);

            List<Command> commandList = deSerializer.readAll().stream().map(o -> (Command) o).toList();
            logger.debug("Commands loaded successfully");
        } catch (FileNotFoundException fileNotFoundException) {
            logger.error(fileNotFoundException.getMessage());
            logger.error(Arrays.toString(fileNotFoundException.getStackTrace()));
        }
        catch (IOException | ClassNotFoundException exception) {
            logger.error(exception.getMessage());
            logger.error(Arrays.toString(exception.getStackTrace()));
            Assert.fail();
        }
    }
}
