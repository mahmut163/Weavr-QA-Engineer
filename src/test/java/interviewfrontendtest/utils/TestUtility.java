package interviewfrontendtest.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestUtility {
    private static int timeout=Integer.parseInt(ApplicationConfig.readFromConfigProperties(
            "config.properties","timeout"
    ));
   static Faker faker=new Faker();
    private static WebDriver driver;


    public TestUtility(WebDriver driver) {
        this.driver = driver;
    }
    //implcit wait
    public static void implicitWait(int second){
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }
    //explicit wait
    public  void waitForElementPresent(WebElement element ){
        WebDriverWait wait=new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String generateName(){
        String name=faker.name().firstName();
        return name;
    }

    public static String generateEmail(){
        String email = generateName() + "@gmail.com";
        return email;
    }
}
