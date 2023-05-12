package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	// check environment
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	By firstNameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By dayDropdown = By.name("DateOfBirthDay");
	By monthDropdown = By.name("DateOfBirthMonth");
	By yearDropdown = By.name("DateOfBirthYear");
	By emailAddressTextbox = By.id("Email");
	By companyNameTextbox = By.id("Company");

	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")){
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		}
		
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Thu Qua";
		lastName = "Vo Thi";
		emailAddress = "vttq" + getRandomNumber() + "@gmail.com";
		companyName = "AutomationTesting";
		password = "Abcd1234";
		day = "20"; 
		month = "July"; 
		year = "1990";
		countryName = "United States";
		provinceName = "Alabama";
		cityName = "Miami";
		addressName = "123 PO Box";
		postalCode = "33111";
		phoneNumber = "0999999999";
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		new Select(driver.findElement(dayDropdown)).selectByVisibleText(day);
		new Select(driver.findElement(monthDropdown)).selectByVisibleText(month);
		new Select(driver.findElement(yearDropdown)).selectByVisibleText(year);		
		driver.findElement(emailAddressTextbox).sendKeys(emailAddress);
		driver.findElement(companyNameTextbox).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		// Login
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		// Click My Account
		driver.findElement(By.cssSelector("a.ico-account")).click();

		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		Assert.assertEquals(new Select(driver.findElement(dayDropdown)).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(monthDropdown)).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(yearDropdown)).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(emailAddressTextbox).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(companyNameTextbox).getAttribute("value"), companyName);

		
	}

	@Test
	public void TC_02_Add_New() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();

		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " +lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(),companyName );
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),addressName );
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),countryName );

		driver.findElement(By.cssSelector("button.add-address-button")).click();
		
	}

	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}