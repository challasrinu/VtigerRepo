package Generic_Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class contains reusable methods to read/write data from excel
 * @author challa
 */
public class ExcelUtility {
	Workbook wb;
	DataFormatter df;
	/**
	 * This method initializes excel
	 * @param excelpath
	 */
public void excelInit(String excelpath) {
	FileInputStream fis=null;
	try {
		fis=new FileInputStream(excelpath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		wb = WorkbookFactory.create(fis);
	} catch (EncryptedDocumentException |IOException e) {
		e.printStackTrace();
	} 
	 df = new DataFormatter();
	}
/**
 * This method fetches data from specified cell
 * @param sheetname
 * @param rowNum
 * @param cellNum
 * @return String
 */
public String readFromExcel(String sheetname, int rowNum, int cellNum) {
	return df.formatCellValue(wb.getSheet(sheetname).getRow(rowNum).getCell(cellNum));
	}
/**
 * This method fetches the data required for specified test case
 * @param sheetName
 * @param exceptedTestName
 * @return Map<String, String>
 */
	public Map<String, String> readFromExcel(String sheetName, String exceptedTestName) {
		Map<String, String> map=new HashMap<>();
		Sheet sheet = wb.getSheet(sheetName);
		int requiredRowNum = 0;
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			requiredRowNum=i;
			if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(exceptedTestName));
			break;
		}
		for(int i=requiredRowNum;i<=sheet.getLastRowNum();i++) {
			if(df.formatCellValue(sheet.getRow(i).getCell(2)).equals("####"))
           break;
			map.put(df.formatCellValue(sheet.getRow(i).getCell(2)),df.formatCellValue(sheet.getRow(i).getCell(3)));
		}
		return map;
	}
	/**
	 * This method writes to excel
	 * @param sheetName
	 * @param expectedTestName
	 * @param status
	 */
	public void writeToExcel(String sheetName, String expectedTestName, String status) {
		Sheet sheet = wb.getSheet(sheetName);
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestName)) {
				sheet.getRow(i).createCell(4).setCellValue(status);
				break;
			}
		}
	}
	public void saveExcel(String excelpath) {
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(excelpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



