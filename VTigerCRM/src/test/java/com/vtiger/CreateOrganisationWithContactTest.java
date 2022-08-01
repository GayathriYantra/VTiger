package com.vtiger;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.GenericUtility.ExcelUtility;
import com.crm.GenericUtility.FileUtility;
import com.crm.GenericUtility.JavaUtility;
import com.crm.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationWithContactTest {

	public static void main(String[] args) throws Throwable {

		// setting up browser
		WebDriverManager.firefoxdriver().setup();

		// creating object for browser
		WebDriver driver = new FirefoxDriver();
		
		 ExcelUtility eLib = new ExcelUtility();
		 FileUtility fLib=new FileUtility();
		 JavaUtility jLib = new JavaUtility();
		 WebDriverUtility wLib = new WebDriverUtility();

		 //Fetch the common data from Property file
		 String URL = fLib.getPropertyKeyValue("url");
		 String BROWSER = fLib.getPropertyKeyValue("browser");
		 String USERNAME = fLib.getPropertyKeyValue("username");
		 String PASSWORD = fLib.getPropertyKeyValue("password");
		 
		 // maximizing the browser
		 wLib.maximizeTheWindow(driver);
		 
		// passing the url
		 driver.get(URL);
		// passing wait condition
		 wLib.waitUntilPageGetsLoaded(driver);

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = fLib.getPropertyKeyValue("loginTitle");
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger login page is not displayed" + driver.getTitle());
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wLib.waitTillPageLoadTitle(driver, loginTitle);
		
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
		driver.findElement(By.id("email1")).sendKeys("gayathridayalan.ece@gmail.com");

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
