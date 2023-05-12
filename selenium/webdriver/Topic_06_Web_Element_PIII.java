package webdriver;

import java.util.Random;
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
	
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, password, fullName;
	
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
		
		rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = "Automation" + rand.nextInt(999) + "@gmail.com"; 
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}

	@Test
	public void TC_01_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(sendButton).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(emailTextbox).sendKeys("123434@1245.3242");
		driver.findElement(passwordTextbox).sendKeys("123456");

		driver.findElement(sendButton).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");	
		
	}
	
	@Test
	public void TC_03_Password_Less_Than_6_Character() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("12345");

		driver.findElement(sendButton).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");	
		
	}
	
	@Test
	public void TC_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys("123456");

		driver.findElement(sendButton).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");	
		
	}
	
	@Test
	public void TC_05_Create_New_Account() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()= 'Contact Information']/parent::div/following-sibling::div/p")).getText();				
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		

		
	}
	
	@Test
	public void TC_06_Login_Valid_Info() {
		
		driver.findElement(myAccountlink).click();
		sleepInSecond(1);
		
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		driver.findElement(sendButton).click();
		sleepInSecond(1);
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()= 'Contact Information']/parent::div/following-sibling::div/p")).getText();				
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
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