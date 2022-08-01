package com.vtiger.organisation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

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
public class CreateOrganisationWithIndustryTest 
{
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;
		// create organization with industry is done
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

		//fetch organization name from excel sheet
		String organisationName = eLib.getDataFromExcelSheet("Organisations",1,2)+randNum;

		//fetch the Industry from excel sheet
		String industry = eLib.getDataFromExcelSheet("Organisations", 1, 6);

		//fetch the Type from excel sheet
		String type = eLib.getDataFromExcelSheet("Organisations", 1, 7);


		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		if (driver.getTitle().equals(LOGIN_TITLE)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		LoginPage login= new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);

		//click on organaisation link  
		HomePage homepage=new HomePage(driver);
		homepage.clickOrganisationLink();

		//click on create organization LookupIcon
		OrganisationPage organisationPage = new OrganisationPage(driver);
		organisationPage.clickOrganisationLkpIcon();

		//enter the Organisation name, select industry, select type
		CreateOrganizationPage createOrganization=new CreateOrganizationPage(driver);
		createOrganization.enterOrgname(organisationName);
		createOrganization.selectIndustry("Banking");
		createOrganization.selectType("Press");
		createOrganization.clickSaveBtn();

		// verify whether the Organization is created or not
		OrganisationalInfoPage organisationalInfoPage = new OrganisationalInfoPage(driver);
		String orgTitle = organisationalInfoPage.getOrganisationInfoText().getText();
		
		if(orgTitle.contains(organisationName))
		{
			System.out.println("Organisation with industry and type is created");
		}
		else
		{
			System.out.println("Organisation with industry and type is not created" );
		}

		//click on Sign Out
		homepage.Logout(driver);
		
		//quit the browser
		driver.quit();
	}
}


