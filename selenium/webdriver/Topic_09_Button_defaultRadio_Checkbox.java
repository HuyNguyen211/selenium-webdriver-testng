package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}
				
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), 
				"Thông tin này không thể để trống");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), 
				"Thông tin này không thể để trống");
	}
	
	//@Test
	public void TC_02_Default_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		//Default - the input:
		//Action: click
		//Verify
		
		//Custom - the input:
		//Action: ko click dc
		//Verify dc
		
		By petrolRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By dieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");
		By twodieselRadio = By.xpath("//label[text()='2.0 Diesel, 125kW']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(petrolRadio).isSelected());
		
		driver.findElement(petrolRadio).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(petrolRadio).isSelected());
		
		driver.findElement(dieselRadio).click();
		sleepInSecond(2);
		
		//Deselected
		Assert.assertFalse(driver.findElement(petrolRadio).isSelected());
		
		//Selected
		Assert.assertTrue(driver.findElement(dieselRadio).isSelected());
		
		//Disabled
		Assert.assertFalse(driver.findElement(twodieselRadio).isEnabled());
		
		//Enabled
		Assert.assertTrue(driver.findElement(petrolRadio).isEnabled());
		Assert.assertTrue(driver.findElement(dieselRadio).isEnabled());
	}
	
	//@Test
	public void TC_03_Checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
				
		By leatherCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		
		//if(driver.findElement(luggageCheckbox).isSelected()) = nếu "nó đã dc chọn"
		//if(!driver.findElement(luggageCheckbox).isSelected()) = nếu "nó chưa dc chọn"
		
		//Selected
		checkToCheckbox(luggageCheckbox);
		
		//verify
		Assert.assertTrue(isElementSelected(luggageCheckbox));
		Assert.assertTrue(isElementSelected(leatherCheckbox));
		
		//Disable
		Assert.assertFalse(isElementEnabled(leatherCheckbox));
		Assert.assertFalse(isElementEnabled(towbarCheckbox));
		
		//De-selected
		uncheckToCheckbox(luggageCheckbox);
		
		//verify
		Assert.assertFalse(isElementSelected(luggageCheckbox));
		Assert.assertFalse(isElementSelected(towbarCheckbox));
		
		
	}
	
	//@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		System.out.println("Checkbox total = " +checkboxes.size());
		
		//Action - select
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		//verify - selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
			
		}
		
		//Action - deselect
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		//verify - deselected
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
			
		}
	}
	
	//@Test
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		//NHƯỢC ĐIỂM CỦA CASE 3
		//1 element phải define tới 2 locator
		//dễ nhầm lẫn: khi chuyển giao code cho 1 team member khác sử dụng
		//bảo trì: sinh ra nhiều code
		
		By winterCheckboxInput = By.cssSelector("input[value='Winter']");
		//By winterCheckboxSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle']");
		
		//Case 1: dùng thẻ input
		//Selenium click() > sinh lỗi ElementNoInteractableException
		//isSelected() > work
						
		//Case 2: dùng thẻ span
		//Selenium click() > work
		//isSelected() > ko work
		
		//Case 3: dùng thẻ span > click()
		// dùng the input > isSelected()
		
		//Case 4: dùng thẻ input
		//javescript > click() (ko quan tâm element ẩn/ hiện)
		//isSelected > verify
		
		//Action
		clickByJavascript(winterCheckboxInput);
		sleepInSecond(2);
				
		//Verify
		Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
	}
	
	//@Test
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);
		
		Assert.assertTrue(isElementSelected(checkedCheckbox));
		Assert.assertTrue(isElementSelected(indeterminateCheckbox));
		
		clickByJavascript(checkedCheckbox);
		sleepInSecond(1);
		clickByJavascript(indeterminateCheckbox);
		sleepInSecond(1);
		
		Assert.assertFalse(isElementSelected(checkedCheckbox));
		Assert.assertFalse(isElementSelected(indeterminateCheckbox));
		
	}
	
	//@Test
	public void TC_07_Custom_Radio_2() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		By banthanRadio = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		By nguoithanRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		clickByJavascript(nguoithanRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
		
		clickByJavascript(banthanRadio);
		sleepInSecond(2);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(), 0);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(), 0);
				
	}
	
	@Test
	public void TC_08_Custom_Radio_3() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By haiphongRadio = By.xpath("//div[@aria-label='Hải Phòng']");
		By quangnamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
		
		Assert.assertEquals(driver.findElement(haiphongRadio).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "false");
		
		driver.findElement(haiphongRadio).click();
		sleepInSecond(2);
		driver.findElement(quangnamCheckbox).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(haiphongRadio).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangnamCheckbox).getAttribute("aria-checked"), "true");
		
		//Cách 2
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hải Phòng' and @aria-checked = 'true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked = 'true']")).isDisplayed());
	}
	
	public void clickByJavascript(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
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