package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PI {
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
		
		// tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// tương tác với Element thì sẽ thông qua biến WebElement 
	
	}

	@Test
	public void TC_01_() {
		// >= 2tab: Nó sẽ đóng tab/window mà nó đang đứng 
		driver.close();
		
		 // Đóng Browser
		driver.quit();
		
		// co the luu no vao 1 bien de su dung cho cac step sau --> dung lai cho nhieu lan
		// tim 1 Element
		WebElement emailTextbox = driver.findElement(By.xpath(""));
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		
		// co the su dung luon (ko can tao bien)
		driver.findElement(By.xpath("//button[@id='login']")).click();
		
		// tim nhieu Element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		// Mo ra 1 URL
		driver.get("https://www.google.com/");
		
		
		// tra ve URL cua page hien tai;
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(),"https://vi-vn.facebook.com/");
		
		// tra ve HTML cua page hien tai
		// verify tuong doi
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn.") );
		
		// tra ve title cua page hien tai
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		// Lay ra ID cua Window/tab ma driver đang  đứng(active)
		String loginWindowID = driver.getWindowHandle();
		
		// Lay ra ID cua tat ca Window/Tab
		Set<String> allIDs = driver.getWindowHandles();
		
		// Cookie/ Cache
		Options opt = driver.manage();
		
		// Login thanh cong --> Luu lai
		opt.getCookies();
		// Tcs khac --> set cookie vao lai --> ko can phai login nua
		
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		// Khoảng thgian chờ element xuất hiện 
		time.implicitlyWait(5, TimeUnit.SECONDS);
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		// Khoảng thgian chờ PageLoad xong trong vong x giay
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		// Khoảng thgian chờ script duoc thuc thi xong trong vong x giay
		time.setScriptTimeout(5, TimeUnit.SECONDS);

		Window win = opt.window();
		
		win.fullscreen();
		win.maximize();
		win.getPosition();
		win.getSize();
		
		
		Navigation nav = driver.navigate();
		
		nav.back();
		nav.refresh();
		nav.forward();
		nav.to("https://www.google.com/");
		
		TargetLocator tar = driver.switchTo();
		tar.alert();
		
		tar.frame("");
		
		tar.window("");
		
	}

	@Test
	public void TC_02_Logo() {
		
	}

	@Test
	public void TC_03_Form() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}