import automation.Pages.*;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import automation.utils.Constants;
import automation.utils.FrameworkProperties;
import automation.utils.Utils;

import static automation.utils.Utils.getRandomString;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get(Constants.URL);

        Homepage homepage = new Homepage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage= new CartPage();
        Checkout checkout = new Checkout();


        //setting zoom level
//        CommonFunctions.setPageZoom(20);

        //Navigating to login page

// Wait for the page to adjust after zoom
//        Thread.sleep(2000);

        Utils.takeScreenshot("Homepage");
        Utils.takeScreenshot();
        homepage.clickLoginButton();
            signInPage.logIn(frameworkProperties.getProperty("signinuser"),frameworkProperties.getProperty("password"));

        if(homepage.getDisplayName().equals(frameworkProperties.getProperty("username"))){
            System.out.println("User  is logedin sucessfully");
        }else {
            System.out.println("User is not logedin" );
            System.out.println(homepage.getDisplayName());
        }



        //Navigating to shop page
        homepage.clickShopButton();
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        shopPage.clickOnCartButton();
        cartPage.clickOnProceedButton();
//        checkout.fillAddressDetails();
        checkout.placeOrder();
        checkout.getOrderStatus();

        DriverSingleton.closeObjectInstance();
    }

    public static void StringTest(String[] args) {
        System.out.println(getRandomString(10));
        System.out.println(getRandomString(10));
        System.out.println(getRandomString(10));
        System.out.println(getRandomString(10));
    }
}
