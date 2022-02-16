package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea_Bai_tap_1 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String email, loginPageUrl, userID, userPW;
	String customerName, gender, DoB, addressInput, addressOutput, city, state, pinNumber, phoneNumber, password, customerID;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		customerName = "John Wick";
		gender = "male";
		DoB = "1965-05-21";
		addressInput = "123\nXo Viet\nPhuong 26";
		addressOutput = "123 Xo Viet Phuong 26";
		city = "Ho Chi Minh";
		state = "Binh Thanh";
		pinNumber = "421235";
		phoneNumber = "0956212213";
		email = "HuyNguyen" + getRandomNumber() + "@yopmail.net";
		password = "123456";

		editAddressInput = "549\nDBL\nPhuong 26";
		editAddressOutput = "549 DBL Phuong 26";
		editCity = "Ha Noi";
		editState = "Tran Duy Hung";
		editPin = "879451";
		editPhone = "0124750123";
		editEmail = "HuyNguyen" + getRandomNumber() + "@hotmail.vn";

		loginPageUrl = "https://demo.guru99.com/v4/";
		driver.get(loginPageUrl);

	}

	@Test
	public void TC_01_Register() {
		// create new account
		driver.findElement(By.cssSelector("a[href='http://demo.guru99.com/']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		// get ID & password
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPW = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		// login
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPW);
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userID);

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(By.name("name")).sendKeys(customerName);

		WebElement dobTextbox = driver.findElement(By.name("dob"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
		sleepInSecond(2);
		dobTextbox.sendKeys(DoB);

		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DoB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		// Verify Customer Info at Edit page correctly
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), DoB);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), addressInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), pinNumber);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), phoneNumber);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), email);

		// Edit Info
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(editAddressInput);
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(editCity);
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(editState);
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(editPin);
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(editPhone);
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		sleepInSecond(10);
		
		//verify after edit
		driver.get("https://demo.guru99.com/v4/manager");
		sleepInSecond(3);
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), editAddressInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), editCity);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), editState);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), editPin);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), editPhone);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), editEmail);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}