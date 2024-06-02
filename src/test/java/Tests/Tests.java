package Tests;

import Pages.*;
import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.Assert.assertEquals;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static Homepage homepage;
    static SignInPage signInPage;
    static ShopPage shopPage;
    static CartPage cartPage;
    static Checkout checkout;

    @BeforeClass
    public static void setup() {
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();

        homepage = new Homepage();
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        checkout = new Checkout();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testingAuth() {
        driver.get(Constants.URL);
        homepage.clickLoginButton();
        signInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.DISPLAY_NAME), homepage.getDisplayName());
    }
    @Test
    public void testingAddingThingsToCart() throws InterruptedException {
        driver.get(Constants.URL);
        homepage.clickShopButton();
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        assertEquals(Constants.CART_QUANTITY, shopPage.getCartProductCount());

    }

    @Test
    public void testingPurchaseProcess() throws InterruptedException {
        driver.get(Constants.URL);
        homepage.clickLoginButton();
        signInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));;
        homepage.clickShopButton();
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        Thread.sleep(2000);
        shopPage.clickOnCartButton();
        cartPage.clickOnProceedButton();
//        checkout.fillAddressDetails();
        checkout.placeOrder();
        assertEquals(Constants.ORDER_STATUS, checkout.getOrderStatus());
    }
}
