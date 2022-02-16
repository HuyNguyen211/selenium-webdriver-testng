package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_run_on_Multiple_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01_Firefox() {
		//Executable file: geckodriver
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//Class: FirefoxDriver
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com");
		driver.quit();
	}
	
	@Test
	public void TC_02_Chrome() {
		//Executable file: chromedriver
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//Class: ChromeDriver
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
		driver.quit();
	}

	@Test
	public void TC_03_Edge() {
		//Executable file: edgedriver
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//Class: EdgeDriver
		driver = new EdgeDriver();
		driver.get("https://www.facebook.com");
		driver.quit();
	}
	
}