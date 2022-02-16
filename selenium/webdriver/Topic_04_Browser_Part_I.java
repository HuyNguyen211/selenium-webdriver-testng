package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_I {
	WebDriver driver;
	WebElement elemnt;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}
		
	@Test
	public void TC_01_Browser() {
		//Action lên Browser
		//open browser
		//open URL
		//refresh/back/forward
		//maximize/minimize/fullscreen
		//...
		
		//Lấy dữ liệu từ Browser: trả về 1 kiểu dữ liệu nào đó, sử dụng dữ liệu đó để thực thi cho 1 step khác
		//get URL/get title/get source page/get position/get location
		//...
		
		//các hàm/ method để thao tác với Browser là thông qua biến driver
		
		
	}
	
	@Test
	public void TC_02_Element() {
		//các hàm/ method để thao tác với Element là thông qua biến element
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