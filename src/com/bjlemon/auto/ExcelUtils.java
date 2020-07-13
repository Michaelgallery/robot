package com.bjlemon.auto;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	// 商品名称下标
	private static final int PRODCUT_NAME = 1;
	// 商品图片下标
	private static final int PRODCUT_IMAGE = 2;
	// 商品价格下标
	private static final int PRODCUT_PRICE = 5;
	// 商品优惠券金额下标
	private static final int COUPAN_PRICE = 16;
	// 商品优惠券短链接下标
	private static final int COUPAN_PATH = 20;
	// 商品优惠券淘口令下标
	private static final int COUPAN_SEARCH_PASSWORD = 21;
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
