package Tests;

import Pages.*;
import drivers.DriverSingleton;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import utils.Constants;
import utils.FrameworkProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static Homepage homepage;
    static SignInPage signInPage;
    static ShopPage shopPage;
    static CartPage cartPage;
    static Checkout checkout;

    @BeforeAll
    public static void setupAll() {
        frameworkProperties = new FrameworkProperties();
    }

    @BeforeEach
    public void setup() {
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();

        homepage = new Homepage();
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        checkout = new Checkout();
    }

    @AfterEach
    public void tearDownEach() {
        if (driver != null) {
            driver.close();
        }
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(2)
    public void testingAuth() {
        driver.get(Constants.URL);
        homepage.clickLoginButton();
        signInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.DISPLAY_NAME), homepage.getDisplayName());
    }

    @ParameterizedTest
    @Order(1)
    @CsvSource({
            "amanshah, QW1hbkAxMjM=",
            "secondtest, MTIzNDU2"
    })
    public void testingAuthUsingCsv(String username, String password) {
        driver.get(Constants.URL);
        homepage.clickLoginButton();
        signInPage.logIn(username, password);
        assertTrue(homepage.getDisplayName().contains("Hello"));
    }

    @Test
    @Order(3)
    public void testingAddingThingsToCart() throws InterruptedException {
        driver.get(Constants.URL);
        homepage.clickShopButton();
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        assertEquals(Constants.CART_QUANTITY, shopPage.getCartProductCount());
    }

    @Test
    @Order(4)
    public void testingPurchaseProcess() throws InterruptedException {
        driver.get(Constants.URL);
        homepage.clickLoginButton();
        signInPage.logIn(frameworkProperties.getProperty(Constants.USERNAME), frameworkProperties.getProperty(Constants.PASSWORD));
        homepage.clickShopButton();
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        Thread.sleep(2000);
        shopPage.clickOnCartButton();
        cartPage.clickOnProceedButton();
        // checkout.fillAddressDetails();
        checkout.placeOrder();
        assertEquals(Constants.ORDER_STATUS, checkout.getOrderStatus());
    }
}
