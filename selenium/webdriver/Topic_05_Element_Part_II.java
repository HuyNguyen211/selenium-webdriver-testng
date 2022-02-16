package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
		
	public void TC_01() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		
		String searchPlaceholder = driver.findElement(By.id("search")).getAttribute("placeholder");
		System.out.println(searchPlaceholder);
	
		String isntructionText = driver.findElement(By.cssSelector("p.form-instructions")).getText();
		System.out.println(isntructionText);
	}
	
	public void TC_02() {
		driver.get("https://www.facebook.com");
		WebElement loginButton = driver.findElement(By.name("login"));
		System.out.println(loginButton.getCssValue("font-size"));
		System.out.println(loginButton.getCssValue("background-color"));
		System.out.println(loginButton.getCssValue("width"));
		System.out.println(loginButton.getCssValue("font-family"));
	}

	public void TC_03() {
		driver.get("https://www.facebook.com");
		WebElement loginButton = driver.findElement(By.name("login"));
		String loginButtonTagname = loginButton.getTagName();
		
		//tagname dc lấy ra là button
		
		//other steps...
		
		//sử dụng đầu ra của step trên làm đầu vào của step này
		loginButton = driver.findElement(By.xpath("//" + loginButtonTagname + "[@id='loginbutton']"));
		//nối chuỗi
	}
	
	@Test
	public void TC_04() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.id("email")).sendKeys("omelya@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("email")).submit();
		
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