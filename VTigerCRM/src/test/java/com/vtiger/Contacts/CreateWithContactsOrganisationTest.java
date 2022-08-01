package com.vtiger.Contacts;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author admin
 *
 */
public class CreateWithContactsOrganisationTest {

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
			System.out.println("VTiger login page is not displayed" + driver.getTitle());
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

		// click on organization module
		driver.findElement(By.linkText("Organizations")).click();

		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(organizationTitle)) {
			System.out.println("Organisation Page is Displayed, PASS");
		} else {
			System.out.println("Organisation page is not displayed" + driver.getTitle());
		}

		// clicking on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// VERIFYING CREATE NEW ORGANIZATION PAGE IS DISPLAYED OR NOT
		WebElement elementNewOrganization = driver.findElement(By.xpath("//span[text()='Creating New Organization']"));
		if (elementNewOrganization.isDisplayed()) {
			System.out.println("Create new organization page is displayed, PASS");
		} else {
			System.out.println("Create new organization page is not displayed, FAIL");
		}

		// Entering the organization name
		driver.findElement(By.name("accountname")).sendKeys("AK Enterprises");

		// Selecting Industry
		Select select = new Select(driver.findElement(By.xpath("//select[@name='industry']")));
		select.selectByValue("Banking");

		// selecting type
		Select select1 = new Select(driver.findElement(By.xpath("//select[@name='accounttype']")));
		select1.selectByValue("Competitor");

		// entering email
		driver.findElement(By.id("email1")).sendKeys("arun112@gmail.com");

		// click on save button
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

		// VERIFICATION
		WebElement informationElement = driver.findElement(By.linkText("More Information"));
		if (informationElement.isDisplayed()) {
			System.out.println("Organization Information page is displayed, PASS");
		} else {
			System.out.println("Organization Information page is not displayed, FAIL");
		}

		// clicking on contacts module
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();

		// VERIFICATION CONTACTS PAGE IS DISPLAYED OR NOT
		String contactTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(contactTitle)) {
			System.out.println("Contact Page is Displayed, PASS");
		} else {
			System.out.println("contact page is not displayed" + driver.getTitle());
		}

		// clicking on create contact image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// VERIFICATION
		WebElement createNewContactElement = driver.findElement(By.xpath("//span[text()='Creating New Contact']"));

		if (createNewContactElement.isDisplayed()) {
			System.out.println("Create new contact page is displayed, PASS");
		} else {
			System.out.println("Create new conatct page is not displayed, FAIL");
		}

		// selecting salutation
		Select select2 = new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
		select2.selectByValue("Mr.");

		// entering firstname
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Arun");

		// entering lastname
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("K");

		// clicking on orgnization name
		driver.findElement(By.xpath("//tbody/tr[5]/td[2]/img[1]")).click();
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();

		for (String popup : allId) {
			driver.switchTo().window(popup);
			String actTitle = driver.getTitle();
			if (actTitle.contains("Accounts&action")) {
				break;
			}
		}

		// searching the organization name and clicking it
		driver.findElement(By.name("search_text")).sendKeys("AK Enterprises");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains("Arun")) {
			System.out.println("Contact is created, PASS");
		} else {
			System.out.println("Contact is not created, FAIL");
		}

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();

	}
}
