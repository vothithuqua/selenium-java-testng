package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PI {
	// check environment
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextboxBy = By.id("Email");
	By passwordTextboxBy = By.id("Pass");


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")){
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		WebElement element = driver.findElement(By.className(""));
		
		// Dùng cho các textbox/ textarea/ dropdown (Editable)
		// Xoá dữ liệu đi trước khi nhập text 
		element.clear();
		
		// Dùng cho các textbox/ textarea/ dropdown (Editable)
		// Nhập liệu
		element.sendKeys("");
		
		// Click vào các button/ link/ checkbox/ radio/ image/ ...
		element.click();
		
		
		String searchAttribute = element.getAttribute("placeholder");
		String emailtextboxAtrribute = element.getAttribute("value");
		
		//Gui: font/size/ color/ Location/ position/..
		element.getCssValue("background-color");
		
		// Vị trí Element so với Web ( bên ngoài) 
		Point point = element.getLocation();
		point.x = 324;
		point.y = 324;
		
		// Kích etthước của Element (bên trong)
		Dimension di = element.getSize();
		di.getHeight();
		di.getWidth();
		
		System.out.println(di.height);
		System.out.println(di.width);

		// Location + size
		Rectangle rec = element.getRect();
		
		// Screenshot khi testcase fail
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// Cần lấy tên thẻ HTML của element đó --> Truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//"+ emailTextboxTagname + "[@id = 'email']" ));
		
		// Lấy text từ Error message/ Success message/ label/ header/ ...
		element.getText();
		
		// Dung verify xem 1 element hien thi hay ko
		// Phạm vi: Tất cả các element 
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());

		// Dung verify xem 1 element có thao tác được hay ko 
		// Phạm vi: Tất cả các element 
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
				
		// Dung verify xem 1 element được chọn hay chưa 
		// Phạm vi: Checkbox/ Radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		// Dùng trong form , các element nằm trong thẻ form 
		// Tương ứng với hành vi End user (Enter)
		element.submit();		
	}

	@Test
	public void TC_02_Register() {
		driver.get("");
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");
		
	}

	@Test
	public void TC_03_Login() {
		driver.get("");
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(passwordTextboxBy).sendKeys("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}