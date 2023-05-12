package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery_Speed() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slow");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Fast");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slower");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");

	}

	@Test
	public void TC_02_JQuery_Title() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Mrs.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mrs.");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Dr.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Prof.");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Other");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Other");

	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Justen Kitsune");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");

	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

	}

	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterEndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");

		enterEndSelectItemInDropdown("input.search", "span.text", "Belize");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belize");

	}

	public void sleepInSecond(long TimeInSecond) {
		try {
			Thread.sleep(TimeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Khai báo hàm dùng chung 
	// Đi kèm với tham số
	public void selectItemInDropdown(String parrentCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parrentCss)).click();
		sleepInSecond(1);
		// Chờ load các item
		// Locator phải lấy đại diện tất cả
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		// Đưa hết item vào List
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		// Gán biến tempItem ( như 1 biến tạm)
		for (WebElement tempItem : speedDropdownItems) {
			// Kiểm tra item cần chọn
			// trim() cut space in text
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				// Click item
				tempItem.click();
				break;
			}
		}
	}

	public void enterEndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		// Nhap expected text item
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);

		// Đưa hết item vào List
		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		// Gán biến tempItem ( như 1 biến tạm)
		for (WebElement tempItem : speedDropdownItems) {
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				sleepInSecond(1);
				// Click item
				tempItem.click();
				break;
			}
		}

	}

}