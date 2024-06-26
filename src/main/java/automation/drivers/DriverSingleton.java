package automation.drivers;

import automation.drivers.strategies.DriverStrategy;
import automation.drivers.strategies.DriverStrategyImplimentor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance =null;
    private static WebDriver driver;

    private DriverSingleton(String driver){
        instatntiate(driver);
    }
    public WebDriver instatntiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplimentor.chooseStrategy(strategy);
        driver= driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static DriverSingleton getInstance(String driver){
        if(instance==null){
            instance = new DriverSingleton(driver);
        }
        return instance;
    }
    public  static void closeObjectInstance() throws InterruptedException {
        Thread.sleep(5000);
        instance=null;
        driver.quit();
    }
    public static WebDriver getDriver(){
        return driver;
    }

}
