package com.vtiger.Datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author admin
 *
 */
public class InsertSingleDataIntoExcelTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fileinputstream=new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\testdata1.xlsx");
		Workbook workbook = WorkbookFactory.create(fileinputstream);
		Sheet sheet = workbook.getSheet("sheet1");
		Row row = sheet.createRow(2);
		Cell cell = row.createCell(0);
		cell.setCellValue("TY_02");
		FileOutputStream fileoutputstream = new FileOutputStream("C:\\Users\\\\admin\\\\eclipse-workspace\\\\VTigerCRM\\\\src\\\\test\\\\resources\\\\testdata1.xlsx");
		workbook.write(fileoutputstream);
		System.out.println("Done Writing");
	}

}
