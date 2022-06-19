package interviewfrontendtest.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
    public static WebDriver driver;
    public static String browserName="chrome";

    public static void browserSetUp( String Url){
        if(driver==null){
            if(browserName.equalsIgnoreCase("chrome")){

                System.setProperty("webdriver.chrome.driver","c:\\webdriver\\chromedriver.exe");
                driver=new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(Url);

            }
            else if(browserName.equalsIgnoreCase("Fire fox")){
                System.setProperty("webdriver.gecko.driver","c:\\webdriver\\geckodriver.exe");
                driver=new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get(Url);
            }
        }
    }

    public static void closeBrowser(){
        driver.close();
        driver.quit();
    }


}


