package automation.drivers.strategies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {
    @Override
    public WebDriver setStrategy() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        System.out.println();
        System.out.println("Initiating browser: FIREFOX");
        System.out.println();
        return driver;
    }
}
