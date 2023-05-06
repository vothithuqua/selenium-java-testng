package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PIII {
	// check environment
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");
	By myAccountlink = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By sendButton = By.id("send2");




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
	public void TC_01_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(2);
		
		driver.findElement(sendButton).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(2);
		
		driver.findElement(emailTextbox).sendKeys("123434@1245.3242");
		driver.findElement(passwordTextbox).sendKeys("123456");

		driver.findElement(sendButton).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");	
		
	}
	
	@Test
	public void TC_03_Password_Less_Than_6_Character() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(2);
		
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("12345");

		driver.findElement(sendButton).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");	
		
	}
	
	@Test
	public void TC_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(2);
		
		driver.findElement(emailTextbox).sendKeys("ttttthuqua@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");

		driver.findElement(sendButton).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");	
		
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