package automation.drivers.strategies;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.setExperimentalOption("useAutomationExtension", false);

//
//        options.addArguments("--disable-web-security");
//        options.addArguments("--disable-site-isolation-trials");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");


        System.out.println("Initiating browser: CHROME");
        return new ChromeDriver(options);
    }
}
