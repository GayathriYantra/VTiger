package com.vtiger.organisation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericUtility.ExcelUtility;
import com.crm.GenericUtility.FileUtility;
import com.crm.GenericUtility.JavaUtility;
import com.crm.GenericUtility.WebDriverUtility;
import com.crm.vtiger.objectRepository.CreateOrganizationPage;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;
import com.crm.vtiger.objectRepository.OrganisationPage;
import com.crm.vtiger.objectRepository.OrganisationalInfoPage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class CreateOrganisationTest {

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
		String ORG_TITLE = fLib.getPropertyKeyValue("orgTitle");



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

		//Fetch data from excel sheet
		String organisationName = eLib.getDataFromExcelSheet("Organisations",1,2)+randNum;

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		if (driver.getTitle().equals(LOGIN_TITLE)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		LoginPage login= new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);

		//click on organaisation link  //(//a[.='Organisation'])
		HomePage homepage=new HomePage(driver);
		homepage.clickOrganisationLink();

		//click on create organization LookupIcon
		OrganisationPage organisationPage = new OrganisationPage(driver);

		//click on create organisation
		CreateOrganizationPage createOrganization=new CreateOrganizationPage(driver);
		createOrganization.enterOrgname(organisationName);
		createOrganization.clickSaveBtn();

		// verify whether the Organization is created or not
		OrganisationalInfoPage organisationalInfoPage = new OrganisationalInfoPage(driver);
		String orgTitle = organisationalInfoPage.getOrganisationInfoText().getText();
		System.out.println(orgTitle);

		if(orgTitle.contains(ORG_TITLE))
		{
			System.out.println("Organisation is created");
		}
		else
		{
			System.out.println("Organisation is not created" );
		}

		//Click on Sign Out
		homepage.Logout(driver);
		
		//quit the browser
		driver.quit();
	}
}
