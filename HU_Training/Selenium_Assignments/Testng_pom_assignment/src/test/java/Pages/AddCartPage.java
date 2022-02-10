package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AddCartPage {
    WebDriver driver;
    public static Logger logger=null;

    public AddCartPage(WebDriver driver)
    {   this.driver=driver;
        logger=Logger.getLogger(LoginPage.class.getName());
    }
    public void select_highest() throws InterruptedException {
        logger.info("Select highest priced item");

        driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]//option[1]")).click();
        Select obj=new Select( driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]") ));

        obj.selectByVisibleText("Price (high to low)");
    }
    public void select_lowest() throws InterruptedException {
        logger.info("Select lowest priced item");

        driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]//option[1]")).click();

        Select obj=new Select( driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]") ));

        obj.selectByVisibleText("Price (low to high)");
    }
    public void check_price(String limit) throws InterruptedException {
        logger.info("check price in limit");
        String text = driver.findElement(By.xpath("//div[@class=\"inventory_item_price\"][1]")).getText();

        String resultStr="";                     // remove $
        for (int i=1;i<text.length();i++)
            resultStr=resultStr+text.charAt(i);

        double d1=Double.parseDouble(resultStr);
        double d2=Double.parseDouble(limit);
        if(d1>d2)
            Assert.assertEquals(resultStr, limit);

    }
    public void check_visibility_addtocart(String element)
    {
        logger.info("check visibility of add to  cart");
        String expected ="ADD TO CART";
        String original = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).getText();
        Assert.assertEquals(original, expected);
    }

    public void click_addtocart(String element) throws InterruptedException {Thread.sleep(700);
        logger.info("click add to cart");
        driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();

    }
    public void check_visibility_remove(String element)
    {   logger.info("check visibility of remove");
        String expected ="REMOVE";
        String original = driver.findElement(By.xpath("//button[text()='Remove']")).getText();
        Assert.assertEquals(original, expected);
    }
    public void click_remove(String element) throws InterruptedException {Thread.sleep(700);
        logger.info("click remove");
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

    }
    public void click_cart()
    {  logger.info("click on cart");
        driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]")).click();
    }
    public int return_number_of_items()
    {   logger.info("return number of items in cart");
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
       return Integer.parseInt(number);
    }
    public void check_increment(int num1) throws InterruptedException {
        logger.info("check increment");
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
        int num2=Integer.parseInt(number);
        if(num1+1 == num2)
           this.click_cart();
        else
        {  JavascriptExecutor js= (JavascriptExecutor)driver;
            // reload
            js.executeScript("document.location.reload()");

            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    }
    public void click_checkout() throws InterruptedException {
        logger.info("click checkout");
        driver.findElement(By.xpath("//button[@id=\"checkout\"]")).click();

    }
}
