package automation.utils.log4j;

import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.util.logging.Logger;

public class Log {
    private static Logger logger = Logger.getLogger(Log.class.getName());

    public static Logger getLogger(String className) {
        String path = new File("").getAbsolutePath();
        DOMConfigurator.configure(path + "log4j.xml");
        logger = Logger.getLogger(className);
        return logger;
    }

    public static void startTest(String testName) {
        logger.info("Starting test: " + testName);
    }

    public static void endTest(String testName) {
        logger.info("Ending test: " + testName);
    }

    public static void pass(String testName) {
        logger.info("Test passed: " + testName);
    }

    public static void fail(String testName) {
        logger.info("Test failed: " + testName);
    }

    public static void skip(String testName) {
        logger.info("Test skipped: " + testName);
    }

    public static void evidence( String testName, String evidence) {
        logger.info("Evidence: " + evidence + " is generated for test: " + testName);
    }


    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warning(String message) {
        logger.debug(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

}
