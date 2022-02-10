package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ConfirmationPage {
    WebDriver driver;
    WebDriverWait wait;
    Logger logger=null;
    By result=By.xpath("//h2[contains(text(),\"PAYMENT\")]");
    public ConfirmationPage(WebDriver driver)
    {
        this.driver=driver;
        wait = new WebDriverWait(driver, 20);
        logger=Logger.getLogger(ConfirmationPage.class.getName());
    }

    public void check_result() throws InterruptedException , WebDriverException {

            logger.info("Check result");
            wait.until(ExpectedConditions.visibilityOfElementLocated(result)); // explicit wait for page to be loaded

        String original = driver.findElement(result).getText();
            String expected = "PAYMENT SUCCESS";
            Assert.assertEquals(original, expected);

    }

}
