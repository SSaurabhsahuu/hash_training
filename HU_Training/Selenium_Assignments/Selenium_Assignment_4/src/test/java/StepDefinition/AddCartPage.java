package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AddCartPage {
    WebDriver driver;


    public AddCartPage(WebDriver driver)
    {this.driver=driver;
    }
    public void select_highest() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]//option[1]")).click();
        Select obj=new Select( driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]") ));
        Thread.sleep(3000);
        obj.selectByVisibleText("Price (high to low)");
    }
    public void select_lowest() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]//option[1]")).click();

        Select obj=new Select( driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]") ));
        Thread.sleep(3000);
        obj.selectByVisibleText("Price (low to high)");
    }
    public void check_price(String limit) throws InterruptedException {
        String text = driver.findElement(By.xpath("//div[@class=\"inventory_item_price\"][1]")).getText();

        String resultStr="";                     // remove $
        for (int i=1;i<text.length();i++)
            resultStr=resultStr+text.charAt(i);

        double d1=Double.parseDouble(resultStr);
        double d2=Double.parseDouble(limit);
        if(d1>d2)
            Assert.assertEquals(resultStr, limit);
        Thread.sleep(3000);
    }
    public void check_visibility_addtocart(String element)
    {

        String expected ="ADD TO CART";
        String original = driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).getText();
        Assert.assertEquals(original, expected);
    }

    public void click_addtocart(String element) throws InterruptedException {Thread.sleep(700);
        driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
        Thread.sleep(3000);
    }
    public void check_visibility_remove(String element)
    {
        String expected ="REMOVE";
        String original = driver.findElement(By.xpath("//button[text()='Remove']")).getText();
        Assert.assertEquals(original, expected);
    }
    public void click_remove(String element) throws InterruptedException {Thread.sleep(700);
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        Thread.sleep(3000);
    }
    public void click_cart()
    { driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]")).click();
    }
    public int return_number_of_items()
    {
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
       return Integer.parseInt(number);
    }
    public void check_increment(int num1) throws InterruptedException {
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
        int num2=Integer.parseInt(number);
        if(num1+1 == num2)
           this.click_cart();
        else
        {  JavascriptExecutor js= (JavascriptExecutor)driver;
            // reload
            js.executeScript("document.location.reload()");
            Thread.sleep(3000);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    }
    public void click_checkout() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id=\"checkout\"]")).click();
        Thread.sleep(3000);
    }
}
