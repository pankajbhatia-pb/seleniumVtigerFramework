package com.crm.genericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtils {

	String filePath = "./Data/commonData.properties";
	String excelPath = "./Data/TestData.xlsx";

	public Properties getPropertyFileObject() throws Throwable {
		FileInputStream fis = new FileInputStream(filePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		return pObj;
	}
	public String getExcelFileData(String sheetName, int rowNumber, int cellNumber) throws Throwable {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		String dataInCell = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return dataInCell;
	}
	public void setExcelFileData(String sheetName, int rowNumber, int cellNumber,String newData) throws Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).setCellValue(newData);
		FileOutputStream fos=new FileOutputStream(excelPath);
		wb.write(fos);
		wb.close();
	}

}
