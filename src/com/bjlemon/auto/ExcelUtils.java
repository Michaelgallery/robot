package com.bjlemon.auto;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	//导入
	public static void readExcel()throws Exception{
	    //读取文件
	    FileInputStream fis = new FileInputStream(new File("D:\\组1-2020-07-12.xls"));
	    //得到工作薄
	    Workbook wb = new HSSFWorkbook(fis);
	    //2.拿到第个sheet表
	    Sheet sheet = wb.getSheetAt(0);
	    //3.拿到wb中的行(不要拿头部)
	    int lastRowNum = sheet.getLastRowNum();
	    for (int i = 1; i <= lastRowNum; i++) {
	        Row row = sheet.getRow(i);
	        //4.拿到每一列(格子)
	        short lastCellNum = row.getLastCellNum();
	        for (int j = 0; j < lastCellNum; j++) {
	            Cell cell = row.getCell(j);
	            System.out.print(cell.getStringCellValue()+" ");
	        }
	        System.out.println();
	    }
	}
	
	public static void main(String[] args) throws Exception {
		readExcel();
	}

}
