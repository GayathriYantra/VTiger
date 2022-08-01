package com.vtiger.Email;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class CreateEmailAndVerifyTest {

	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;
		// Fetching the data from propertyfile
		FileInputStream fileinputstream = new FileInputStream(
				"C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\commondata.properties");
		Properties properties = new Properties();
		properties.load(fileinputstream);

		// get the property from propertyfile by using getProperty
		String url = properties.getProperty("url", "IncorrectKey");
		String username = properties.getProperty("username", "IncorrectKey");
		String password = properties.getProperty("password", "IncorrectKey");
		String browser = properties.getProperty("browser", "IncorrectKey");

		// setting up browser // Example for polymorphism
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		// maximizing the browser
		driver.manage().window().maximize();

		// passing the url
		driver.get(url);

		// passing wait condition
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = "vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// passing explicitly wait for getting home page to loaded
		wait.until(ExpectedConditions.titleContains("Administrator"));
		System.out.println("VTiger Home page is displayed, PASS");

		// click on email module
		driver.findElement(By.xpath("//a[text()='Email']")).click();

		// verify email page is displayed or not
		String emailTitle = "Administrator - Email - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(emailTitle)) {
			System.out.println("Email page is displayed, PASS");
		} else {
			System.out.println("Email page is not displayed, FAIL");
		}

		// click on compose buttom
		driver.findElement(By.xpath("//a[text()='Compose']")).click();

		// switching to the window for entering the values in the field
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();

		for (String check : allId) {
			driver.switchTo().window(check);
			String title = driver.getTitle();
			if (title.contains("Emails&action")) {
				break;
			}
		}
		// clicking on select to image to select email
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		// entering into sub child
		String subParent = driver.getWindowHandle();
		Set<String> subAllId = driver.getWindowHandles();

		for (String check2 : subAllId) {
			driver.switchTo().window(check2);
			String title2 = driver.getTitle();
			if (title2.contains("Contacts&action")) {
				break;
			}
		}

		// search box
		driver.findElement(By.id("search_txt")).sendKeys("K");

		// click on search button
		driver.findElement(By.name("search")).click();

		// clicking on contact
		driver.findElement(By.xpath("//a[text()='Arun K']")).click();

		// confirmation
		System.out.println("Email is selected");

		// come back to subparent page
		driver.switchTo().window(subParent);

		// entering subject
		driver.findElement(By.id("subject")).sendKeys("this is demo subject");

		// entering details in body
		driver.findElement(By.xpath("//iframe[@title='Rich text editor, description, press ALT 0 for help.']"))
				.sendKeys("this is to check that body is accepting field or not");

		// confirmation
		System.out.println("body field are filled");

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// coming back to main window
		driver.switchTo().window(parentId);

		// confirmation
		System.out.println("email is created");

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();

	}
}
