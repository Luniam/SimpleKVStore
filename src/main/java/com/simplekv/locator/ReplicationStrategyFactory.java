package com.simplekv.locator;

import com.simplekv.config.Config;
import com.simplekv.config.DatabaseDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReplicationStrategyFactory {

    private static Logger logger = LoggerFactory.getLogger(ReplicationStrategyFactory.class);

    public static ReplicationStrategy getReplicationStrategy() {
        try {
            Config config = DatabaseDescriptor.getConfig();
            Class<?> chosenStrategy = Class.forName(config.replication_strategy);
            Constructor<?> constructor = chosenStrategy.getConstructor();
            return (ReplicationStrategy) constructor.newInstance();
        } catch (ClassNotFoundException classNotFoundException) {
            logger.error("Class not found exception occurred when creating a replication strategy class");
        } catch (NoSuchMethodException noSuchMethodException) {
            logger.error("No such method exception occurred when creating a replication strategy class");
        } catch (InvocationTargetException e) {
            logger.error("Invocation exception occurred when creating a replication strategy class");
        } catch (InstantiationException e) {
            logger.error("Instantiation exception occurred when creating a replication strategy class");
        } catch (IllegalAccessException e) {
            logger.error("Illegal access exception occurred when creating a replication strategy class");
        }
        return null;
    }
}
