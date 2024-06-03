import automation.drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.FrameworkProperties;
public class TestRunner {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");


  WebElement loginButton= driver.findElement(By.cssSelector("#menu-item-2017 > a"));

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();



    }
}
