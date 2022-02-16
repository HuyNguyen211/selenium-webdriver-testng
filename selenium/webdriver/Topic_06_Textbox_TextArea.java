package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// login
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		sleepInSecond(5);

		// 'Add Employee' sub-menu not display at Dashboard page
		Assert.assertFalse(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		// open 'Add Employee' page
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// 'Add Employee' sub-menu display at Dashboard page
		Assert.assertTrue(driver.findElement(By.cssSelector("a#menu_pim_addEmployee")).isDisplayed());

		String firstName = "Huy";
		String lastName = "Nguyen";
		String editFirstName = "Steve";
		String editLastName = "Job";
		
		// enter firstname / lastname
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);

		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");

		// Click Save
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);
		
		By firstNameTextboxBy = By.id("personal_txtEmpFirstName");
		By lastNameTextboxBy = By.id("personal_txtEmpLastName");
		By employeeIDTextboxBy = By.id("personal_txtEmployeeId");

		// verify fields are not enable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());

		// verify fields value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIDTextboxBy).getAttribute("value"), employeeID);

		// click edit button
		driver.findElement(By.id("btnSave")).click();

		// verify fields are enable
		Assert.assertTrue(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertTrue(driver.findElement(employeeIDTextboxBy).isEnabled());

		// edit 'First Name/ Last Name'
		driver.findElement(firstNameTextboxBy).clear();
		driver.findElement(firstNameTextboxBy).sendKeys(editFirstName);
		driver.findElement(lastNameTextboxBy).clear();
		driver.findElement(lastNameTextboxBy).sendKeys(editLastName);

		// click save button
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(3);

		// verify fields are not enable
		Assert.assertFalse(driver.findElement(firstNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(lastNameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(employeeIDTextboxBy).isEnabled());

		// verify fields value matching with input value
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), editLastName);
		
		//click on 'Immigration' tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		//click on 'Add' button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		//enter to 'Immigration number' textbox
		driver.findElement(By.id("immigration_number")).sendKeys("159357246");
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys("SteveJob's\nPassport\nID");
		sleepInSecond(5);
		
		driver.findElement(By.id("btnSave")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//td[@class='document']/a[text()='Passport']")).click();
		
		//verify
		Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "159357246");		
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), "SteveJob's\nPassport\nID");
		
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
}