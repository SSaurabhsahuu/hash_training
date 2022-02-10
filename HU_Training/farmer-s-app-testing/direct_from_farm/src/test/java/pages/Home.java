package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Home extends BaseClass{
    public static String url="https://farmersapp-frontend-amxbp6pvia-as.a.run.app/"+"market";
    public By imgXpath= By.xpath("//img[@class='router-link-active']");
    By login_nav=By.xpath("(//button[@class=\"mat-focus-indicator mat-flat-button mat-button-base mat-primary\"])[2]");
    By register_nav=By.xpath("(//button[@class=\"mat-focus-indicator mat-flat-button mat-button-base mat-primary\"])[1]");
    By sell_with_us_nav=By.xpath("//button[@class='mat-focus-indicator farmer-reg-btn mat-stroked-button mat-button-base mat-primary']");
    By image_xpath=By.xpath("//img[@class='img-fluid']");
    By arrow_button=By.xpath("//button[@class='mat-focus-indicator mat-icon-button mat-button-base mat-accent']");
    By product_category_text=By.xpath("//h1[text()='Product Categories']");
    By back_to_top=By.xpath("//button[@class='mat-focus-indicator mat-flat-button mat-button-base']");
    List<WebElement> button_list;
    By select_category;
    By logo=By.xpath("(//img[@type='submit'])[2]");
    public Logger logger = Logger.getLogger(this.getClass().getName());


    public void check_if_icon_and_text_is_visible(){
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        List<WebElement> imgList=driver.findElements(imgXpath);
        for (WebElement webElement : imgList) {
            if (!webElement.isDisplayed()) {
                logger.fatal("Logo not displayed");
            }
            logger.info("Logo is displayed successfully");
        }
    }

    public void check_if_nav_button_is_visible(){
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        if(driver.findElement(login_nav).isDisplayed()){
            logger.info("login button in the nav bar is visible");
        }
        else{
            logger.fatal("Login button in the nav bar is not visible");
        }
        if(driver.findElement(register_nav).isDisplayed()){
            logger.info("Register button in the nav bar is visible");
        }
        else{
            logger.fatal("Register button in the nav bar is not visible");
        }
        if(driver.findElement(sell_with_us_nav).isDisplayed()){
            logger.info("Sell with us nav button in the nav bar is visible");
        }
        else{
            logger.fatal("Sell with us nav button in the nav bar is not visible");
        }
    }

    public void check_if_all_image_are_displayed(){

        List<WebElement> images=driver.findElements(image_xpath);
        int count=0;
        for (WebElement image : images) {
            if (image.isDisplayed()) {
                count += 1;
            }
        }
        if(count==images.size()){
            logger.info("All the image is displayed in the home page");
        }
        else{
            logger.fatal("All the image in the home page is not displayed");
        }
    }

    public void check_if_above_arrow_is_clicked_and_displayed(){

        button_list=driver.findElements(arrow_button);
        for (int i=0;i<2;i++) {

            if (button_list.get(i).isDisplayed()) {
                logger.info("arrow is displayed");
                button_list.get(i).click();
                logger.info("arrow is clicked");
            } else
                logger.fatal("arrow button is not displayed");
        }
    }

    public void check_if_down_arrow_is_clicked_and_displayed(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        for (int i=2;i<button_list.size();i++) {
            if (button_list.get(i).isDisplayed()) {
                logger.info("arrow is displayed");
                button_list.get(i).click();
                logger.info("arrow is clicked");
            } else
                logger.fatal("arrow button is not displayed");
        }
    }


    public void click_Prduct_category(String category_name) throws InterruptedException {

        logger.info("click on "+category_name+" category");
        select_category=By.xpath("//h1[text()=\""+category_name+"\"]/ancestor::mat-card");
        WebElement ele=driver.findElement(select_category);
        if(ele.isDisplayed()) {
            ele.click();
        }

      //  Thread.sleep(2000);
        driver.findElement(logo).click();

    }

    public void check_if_product_category_is_displayed(){

        if(driver.findElement(product_category_text).isDisplayed())
            logger.info("Product category text is displayed");
        else
            logger.fatal("Product category text is not displayed");
    }

    public void click_on_back_to_top(){
        WebElement btn=driver.findElement(back_to_top);
        btn.click();
    }
}
