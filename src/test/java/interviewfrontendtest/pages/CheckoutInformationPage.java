package interviewfrontendtest.pages;

import interviewfrontendtest.utils.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {

    private WebDriver driver;
    private TestUtility testUtility;
    @FindBy(id = "first-name")
    private WebElement firstNameField;
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    @FindBy(id = "postal-code")
    private WebElement zipCodeField;
    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    public void setFirstName(String firstName){
        testUtility.waitForElementPresent(firstNameField);
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        testUtility.waitForElementPresent(lastNameField);
        lastNameField.sendKeys(lastName);
    }

    public void setZipCode(String zipCode){
        testUtility.waitForElementPresent(zipCodeField);
        zipCodeField.sendKeys(zipCode);
    }

    public CheckOutPageOverview clickContinue() {
        testUtility.waitForElementPresent(continueButton);
        continueButton.click();
        return new CheckOutPageOverview(driver);
    }

    public void setCheckOutInfo(String firstName, String lastName, String zipCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setZipCode(zipCode);
    }




}
