package com.crm.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class ExcelUtility 
{
	/**
	 * To Fetch the data from the excel sheet
	 * @return
	 * @throws Throwable 
	 * @throws FileNotFoundException 
	 * @throws EncryptedDocumentException 
	 */
	public String getDataFromExcelSheet(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, FileNotFoundException, Throwable
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		DataFormatter format=new DataFormatter();
		String value = format.formatCellValue(cell);
		return value;
	}
	/**
	 * to insert the data into excel sheet
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws EncryptedDocumentException 
	 * 
	 */
	public void insertDataIntoExcelSheet(String sheetName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, FileNotFoundException, IOException
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fileoutputstream=new FileOutputStream(IConstants.excelPath);
		workbook.write(fileoutputstream);
	}
	/**
	 * to get the last row number
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 * @throws Throwable
	 * @throws Throwable
	 */
	public int getLastRowNumFromExcel(String sheetName) throws Throwable, Throwable, Throwable
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
		Sheet sheet1 = workbook.getSheet(sheetName);
		int row = sheet1.getLastRowNum();
		return row;
	}
}
