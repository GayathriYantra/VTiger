package com.vtiger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentAndVerify {

	public static void main(String[] args) throws InterruptedException
	{
				// setting up browser
				WebDriverManager.firefoxdriver().setup();

				// creating object for browser
				WebDriver driver = new FirefoxDriver();

				// maximizing the browser
				driver.manage().window().maximize();

				// passing the url
				driver.get("http://localhost:8888/");

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
				driver.findElement(By.name("user_name")).sendKeys("admin");
				driver.findElement(By.name("user_password")).sendKeys("admin");
				driver.findElement(By.id("submitButton")).submit();

				// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				// passing explicitly wait for getting home page to loaded
				wait.until(ExpectedConditions.titleContains("Administrator"));
				System.out.println("VTiger Home page is displayed, PASS");

				// click on documents module
				driver.findElement(By.xpath("//a[text()='Documents']")).click();

				// verify documents page is displayed or not
				String documentsTitle = "Administrator - Documents - vtiger CRM 5 - Commercial Open Source CRM";
				if (driver.getTitle().equals(documentsTitle)) {
					System.out.println("Document page is displayed, PASS");
				} else {
					System.out.println("Document page is not displayed, FAIL");
				}

				// clicking on create document img
				driver.findElement(By.xpath("//img[@title='Create Document...']")).click();

				// enter title field
				driver.findElement(By.name("notes_title")).sendKeys("DemoDocuments");

				// entering details in the frame
				driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']"))
						.sendKeys("This is working");

//				Thread.sleep(3000);
//				driver.switchTo().frame(frameElement);
//				
//				WebElement Element = driver.findElement(By.xpath("//body[@class='cke_show_borders']"));
//				
//				System.out.println(Element.isEnabled());
//				JavascriptExecutor jse = (JavascriptExecutor)driver;
//				jse.executeScript("arguments[0].innerText='demo entry'", Element);
//				
//				//switchback to default frame
//				driver.switchTo().defaultContent();

				// scrolldown
				WebElement scrollElement = driver.findElement(By.xpath("//b[text()='File Information']"));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", scrollElement);

				// sending file to upload
				driver.findElement(By.name("filename")).sendKeys("C:\\Users\\ARUN\\OneDrive\\Desktop\\DEMO.txt");

				// click on save
				driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();

				// verify documents is created or not
				String documents = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (documents.contains("DemoDocuments")) {
					System.out.println("Document is created, PASS");
				} else {
					System.out.println("Document is not created, FAIL");
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


