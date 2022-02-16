package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Wait cho các trạng thái của Element: visible/ presence/ invisible/ staleness
		expliciWait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		//Wait cho việc tìm Element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		//span#number-button>span.ui-selectmenu-icon
		//ul#number-menu div
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "3");
	}
	
	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
		
	}
	
	//@Test
	public void TC_03_VuJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
	}
	
	//@Test
	public void TC_04_Angula_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		
		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-star-inserted", "Thành phố Hồ Chí Minh");
		//1: get text
		//Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		
		//2: Text ko nằm trong Html > nằm trong DOM (Console)
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
		
		//3: get Attribute
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"), "Thành phố Hồ Chí Minh");
		
		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-star-inserted", "Quận Bình Thạnh");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getText(), "Quận Bình Thạnh");
		
		selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-star-inserted", "Phường 26");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] span.ng-value-label")).getText(), "Phường 26");
		
	}
	
	@Test
	public void TC_04_Angula_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		
		enterIntoCustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']", "div[role='option']>span.ng-star-inserted", "Thành phố Hồ Chí Minh");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		
		enterIntoCustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']", "div[role='option']>span.ng-star-inserted", "Quận Bình Thạnh");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getText(), "Quận Bình Thạnh");
		
		enterIntoCustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']", "div[role='option']>span.ng-star-inserted", "Phường 26");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] span.ng-value-label")).getText(), "Phường 26");
	}

	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
		//Step 1: Click vào 1 Element cho nó xổ ra các item
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(2);
		
		//Step 2: Chờ cho các item load ra thành công
		//Lưu ý 1: Locator phải chứa hết tất cả các item
		//Lưu ý 2: Locator phải đến node cuối cùng chứa text
		//Wait visible: 8 cái
		//Wait presence: 19 cái
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		//Step 3: Tìm item cần chọn
		
		//Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
		//Duyệt qua từng item > getText của item ra
		for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
			
			//Nếu text = text mình đang tìm
			if (actualText.equals(expectedTextItem)) {
				// + Case 1: Nếu item cần chọn đã nhìn thấy dc thì ko cần scroll tới Element
				// + Case 2: Nếu item cần chọn chưa nhìn thấy dc thì scroll xuống đến khi tìm dc Element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				
				//Step 4: Click vào item đó
				item.click();
				
				break;
			}
		}
	}
	
	public void enterIntoCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
		//Step 1: Phải lấy đến thẻ input (textbox) để sendkey vào
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);;
		sleepInSecond(2);
		
		//Step 2: Chờ cho các item load ra thành công
		//Lưu ý 1: Locator phải chứa hết tất cả các item
		//Lưu ý 2: Locator phải đến node cuối cùng chứa text
		//Wait visible: 8 cái
		//Wait presence: 19 cái
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
				
		//Step 3: Tìm item cần chọn
						
		//Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
			
		//Duyệt qua từng item > getText của item ra
		for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual Text = " + actualText);
				
			//Nếu text = text mình đang tìm
			if (actualText.equals(expectedTextItem)) {
				// + Case 1: Nếu item cần chọn đã nhìn thấy dc thì ko cần scroll tới Element
				// + Case 2: Nếu item cần chọn chưa nhìn thấy dc thì scroll xuống đến khi tìm dc Element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				
				//Step 4: Click vào item đó
				item.click();
						
				break;
			}
		}
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