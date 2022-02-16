package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_IV {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password, nonExistedEmail;
	
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By loginButtonBy = By.id("send2");
	By createButtonBy = By.xpath("//a[@title=\"Create an Account\"]");
	
	By emailRequireMessage = By.id("advice-required-entry-email");
	By emailErrorMessage = By.id("advice-validate-email-email");
	By passwordRequireMessage = By.id("advice-required-entry-pass");
	By passwordErrorMessage = By.id("advice-validate-password-pass");
	By loginErrorMessage = By.xpath("//span[contains(text(),\"Invalid login or password.\")]");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		firstName = "Huy";
		lastName = "Nguyen";
		fullName = firstName + " " + lastName;
		emailAddress = "HuyNguyen" + getRandomNumber() + "@hotmail.net";
		nonExistedEmail = "HuyNguyen" + getRandomNumber() + "@yopmail.com";
		password = "123456789";
	}
	
	@BeforeMethod
	public void beforeMethod(){
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		
	}
	
	@Test
	public void TC_01_Login_Empty_Data() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailRequireMessage).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(passwordRequireMessage).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_02_Login_Invalid_EMail() {
		driver.findElement(emailTextboxBy).sendKeys("12345@123454.123");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMessage).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.findElement(emailTextboxBy).sendKeys("huynt021193@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("1234");
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMessage).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_Create_New_Account_Success() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Input data
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
		
		// click register
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		// verify thông tin trên My Account page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		// logout
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		// verify after back to home page
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
	
	}
	
	@Test
	public void TC_05_Login_Incorrect_Email_Or_Password() {
		// Existed Email + incorrect Password > Unsuccess
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(loginErrorMessage).getText(), "Invalid login or password.");
		
		// Existed Email + incorrect Password > Unsuccess
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(nonExistedEmail);
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(loginErrorMessage).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_06_Login_Valid_Email_And_Password() {
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
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

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
}