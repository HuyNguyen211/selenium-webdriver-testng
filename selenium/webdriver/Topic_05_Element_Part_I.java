package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}
		
	@Test
	public void TC_01() {
	//các hàm (function/method) Or các câu lệnh (command)
	//tương tác với Browser
	//thông qua biến driver. - đại diện cho WebDriver
	
	//tương tác với Element
	//driver.findElement()
	
	//nếu element chỉ sử dụng 1 lần thì ko cần khai báo biến
	//driver.findElement(By.name("login")).click();
	
	//nếu element sử dụng nhiều lần thì nên khai báo biến để tái sử dụng
	//WebElement emailTextbox = driver.findElement(By.cssSelector("email"));
	//emailTextbox.clear();
	//emailTextbox.sendKeys("abc@gmail.com");
	//emailTextbox.getCssValue("background-color");
		
	WebElement element = driver.findElement(By.xpath(""));
	
	//xóa dữ liệu trong textbox/ textarea/ editable dropdown (những vùng cho phép edit/ nhập liệu)
	element.clear();
	
	//nhập dữ liệu vào textbox/ textarea/ editable dropdown (những vùng cho phép edit/ nhập liệu)
	element.sendKeys("huynguyen@gmail.com");
	
	//click vào button/ radio button/ checkbox/ link/ image/...
	element.click();
	
	driver.getCurrentUrl();
	driver.getPageSource();
	driver.getTitle();
	driver.getWindowHandle();
	driver.getWindowHandles();
	driver.manage().window().getPosition();
	driver.manage().window().getSize();
	
	//lấy ra giá trị của attribute
	element.getAttribute("placeholder");
	
	//lấy ra text của element
	element.getText();
	
	//thường dùng để test GUI: font/ size/ color/ location/ position/...
	//các thuộc tính của CSS đều có thể lấy ra được
	element.getCssValue("");
	
	//ít khi dùng
	element.getLocation();
	element.getRect();
	Dimension elementSize = element.getSize();
	
	//chụp hình của element lại và lưu bằng 1 format nào đó
	String base64Image = element.getScreenshotAs(OutputType.BASE64);
	element.getScreenshotAs(OutputType.BYTES);
	element.getScreenshotAs(OutputType.FILE);
		
	//lấy ra tên thẻ (html) của element
	element = driver.findElement(By.xpath("//input[@id='email']"));
	element = driver.findElement(By.cssSelector("input[id='email']"));
	String elementTagname = element.getTagName();
	//tên thẻ dc lấy ra là input
	
	//hàm action: luôn luôn là void
	//hàm get: luôn là 1 kiểu dữ liệu (đa phần là String)
	//hàm verify: luôn luôn là boolean (đúng/sai)(true/faulse)
	
	//kiểm tra hiển thị của element - người dùng có thể nhìn thấy dc hay ko
	element.isDisplayed();
	//mong đợi nó hiển thị
	Assert.assertTrue(element.isDisplayed());
	//mong đợi nó ko hiển thị
	Assert.assertFalse(element.isDisplayed());
	
	//kiểm tra element có thể thao tác dc hay ko (hay thuộc dạng Read-only/ disable element)
	element.isEnabled();
	//mong đợi nó thao tác dc (enable)
	Assert.assertTrue(element.isEnabled());
	//mong đợi nó ko thao tác dc (disable/ read-only)
	Assert.assertFalse(element.isEnabled());
	
	//kiểm tra element đã dc chọn hay chưa: Radio Button/ Checkbox/ Dropdown
	element.isSelected();
	//mong đợi nó đã dc chọn
	Assert.assertTrue(element.isSelected());
	//mong đợi nó ko dc chọn
	Assert.assertFalse(element.isSelected());
	
	//submit = Enter trên bàn phím
	//chỉ dùng cho thẻ form/ element nằm trong thẻ form
	element.submit();
	
		
	}
	
	@Test
	public void TC_02() {
	
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