import Pages.*;
import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import utils.CommonFunctions;
import utils.FrameworkProperties;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton= DriverSingleton.getInstance(frameworkProperties.getProperty("browser"));

        WebDriver driver=DriverSingleton.getDriver();
        driver.get("https://bitheap.tech/");

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


        homepage.clickLoginButton();
        signInPage.logIn("amanshah","Aman@123");

        if(homepage.getUserName().equals("Hello, Aman")){
            System.out.println("User  is logedin sucessfully");
        }else {
            System.out.println("User is not logedin" );
            System.out.println(homepage.getUserName());
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
}
