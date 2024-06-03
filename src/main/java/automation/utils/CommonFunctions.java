package automation.utils;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {
    public static void waitUntilWebElementVisible(WebElement webElement) {
        //wait until the element is visible
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        System.out.println(webElement.getText() + " is displayed: " + webElement.isDisplayed());

    }

    public static void selectByValue(WebElement dropdown, String value) {
        // wait until the dropdown is visible
        WebDriverWait wait;
        wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(dropdown));

        // create an instance of the Select class
        Select select = new Select(dropdown);

        // select the option by value
        select.selectByValue(value);

        // print confirmation message
        System.out.println("Selected option with value: " + value);
    }

    public static void selectByIndex(WebElement dropdown, int index) {
        // wait until the dropdown is visible
        WebDriverWait wait;
        wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(dropdown));

        // create an instance of the Select class
        Select select = new Select(dropdown);

        // select the option by index
        select.selectByIndex(index);

        // print confirmation message
        System.out.println("Selected option with index: " + index);
    }

    public static void selectByVisibleText(WebElement dropdown, String text) {
        // wait until the dropdown is visible
        WebDriverWait wait;
        wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(dropdown));

        // create an instance of the Select class
        Select select = new Select(dropdown);

        // select the option by visible text
        select.selectByVisibleText(text);

        // print confirmation message
        System.out.println("Selected option with visible text: " + text);
    }

    public static void jsClick(WebElement webElement) {
        // create javascript executor object
        JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getDriver();
        // wait until the element is clickable
        WebDriverWait wait;
        wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        // execute javascript click command
        js.executeScript("arguments[0].click();", webElement);

        // print confirmation message
        System.out.println(webElement.getText() + " is clicked");
    }

    public static void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriver(), Constants.EXPLICIT_WAIT_TIME);

        try {
            //WAIT UNTIL element is visible
            wait.until(ExpectedConditions.visibilityOf(element));
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            System.out.println("Clicked on the element: " + element.getText());
        } catch (Exception e) {
            System.out.println("Element not clickable, attempting to scroll into view and click using JavaScript");

            // Scroll the element into view
            JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            //getting element in focus using js
            js.executeScript("arguments[0].focus();", element);

            // Wait until the element is clickable
            // Try clicking again after scrolling
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Click the element using Javascript
            js.executeScript("arguments[0].click();", element);

            // Print confirmation message
            System.out.println("Clicked on the element: " + element.getText());
        }
    }

    public static void setPageZoom(long zoom) {
        // Ensure zoom level is a valid percentage
        if (zoom < 0) {
            throw new IllegalArgumentException("Zoom level must be a positive percentage.");
        }

        // Get the JavascriptExecutor instance from the WebDriver
        JavascriptExecutor js = (JavascriptExecutor) DriverSingleton.getDriver();

        // Execute the JavaScript to set the zoom level
        js.executeScript("document.body.style.zoom = '" + zoom + "%'");

        // Print confirmation message
        System.out.println("Page zoom set to: " + zoom + "%");
    }

    public static String getCurrentUrlOfWebPage() {
        // Get the current URL of the web page
        String currentUrl = DriverSingleton.getDriver().getCurrentUrl();
        // Print the current URL
        System.out.println("Current URL: " + currentUrl);
        return currentUrl;

    }


}
