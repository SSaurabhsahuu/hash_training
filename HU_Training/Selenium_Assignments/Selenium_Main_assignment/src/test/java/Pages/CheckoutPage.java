package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    Logger logger = null;
    By pay = By.xpath("//button[@type='submit']");
    By email = By.xpath("//input[@id='email']");
    By card_number = By.xpath("//input[@id='card_number']");
    By expiry = By.xpath("//input[@id='cc-exp']");
    By cvc = By.xpath("//input[@id='cc-csc']");
    By zipCode = By.xpath("//input[@id=\"billing-zip\"]");
    By confirm_Pay = By.xpath("//button[@id=\"submitButton\"]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 20);
        logger = Logger.getLogger(CheckoutPage.class.getName());
    }
    public void check_total_prize()   // verify calculated and shown price
    {
        List<WebElement> item=driver.findElements(By.xpath("//tr"));  // list of parent elements
        int total_price=0;
        for(int i=1;i< item.size();i++)
        {
            String str_price=item.get(i).findElement(By.xpath(".//td[2]")).getText(); // access child elements

            int price=Integer.parseInt(str_price);
         //   System.out.println("price = "+price);
            total_price+=price;
        }

        String str=driver.findElement(By.xpath("//p[@id=\"total\"]")).getText();

        String calculated="";                     // remove alphabets
        for (int i=0;i<str.length();i++) {
           if(str.charAt(i) >='0' && str.charAt(i) <='9')
               calculated = calculated + str.charAt(i);
        }

        String expected=Integer.toString(total_price);

        Assert.assertEquals(calculated, expected);
    }
    public void click_pay() throws InterruptedException {
        logger.info("click pay");
        driver.findElement(pay).click();

    }

    public void enterEmail(String str) throws InterruptedException {
        logger.info("enter email "+str);

        driver.switchTo().frame(0);         // switch to iframe
        wait.until(ExpectedConditions.visibilityOfElementLocated(email));   // explicit wait till email is visible

        WebElement element = driver.findElement(email);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(str);
        actions.build().perform();

    }

    public void enterCardNumber(String str) throws InterruptedException {
        logger.info("Enter card number "+str);
        WebElement element = driver.findElement(card_number);

        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(str);
        actions.build().perform();
    }

    public void enterExpiryDate(String str) throws InterruptedException {
        logger.info("Enter Expiry date "+str);
        WebElement element = driver.findElement(expiry);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(str);
        actions.build().perform();
    }

    public void enterCVC(String str) throws InterruptedException {
        logger.info("Enter CVC "+str);
        WebElement element = driver.findElement(cvc);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(str);
        actions.build().perform();
    }

    public void enterZipCode(String str) throws InterruptedException {
        logger.info("Enter Zip code "+str);
       if(str.equals("")==false) {
           WebElement element = driver.findElement(zipCode);
           actions.moveToElement(element);
           actions.click();
           actions.sendKeys(str);
           actions.build().perform();
       }
    }

    public void enter_card_details() {      // to enter details from .csv file
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/test/java/CardDetails.csv"));
            int c = 0,random_number;
            random_number = (int)(Math.random()*(10)+1);  //  generate random numbers from 1 to 10

            while ((line = br.readLine()) != null) {

                String[] card = line.split(",");    // use comma as separator
                c++;

                if (c != 1 && Integer.parseInt(card[0])==random_number ) {
                    this.enterEmail(card[1]);
                    this.enterCardNumber(card[2]);
                    this.enterExpiryDate(card[3]);
                    this.enterCVC(card[4]);
                    this.enterZipCode(card[5]);
                    br.close();
                    return;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // to enter particular card details
    public void enter_card_details(String[] card_details) throws InterruptedException {

                    this.enterEmail(card_details[0]);
                    this.enterCardNumber(card_details[1]);
                    this.enterExpiryDate(card_details[2]);
                    this.enterCVC(card_details[3]);
                    this.enterZipCode(card_details[4]);

    }
    public void confirm_pay() {
        logger.info("click confirm pay");
        WebElement element = driver.findElement(confirm_Pay);
        actions.moveToElement(element);
        actions.click();
        actions.build().perform();
      //  wait.until(ExpectedConditions.titleIs("Confirmation"));
    }
}
