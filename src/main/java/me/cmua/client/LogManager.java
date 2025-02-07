package me.cmua.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {

    private Logger logger;

    public LogManager(String name) {
        logger = LoggerFactory.getLogger(name);

    }

    public void info(String msg) {
        logger.info(msg, Thread.currentThread().getName());
    }

    public void error(String msg) {
        logger.error(msg, Thread.currentThread().getName());
    }

}
