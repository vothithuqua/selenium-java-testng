package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {

	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();
		sleepInSecond(3);
		
		//1 co the switch qua va tuong tac
		//alert = driver.switchTo().alert();
		
		// 2 Can wait truoc khi nao xuat hien thi moi switch qua va tuong tac
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert xuat hien
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// accept alert
		alert.accept();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();
		sleepInSecond(3);
		
		// 2 Can wait truoc khi nao xuat hien thi moi switch qua va tuong tac
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert xuat hien
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// accept alert
		alert.dismiss();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
		
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
		sleepInSecond(3);
		
		// 2 Can wait truoc khi nao xuat hien thi moi switch qua va tuong tac
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Verify alert xuat hien
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String courseName = "Automation Testing";
		// Nhap text
		alert.sendKeys(courseName);
		sleepInSecond(2);
		
		// accept alert
		alert.accept();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
		
	}
	
	@Test
	public void TC_04_Authentication_Alert_I() {
		//driver.get("http://admin:admin@the-internet.herokuapp.com/");
		//String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		//driver.get(passUserAndPassToUrl(authenUrl,"admin","admin"));
		driver.get(passUserAndPassToUrl("https://the-internet.herokuapp.com/basic_auth","admin","admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password +"@" +arrayUrl[1];
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