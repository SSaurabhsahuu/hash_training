package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class FarmerAddsProduct extends BaseClass{
    By add=By.xpath("//a[contains(text(),'Add')]");
    By option=By.xpath("(//mat-icon)[2]");
    //    By order_visibility=By.xpath("//h1[contains(text(),'163150919308462')]");
    String order_visibility="//h1[contains(text(),";
    By out_for_delivery=By.xpath("//button[@role='menuitem']");
    By pending_order=By.xpath("(//span[@class='mat-button-wrapper'])[1]");
    By order_id_farmer=By.xpath("(//h1)[2]");
    By status=By.xpath("(//h1)[4]");
    By completed_order=By.xpath("(//span[@class='mat-button-wrapper'])[2]");
    By view_orders=By.xpath("//a[contains(text(),'View Orders')]");
    By product_name=By.xpath("(//input[contains(@id,\"mat-input\")])[1]");
    By price=By.xpath("(//input[contains(@id,\"mat-input\")])[2]");
    By shipping_cost=By.xpath("(//input[contains(@id,\"mat-input\")])[3]");
    By stock=By.xpath("(//input[contains(@id,\"mat-input\")])[4]");
    By min_selling_quantity=By.xpath("(//input[contains(@id,\"mat-input\")])[5]");
    String unit="//span[text()=\"";
    String category="//span[text()=\"";
    String sub_category="//span[text()=\"";
    By dropdown1=By.xpath("(//div[contains(@id,\"mat-select\")])[1]");
    By dropdown2=By.xpath("(//div[contains(@id,\"mat-select\")])[2]");
    By dropdown3=By.xpath("(//div[contains(@id,\"mat-select\")])[3]");
    By next1=By.xpath("(//span[text()=\" Next \"])[1]");
    By next2=By.xpath("(//span[text()=\" Next \"])[2]");
    By uploadImage=By.xpath("//input[@id=\"fileupload\"]");
    By uploadResult=By.xpath("//span[text()=\"view\"]");
    By pincode=By.xpath("//input[contains(@id,\"mat-chip\")]");
    By confirm_pincode=By.xpath("//span[text()=\"Confirm Pincodes\"]");
    By my_products=By.xpath("//a[contains(text(),\" My Products\")]");
    String verify_product_name="//p[contains(text(),\"";

    public Logger logger = Logger.getLogger(this.getClass().getName());

    public void click_add()
    {    logger.info("click on add");
        wait.until(ExpectedConditions.visibilityOfElementLocated(add));
        WebElement ele=driver.findElement(add);
        ele.click();

    }
    public void click_view_order()
    {    logger.info("click on view order");
        wait.until(ExpectedConditions.visibilityOfElementLocated(view_orders));
        WebElement ele=driver.findElement(view_orders);
        ele.click();

    }

    public void click_pending_order()
    {    logger.info("click on pending order");
        wait.until(ExpectedConditions.visibilityOfElementLocated(pending_order));
        WebElement ele=driver.findElement(pending_order);
        ele.click();

    }

    public void validateOrderId(String id){
        wait.until(ExpectedConditions.visibilityOfElementLocated(order_id_farmer));
        WebElement ele=driver.findElement(order_id_farmer);
        String order_id=ele.getText();
        Assert.assertEquals(order_id.toLowerCase(),id.toLowerCase());

    }

    public void enterProductname(String name) throws InterruptedException {
        logger.info("Entering Productname");
        WebElement element=driver.findElement(product_name);
        element.clear();
        element.sendKeys(name);

    }
    public void enter_Price_per_unit(String name) throws InterruptedException {
        logger.info("Entering Price_per_unit");
        WebElement element=driver.findElement(price);
        element.clear();
        element.sendKeys(name);

    }
    public void enter_shipping_cost(String name) throws InterruptedException {
        logger.info("Entering shipping_cost ");
        WebElement element=driver.findElement(shipping_cost);
        element.clear();
        element.sendKeys(name);

    }
    public void enter_current_stock(String name) throws InterruptedException {
        logger.info("Entering current_stock");
        WebElement element=driver.findElement(stock);
        element.clear();
        element.sendKeys(name);

    }
    public void enter_minimum_selling_quantity(String name) throws InterruptedException {
        logger.info("Entering minimum_selling_quantity");
        WebElement element=driver.findElement(min_selling_quantity);
        element.clear();
        element.sendKeys(name);

    }
    public void select_unit(String choice) throws InterruptedException {
        logger.info("select unit");
        WebElement ele=driver.findElement(dropdown1);
        ele.click();
        WebElement element=driver.findElement(By.xpath(unit+choice+"\"]"));
        element.click();

    }
    public void select_category(String choice) throws InterruptedException {
        logger.info("select category");
        WebElement ele=driver.findElement(dropdown2);
        ele.click();
        WebElement element=driver.findElement(By.xpath(category+choice+"\"]"));
        element.click();

    }
    public void select_sub_category(String choice) throws InterruptedException {
        logger.info("select sub_category");
        WebElement ele=driver.findElement(dropdown3);
        ele.click();
        WebElement element=driver.findElement(By.xpath(sub_category+choice+"\"]"));
        element.click();

    }
    public void clickOnNext_1(){

        WebElement nextButton=driver.findElement(next1);
        nextButton.click();

    }
    public void clickOnNext_2(){

        WebElement nextButton=driver.findElement(next2);
        nextButton.click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }
    public void uploadImage(String str) throws InterruptedException {

        logger.info("Uploading images");
        WebElement upload_file = driver.findElement(uploadImage);
        System.out.println(System.getProperty("user.dir")+"\\images\\Onion.jpg");
        upload_file.sendKeys(System.getProperty("user.dir")+"\\images\\Onion.jpg");
        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadResult));
    }
    public void click_confirm_pincode()
    {    logger.info("click on confirm_pincode");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirm_pincode));
        WebElement ele=driver.findElement(confirm_pincode);
        ele.click();

    }
    public void enter_pincode(String name) throws InterruptedException {
        logger.info("Enter pincode");
        WebElement element=driver.findElement(pincode);
        element.clear();
        element.sendKeys(name);

    }
    public void click_on_my_product(String actual)
    {    logger.info("click on my_product");
        WebElement ele=driver.findElement(my_products);
        ele.click();

        System.out.println(verify_product_name+actual+"\")]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(verify_product_name+actual+"\")]")));
        String result=driver.findElement(By.xpath(verify_product_name+actual+"\")]")).getText();
        System.out.println(result);
        Assert.assertEquals(result,"Product Name: "+actual);

    }

    public void check_for_status(String sts){
        logger.info("Checking for status");
        Assert.assertEquals(driver.findElement(status).getText(),sts);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

    }

    public void click_on_option(){
        WebElement ele=driver.findElement(option);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("product image is not displayed");

    }

    public void click_on_out_for_delivery(){
        WebElement ele=driver.findElement(out_for_delivery);
        if(ele.isDisplayed())
            ele.click();
        else
            logger.fatal("out for delivery is not displayed");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    public void validate_complete_order(String order_id){
        logger.info("click on complete order");
        wait.until(ExpectedConditions.visibilityOfElementLocated(completed_order));
        WebElement ele=driver.findElement(completed_order);
        ele.click();

        String[] orId=order_id.split(" ");
        order_visibility=order_visibility+ "'"+orId[orId.length-1]+"')]";
        System.out.println(order_visibility);
        Assert.assertTrue(driver.findElement(By.xpath(order_visibility)).isDisplayed());
        logger.info("Order is available in complete order");
    }
}
