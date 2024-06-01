package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonFunctions;

public class CartPage {
    private WebDriver driver;
    public CartPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".checkout-button")
    private WebElement proceedToCheckoutButton;

    public void  clickOnProceedButton(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        System.out.println("proceedToCheckoutButton Button is displayed: "+ proceedToCheckoutButton.isDisplayed());

        try {
            CommonFunctions.clickElement(proceedToCheckoutButton);
        } catch (Exception e) {
            try {
                System.out.println("Standard click failed, Adding to cart using Url");
                driver.get("https://bitheap.tech/checkout/");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
    }

}
