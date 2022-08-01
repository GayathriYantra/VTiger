package com.vtiger.Datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author admin
 *
 */
public class FetchOnly1stAnd2ndColDataTest {
	

	public static void main(String[] args) throws Throwable, FileNotFoundException, IOException 
	{
	Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\Testdata2.xlsx"));
	Sheet sheet = workbook.getSheet("Sheet1");
	int count = sheet.getLastRowNum();
	for(int i=0;i<count;i++)
	{
		Row row = sheet.getRow(i);
		String firstCellData = row.getCell(0).toString();
		DataFormatter dataformatter = new DataFormatter();
		
		String secondCellData = row.getCell(1).toString();	
		System.out.println(firstCellData+"\t"+secondCellData);
	}
	
	}

}
