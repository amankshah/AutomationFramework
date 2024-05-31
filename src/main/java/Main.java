import org.openqa.selenium.WebDriver;

public class Main {

    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        System.out.println("Initiating browser: "+frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get("https://www.google.com");
    }
}
