import Pages.Homepage;
import Pages.SignInPage;
import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import utils.FrameworkProperties;

public class Main {

    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");

        Homepage homepage = new Homepage();
        SignInPage signInPage = new SignInPage();

        homepage.clickLoginButton();
        signInPage.logIn("amanshah","Aman@123");

        if(homepage.getUserName().equals("Hello, Aman")){
            System.out.println("Test Case Passed");
        }else {
            System.out.println("Failed" );
            System.out.println(homepage.getUserName());
        }

        DriverSingleton.closeObjectInstance();
    }
}
