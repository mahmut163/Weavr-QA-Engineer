package interviewfrontendtest.pages;

import interviewfrontendtest.utils.TestUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    TestUtility testUtility;

    @FindBy(id="user-name")
    WebElement userNameField;

    @FindBy(id="password")
    WebElement passwordField;

    @FindBy(id="login-button")
    WebElement loginBUtton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }

    //action
    public boolean verifyLoginPageOpened(){
        testUtility.waitForElementPresent(loginBUtton);
        if(loginBUtton.isDisplayed())
            return true;
        else
            return false;
    }

    public void login(String userName , String passWord){
        testUtility.waitForElementPresent(userNameField);
        userNameField.sendKeys(userName);
        testUtility.waitForElementPresent(passwordField);
        passwordField.sendKeys(passWord);
        loginBUtton.click();
    }



}

