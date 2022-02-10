package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SunscreenProductPage {
    WebDriver driver;
    public static Logger logger=null;
    By icon=By.xpath("//span[@data-toggle='popover']");
    By task=By.xpath("//div[@class=\"popover-body\"]");

    By SPF_50_price=By.xpath("//p[contains(text(),'SPF-50')]/following-sibling::p");
    By SPF_50_button=By.xpath("//p[contains(text(),'SPF-50')]/following-sibling::button");

    By SPF_30_price=By.xpath("//p[contains(text(),'SPF-30')]/following-sibling::p");
    By SPF_30_button=By.xpath("//p[contains(text(),'SPF-30')]/following-sibling::button");
    By cart_button=By.xpath("//span[@id='cart']");

    public SunscreenProductPage(WebDriver driver)
    {   this.driver=driver;
        logger=Logger.getLogger(MoisturizerProductPage.class.getName());
    }
    public void click_i_icon()
    {
        logger.info("clcik i icon");
        driver.findElement(icon).click();
    }
    public int get_price_from_string(String Price_string)// removes alphabetic chararters and other symbols
    {
        logger.info("get price from string");
        String resultStr="";

        for (int i=0;i<Price_string.length();i++)
        {
            if (Price_string.charAt(i)>='0' && Price_string.charAt(i)<='9')
                resultStr=resultStr+Price_string.charAt(i);
        }

        return Integer.parseInt(resultStr);
    }
    public void add_to_cart(By item_price,By item_button) // select least priced item and add to cart
    {    logger.info("add to cart");
        List<WebElement> wb_price=driver.findElements(item_price); // list of price

        String Price_string= wb_price.get(0).getText();
        int price=get_price_from_string(Price_string);

        int selected=0; // index of least priced item

        for(int i=1;i<wb_price.size();i++)  // gives index of least priced item
        {   String temp= wb_price.get(i).getText();

            int int_price=get_price_from_string(temp);

            if(price>int_price) {
                price = int_price;
                selected=i;
            }
        }
        for(int i=0;i<wb_price.size();i++)  // click least priced item
        {
            if(i==selected)
            {
                List<WebElement> button= driver.findElements(item_button);
                button.get(i).click();
            }
        }

    }
    public void perform_task()
    {    logger.info("perform task");

        add_to_cart(SPF_50_price,SPF_50_button);   // adds least priced spf 50
        add_to_cart(SPF_30_price,SPF_30_button);   // adds least priced spf 30
    }
    public void click_on_cart()
    {    logger.info("click on cart");
        driver.findElement(cart_button).click();
    }
}
