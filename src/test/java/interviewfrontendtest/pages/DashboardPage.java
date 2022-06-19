package interviewfrontendtest.pages;

import interviewfrontendtest.utils.TestUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DashboardPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(id="react-burger-menu-btn")
    WebElement menuBtn;

    @FindBy(css = "select.product_sort_container")
    WebElement filterContainer;
    @FindBy(id = "inventory_container")
    WebElement productListTable;
    @FindAll(@FindBy(css = ".inventory_item"))
    List<WebElement> productsList;
    @FindBy(linkText = "Sauce Labs Onesie")
    WebElement productName;
    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement addToCartButton;
    @FindBy(id = "shopping_cart_container")
    WebElement shoppingCartContainer;
    @FindBy(id = "checkout")
    WebElement checkOutButton;
    @FindBy(css = ".inventory_details_price")
    WebElement productPrice;


    private static final String DIV_PRODUCT_FINDER = "//div[text() = '?']";


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    public boolean verifyLogin(){
        testUtility.waitForElementPresent(menuBtn);
        return menuBtn.isDisplayed();
    }

    public CheckoutInformationPage clickCheckOut() {
        testUtility.waitForElementPresent(checkOutButton);
        checkOutButton.click();
        return new CheckoutInformationPage(driver);
    }

    public void clickAddToCart() {
        testUtility.waitForElementPresent(addToCartButton);
        addToCartButton.click();
    }

    public void clickShoppingCart() {
        testUtility.waitForElementPresent(shoppingCartContainer);
        shoppingCartContainer.click();
    }

    public void filterProducts(FilterContent value) {
        Select select=new Select(filterContainer);
        select.selectByValue(value.getValue());
    }

    public void selectProduct(String productName) {
        getProduct(productName).click();
    }

    public WebElement getProduct(String productName) {

        WebElement product = driver.findElement(By.xpath(DIV_PRODUCT_FINDER.replace("?", productName)));
        testUtility.waitForElementPresent(product);
        return product;
    }

    public List<WebElement> getProductsList() {
        return productsList;
    }

    public boolean isProductsAvailable() {
        return !productsList.isEmpty();
    }

    public double getProductPrice () {
        testUtility.waitForElementPresent(productPrice);
        String strPrice = productPrice.getText().substring(1);
        return Double.parseDouble(strPrice);
    }




}
