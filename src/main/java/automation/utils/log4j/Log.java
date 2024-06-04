package automation.utils.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
    private static Logger logger = Logger.getLogger(Log.class);

    public static void initialize() {
        DOMConfigurator.configure("log4j.xml");
    }

    public static Logger getLogData(String className) {
        logger = Logger.getLogger(className);
        return logger;
    }

    public static void startTest(String testName) {
        logger.info("Starting test: " + testName);
    }

    public static void endTest(String testName) {
        logger.info("Ending test: " + testName);
    }

    public static void pass(String message) {
        logger.info("Test passed: " + message);
    }

    public static void fail(String message) {
        logger.error("Test failed: " + message);
    }

    public static void skip(String message) {
        logger.warn("Test skipped: " + message);
    }

    public static void evidence(String testName, String evidence) {
        logger.info("Evidence: " + evidence + " is generated for test: " + testName);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
