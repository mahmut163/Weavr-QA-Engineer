package interviewfrontendtest;

import interviewfrontendtest.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import interviewfrontendtest.utils.ApplicationConfig;
import interviewfrontendtest.utils.TestBase;

public class TestRunner extends TestBase {

    private final String PRODUCT_NAME = "Sauce Labs Onesie";

    LoginPage loginPage;
    DashboardPage dashboardPage;
    String userName = ApplicationConfig.readFromConfigProperties("config.properties", "username");
    String passWord = ApplicationConfig.readFromConfigProperties("config.properties", "password");
    String Url = ApplicationConfig.readFromConfigProperties("config.properties", "url");
    String firstName = ApplicationConfig.readFromConfigProperties("config.properties", "firstName");
    String lastName = ApplicationConfig.readFromConfigProperties("config.properties", "lastName");
    String zipCode = ApplicationConfig.readFromConfigProperties("config.properties", "zipCode");

    CheckoutInformationPage checkoutInformationPage;
    CheckOutPageOverview checkOutPageOverview;

    @BeforeClass

    public void setUp() {
        browserSetUp(Url);
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.verifyLoginPageOpened());
        loginPage.login(userName, passWord);
        dashboardPage = new DashboardPage(driver);

    }

    @Test
    public void buyProducts() {

        dashboardPage.filterProducts(FilterContent.LOW_TO_HIGH);

        Assert.assertTrue(dashboardPage.isProductsAvailable());

        dashboardPage.selectProduct(PRODUCT_NAME);
        double previousPrice = dashboardPage.getProductPrice();
        dashboardPage.clickAddToCart();
        dashboardPage.clickShoppingCart();
        checkoutInformationPage = dashboardPage.clickCheckOut();
        checkoutInformationPage.setCheckOutInfo(firstName, lastName, zipCode);
        checkOutPageOverview = checkoutInformationPage.clickContinue();

        Assert.assertTrue(checkOutPageOverview.verifyCheckOut());

        double priceInCheckout = checkOutPageOverview.getProductPrice();
        Assert.assertEquals(previousPrice, priceInCheckout);

        checkOutPageOverview.finishOrder();
        Assert.assertTrue(checkOutPageOverview.isOrderSuccessful());

    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();

    }
}
