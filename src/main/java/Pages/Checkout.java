package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonFunctions;
import utils.Constants;
import utils.Utils;

import static utils.Constants.ORDER_STATUS;

public class Checkout {
    private WebDriver driver;
    public Checkout(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "billing_company")
    private WebElement company;

    @FindBy(id = "billing_address_1")
    private WebElement address;

    @FindBy(id = "billing_address_2")
    private WebElement address2;

    @FindBy(id = "billing_city")
    private WebElement city;

    @FindBy(id = "billing_country")
    private WebElement country;

    @FindBy(id = "billing_state")
    private WebElement state;

    @FindBy(id = "billing_postcode")
    private WebElement postcode;

    @FindBy(id = "billing_phone")
    private WebElement phone;

    @FindBy(id = "billing_email")
    private WebElement email;

    @FindBy(id = "place_order")
    private WebElement placeOrder;

    @FindBy(css = "header > h1")
    private WebElement orderStatus;

    @FindBy(css = ".woocommerce-order-overview__order strong")
    private WebElement orderNumber;

    public void fillAddressDetails() {

        try {
            CommonFunctions.waitUntilWebElementVisible(firstName);
            firstName.clear();
            firstName.sendKeys(Constants.FIRST_NAME);


            lastName.clear();
            lastName.sendKeys(Constants.LAST_NAME);

            company.clear();
            company.sendKeys(Constants.COMPANY);

            address.clear();
            address.sendKeys(Constants.ADDRESS);

            address2.clear();
            address2.sendKeys(Constants.ADDRESS_2);

            city.clear();
            city.sendKeys(Constants.CITY);

            postcode.clear();
            postcode.sendKeys(Constants.POSTCODE);

            CommonFunctions.selectByVisibleText(country, Constants.COUNTRY);

            phone.clear();
            phone.sendKeys(Constants.PHONE);

            email.clear();
            email.sendKeys(Constants.EMAIL);

            CommonFunctions.selectByVisibleText(state, Constants.STATE);
            System.out.println("Address Details are filled");
        } catch (Exception e) {
            System.out.println("Address Details are not filled");
            throw new RuntimeException(e);

        }
    }

    public void placeOrder() {
        CommonFunctions.waitUntilWebElementVisible(placeOrder);

        try {
            placeOrder.click();
        } catch (Exception e) {
            try {
                System.out.println("Standard click failed, attempting JavaScript click.");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", placeOrder);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }


        System.out.println("Order Placed");
    }


    public String  getOrderStatus() {
        CommonFunctions.waitUntilWebElementVisible(orderStatus);
        //Wait until the page loads
        CommonFunctions.waitUntilWebElementVisible(orderNumber);


        if (orderStatus.getText().equals(ORDER_STATUS)) {
            System.out.println("Order Status: " + orderStatus.getText());
            System.out.println("Order Number: " + orderNumber.getText());
//            utils.Utils.takeScreenshot();
            Utils.takeScreenshot("OrderPlaced",orderStatus);
            System.out.println("Conrgatuations, your order has been placed!🎉");
        }else{
            System.out.println("Order Status: " + orderStatus.getText());
        }

        return orderStatus.getText();

    }
}
