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
public class FetchMultipleDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\Testdata2.xlsx"));
		Sheet sheet = workbook.getSheet("Sheet1");
		for(int i =0; i<=sheet.getLastRowNum();i++)
		{
			Row row = sheet.getRow(i);
			for(int j=0;j<=row.getLastCellNum();j++)
			{
				Cell cell = row.getCell(j);
				DataFormatter dataformatter=new DataFormatter();
				String data = dataformatter.formatCellValue(cell);
				System.out.println(data);
			}
		}
	}

}