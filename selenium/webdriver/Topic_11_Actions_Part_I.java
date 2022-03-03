package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// 1. Khởi tạo Action lên
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
		
	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement yourAgeTextbox = driver.findElement(By.id("age"));
		
		//Hover chuột vào textbox
		action.moveToElement(yourAgeTextbox).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	//@Test
	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		
		// 2. Gọi hàm cần dùng
		// 3. Gọi cái perform() cuối cùng
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Home & Living']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//a[text()='Storage']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Home Furnishing");
		
	}
	
	//@Test
	public void TC_03_Click_and_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Khai báo và lưu trữ tất cả 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// WebElement firstNumber = allNumbers.get(0);
		// WebElement secondNumber = allNumbers.get(1);
		// WebElement thirdNumber = allNumbers.get(2);
		
		//Click and Hold vào 6
		//Hover tới 11
		//Nhả chuột trái
		//Thực thi các câu lệnh
		action.clickAndHold(allNumbers.get(5)).moveToElement(allNumbers.get(10)).release().perform();
		sleepInSecond(5);
		
		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(), 4);
	}
	
	@Test
	public void TC_04_Click_and_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Khai báo và lưu trữ tất cả 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		Keys controlKey;
		
		if (osName.contains("Windows")) {
			controlKey = Keys.CONTROL;
		} else {
			controlKey = Keys.COMMAND;
		}
		
		//Chọn 1, 5, 11
		//Bấm Ctrl
		//Click 1
		//Click 5
		//Click 11
		//Thực thi các câu lệnh
		//Nhả Ctrl
		
		action.keyDown(controlKey).perform();
		action.click(allNumbers.get(0)).click(allNumbers.get(4)).click(allNumbers.get(10)).perform();
		action.keyUp(controlKey).perform();
		
		List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbersSelected.size(), 3);
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