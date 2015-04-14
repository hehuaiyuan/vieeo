package org.vieeo.test.component.excel;

import static org.junit.Assert.*

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.junit.Test

class TestExcel2 {

	@Test
	public void test() {
		InputStream inp = new FileInputStream("D:/盛大1月份本地对账.xlsx");
		//InputStream inp = new FileInputStream("workbook.xlsx");

		//Workbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));  //for excel 2003
		Workbook wb = new XSSFWorkbook(inp);   //for excel 2007 and high

		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.lastRowNum;
		println "select t.order_code,t.supplier_order_code,t.create_date from netbartrade.dev_delivery_order t where t.create_date>=to_date('20130101','yyyyMMdd') and t.create_date<to_date('20130201','yyyyMMdd') ";
		println "and t.supplier_order_code in("
		for(i in 4000..rowNum){
			Row row = sheet.getRow(i);
			print "'"+row.getCell(0).getStringCellValue().substring(1)+"'";
			if(i<rowNum) println ",";
		}
		println ");";

		/*println "select t.order_code,t.supplier_order_code,t.create_date from netbartrade.dev_delivery_order t where t.create_date>=to_date('20130101','yyyyMMdd') and t.create_date<to_date('20130201','yyyyMMdd') and t.supplier_id = 10000  ";
		println "and t.supplier_order_code in("
		for(i in 501..1000){
			Row row = sheet.getRow(i);
			print "'"+row.getCell(0).getStringCellValue().substring(1)+"'";
			if(i<100) println ",";
		}
		println ");";

		println "select t.order_code,t.supplier_order_code,t.create_date from netbartrade.dev_delivery_order t where t.create_date>=to_date('20130101','yyyyMMdd') and t.create_date<to_date('20130201','yyyyMMdd') and t.supplier_id = 10000  ";
		println "and t.supplier_order_code in("
		for(i in 1001..1500){
			Row row = sheet.getRow(i);
			print "'"+row.getCell(0).getStringCellValue().substring(1)+"'";
			if(i<100) println ",";
		}
		println ");";

		println "select t.order_code,t.supplier_order_code,t.create_date from netbartrade.dev_delivery_order t where t.create_date>=to_date('20130101','yyyyMMdd') and t.create_date<to_date('20130201','yyyyMMdd') and t.supplier_id = 10000  ";
		println "and t.supplier_order_code in("
		for(i in 1001..1500){
			Row row = sheet.getRow(i);
			print "'"+row.getCell(0).getStringCellValue().substring(1)+"'";
			if(i<100) println ",";
		}
		println ");";*/

		inp.close();

	}

}
