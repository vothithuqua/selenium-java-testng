package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

public class Topic_10_Button_Radio_Checkbox {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By loginButton = By.cssSelector("button.fhs-btn-login");

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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		// Verify Login button la Disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987666777");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(2);
		
		// Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");
		
	}
	
	@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click chon 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Ulcerative Colitis')]/preceding-sibling::input")).click();
		
		// Click chon 1 radio button
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		// Verify cac checkbox/radio da duoc selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Ulcerative Colitis')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
		driver.findElement(By.xpath("//label[contains(text(),'Ulcerative Colitis')]/preceding-sibling::input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Ulcerative Colitis')]/preceding-sibling::input")).isSelected());

		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Luu all item vao list
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Dung vong lap de duyet
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
			//sleepInSecond(1);
			
		}
		// Verify tat ca checkbox duoc chon thanh cong
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
			}	
		
		// Chon bat ki 1 checkbox
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("Value").equals("Bleeding Disorders")) {
				checkbox.click();
			}	
		}
	}
	
	@Test
	public void TC_03_Default_Checkbox_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		checkToCheckbox(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected());
		
		UncheckToCheckbox(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		
		if (!driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		}
	}
	
	@Test
	public void TC_04_Default_Checkbox_Radio_Button() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		driver.findElement(By.xpath("//label[contains(text(),'Summer')]/preceding-sibling::div/input")).click();
		sleepInSecond(3);

		if (!driver.findElement(By.xpath("//label[contains(text(),'Summer')]/preceding-sibling::div/input")).isSelected()) {
			driver.findElement(By.xpath("//label[contains(text(),'Summer')]/preceding-sibling::div/input")).click();
		}
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		checkToCheckbox(By.xpath("//label[contains(text(),'Checked')]/preceding-sibling::div/input"));
		checkToCheckbox(By.xpath("//label[(text()='Indeterminate')]/preceding-sibling::div/input"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Checked')]/preceding-sibling::div/input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[(text()='Indeterminate')]/preceding-sibling::div/input")).isSelected());
		
		UncheckToCheckbox(By.xpath("//label[contains(text(),'Checked')]/preceding-sibling::div/input"));
		UncheckToCheckbox(By.xpath("//label[(text()='Indeterminate')]/preceding-sibling::div/input"));
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Checked')]/preceding-sibling::div/input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[(text()='Indeterminate')]/preceding-sibling::div/input")).isSelected());
		
	}
	
	public void checkToCheckbox (By by) {
		driver.findElement(by).isSelected();
		driver.findElement(by).click();
	}
	
	public void UncheckToCheckbox (By by) {
		driver.findElement(by).isSelected();
		driver.findElement(by).click();
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