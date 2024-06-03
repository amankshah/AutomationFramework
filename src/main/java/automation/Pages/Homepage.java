package automation.Pages;

import automation.drivers.DriverSingleton;
import automation.utils.Utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.CommonFunctions;

import static automation.utils.Utils.takeScreenshot;

public class Homepage {
    private WebDriver driver;

    public Homepage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menu-item-2017")
    private WebElement loginButton;

    @FindBy(id = "menu-item-1310")
    private WebElement shopButton;

    @FindBy(id = "menu-item-2020")
    private WebElement displayName;

    @FindBy(id = "mp_form_fixed_bar1")
    private WebElement mpFormFixedBar;

    @FindBy(css = "#mp_form_fixed_bar1 > input")
    private WebElement mpFormFixedBarCloseButton;

    public void clickLoginButton() {
        try {
            if (mpFormFixedBar.isDisplayed()) {
                System.out.println("mpFormFixedBar is displayed");
                System.out.println("mpFormFixedBarCloseButton is displayed: " + mpFormFixedBarCloseButton.isDisplayed());
                mpFormFixedBarCloseButton.click();
                System.out.println("mpFormFixedBarCloseButton is clicked");
            } else {
                System.out.println("mpFormFixedBar is not displayed");
            }

            // Wait for the page to adjust after zoom
            Thread.sleep(2000);

            WebDriverWait wait = new WebDriverWait(driver, 25);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            System.out.println("Login Button is displayed: " + loginButton.isDisplayed());

            try {

                try {
                    loginButton.click();
                } catch (Exception e) {
                    takeScreenshot();

                    CommonFunctions.clickElement(loginButton);
                }
            } catch (Exception e) {
                System.out.println("Standard click failed, attempting JavaScript click.");
                takeScreenshot();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", loginButton);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickShopButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        System.out.println("shopButton is displayed: " + shopButton.isDisplayed());
        try {
            shopButton.click();
        } catch (Exception e) {
            CommonFunctions.clickElement(shopButton);
            takeScreenshot();
            throw new RuntimeException(e);
        }

    }

    public String getDisplayName() {
        takeScreenshot("displayName", displayName,true);
        return displayName.getText();
    }
}
