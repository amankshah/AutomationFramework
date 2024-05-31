import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import utils.FrameworkProperties;

public class Main {

    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
}
