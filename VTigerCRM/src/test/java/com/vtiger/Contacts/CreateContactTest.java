package com.vtiger.Contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericUtility.ExcelUtility;
import com.crm.GenericUtility.FileUtility;
import com.crm.GenericUtility.JavaUtility;
import com.crm.GenericUtility.WebDriverUtility;
import com.crm.vtiger.objectRepository.ContactInfoPage;
import com.crm.vtiger.objectRepository.ContactsPage;
import com.crm.vtiger.objectRepository.CreateContactsPage;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class CreateContactTest 
{

	public static void main(String[] args) throws Throwable
	{

	WebDriver driver= null;

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
	String CONT_TITLE = fLib.getPropertyKeyValue("contactTitle");



	// setting up browser // Example for polymorphism
	if (BROWSER.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	} else if (BROWSER.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	} else {
		driver = new ChromeDriver();
	}

	//TO get the Random Number
	int randNum = jLib.getRandomNum();

	// maximizing the browser
	wLib.maximizeTheWindow(driver);

	// passing the url
	driver.get(URL);

	// passing wait condition
	wLib.waitUntilPageGetsLoaded(driver);

	//Fetch firstname and lastname from excel sheet
	String firstName1 = eLib.getDataFromExcelSheet("Contacts",2,2)+randNum;
	String lastName1 = eLib.getDataFromExcelSheet("Contacts",2,3)+randNum;

	// enter username, password and click on login button
	LoginPage login= new LoginPage(driver);
	login.loginToApp(USERNAME, PASSWORD);

	// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
	if (driver.getTitle().equals(LOGIN_TITLE)) {
		System.out.println("VTiger Login Page is Displayed, PASS");
	} else {
		System.out.println("VTiger Login page is not displayed, FAIL");
	}


	// click on contacts button
	HomePage homepage = new HomePage(driver);
	homepage.clickContactLink();

	// click on create contact lookup icon
	ContactsPage contact = new ContactsPage(driver);
	contact.clickCreateContactLkpIcon();

	// enter firstname and lastName and click on save button
	CreateContactsPage createContactsPage=new CreateContactsPage(driver);
	createContactsPage.createContact(firstName1, lastName1);

	// verify whether the contact is created or not
	ContactInfoPage contactInfoPage=new ContactInfoPage(driver);
	String contactTitle = contactInfoPage.getCreatedContactName().getText();

	if (contactTitle.contains(CONT_TITLE)) {
		System.out.println("contact is created");
	} else {
		System.out.println("contact is not created");
	}

	//Click on Sign Out
	homepage.Logout(driver);

	//quit the browser
	driver.quit();
}

}
