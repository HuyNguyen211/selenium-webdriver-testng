package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
		
	@Test
	public void TC_01_get_URL() {
		driver.get("http://live.techpanda.org/");
		
		//Click vào My Account dưới Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify Url login page
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click vào Button Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify Url Register page
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		//Click vào My Account dưới Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify title của login page
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		//Click vào Button Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify title của Register page
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		
		//Click vào My Account dưới Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Click vào Button Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify title của Register page
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
		//Back lại Login page
		driver.navigate().back();
		sleepInSecond(3);
		
		//Verify title của login page
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		//Foward tới Register page
		driver.navigate().forward();
		sleepInSecond(3);
		
		//Verify title của Register page
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
		
	}

	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/");
		
		//Click vào My Account dưới Footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify login page có chứa text "Login or Create an Account"
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		//Click vào Button Create an Account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Verify Register page có chứa text "Create an Account"
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
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