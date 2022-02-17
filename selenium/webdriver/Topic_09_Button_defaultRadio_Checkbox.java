package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_defaultRadio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
		
	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		//verify login button is disable
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		//điền email + password
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("huynt@yopmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("admin123");
		sleepInSecond(2);
		
		//verify login button is enable
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//verify login button color = RED
		String loginButtonColorRGB = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGB color =" + loginButtonColorRGB);
		Assert.assertEquals(loginButtonColorRGB, "rgb(201, 33, 39)");
		
		//convert RGB to Hexa
		String loginButtonColorHexa = Color.fromString(loginButtonColorRGB).asHex();
		System.out.println("Hexa color =" + loginButtonColorHexa);
		Assert.assertEquals(loginButtonColorHexa.toUpperCase(), "#C92127");
		
		//tải lại page
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		//remove thuộc tính disable của login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButtonBy));
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
	
	@Test
	public void TC_02_Default_Radio() {
		
	}
	
	@Test
	public void TC_03_Checkbox() {
		
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