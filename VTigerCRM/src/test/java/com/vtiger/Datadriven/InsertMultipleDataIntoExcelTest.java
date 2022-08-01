package com.vtiger.Datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author admin
 *
 */
public class InsertMultipleDataIntoExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException {
		Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\testdata1.xlsx"));
		Sheet sheet = workbook.getSheet("sheet1");
		WebDriver driver = WebDriverManager.chromedriver().create();

		// Fetching the data from propertyfile
		FileInputStream fileinputstream = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\commondata.properties");
		Properties properties = new Properties();
		properties.load(fileinputstream);

		// get the property from propertyfile by using getProperty
		String url = properties.getProperty("url", "IncorrectKey");
		String username = properties.getProperty("username", "IncorrectKey");
		String password = properties.getProperty("password", "IncorrectKey");
		String browser = properties.getProperty("browser", "IncorrectKey");
		driver.get(url);
		
		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).submit();
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		int count=links.size();
		for(int i = 0;i<count; i++)
		{
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(links.get(i).getAttribute("href"));
		}
		FileOutputStream fileoutputstream=new FileOutputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\testdata1.xlsx");
		workbook.write(fileoutputstream);
		System.out.println("Links Inserted");
		
	}

}
