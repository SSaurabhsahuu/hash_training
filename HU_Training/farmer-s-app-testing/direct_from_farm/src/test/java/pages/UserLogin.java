package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class UserLogin extends BaseClass{
    By login_nav=By.xpath("(//button[@class=\"mat-focus-indicator mat-flat-button mat-button-base mat-primary\"])[2]");
    By email=By.xpath("(//input[contains(@id,\"mat-input\")])[1]");
    By password=By.xpath("(//input[contains(@id,\"mat-input\")])[2]");
    By login_button= By.xpath("//button[@class=\"mat-focus-indicator full-width-field custom-btn mat-raised-button mat-button-base mat-primary\"]");
    By message=By.xpath("//div[contains(@class,\"toast-message\")]");

    public Logger logger = Logger.getLogger(this.getClass().getName());

    public void clickLogin_nav()
    {    //logger.info("click on Login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(login_nav));
        WebElement ele=driver.findElement(login_nav);
        ele.click();
    }
    public void enterEmail(String str) throws InterruptedException {
       // logger.info("Entering Email");
        WebElement element=driver.findElement(email);
        element.clear();
        element.sendKeys(str);
    }
    public void enterPassword(String str) throws InterruptedException {
       // logger.info("Entering Password");
        WebElement element=driver.findElement(password);
        element.clear();
        element.sendKeys(str);
    }
    public void clickLogin_button()
    {   //logger.info("click on Login button");
        WebElement ele=driver.findElement(login_button);
        ele.click();
    }
    public void validate_message(String msg)
    {    //logger.info("message");                    // validate message at bottom

        String result_msg=driver.findElement(message).getText();
        Assert.assertEquals(result_msg, msg);

    }
    public void result_page(String result) throws InterruptedException {
      // Thread.sleep(6000);                              // validates the result page
        String url=driver.getCurrentUrl();
        Assert.assertEquals(url,result);
    }
}
