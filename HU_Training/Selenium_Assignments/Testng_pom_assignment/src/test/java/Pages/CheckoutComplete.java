package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutComplete {
    WebDriver driver;


    public CheckoutComplete(WebDriver driver)
    {this.driver=driver;
    }
    public void check_success() throws InterruptedException {
        String original=driver.findElement(By.xpath("//h2[@class=\"complete-header\"]")).getText();
        String expected="THANK YOU FOR YOUR ORDER";
        Assert.assertEquals(original, expected);

    }
}
