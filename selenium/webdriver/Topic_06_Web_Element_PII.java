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

public class Topic_06_Web_Element_PII {
	// check environment
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text() = 'Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By jobRole1Drop = By.cssSelector("#job1");
	By jobRole2Drop = By.cssSelector("#job2");
	By jobRole3Drop = By.cssSelector("#job3");
	By developmentCheckbox = By.cssSelector("#development");
	By javaCheckbox = By.cssSelector("#java");
	By slider01 = By.cssSelector("#slider-1");
	By slider02 = By.cssSelector("#slider-2");



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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Textbox
		if (driver.findElement(emailTextbox).isDisplayed()){
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		// Texarea
		if (driver.findElement(educationTextArea).isDisplayed()){
			driver.findElement(educationTextArea).sendKeys("Selenium GRID");
			System.out.println("Education textArea is displayed");
		} else {
			System.out.println("Education textArea is not displayed");
		}
		
		// Radio button
		if (driver.findElement(ageUnder18Radio).isDisplayed()){
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under 18 is displayed");
		} else {
			System.out.println("Age Under 18 is not displayed");
		}
		
		// 
		if (driver.findElement(nameUser5Text).isDisplayed()){
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	 @Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// passwordTextbox
		if (driver.findElement(passwordTextbox).isEnabled()){
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disable");
		}
		
		// biographyTextArea
		if (driver.findElement(biographyTextArea).isEnabled()){
			System.out.println("Biography textArea is enabled");
		} else {
			System.out.println("Biography textArea is disable");
		}
		
		// emailTextbox
		if (driver.findElement(emailTextbox).isEnabled()){
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disable");
		}
		
		// jobRole1Drop
		if (driver.findElement(jobRole1Drop).isEnabled()){
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disable");
		}
		
		// jobRole2Drop
		if (driver.findElement(jobRole2Drop).isEnabled()){
			System.out.println("Job Role 2 is enabled");
		} else {
			System.out.println("Job Role 2 is disable");
		}
		
		// jobRole3Drop
		if (driver.findElement(jobRole3Drop).isEnabled()){
			System.out.println("Job Role 3 is enabled");
		} else {
			System.out.println("Job Role 3 is disable");
		}
		
		// developmentCheckbox
		if (driver.findElement(developmentCheckbox).isEnabled()){
			System.out.println("Development Checkbox is enabled");
		} else {
			System.out.println("Development Checkbox is disable");
		}
		
		// slider01
		if (driver.findElement(slider01).isEnabled()){
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disable");
		}
		
		// slider01
		if (driver.findElement(slider02).isEnabled()){
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02 is disable");
		}
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Verify checkbox/ radio button are deselected
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(javaCheckbox).isSelected());

		// Click to checkbox/ radio button
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		driver.findElement(javaCheckbox).click();
		
		// Verify checkbox/radio button are selected
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(javaCheckbox).isSelected());
		
		
		
		sleepInSecond(2);

		
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.cssSelector("#email")).sendKeys("johnwick2022@gmail.com");
		
		By passwordTextbox = By.id("new_password");
		
		
		driver.findElement(passwordTextbox).sendKeys("abc");
		sleepInSecond(2);
		
		// Veridy lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		sleepInSecond(2);
		
		// Veridy uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(2);
		
		// Veridy number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
		sleepInSecond(2);
		
		// Veridy special char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCDXYZH");
		sleepInSecond(2);
		
		// Veridy char >= 8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123abcABC!@#");
		sleepInSecond(2);
		
		// Veridy full data		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

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