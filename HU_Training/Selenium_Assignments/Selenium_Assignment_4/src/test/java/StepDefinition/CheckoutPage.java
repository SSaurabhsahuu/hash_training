package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    WebDriver driver;
    By firstname=By.xpath("//input[@id=\"first-name\"]");
    By lastname=By.xpath("//input[@id=\"last-name\"]");
    By postalcode= By.xpath("//input[@id=\"postal-code\"]");

    public CheckoutPage(WebDriver driver)
    {this.driver=driver;
    }

    public void enterFirstname(String str) throws InterruptedException {
        WebElement element2=driver.findElement(firstname);
        element2.sendKeys(str);
        Thread.sleep(3000);
    }
    public void enterLastname(String str) throws InterruptedException {
        WebElement element3=driver.findElement(lastname);
        element3.sendKeys(str);
        Thread.sleep(3000);
    }
    public void enterPostalcode(String str) throws InterruptedException {
        WebElement element3=driver.findElement(postalcode);
        element3.sendKeys(str);
        Thread.sleep(3000);
    }
    public void clickContinue()
    {
        driver.findElement(By.xpath("//input[@id=\"continue\"]")).click();
    }
}
