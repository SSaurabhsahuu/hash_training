package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class YourCartPage {
    WebDriver driver;

    public YourCartPage(WebDriver driver)
    {this.driver=driver;
    }

    public void continue_shopping() throws InterruptedException {
        Thread.sleep(700);
        driver.findElement(By.xpath("//button[@id=\"continue-shopping\"]")).click();
    }
    public void check_count_of_purchase()
    {
        int count=driver.findElements(By.xpath("(//div[@class=\"cart_item\"])")).size();
        String number = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).getText();
        String num=Integer.toString(count);

        Assert.assertEquals(num, number);
    }
    public void checkout() throws InterruptedException {
        driver.findElement(By.xpath("(//button[@id=\"checkout\"])")).click();
        Thread.sleep(3000);
    }

}
