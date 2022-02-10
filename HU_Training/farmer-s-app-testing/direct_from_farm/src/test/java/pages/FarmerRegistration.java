package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FarmerRegistration extends BaseClass{
    By sell_with_us_nav=By.xpath("//button[@class='mat-focus-indicator farmer-reg-btn mat-stroked-button mat-button-base mat-primary']");
    By firstname=By.xpath("(//input[contains(@id,\"mat-input\")])[1]");
    By lastname=By.xpath("(//input[contains(@id,\"mat-input\")])[2]");
    By phone=By.xpath("(//input[contains(@id,\"mat-input\")])[3]");
    By address1=By.xpath("(//input[contains(@id,\"mat-input\")])[4]");
    By address2=By.xpath("(//input[contains(@id,\"mat-input\")])[5]");
    By pinCode=By.xpath("(//input[contains(@id,\"mat-input\")])[6]");
    By city=By.xpath("(//input[contains(@id,\"mat-input\")])[7]");
    By state=By.xpath("(//input[contains(@id,\"mat-input\")])[8]");
    By adhaar=By.xpath("(//input[contains(@id,\"mat-input\")])[9]");
    By uploadImage=By.xpath("//input[@id=\"fileupload\"]");
    By uploadResult=By.xpath("//span[text()=\"view\"]");
    By email=By.xpath("(//input[contains(@id,\"mat-input\")])[10]");
    By password=By.xpath("(//input[contains(@id,\"mat-input\")])[11]");
    By confirm_password=By.xpath("(//input[contains(@id,\"mat-input\")])[12]");
    By register_button=By.xpath("//button[@class='mat-focus-indicator register-button custom-btn mat-raised-button mat-button-base mat-primary']");

    String next="(//span[text()=' Next '])";
    By message=By.xpath("//div[contains(@class,\"toast-message\")]");

    public Logger logger = Logger.getLogger(this.getClass().getName());
    public void click_sell_with_us_nav()
    {    logger.info("click on register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(sell_with_us_nav));
        WebElement ele=driver.findElement(sell_with_us_nav);
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
    public String generatePhoneNumber()          // generate 10 digit phone number
    {    int size=0;

        while( size!= 10)
        {     Random rand = new Random();
            int resRandom = rand.nextInt((999999999 - 100) + 1) + 10;
            String phone="9";
            phone +=resRandom;
            size=phone.length();

            if(size==10)
                return phone;
        }
      return null;
    }
    public void enterPhone(String str) throws InterruptedException {
        logger.info("Entering Phone no.");
        WebElement element=driver.findElement(phone);
        element.clear();
        element.sendKeys(str);

    }
    public void clickOnNext(String index){

        WebElement nextButton=driver.findElement(By.xpath(next+index));
        driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
        nextButton.click();

    }

    public void enterAddress1(String str) throws InterruptedException {
        logger.info("Entering Address Line 1");
        WebElement element=driver.findElement(address1);
        element.clear();
        element.sendKeys(str);

    }

    public void enterAddress2(String str) throws InterruptedException {
        logger.info("Entering Address Line 2");
        WebElement element=driver.findElement(address2);
        element.clear();
        element.sendKeys(str);

    }
    public String generatePincode()                      // generate 6 digit pin number
    {  int size=0;
        while( size != 6)
        {     Random rand = new Random();
            int resRandom = rand.nextInt(999999) + 100000;
            String pin = "";
            pin +=resRandom;
            size=pin.length();

            if(size==6)
                return pin;
        }
        return null;
    }
    public void enterPinCode(String str) throws InterruptedException {
        logger.info("Entering pincode");
        WebElement element=driver.findElement(pinCode);
        element.clear();
        element.sendKeys(str);

    }

    public void enterCity(String str) throws InterruptedException {
        logger.info("Entering city");
        WebElement element=driver.findElement(city);
        element.clear();
        element.sendKeys(str);

    }

    public void enterState(String str) throws InterruptedException {
        logger.info("Entering State");
        WebElement element=driver.findElement(state);
        element.clear();
        element.sendKeys(str);

    }
    public String generateAdhaarNumber()                    // generate 12 digit adhaar number
    {  int size=0;
        while( size != 12)
        {     Random rand = new Random();
            int resRandom = rand.nextInt(999999999) + 100000000;
            String adhaar="999";
            adhaar +=resRandom;
            size=adhaar.length();

            if(size==12)
                return adhaar;
        }
        return null;
    }
    public void enterAdhaar(String str) throws InterruptedException {
        logger.info("Entering adhaar card number");
        WebElement element=driver.findElement(adhaar);
        element.clear();
        element.sendKeys(str);

    }

    public void uploadImage(String str) throws InterruptedException {

        logger.info("Uploading images");
        WebElement upload_file = driver.findElement(uploadImage);
        upload_file.sendKeys(System.getProperty("user.dir")+"\\images\\farmer1.jpg");

        wait.until(ExpectedConditions.visibilityOfElementLocated(uploadResult));

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
    public String get_message()
    {    logger.info("message");                             // returns msg from bottom

        String msg=driver.findElement(message).getText();
        return msg;

    }
    public void result_page(String result) throws InterruptedException {
        //Thread.sleep(3000);                             // validates the result page
        String url=driver.getCurrentUrl();
        //Assert.assertEquals(result, url);
    }
}
