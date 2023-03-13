package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	// check environment
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")){
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Open Register page
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	// HTML of FirstName textbox
	// <input type="text" data-val="true" data-val-required="First name is required." id="FirstName" name="FirstName">
	// id="FirstName" name="FirstName"
	// Ten the of Element (tagname HTML): input
	// Ten cua thuoc tinh (Attribute name): type data-val data-val-required id name
	// Gia tri cua thuoc tinh (Attribute value): text true First name is required. FirstName FirstName

	@Test
	public void TC_01_ID() {
		// Thao tac len element --> tim duoc element: findElement
		// Find theo: id/class/name/css/ xpath
		// Find  tìm thấy element --> action len element: click/sendkey
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		// Open Search page
		driver.get("https://demo.nopcommerce.com/search");
		// Enter text in Search textbox
		driver.findElement(By.className("search-text")).sendKeys("MacBook");
	}

	@Test
	public void TC_03_Name() {
		// Click in Advanced Search checkbox
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	public void TC_04_TagName() {
		// Find bao nhieu the input on screen current
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test
	public void TC_05_LinkText() {
		// Click in Addresses link ( tuyet doi)
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText() {
		// link tuong doi
		driver.findElement(By.partialLinkText("vendor account")).click();
	}

	@Test
	public void TC_07_Css() {
		// Open Register page
		driver.get("https://demo.nopcommerce.com/register");
		
		// 1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		
		// 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		
		// 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("auto@gmail.com");
		
	}
	
	@Test
	public void TC_08_XPath() {
		// Open Register page
		driver.get("https://demo.nopcommerce.com/register");
		
		// 1
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		
		// 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		
		// 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("auto@gmail.com");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}