package com.vtiger.Products;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
public class CreateProductWithVendorTest 
{

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=null;
		// Fetching the data from propertyfile
		FileInputStream fileinputstream = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\commondata.properties");
		Properties properties = new Properties();
		properties.load(fileinputstream);

		// get the property from propertyfile by using getProperty
		String url = properties.getProperty("url", "IncorrectKey");
		String username = properties.getProperty("username", "IncorrectKey");
		String password = properties.getProperty("password", "IncorrectKey");
		String browser = properties.getProperty("browser", "IncorrectKey");

		// setting up browser                // Example for run polymorphism
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();	
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();	
			driver=new FirefoxDriver();
		}else
		{
			driver=new ChromeDriver();
		}
		Random random = new Random();
		int randomNum = random.nextInt(1000); //give the expectation 
		FileInputStream fileinputstream1 = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\TestData.xlsx");
		
		//Take control of excel file
		Workbook wb = WorkbookFactory.create(fileinputstream1);
		
		String val = wb.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		
		
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

		// mouse hover to more
		WebElement moreElement = driver.findElement(By.xpath("//a[text()='More']"));
		Actions action = new Actions(driver);
		action.moveToElement(moreElement).perform();

		// click on vendors
		driver.findElement(By.xpath("//a[text()='Vendors']")).click();

		// verify vendors page is displayed or not
		String vendorTitle = "Administrator - Vendors - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(vendorTitle)) {
			System.out.println("Vendor page is displayed, PASS");
		} else {
			System.out.println("Vendor page is not displayed, FAIL");
		}

		// clicking on create vendor img
		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();

		// enter value in vendor name
		driver.findElement(By.name("vendorname")).sendKeys(val);

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verifying vendor is created or not
		String vendor = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (vendor.contains(val)) {
			System.out.println("Vendor is created, PASS");
		} else {
			System.out.println("Vendor is not created, FAIL");
		}

		// clicking on products module
		driver.findElement(By.xpath("//a[@href='index.php?module=Products&action=index']")).click();

		// verifying products page is displayed or not
		String productsTitle = "Administrator - Products - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(productsTitle)) {
			System.out.println("Products page is displayed, PASS");
		} else {
			System.out.println("Products page is not displayed, FAIL");
		}

		// clicking on create product img
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		// entering details in product name tab
		driver.findElement(By.name("productname")).sendKeys("RK Product");

		// selecting vendorname
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();

		for (String check : allId) {
			driver.switchTo().window(check);
			String title = driver.getTitle();
			if (title.contains("Vendors&action")) {
				break;
			}
		}
		// entering the vendor name
		driver.findElement(By.id("search_txt")).sendKeys("KiranRaj");

		// click on search button
		driver.findElement(By.name("search")).click();

		// select vendor name
		driver.findElement(By.xpath("//a[text()='KiranRaj']")).click();

		// switch back to main window
		driver.switchTo().window(parentId);

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify whether product is created or not
		String product = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (product.contains("RK Product")) {
			System.out.println("Product is created, PASS");
		} else {
			System.out.println("Product is not created, FAIL");
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