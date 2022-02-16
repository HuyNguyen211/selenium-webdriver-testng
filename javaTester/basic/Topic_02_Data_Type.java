package basic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Topic_02_Data_Type {
	WebDriver driver;
	
	public void main(String[] args) {
		//2 loại kiểu dữ liệu
		//Kiểu nguyên thủy: Primitive Type
		//8 loại:
		//char - ký tự - 16
		char c = 'A';
		System.out.println(c);
		
		//byte - số nguyên - 8
		byte bNumber = 15;
		System.out.println(bNumber);
		
		//short - số nguyên - 16
		short sNumber = -32000;
		System.out.println(sNumber);
		
		//int - số nguyên - 32
		int iNumber = 2000000000;
		System.out.println(iNumber);
		
		//long - số nguyên - 64
		long lNumber = 945612371;
		System.out.println(lNumber);
		
		//float - số thực - 32
		float fNumber = 9.5f;
		System.out.println(fNumber);
		
		//double - số thực - 64
		double dNumber = 9.5d;
		System.out.println(dNumber);
		
		//boolean - logic - 1 (true/ faulse)
		boolean marriestatus = true;
		System.out.println(marriestatus);
		
		//Kiểu tham chiếu: Reference Type
		//String
		String name = "asd 123 !@#";
		String cityName = new String("HCM");
		
		//Object
		Object o = new Object();
		
		//Array
		String[] address = {"HN", "DN", "SG"};
		Integer[] phone = {1234, 45678};
		long a[] = {1000L, 2000L, 3000L};
		
		//Class
		Topic_02_Data_Type topic = new Topic_02_Data_Type();
		
		//Interface
		//WebDriver driver;
		
		//Collection: List/ Set/ Queue
		List<String> addresses = new ArrayList<String>();
		
		WebElement emailTextbox = driver.findElement(By.cssSelector("email"));
		
		List<WebElement> checkboxs = driver.findElements(By.name("input"));
		
		//driver nên dc khai báo ở biến toàn cục chứ ko phải ở biến cục bộ
		
		//biến local = biến cục bộ
		//khi chưa khởi tạo sẽ ko có giá trị
		
		//biến global = biến toàn cục
		//khi chưa khởi tạo sẽ có giá trị mặc định

	}

}
