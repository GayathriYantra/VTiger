package com.vtiger.Contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class CreateContactTest {

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

		// maximize the browser
		driver.manage().window().maximize();

		// implicit wait of 10 Seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// enter the URL of RMG Yantra application
		driver.get(url);

		// enter the username in username textfield
		driver.findElement(By.name("user_name")).sendKeys(username);

		// enter the password in password txtfield
		driver.findElement(By.name("user_password")).sendKeys(password);

		// click on login button
		driver.findElement(By.id("submitButton")).click();

		// click on contacts button
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();

		// click on create contact lookup icon
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// enter fistname
		driver.findElement(By.name("firstname")).sendKeys("Yazhisai7");

		// enter lastname
		driver.findElement(By.name("lastname")).sendKeys("Sarath7");

		// click on save button
		driver.findElement(By.xpath("//input[contains(@class,'crmButton small save')]")).click();

		// verify whether the contact is created or not
		String contitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		// String expectedcontitle="[ CON7 ] Yazhisai7 Sarath7 - Contact Information";
		// if(contitle.equals(expectedcontitle))
		if (contitle.contains("Yazhisai7")) {
			System.out.println("contact is created");
		} else {
			System.out.println("contact is not created");
		}

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
