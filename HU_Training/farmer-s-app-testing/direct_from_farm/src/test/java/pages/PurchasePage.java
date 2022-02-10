package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class PurchasePage extends BaseClass{

    By select_category;
    By select_product;
    By otp=By.xpath("(//p[@class='ng-star-inserted'])[1]");
    By enter_otp=By.xpath("//input[@class='ng-untouched ng-pristine ng-valid']");
    By submit_otp=By.xpath("//button[@role='menuitem']");
    By review=By.xpath("//textarea");
    By rating_star=By.xpath("(//span[@class='ng-star-inserted'])[10]");
    By btn_add_review=By.xpath("//button[@class='mat-focus-indicator mat-flat-button mat-button-base mat-primary']");
    By product_image=By.xpath("//img[@class='mat-card-image']");
    By product_name=By.xpath("//mat-card-title[@class='mat-card-title']");
    By product_title=By.xpath("//h1");
    By getOrder_history_id=By.xpath("(//h2[contains(text(),'Order')])[1]");
    By logout=By.xpath("//mat-icon[text()='logout']");
    By order_history=By.xpath("//button[@routerlink='/user/order-history']");
    By select_pincode=By.xpath("//input[contains(@id,\"mat-input\")]");
    By ok_button=By.xpath("//span[text()=\" Ok \"]");
    By increase_quantity=By.xpath("//mat-icon[contains(@class,\"quantity right\")]");
    By getname=By.xpath("//mat-card-title[@class=\"mat-card-title\"]");
    By getprice=By.xpath("//span[@class=\"product-price\"]");
    String name_from_cart="//h1[contains(text(),\"";
    By price_from_cart=By.xpath("(//h3)[1]");
    By quantity_from_cart=By.xpath("//span[contains(@class,\"quantity value\")]");
    By delivery_cost=By.xpath("(//h3)[2]");
    By profile_logo=By.xpath("//mat-icon[@aria-label='user profile avatar']");
    String subtotal="(//h3//span)";
    String orderIdPath="(//span[contains(text(),'Order Id')])[1]";
    By total_cost_cart=By.xpath("//p[contains(text(),\"Total Cart Value\")]");
    By add_to_cart=By.xpath("//button[@class=\"mat-focus-indicator mat-flat-button mat-button-base mat-primary\"]");
    By cart=By.xpath("//mat-icon[text()=\"shopping_cart\"]");
    By select_delivery_address=By.xpath("//span[text()=\"Select Delivery Address \"]//ancestor::button");
    By select_radio=By.xpath("(//mat-radio-button[@class=\"mat-radio-button mat-primary\"])[1]");
    By select_payment_option=By.xpath("//span[text()=\" Pay on Delivery \"]");
    By message=By.xpath("(//div//h1)[1]");
    By item_title=By.xpath("//mat-card-title");

    public Logger logger = Logger.getLogger(this.getClass().getName());
    public void click_Prduct_category(String category_name) throws InterruptedException {
        logger.info("click on Prduct_category");
        select_category=By.xpath("//h1[text()=\""+category_name+"\"]/ancestor::mat-card");
        WebElement ele=driver.findElement(select_category);
        ele.click();

    }
    public void click_Prduct(String product_name)
    {    logger.info("click on Prduct");
        select_product=By.xpath("//h2[text()=\""+product_name+"\"]//ancestor::mat-card//img");
        WebElement ele=driver.findElement(select_product);
        ele.click();
    }
    public void click_add_to_cart() throws InterruptedException {
        logger.info("click on add_to_cart");
        WebElement ele=driver.findElement(add_to_cart);
        ele.click();
        //Thread.sleep(2000);
    }
    public void enter_pincode(String pin)
    {
        logger.info(" Enter pincode ");
        WebElement ele=driver.findElement(select_pincode);
        ele.clear();
        ele.sendKeys(pin);
    }
    public void click_ok() throws InterruptedException {
        logger.info("click on Ok");
       // Thread.sleep(2000);
        WebElement ele=driver.findElement(ok_button);
        ele.click();
    }
    public void click_increase_quantity(int number) throws InterruptedException {
        logger.info("click on increase_quantity");
       // Thread.sleep(2000);
        WebElement ele=driver.findElement(increase_quantity);

        for(int i=1;i<number;i++)
            ele.click();
    }
    public String get_product_name()
    {
        logger.info("get_product_name");

        return driver.findElement(getname).getText();
    }
    public String get_product_price()
    {
        logger.info("get_product_price");

        return returnNumber(driver.findElement(getprice).getText());
    }
    public String returnNumber(String str)		// removes quotes from data retrived from .csv file
    {String resultStr="";

        for (int i=0;i<str.length();i++)
        {
            if (str.charAt(i)=='.' || (str.charAt(i)>='0' && str.charAt(i)<='9'))
                resultStr=resultStr+str.charAt(i);
        }
        return resultStr;
    }
    public void validate_item_details(String name,String price,int quantity)
    {
        logger.info("validate_item_details");
        String name_cart=driver.findElement(By.xpath(name_from_cart+name+"\")]")).getText();
        String price_cart=returnNumber(driver.findElement(price_from_cart).getText());
        String quantity_cart=returnNumber(driver.findElement(quantity_from_cart).getText());
        String d_cost=returnNumber(driver.findElement(delivery_cost).getText());
        String subtotal_cart=returnNumber(driver.findElement(By.xpath(subtotal+"[1]")).getText());

        double calculated_subtotal= Double.parseDouble(price_cart)*Double.parseDouble(quantity_cart)+Double.parseDouble(d_cost);

        Assert.assertEquals(name,name_cart);
        Assert.assertEquals(price,price_cart);
        Assert.assertEquals(String.valueOf(quantity),quantity_cart);
        Assert.assertTrue(calculated_subtotal ==  Double.parseDouble(subtotal_cart));


    }
    public void click_on_cart()
    {
        logger.info("click on cart");
        WebElement ele=driver.findElement(cart);
        ele.click();
    }
    public void click_on_delivery_address()
    {
        logger.info("click on delivery_address");
        WebElement ele=driver.findElement(select_delivery_address);
        ele.click();
    }
    public void click_on_radio()
    {
        logger.info("click on ");
        WebElement ele=driver.findElement(select_radio);
        ele.click();
    }
    public void click_delivery_method() throws InterruptedException {
        logger.info("click on delivery_method");
        WebElement ele=driver.findElement(select_payment_option);
        ele.click();
       // Thread.sleep(2000);

    }
    public String validate_order_result()
    {
        logger.info("click on order_result");

        String result=driver.findElement(message).getText();

        Assert.assertEquals(result, "Order Placed Successfully");
        String orderIdText=driver.findElement(By.xpath(orderIdPath)).getText();
        return orderIdText;
    }
    public void validate_item(String expected)
    {
        logger.info("validate item in product");
        String actual=driver.findElement(item_title).getText();
        Assert.assertTrue(actual.contains(expected));

    }
    public void result_page(String result) throws InterruptedException {
      //  Thread.sleep(3000);
        String url=driver.getCurrentUrl();
        Assert.assertEquals(result, url);
    }
    public void check_total_subtotal()
    {
        logger.info("click on check_total_subtotal");
        int size=driver.findElements(By.xpath(subtotal)).size();
        String total_price_cart=returnNumber(driver.findElement(total_cost_cart).getText());
        double total_price=0.00;
        for(int i=1;i<=size;i++)
        {
            String subtotal_price=returnNumber(driver.findElement(By.xpath(subtotal+"["+i+"]")).getText());
            total_price+=Double.parseDouble(subtotal_price);
        }

        Assert.assertEquals(String.valueOf(total_price)+"0", total_price_cart);

    }
    public void click_on_profile_logo()
    {

        logger.info("click on profile logo");
        WebElement ele=driver.findElement(profile_logo);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("profiel logo is not displayed");

    }

    public void click_on_order_history()
    {

        logger.info("click on orderHistory");
        WebElement ele=driver.findElement(order_history);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("order history is not displayed");

    }

    public void click_on_logout()
    {

        logger.info("click on logout");
        WebElement ele=driver.findElement(logout);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("logout is not displayed");

    }
    public void validate_order_history(String id)
    {

        logger.info("click on order_result");

        String orderIdText=driver.findElement(getOrder_history_id).getText();
        String oId[]=orderIdText.split("\n");
        Assert.assertEquals(oId[0].toLowerCase(),id.toLowerCase());
    }
    public String click_on_product_image()
    {
        logger.info("click on product image");
        String name=driver.findElement(product_name).getText();
        WebElement ele=driver.findElement(product_image);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("product image is not displayed");

        return name;
    }

    public void validate_product_name(String name)
    {

        logger.info("Validating product name in rating page");

        Assert.assertEquals(driver.findElement(product_title).getText().toLowerCase(),name.toLowerCase());
    }

    public void give_rating()
    {
        logger.info("Giving rating to product");
        WebElement ele=driver.findElement(rating_star);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("rating is not displayed");
        WebElement element=driver.findElement(review);
        element.sendKeys(Keys.TAB);
        element.clear();
        element.sendKeys("Good Product");
        driver.findElement(btn_add_review).click();

    }

    public String get_otp(){
        String[] orderOtp =driver.findElement(otp).getText().split(":");

        return orderOtp[orderOtp.length-1];
    }

    public void entering_otp(String otpNo){
        WebElement element=driver.findElement(enter_otp);
        element.clear();
        element.sendKeys(otpNo);

        WebElement ele=driver.findElement(submit_otp);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("rating is not displayed");
    }
}
