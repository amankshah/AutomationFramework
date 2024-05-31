package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

public class ShopPage {
    private WebDriver driver;
    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#main > ul > li a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart.added")
    private WebElement addToCartButton;

    @FindBy(css ="body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement productCount;

    @FindBy(css="body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private  WebElement cartButton;

    public void ShortProducts(){
        driver.get("https://bitheap.tech/shop/?orderby=price");
    };

    public void addProductToCart(){
        addToCartButton.click();
        if(productCount.getText().contains(Constants.CART_QUANTITY)) {
            System.out.println("Product is added to the Cart");
        }else{
            System.out.println("Product Not added to cart");
        }
    }

    public void clickOnCartButton(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        System.out.println("Login Button is displayed: "+ cartButton.isDisplayed());
        cartButton.click();
    }






}
