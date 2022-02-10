package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    Logger logger=null;
    By temperature=By.xpath("//span[@id='temperature']");
    By moisturizer=By.xpath("//button[text()='Buy moisturizers']");
    By sunscreen=By.xpath("//button[text()='Buy sunscreens']");

    public HomePage(WebDriver driver)
    {this.driver=driver;
        logger=Logger.getLogger(HomePage.class.getName());
    }
    public void click_on_moisturizer() throws InterruptedException {
        logger.info("click on moisturizer");
        driver.findElement(moisturizer).click();
    }
    public void click_on_sunscreen()
    {    logger.info("click on sunscreen");
        driver.findElement(sunscreen).click();
    }

}
