package Test;

import Pages.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTest.class)
public class AllTests {
    WebDriver driver;
    LoginPage login;
    AddCartPage cart;
    YourCartPage yourcart;
    CheckoutPage cop;
    CheckoutOverview checkoutoverview;
    CheckoutComplete coc;


    @BeforeTest
    public static void loadlog4()
    {
        String log4jpath=System.getProperty("user.dir")+"/log4j.properties";
        PropertyConfigurator.configure(log4jpath);
    }
    @BeforeTest
    public void setup() throws InterruptedException { System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }
    // Forcefully failed this test as to verify listener.
   //@Test
    public void TestToFail()
    {
        System.out.println("This method to test fail");
        Assert.assertTrue(false);
    }
    @Test
    public void main_fun() throws InterruptedException {
        login=new LoginPage(driver);
        String name="standard_user";
        String pass="secret_sauce";
        login.enterUsername(name);
        login.enterPassword(pass);
        login.clickLogin();

        cart=new AddCartPage(driver);
        cart.select_highest();
        String limit="100";
        cart.check_price(limit);
        cart.check_visibility_addtocart("Add to cart");
        cart.click_addtocart("Add to cart");
        cart.check_visibility_remove("Remove");
        cart.click_remove("Remove");
        cart.check_visibility_addtocart("Add to cart");
        cart.click_addtocart("Add to cart");
        cart.click_cart();

        yourcart= new YourCartPage(driver);
        yourcart.continue_shopping();

        cart.select_lowest();
        cart.click_addtocart("Add to cart");
        cart.click_cart();
        cart.click_checkout();

        cop=new CheckoutPage(driver);
        cop.enterFirstname("Saurabh");
        cop.enterLastname("Kumar");
        cop.enterPostalcode("345677");
        cop.clickContinue();


        checkoutoverview=new CheckoutOverview(driver);
        checkoutoverview.check_total_prize();
        checkoutoverview.click_finish();

        coc=new CheckoutComplete(driver);
        coc.check_success();

    }

    @AfterTest
    public void end()
    {
        driver.quit();
    }




}
