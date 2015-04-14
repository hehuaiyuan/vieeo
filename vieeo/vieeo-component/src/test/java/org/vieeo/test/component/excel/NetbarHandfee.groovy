package org.vieeo.test.component.excel;

import static org.junit.Assert.*

import org.apache.commons.lang.StringUtils
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.junit.Test

class NetbarHandfee {

	@Test
	public void test() {
		InputStream inp = new FileInputStream("D:\\新建文件夹\\盛淘改手续费\\专卡专供手续费比例.xlsx");
		//InputStream inp = new FileInputStream("workbook.xlsx");

		//Workbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));  //for excel 2003
		Workbook wb = new XSSFWorkbook(inp);   //for excel 2007 and high

		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.lastRowNum;
		int size = 0;
		for(i in 0..rowNum){
			Row row = sheet.getRow(i);

			String name  = row.getCell(0).getStringCellValue();
			if(StringUtils.isBlank(name)){
				continue;
			}

			if("供货商产品名称".equals(name)){
				if(size >0 ) {
					println ");";
					println "\n";
				}

				println "update netbarinventory.sup3_supplier_product"
				println "set transaction_fee_rate = " + ((sheet.getRow(i+1).getCell(1).getNumericCellValue()*1000) as Long) as String
				println "where supplier_id in(select s.id from netbarinventory.sup3_supplier s where s.parent_id = 10000 and s.status = 1)";
				println "and supplier_product_name in("
				size++;
			}else {
				print "'${name}'";
				if(i == rowNum){
					println ");";
					continue;
				}
				if(StringUtils.isNotBlank(sheet.getRow(i+1).getCell(0).getStringCellValue())){
					println ",";
				}
			}
		}

		inp.close();
	}

}
