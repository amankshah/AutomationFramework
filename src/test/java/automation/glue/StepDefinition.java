package automation.glue;

import automation.ExtentReport.TestCases;
import automation.Pages.*;
import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import automation.utils.FrameworkProperties;
import automation.utils.Utils;
import automation.utils.log4j.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


import static com.relevantcodes.extentreports.utils.DateTimeUtil.getDate;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private Homepage homepage;
    private SignInPage signInPage;
    private Checkout checkout;
    private CartPage cartPage;
    private ShopPage shopPage;

    //Adding Extent Reports For Cucumber
        ExtentTest test;
        static ExtentReports reports = new ExtentReports("report/TestReport-"+ Utils.getDateTime() +".html");

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
        DriverSingleton.getInstance(configurationProperties.getBrowser());
        driver = DriverSingleton.getDriver();
        homepage = new Homepage();
        signInPage = new SignInPage();
        checkout = new Checkout();
        cartPage = new CartPage();
        shopPage = new ShopPage();
        TestCases[] tests= TestCases.values();
        test = reports.startTest(tests[Utils.testCount].getTestName());

        // Initialize Log4j
        Log.initialize();
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());

        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());

        Utils.testCount++;

    }

    @Given("^I go to the website")
    public void i_go_to_the_website() {
        driver=DriverSingleton.getDriver();
        driver.get(Constants.URL);
        Log.info("Navigated to " + Constants.URL);
        test.log(LogStatus.PASS, "Navigated to " + Constants.URL);

    }

    @When("^I click on Login Button")
    public void i_click_on_login_button() {
        homepage.clickLoginButton();
        Log.info("Clicked on Login Button");
        test.log(LogStatus.PASS, "Clicked on Login Button");
    }

    @When("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_login() {
        signInPage.logIn(configurationProperties.getSignInUser(), configurationProperties.getPassword());
        Log.info("Added Credentials and Clicked on Submit Buttons");
        test.log(LogStatus.PASS, "Added Credentials and Clicked on Submit Buttons");
    }
    @Then("^I can log into the website")
    public void i_can_log_into_the_website() {
        if (homepage.getDisplayName().contains("Hello")) {
            test.log(LogStatus.PASS, "Logged In Successful");
            Log.pass("Logged In Successful");
        }else{
            test.log(LogStatus.FAIL, "Login Failed");
            Log.fail("Login Failed");
        }
        assertTrue(homepage.getDisplayName().contains("Hello"));

    }


    //Adding 2nd test


    @When("^I click on Shop Button")
    public void i_click_on_shop_button() {
        homepage.clickShopButton();
        Log.info("Clicked on Shop Button");
        test.log(LogStatus.PASS, "Clicked on Shop Button");
    }

    @When("^I add product to cart")
    public void i_add_product_to_cart() throws InterruptedException {
        shopPage.ShortProducts();
        shopPage.addProductToCart();
        Log.info("Added Product to Cart");
        test.log(LogStatus.PASS, "Added Product to Cart");
    }

    @When("^I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order() {
        shopPage.clickOnCartButton();
        Log.info("Clicked on Cart Button");
        cartPage.clickOnProceedButton();
        checkout.fillAddressDetails();
        checkout.placeOrder();
        Log.info("Placed Order");
        test.log(LogStatus.PASS, "Placed Order");

    }

    @Then("^The element are bought")
    public void the_element_are_bought() {
        if (checkout.getOrderStatus().contains(Constants.ORDER_STATUS)) {
            Log.pass("Order Placed");
            test.log(LogStatus.PASS, "Order Placed");
        }else{
            Log.fail("Order Failed");
            test.log(LogStatus.FAIL, "Order Failed");

        }
        assertTrue(checkout.getOrderStatus().contains(Constants.ORDER_STATUS));
    }

@After
public void closeObjects() throws InterruptedException {
    reports.endTest(test);
    reports.flush();
    DriverSingleton.closeObjectInstance();
    }

}
