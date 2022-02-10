package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class YourCartPage {
    WebDriver driver;
    public static Logger logger=null;

    public YourCartPage(WebDriver driver)
    {this.driver=driver;
        logger=Logger.getLogger(LoginPage.class.getName());
    }

    public void continue_shopping() throws InterruptedException {
        logger.info("click continue shopping");

        driver.findElement(By.xpath("//button[@id=\"continue-shopping\"]")).click();
    }
    public void check_count_of_purchase()
    {    logger.info("check count of purchase");
        int count=driver.findElements(By.xpath("(//div[@class=\"cart_item\"])")).size();
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
        String num=Integer.toString(count);

        Assert.assertEquals(num, number);
    }
    public void checkout() throws InterruptedException {
        logger.info("checkout");
        driver.findElement(By.xpath("(//button[@id=\"checkout\"])")).click();

    }

}
