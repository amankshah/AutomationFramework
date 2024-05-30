import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PhantomJs implements DriverStrategy{
    @Override
    public WebDriver setStrategy() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"headless"});
        options.addArguments(new String[]{"window-size=1200x600"});
        options.addArguments("--no-sandbox");

        options.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(options);

    }
}
