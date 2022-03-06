package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
		
	//@Test
	public void TC_01_ngoaingu24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopupBy = By.cssSelector("div#modal-login-v1");
		
		//verify Popup chưa hiển thị
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		//verify Popup đã hiển thị
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("HuyNguyen");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("Admin123@");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Mật khẩu sai!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		
		//verify Popup đã close
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
	}
	
	@Test
	public void TC_02_JTExpress() {
		driver.get("https://jtexpress.vn/");
		
		By homeSliderPopup = By.cssSelector("div#home-modal-slider");
		
		//verify Popup đang hiển thị
		Assert.assertTrue(driver.findElement(homeSliderPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.close")).click();
		sleepInSecond(2);
		
		//verify Popup đc close
		Assert.assertFalse(driver.findElement(homeSliderPopup).isDisplayed());
		
		String billCode = "841000072647";
		
		driver.findElement(By.name("billcodes")).sendKeys(billCode);
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5")).getText().contains(billCode));
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