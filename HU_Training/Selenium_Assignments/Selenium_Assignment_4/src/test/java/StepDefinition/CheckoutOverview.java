package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CheckoutOverview {
    WebDriver driver;
    public CheckoutOverview(WebDriver driver)
    {this.driver=driver;
    }

    public void check_total_prize()
    {
        List<WebElement> ls=driver.findElements(By.xpath("//div[@class=\"item_pricebar\"]"));
        double count=0;
        for(WebElement wb : ls)
        {
            String str=wb.getText();

            String resultStr="";                     // remove $
                for (int i=1;i<str.length();i++)
                        resultStr=resultStr+str.charAt(i);

            double d=Double.parseDouble(resultStr);
            count+=d;
        }

        String str=driver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]")).getText();
        String resultStr="";                     // remove $
        for (int i=13;i<str.length();i++)
            resultStr=resultStr+str.charAt(i);

        String expected=Double.toString(count);

        Assert.assertEquals(resultStr, expected);
    }
    public void click_finish()
    {
        driver.findElement(By.xpath("//button[@id=\"finish\"]")).click();
    }
}
