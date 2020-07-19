package com.bjlemon.auto;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bjlemon.entity.Product;

public class ExcelUtils {
	// 商品名称下标
	private static final int PRODCUT_NAME = 1;
	// 商品图片下标
	private static final int PRODCUT_IMAGE = 2;
	// 商品价格下标
	private static final int PRODCUT_PRICE = 5;
	// 商品优惠券金额下标
	private static final int COUPAN_PRICE = 15;
	// 商品优惠券短链接下标
	private static final int COUPAN_PATH = 20;
	// 商品优惠券淘口令下标
	private static final int COUPAN_SEARCH_PASSWORD = 19;
	// excel文件路径
	private static final String EXECEL_PATH = "D:\\组1-2020-07-12.xls";

	/**
	 * 解析excel文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Product> readExcel(File file) {
		List<Product> products = new ArrayList<>();
		try {
			// 读取文件
			FileInputStream fis = new FileInputStream(file);
			// 得到工作薄
			Workbook wb = new HSSFWorkbook(fis);
			// 2.拿到第个sheet表
			Sheet sheet = wb.getSheetAt(0);
			// 3.拿到wb中的行(不要拿头部)
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				// 4.拿到每一列(格子)
				Product product = new Product(row.getCell(PRODCUT_NAME).getStringCellValue(),
						row.getCell(PRODCUT_IMAGE).getStringCellValue(),
						row.getCell(PRODCUT_PRICE).getStringCellValue(), row.getCell(COUPAN_PRICE).getStringCellValue(),
						row.getCell(COUPAN_PATH).getStringCellValue(),
						row.getCell(COUPAN_SEARCH_PASSWORD).getStringCellValue());
				products.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public static void main(String[] args) throws Exception {
	}

}
