package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    private WebDriver driver;

    public SignInPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div:nth-child(2) > div > div:nth-child(2) > input");
    private WebElement userNameFilled;

    @FindBy(css = "#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div:nth-child(2) > div > div:nth-child(2) > input");
    private WebElement passwordFilled;

    @FindBy(css = "#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div.oxd-form-actions.orangehrm-login-action > button");
    private  WebElement loginButton;

    public void logIn(String username,String password){
        userNameFilled.sendKeys(username);
        passwordFilled.sendKeys(password);
        loginButton.click();

    }

}
