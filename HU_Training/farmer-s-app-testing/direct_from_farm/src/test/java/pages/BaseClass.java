package pages;

import com.github.javafaker.Faker;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Faker faker;

    @BeforeMethod
    public static WebDriver initialSetup(){
        System.out.println("base class initial setup");
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver=new FirefoxDriver();
        Assert.assertNotEquals(driver,null,"firefox is not opened");
        driver.get("https://www.google.com/");//opening the site
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com/","Expected url is different");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        String log4jPath = System.getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jPath);

        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 20);

        faker = new Faker(new Locale("en-IND"));

        return driver;
    }
    @AfterMethod
    public void closeTest() {
        driver.close();
    }
}
