package automation.glue;

import automation.Pages.*;
import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import automation.utils.FrameworkProperties;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


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
    }

    @Given("^I go to the website")
    public void i_go_to_the_website() {
        driver=DriverSingleton.getDriver();
        driver.get(Constants.URL);

    }

    @When("^I click on Login Button")
    public void i_click_on_login_button() {
        homepage.clickLoginButton();
    }

    @When("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_login() {
        signInPage.logIn(configurationProperties.getSignInUser(), configurationProperties.getPassword());

    }
    @Then("^I can log into the website")
    public void i_can_log_into_the_website() {
        assertTrue(homepage.getDisplayName().contains("Hello"));
    }


    //Adding 2nd test
//    @When("^I go to the website")
//    public void i_go_to_the_website() {
//        driver=DriverSingleton.getDriver();
//        driver.get(Constants.URL);
//    }

    @When("^I click on Shop Button")
    public void i_click_on_shop_button() {
        homepage.clickShopButton();
    }

    @When("^I add product to cart")
    public void i_add_product_to_cart() throws InterruptedException {
        shopPage.ShortProducts();
        shopPage.addProductToCart();
    }

    @When("^I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order() {
        shopPage.clickOnCartButton();
        cartPage.clickOnProceedButton();
        checkout.fillAddressDetails();
        checkout.placeOrder();

    }

    @Then("^The element are bought")
    public void the_element_are_bought() {
        assertTrue(checkout.getOrderStatus().contains(Constants.ORDER_STATUS));
    }



}
