package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
    private WebDriver driver;

    public Homepage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#menu-item-2017 > a")
    private WebElement loginButton;

    @FindBy(id ="menu-item-1310")
    private WebElement shopButton;

    @FindBy(id = "menu-item-2020")
    private WebElement username;

    public void clickLoginButton(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        System.out.println("Login Button is displayed: "+ loginButton.isDisplayed());
        loginButton.click();

    }

    public void clickShopButton(){
        WebDriverWait wait;
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        System.out.println("shopButton  is displayed: "+ shopButton.isDisplayed());
        shopButton.click();
    }

    public String getUserName(){
        return username.getText();
    }



}
