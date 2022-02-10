package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    By username=By.xpath("//input[@id=\"user-name\"]");
    By password=By.xpath("//input[@id=\"password\"]");
    By login_Button= By.xpath("//input[@id=\"login-button\"]");

    public LoginPage(WebDriver driver)
    {this.driver=driver;
      }

    public void enterUsername(String name) throws InterruptedException {
        WebElement element2=driver.findElement(username);
        element2.sendKeys(name);
        Thread.sleep(3000);
    }
    public void enterPassword(String pass) throws InterruptedException {
        WebElement element3=driver.findElement(password);
        element3.sendKeys(pass);
        Thread.sleep(3000);
    }
    public void clickLogin()
    {
        WebElement login=driver.findElement(login_Button);
        login.click();
    }
}
