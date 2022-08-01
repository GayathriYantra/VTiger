package com.vtiger.Datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
public class FetchSingleDataFromExeclSheetTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fileinputstream = new FileInputStream(
				"C:\\Users\\admin\\eclipse-workspace\\VTigerCRM\\src\\test\\resources\\testdata1.xlsx");
		Workbook workbook = WorkbookFactory.create(fileinputstream);
		Sheet sheet = workbook.getSheet("sheet1");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String value = cell.toString(); // toString Method return the float value of numerical
		DataFormatter formate = new DataFormatter();
		String value1 = formate.formatCellValue(cell);
		System.out.println(value1);

	}
}
