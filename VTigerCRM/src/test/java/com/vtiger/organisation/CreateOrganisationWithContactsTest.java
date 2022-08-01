package com.vtiger.organisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
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

import com.crm.GenericUtility.ExcelUtility;
import com.crm.GenericUtility.FileUtility;
import com.crm.GenericUtility.IConstants;
import com.crm.GenericUtility.JavaUtility;
import com.crm.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class CreateOrganisationWithContactsTest {

	public static void main(String[] args) throws Throwable {

		WebDriver driver = null;
		// creating Objects for generic utility classes
		ExcelUtility eLib = new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		//Fetch the Common data from Property file
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String LOGIN_TITLE = fLib.getPropertyKeyValue("loginTitle");

		// setting up browser // Example for polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			System.setProperty(IConstants.chromeKey,IConstants.chromeValue);
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			System.setProperty(IConstants.firefoxKey, IConstants.firefoxValue);
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// maximizing the browser
		wLib.maximizeTheWindow(driver);

		// passing the url
		driver.get(URL);

		// passing wait condition
		wLib.waitUntilPageGetsLoaded(driver);

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		//String loginTitle = fLib.getPropertyKeyValue(LOGIN_TITLE);
		if (driver.getTitle().equals(LOGIN_TITLE)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger login page is not displayed" + driver.getTitle());
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wLib.waitTillPageLoadTitle(driver, LOGIN_TITLE);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// passing explicitly wait for getting home page to loaded
		System.out.println("VTiger Home page is displayed, PASS");
		
		
		// click on organization moduleLOGIN_TITLE
		driver.findElement(By.linkText("Organizations")).click();

		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		//To get the data from excel sheet 
		String orgName = eLib.getDataFromExcelSheet("Organisations",1,4);
		if (driver.getTitle().equals(orgName)) {
			System.out.println("Organisation Page is Displayed, PASS");
		} else 
		{
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
		String OrgName=eLib.getDataFromExcelSheet("Organisations", 1, 2);
		driver.findElement(By.name("accountname")).sendKeys(OrgName);

		// Selecting Industry
		driver.findElement(By.xpath("//select[@name='industry']")).click();
		wLib.select(OrgName, elementNewOrganization);
		String industryType = eLib.getDataFromExcelSheet("Organisations", 1,6);
		//wLib.select(industryType, element);
		//select.selectByValue(eLib.getDataFromExcelSheet("IndustryType", 1, 6));

		// selecting type
		WebElement element1 = driver.findElement(By.xpath("//select[@name='accounttype']"));
		String type = eLib.getDataFromExcelSheet("Organisations",1,7);
		driver.findElement(By.id("email1")).sendKeys(eLib.getDataFromExcelSheet("Organisations", 1, 8));

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
		String contactTitle = fLib.getPropertyKeyValue("contactTitle");
		if (driver.getTitle().equals(contactTitle))
		{
			System.out.println("Contact Page is Displayed, PASS");
		}
		else 
		{
			System.out.println("Contact page is not displayed" + driver.getTitle());
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

		//To generate random number
		Random randNum= new Random();
		int randNum1 = randNum.nextInt();
		// entering firstname
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(eLib.getDataFromExcelSheet("Contacts", 2, 2)+randNum1);

		// entering lastname
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(eLib.getDataFromExcelSheet("Contacts", 2, 3)+randNum1);

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
		driver.findElement(By.name("search_text")).sendKeys(eLib.getDataFromExcelSheet("Organisations", 2, 2)+randNum1);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.id("1")).click();

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains(eLib.getDataFromExcelSheet("Contacts", 2, 2)+randNum1)) {
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
		driver.quit();

	}

}
