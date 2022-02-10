package Test;

import Pages.*;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTest.class)   // to test buy moisturizer
public class MoisturizerTests {
        WebDriver driver;
        BaseClass base;
        HomePage home;
        MoisturizerProductPage product;
        CheckoutPage checkout;
        ConfirmationPage confirm;

        @BeforeTest
        public static void loadlog4()
        {
            String log4jpath=System.getProperty("user.dir")+"/log4j.properties";
            PropertyConfigurator.configure(log4jpath);
        }
        @BeforeTest
        public void setup()  {
            base = new BaseClass();
            driver= base.initialSetup();

            home = new HomePage(driver);
            product = new MoisturizerProductPage(driver);
            checkout = new CheckoutPage(driver);
            confirm=new ConfirmationPage(driver);

        }
        @Test(priority = 1)
        public void home_function() throws InterruptedException {

            home.click_on_moisturizer();
            Reporter.log("homepage completed");
        }
        @Test(priority = 2)
        public void add_product_function(){

            product.click_i_icon();
            product.perform_task();  // adds least priced aloe and almond moisturizer
            product.click_on_cart();
        }
        @Test(priority = 3)
        public void checkout_function() throws InterruptedException {

            checkout.check_total_prize();
            checkout.click_pay();
            checkout.enter_card_details();     // to enter details from .csv file
           /* String[] card_details = {"abc@gmail.com","2223 0031 2200 3222", "3/23", "123",""};
            checkout.enter_card_details(card_details); // to enter details particular card details*/
            checkout.confirm_pay();
        }
        @Test(priority = 4)
        public void confirm_function() throws InterruptedException {

            confirm.check_result();
        }
        @AfterTest
        public void end_function() throws InterruptedException {
            //Thread.sleep(1000);
            base.closeBrowser();
        }
}


