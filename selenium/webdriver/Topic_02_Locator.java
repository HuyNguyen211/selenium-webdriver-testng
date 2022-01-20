package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
	//Khai báo 1 biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			
		//Khởi tạo Browser lên
		driver = new FirefoxDriver();
			
		//Set th�?i gian ch�? để tìm được Element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Mở Page Facebook
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
	}
		
	@Test
	public void TC_01() {
		//Selenium Locator (By class)
		
		//ID
		driver.findElement(By.id("firstname")).sendKeys("HuyNguyen211");
		sleepInSecond(1);
		
		//Name
		driver.findElement(By.name("lastname")).sendKeys("Automation");
		sleepInSecond(1);
		
		//Class
		driver.findElement(By.className("customer-name-middlename")).isDisplayed();
		driver.findElement(By.className("name-middlename")).isDisplayed();
		
		//Tagname
		int inputNumber = driver.findElements(By.tagName("input")).size();
		System.out.println(inputNumber);
		sleepInSecond(1);
		
		//Linktext
		driver.findElement(By.linkText("SEARCH TERMS")).click();
		sleepInSecond(1);
		
		//Partial Linktext
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		sleepInSecond(1);
		
		//Css
		driver.findElement(By.cssSelector("input[id='name']")).sendKeys("IPhone");
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("input[name='name']")).clear();
		driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Samsung");
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("#name")).clear();
		driver.findElement(By.cssSelector("#name")).sendKeys("Sony");
		sleepInSecond(1);
		
		//Xpath
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys("VN");
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//input[@name='description']")).clear();
		driver.findElement(By.xpath("//input[@name='description']")).sendKeys("HQ");
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).clear();
		driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).sendKeys("US");
		sleepInSecond(1);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}