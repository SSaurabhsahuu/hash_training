import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Goibibo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\saurasahu\\eclipse-workspace\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
	
		
		try {
			launchUrl(driver);
			from_to(driver);
			Dropdown_then_search(driver);
			depart_then_highest(driver);
			flight_details(driver);
		}
		finally {
		driver.quit();
		System.out.println("  passed   ");
		}
		
	}
public static void launchUrl(WebDriver driver)
{
	driver.get("https://www.goibibo.com/");
	driver.manage().window().maximize();
	
}
public static void from_to(WebDriver driver) throws InterruptedException
{ driver.findElement(By.xpath("//span[@id=\"oneway\"]")).click();
  
  WebElement from=driver.findElement(By.xpath("//input[@id=\"gosuggest_inputSrc\"]"));
  WebElement destination=driver.findElement(By.xpath("//input[@id=\"gosuggest_inputDest\"]"));
  Thread.sleep(3000);
  
  from.sendKeys("Delhi");
  Thread.sleep(700);
  from.sendKeys(Keys.ARROW_DOWN);
  from.sendKeys(Keys.ENTER);
  
  destination.sendKeys("Bengaluru");
  Thread.sleep(700);
  destination.sendKeys(Keys.ARROW_DOWN);
  destination.sendKeys(Keys.ENTER);
  
  Thread.sleep(700);
  
  driver.findElement(By.xpath("//input[@id=\"departureCalendar\"]")).click();
 
  driver.findElement(By.xpath("//span[@class=\"DayPicker-NavButton DayPicker-NavButton--next\"]")).click();
  Thread.sleep(700);
  driver.findElement(By.xpath("//div[@id=\"fare_20210901\"]")).click();
  
  //System.out.println("from_to");
}
public static void Dropdown_then_search(WebDriver driver) throws InterruptedException
{   
	
	Thread.sleep(700);
	driver.findElement(By.xpath("//span[text()=' Economy']")).click();    // xpath("//span[@class="blueGrey textOverflow"][2]")
	
	Select obj=new Select( driver.findElement(By.xpath("//select[@id=\"gi_class\"]") ));
	Thread.sleep(700);
	obj.selectByVisibleText("Business");
	
	Thread.sleep(700);
	driver.findElement(By.xpath("//button[@id=\"gi_search_btn\"]")).click();  
	//System.out.println("dropdown");
}
public static void depart_then_highest(WebDriver driver) throws InterruptedException
{  Thread.sleep(700);
	driver.findElement(By.xpath("//span[@class=\"black\" and text()='11am - 5pm']")).click();  
	Thread.sleep(700);
	driver.findElement(By.xpath("//span[@class=\"black\" and text()='5pm - 9pm']")).click();  
	
	Thread.sleep(700);
	driver.findElement(By.xpath("//span[text()='PRICE']")).click(); 
	
	Thread.sleep(700);
	driver.findElement(By.xpath("(//button[@class=\"srp-card-uistyles__BookButton-sc-3flq99-21 gyWCJl dF justifyCenter alignItemsCenter\"])[2]")).click(); 
	//System.out.println("depart the highest");
}
public static void flight_details(WebDriver driver) throws InterruptedException
{ 
	Thread.sleep(3000);

	String flight_name=driver.findElement(By.xpath("(//div[@class=\"common-elementsstyles__Wid13-sc-ilw4bs-14 irFRjj flexCol alignItemsStart\"])[1]//span[1]")).getText();
	String n2=driver.findElement(By.xpath("(//div[@class=\"common-elementsstyles__Wid13-sc-ilw4bs-14 irFRjj flexCol alignItemsStart\"])[1]//span[2]")).getText();
	String n3=driver.findElement(By.xpath("(//div[@class=\"common-elementsstyles__Wid13-sc-ilw4bs-14 irFRjj flexCol alignItemsStart\"])[1]//span[3]")).getText();
	
	System.out.println("Flight Name : "+flight_name+" "+n2+" "+n3);
	
	int count=driver.findElements(By.xpath("//label[@class=\"common-elementsstyles__ChckAirln-sc-ilw4bs-1 cnJcdr padL10 padR10 padT10 brdrBot padB10 dF\"]")).size();	
	System.out.println("Goibibo offers : "+count);
//ystem.out.println("flight details");
}


}
