package StepDefinition;

import org.openqa.selenium.WebDriver;

public class BaseClass {
     WebDriver driver;

    public BaseClass(WebDriver driver)
    {this.driver=driver;
    }

   /* public void initialSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
        driver=new ChromeDriver();
    }*/

    public void closeBrowser() {
        driver.close();
    }
}
