package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Radio_Checkbox {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")){
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Radio_Case1() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

	@Test
	public void TC_02_Radio_Case2() {
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
	}
	
	@Test
	public void TC_03_Radio_Checkbox_Google_Form() {
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		
		By radioButton = By.cssSelector("div[aria-label='Đà Nẵng']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Bình']");

		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(2);
		
		// cach 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Đà Nẵng'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Bình'][aria-checked='true']")).isDisplayed());

		// cach 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
	}

	
	public void sleepInSecond(long TimeInSecond) {
		try {
			Thread.sleep(TimeInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}