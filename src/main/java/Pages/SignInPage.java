package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class SignInPage {
    private WebDriver driver;

    public SignInPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-el-fields-cont > div.xoo-aff-group.xoo-aff-cont-text.one.xoo-aff-cont-required.xoo-el-username_cont > div > input")
    private WebElement userNameFilled;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > div.xoo-el-fields-cont > div.xoo-aff-group.xoo-aff-cont-password.one.xoo-aff-cont-required.xoo-el-password_cont > div > input")
    private WebElement passwordFilled;

    @FindBy(css = "body > div.xoo-el-container.xoo-el-style-popup.xoo-el-popup-active > div.xoo-el-modal > div > div > div.xoo-el-srcont > div > div > div.xoo-el-section.xoo-el-active > div > form > button")
    private  WebElement loginButton;

    public void logIn(String username,String password){
        userNameFilled.sendKeys(username);
        passwordFilled.sendKeys(Utils.decode(password));
        loginButton.click();

    }

}
