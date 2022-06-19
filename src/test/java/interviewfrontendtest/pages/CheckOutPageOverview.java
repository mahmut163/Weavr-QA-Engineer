package interviewfrontendtest.pages;

import interviewfrontendtest.utils.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPageOverview {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(id = "checkout_summary_container")
    WebElement checkoutSummaryContainer;
    @FindBy(id = "finish")
    WebElement finishButton;
    @FindBy(id = "checkout_complete_container")
    WebElement checkOutCompleteContainer;

    @FindBy(css = "div.summary_subtotal_label")
    WebElement productPrice;

    public CheckOutPageOverview(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }
    public boolean verifyCheckOut(){
        testUtility.waitForElementPresent(checkoutSummaryContainer);
        return checkoutSummaryContainer.isDisplayed();

    }
    public void finishOrder(){
        testUtility.waitForElementPresent(finishButton);
        finishButton.click();
    }

    public double getProductPrice() {
        testUtility.waitForElementPresent(productPrice);
        String subTotalLabel = productPrice.getText();
        String strPrice = subTotalLabel.substring(subTotalLabel.indexOf("$")+1);
        return Double.parseDouble(strPrice);
    }

    @FindBy (css = ".complete-header")
    WebElement completeMessage;

    public boolean isOrderSuccessful() {

        testUtility.waitForElementPresent(completeMessage);
        return completeMessage.getText().contains("THANK YOU FOR YOUR ORDER");
    }
}
