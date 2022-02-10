package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;
    By username=By.xpath("//input[@id=\"user-name\"]");
    By password=By.xpath("//input[@id=\"password\"]");
    By login_Button= By.xpath("//input[@id=\"login-button\"]");
    public static Logger logger=null;

    public LoginPage(WebDriver driver)
    {this.driver=driver;
    logger=Logger.getLogger(LoginPage.class.getName());
      }

    public void enterUsername(String name) throws InterruptedException {
        logger.info("Entering username");
        WebElement element2=driver.findElement(username);
        element2.sendKeys(name);

    }
    public void enterPassword(String pass) throws InterruptedException {
        logger.info("Entering Password");
        WebElement element3=driver.findElement(password);
        element3.sendKeys(pass);

    }
    public void clickLogin()
    {    logger.info("click on login");
        WebElement login=driver.findElement(login_Button);
        login.click();
    }
}
