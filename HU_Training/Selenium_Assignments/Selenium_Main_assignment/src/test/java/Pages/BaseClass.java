package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
      WebDriver driver;
      Logger logger=null;

    public WebDriver initialSetup(){         // start chrome with given url
        logger=Logger.getLogger(BaseClass.class.getName());
        logger.info("initial setup");

        System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://weathershopper.pythonanywhere.com/");
        return driver;
    }

    public void closeBrowser() {  // close browser
        logger.info("close browser");
        driver.close();
    }
}
