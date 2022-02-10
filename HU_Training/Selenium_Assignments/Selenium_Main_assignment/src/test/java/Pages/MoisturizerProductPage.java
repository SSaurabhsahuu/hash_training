package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MoisturizerProductPage {
    WebDriver driver;
    public static Logger logger=null;
    By icon=By.xpath("//span[@data-toggle='popover']");
    By task=By.xpath("//div[@class=\"popover-body\"]");

    By aloe_price=By.xpath("//p[contains(text(),'Aloe')]/following-sibling::p");
    By aloe_button=By.xpath("//p[contains(text(),'Aloe')]/following-sibling::button");

    By almond_price=By.xpath("//p[contains(text(),'Almond')]/following-sibling::p");
    By almond_button=By.xpath("//p[contains(text(),'Almond')]/following-sibling::button");
    By cart_button=By.xpath("//span[@id='cart']");

    public MoisturizerProductPage(WebDriver driver)
    {   this.driver=driver;
        logger=Logger.getLogger(MoisturizerProductPage.class.getName());
    }
    public void click_i_icon()
    {
        logger.info("clcik i icon");
        driver.findElement(icon).click();
    }
    public int get_price_from_string(String Price_string)  // removes alphabetic chararters and other symbols
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
    public void add_to_cart(By item_price,By item_button)
    {    logger.info("add to cart");

        List<WebElement> wb_price=driver.findElements(item_price);  // list of price

        String Price_string= wb_price.get(0).getText();
        int price=get_price_from_string(Price_string);

        int selected=0; // index of least priced item

        for(int i=1;i<wb_price.size();i++)   // gives index of least priced item
        {   String temp= wb_price.get(i).getText();

            int int_price=get_price_from_string(temp);

            if(price>int_price) {
                price = int_price;
                selected=i;
            }
        }
        for(int i=0;i<wb_price.size();i++)   // click least priced item
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

        add_to_cart(aloe_price,aloe_button);      // adds least priced aloe
        add_to_cart(almond_price,almond_button);  // adds least priced almond
    }
    public void click_on_cart()
    {    logger.info("click on cart");
        driver.findElement(cart_button).click();
    }
}
