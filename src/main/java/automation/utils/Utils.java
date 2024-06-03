package automation.utils;

import automation.drivers.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Utils {
    public static int testCount=0; //This is used in Extent Report Generation

    public static  String decode(String encodedString) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedString));

    }

    public static boolean takeScreenshot(String screenShotName) {
        File file = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        String screenShotPath = Constants.SCREENSHOT_FOLDER+getTodayDate()+"\\"+screenShotName+ "-"+ getRandomString(2)+"_"+getDateTime() + ".png";

        boolean ssTaken;
        try {
            FileUtils.copyFile(file, new File(screenShotPath));
            System.out.println("Screenshot captured: " + screenShotPath);
            ssTaken= true;
        } catch (IOException e) {
            e.printStackTrace();
            ssTaken= false;
        }
        return ssTaken;

    }

    public static void takeScreenshot(){
        takeScreenshot("ss");

    }

    //Taking Screenshot  by scrolling to Web Element and keeping element in focus using js
    public static void takeScreenshot(String screenShotName, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getDriver();
            js.executeScript("arguments[0].scrollIntoView();", element);
            js.executeScript("arguments[0].focus();", element);
            takeScreenshot(screenShotName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenshot(String screenShotName, WebElement element,boolean center) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getDriver();

            // Get the element's position and size
            int elementPosition = element.getLocation().getY();
            int elementHeight = element.getSize().getHeight();

            // Get the window's inner height
            int windowHeight = ((Number) js.executeScript("return window.innerHeight")).intValue();
            int scrollPosition;


            if(center) {
            // Calculate the scroll position to center the element
             scrollPosition = elementPosition - (windowHeight / 2) + (elementHeight / 2);
            }else{
                scrollPosition = elementPosition;
            }
                // Scroll to the calculated position
                js.executeScript("window.scrollTo(0, arguments[0]);", scrollPosition);

            // Ensure the element is focused
            js.executeScript("arguments[0].focus();", element);

            // Take the screenshot
            takeScreenshot(screenShotName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static String getRandomString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    //Get Current system time in DDMMYY-HHMMSS format
    public static String getDateTime() {
        java.util.Date date = new java.util.Date();
        return String.format("%1$tY%1$tm%1$td-%1$tH%1$tM%1$tS", date);
    }

    //Get Today's Date in DDMMYY format
    public static String getTodayDate() {
        java.util.Date date = new java.util.Date();
        return String.format("%1$td%1$tm%1$tY", date);
    }
}
