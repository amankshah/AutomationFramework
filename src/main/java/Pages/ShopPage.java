package Pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonFunctions;
import utils.Constants;

public class ShopPage {
    private WebDriver driver;
    public ShopPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#main > ul > li.product.type-product.post-211.status-publish.first.instock.product_cat-uncategorized.purchasable.product-type-simple > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    private WebElement addToCartButton;

    @FindBy(css ="body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement productCount;

    @FindBy(css="body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private  WebElement cartButton;

    public void ShortProducts(){
        driver.get("https://bitheap.tech/shop/?orderby=price");
    };

    public void addProductToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        System.out.println("addToCartButton Button is displayed: "+ addToCartButton.isDisplayed());

        try {
            CommonFunctions.clickElement(addToCartButton);
        }catch(Exception e){
            System.out.println("Standard click failed, Adding to cart using Url");

            driver.get("https://bitheap.tech/shop/?add-to-cart=211");
        }
        Thread.sleep(2000);
        if(productCount.getText().contains(Constants.CART_QUANTITY)) {
            System.out.println("Product is added to the Cart");
        }else{
            System.out.println("Product Not added to cart");
        }
    }

    public void clickOnCartButton(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        System.out.println("cartButton is displayed: "+ cartButton.isDisplayed());
        cartButton.click();
    }






}
