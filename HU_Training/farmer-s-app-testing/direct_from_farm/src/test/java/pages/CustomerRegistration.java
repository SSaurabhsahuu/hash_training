package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CustomerRegistration extends BaseClass{
        By register_nav=By.xpath("(//button[@class=\"mat-focus-indicator mat-flat-button mat-button-base mat-primary\"])[1]");
        By firstname=By.xpath("(//input[contains(@id,\"mat-input\")])[1]");
        By lastname=By.xpath("(//input[contains(@id,\"mat-input\")])[2]");
        By phone=By.xpath("(//input[contains(@id,\"mat-input\")])[3]");
        By email=By.xpath("(//input[contains(@id,\"mat-input\")])[4]");
        By password=By.xpath("(//input[contains(@id,\"mat-input\")])[5]");
        By confirm_password=By.xpath("(//input[contains(@id,\"mat-input\")])[6]");
        By register_button= By.xpath("//button[@class=\"mat-focus-indicator full-width-field custom-btn mat-raised-button mat-button-base mat-primary\"]");
        By message=By.xpath("//div[contains(@class,\"toast-message\")]");

        public Logger logger = Logger.getLogger(this.getClass().getName());
        public void clickRegister_nav()
        {    logger.info("click on register");
                wait.until(ExpectedConditions.visibilityOfElementLocated(register_nav));
             WebElement ele=driver.findElement(register_nav);
             ele.click();
         }
        public void enterFirstname(String name) throws InterruptedException {
            logger.info("Entering firstname");
            WebElement element=driver.findElement(firstname);
                element.clear();
            element.sendKeys(name);
        }
        public void enterLastname(String str) throws InterruptedException {
        logger.info("Entering lastname");
        WebElement element=driver.findElement(lastname);
        element.clear();
        element.sendKeys(str);
        }
        public void enterPhone(String str) throws InterruptedException {
        logger.info("Entering Phone no.");
        WebElement element=driver.findElement(phone);
                element.clear();
        element.sendKeys(str);
        }
        public String makeInvalidEmail(String str)		// removes " . " from email
        {String resultStr="";

                for (int i=0;i<str.length();i++)
                {
                        if (str.charAt(i)!='.' )
                                resultStr=resultStr+str.charAt(i);
                }
                return resultStr;
        }
        public void enterEmail(String str) throws InterruptedException {
        logger.info("Entering Email");
        WebElement element=driver.findElement(email);
                element.clear();
        element.sendKeys(str);
        }
        public void enterPassword(String pass) throws InterruptedException {
        logger.info("Entering Password");
        WebElement element=driver.findElement(password);
                element.clear();
        element.sendKeys(pass);

        }
        public void enterConfirmPassword(String str) throws InterruptedException {
        logger.info("Entering Confirm password");
        WebElement element=driver.findElement(confirm_password);
                element.clear();
        element.sendKeys(str);
        }

        public void clickRegister_button()
        {    logger.info("click on Register button");
            WebElement ele=driver.findElement(register_button);
            ele.click();
        }
        public String get_message()         // returns message displayed in bottom
        {    logger.info("message");

                String msg=driver.findElement(message).getText();
                return msg;

        }
        public void result_page(String result) throws InterruptedException {
              //  Thread.sleep(3000);              // validates the result page
                String url=driver.getCurrentUrl();
                Assert.assertEquals(result, url);
        }
}
