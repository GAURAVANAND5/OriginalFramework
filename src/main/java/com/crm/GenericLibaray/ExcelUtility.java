package com.crm.GenericLibaray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class will be contains Excel sheet
 * @author GAURAV
 *
 */

public class ExcelUtility {
	/**
	 * This method will used to read the data from Excel sheet.
	 * @param sheetname
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws Throwable
	 */
	
	public String getDataFromExcel(String sheetname,int rowNo,int cellNo) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowNo).getCell(cellNo).getStringCellValue();
		return data;
	}
	/**
	 * This method will used to count the last row number.
	 * @param sheetname
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}
	/**
	 * This method is used to write the data into excel sheet
	 * @param sheetname
	 * @param rowNo
	 * @param cellNo
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public void setDataExcel(String sheetname,int rowNo,int cellNo,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(rowNo);
		Cell cel = row.getCell(cellNo);
		cel.setCellValue(data);
		FileOutputStream fos= new FileOutputStream(IpathConstants.ExcelPath);
		wb.write(fos);
		wb.close();
		
	}

}
