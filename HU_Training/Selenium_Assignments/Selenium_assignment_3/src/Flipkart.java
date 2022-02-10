import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Flipkart {

	public static void main(String[] args) throws InterruptedException, HeadlessException, AWTException, IOException {
	
		System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		launchUrl(driver);
		windowHandling(driver);
		popup_reload_search(driver);
		brand_highlight(driver);
		screenshot(driver);
		driver.quit();
		
	}
	
	public static void launchUrl(WebDriver driver) throws InterruptedException
	{
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();	
		Thread.sleep(700);
		String title=driver.getTitle();
		System.out.println(title);
		if(title.equals("Flipkart Explore Plus"))
			System.out.println(" Title Matched : Pass ");
		else
			System.out.println("Title Match : Failed");
		
	}
	public static void windowHandling(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String parentWindow= driver.getWindowHandle();
		
	
		JavascriptExecutor js= (JavascriptExecutor)driver;
		String newtab = "window.open('https://www.facebook.com/','_blank');";  
		js.executeScript(newtab);
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.switchTo().window(parentWindow);
		Thread.sleep(5000);
		
		String url = js.executeScript("return document.URL;").toString();			
        System.out.println("URL of the site = "+url);
		
	}
	public static void popup_reload_search(WebDriver driver) throws InterruptedException
	{   // close login popup
		WebDriverWait wait= new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='✕']")));
		System.out.println("INFO: x button visible");
		driver.findElement(By.xpath("//button[text()='✕']")).click(); 
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		// reload
		js.executeScript("document.location.reload()");
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        
		// Search mobile
		//	js.executeScript("document.getElementByClass('_3704LK').value='mobiles'");
		WebElement s=driver.findElement(By.xpath("//input[@class=\"_3704LK\"]"));
		s.sendKeys("mobiles");
		Thread.sleep(700);
		s.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
	}
	
	public static void brand_highlight(WebDriver driver) throws InterruptedException
	{	// select samsung
		driver.findElement(By.xpath("//div[@title='SAMSUNG']")).click();
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		
		// highlight (//div[@class=\"_4rR01T\"])[1]
		String javascript = "document.querySelector('._4rR01T').style.border='5px solid red'";  
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript(javascript); 
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
	}
	public static void screenshot(WebDriver driver) throws InterruptedException, HeadlessException, AWTException, IOException
	{
		BufferedImage img=new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		ImageIO.write(img, "png", new File("C:\\Users\\saurasahu\\eclipse-workspace\\Selenium_assignment_3\\screenshots\\screenshot_1.png"));
	
		driver.findElement(By.xpath("(//a[@class=\"_1fQZEK\"])[1]")).click();
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		BufferedImage img2=new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(img2, "png", new File("C:\\Users\\saurasahu\\eclipse-workspace\\Selenium_assignment_3\\screenshots\\screenshot_2.png"));
		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
}
