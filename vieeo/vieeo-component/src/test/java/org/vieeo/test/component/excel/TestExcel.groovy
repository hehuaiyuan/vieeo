package org.vieeo.test.component.excel;

import static org.junit.Assert.*

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.junit.Test

class TestExcel {

	@Test
	public void test() {
		InputStream inp = new FileInputStream("D:/数据.xlsx");
		//InputStream inp = new FileInputStream("workbook.xlsx");

		//Workbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));  //for excel 2003
		Workbook wb = new XSSFWorkbook(inp);   //for excel 2007 and high

		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.lastRowNum;
		for(i in 1..rowNum){
			Row row = sheet.getRow(i);
			println "Insert Into StatisticSystem.dbo.esale_shareprofit_ft(DATA_DESC,INSERTTIME,BILLNO,ORIGINPAYMENTID,MERCHANTORDERID,SETTLEMENTFEEAMOUNT,INTERFACETYPE,PAYCHANNEL,TYPE1,MERCHANTID,MDESC,KIND,AMOUNT,CARDNO,NEWCARDNO,BALANCEVALUE,ORDERAMOUNT,PRODUCTNO,REMARK2,ACCOUNTID,COMPANY,BINDPTID,TERMINALID,TERMINALNAME,USERNAME,cardsaletime) VALUES("+
			"'"+row.getCell(0).getStringCellValue()+"',"+
			"'"+row.getCell(1).getStringCellValue()+"',"+
			"'"+row.getCell(2).getStringCellValue()+"',"+
			"'"+row.getCell(3).getStringCellValue()+"',"+
			"'"+row.getCell(4).getStringCellValue()+"',"+
			"0,"+
			"1,"+
			"31,"+
			"'01',"+
			"'"+row.getCell(5).getStringCellValue()+"',"+
			"'"+row.getCell(6).getStringCellValue()+"',"+
			"'"+row.getCell(7).getStringCellValue()+"',"+
			""+row.getCell(8).getStringCellValue()+","+
			"'"+row.getCell(9).getStringCellValue()+"',"+
			"'"+row.getCell(10).getStringCellValue()+"',"+
			""+row.getCell(11).getStringCellValue()+","+
			""+row.getCell(12).getStringCellValue()+","+
			"'"+row.getCell(13).getStringCellValue()+"',"+
			"'"+row.getCell(14).getStringCellValue()+"',"+
			"'"+row.getCell(15).getStringCellValue()+"',"+
			"'"+row.getCell(16).getStringCellValue()+"',"+
			"'"+row.getCell(17).getStringCellValue()+"',"+
			""+row.getCell(18).getStringCellValue()+","+
			"'"+row.getCell(19).getStringCellValue()+"',"+
			"'"+row.getCell(20).getStringCellValue()+"',"+
			"'"+row.getCell(21).getStringCellValue()+"');"
		}

		Sheet sheet1 = wb.getSheetAt(1);
		int rowNum1 = sheet1.lastRowNum;
		for(i in 1..rowNum1){
			Row row = sheet1.getRow(i);
			println "Insert Into StatisticSystem.dbo.esale_shareprofit_badcard(DATA_DESC,INSERTTIME,BILLNO,ORIGINPAYMENTID,MERCHANTORDERID,SETTLEMENTFEEAMOUNT,INTERFACETYPE,PAYCHANNEL,TYPE1,MERCHANTID,MDESC,KIND,AMOUNT,CARDNO,NEWCARDNO,BALANCEVALUE,ORDERAMOUNT,PRODUCTNO,REMARK2,ACCOUNTID,COMPANY,BINDPTID,TERMINALID,TERMINALNAME,USERNAME,cardsaletime,create_time,SP_TYPE) VALUES("+
			"'"+row.getCell(0).getStringCellValue()+"',"+
			"'"+row.getCell(1).getStringCellValue()+"',"+
			"'"+row.getCell(2).getStringCellValue()+"',"+
			"'"+row.getCell(3).getStringCellValue()+"',"+
			"'"+row.getCell(4).getStringCellValue()+"',"+
			"0,"+
			"1,"+
			"31,"+
			"'01',"+
			"'"+row.getCell(5).getStringCellValue()+"',"+
			"'"+row.getCell(6).getStringCellValue()+"',"+
			"'"+row.getCell(7).getStringCellValue()+"',"+
			""+row.getCell(8).getStringCellValue()+","+
			"'"+row.getCell(9).getStringCellValue()+"',"+
			"'"+row.getCell(10).getStringCellValue()+"',"+
			""+row.getCell(11).getStringCellValue()+","+
			""+row.getCell(12).getStringCellValue()+","+
			"'"+row.getCell(13).getStringCellValue()+"',"+
			"'"+row.getCell(14).getStringCellValue()+"',"+
			"'"+row.getCell(15).getStringCellValue()+"',"+
			"'"+row.getCell(16).getStringCellValue()+"',"+
			"'"+row.getCell(17).getStringCellValue()+"',"+
			""+row.getCell(18).getStringCellValue()+","+
			"'"+row.getCell(19).getStringCellValue()+"',"+
			"'"+row.getCell(20).getStringCellValue()+"',"+
			"'"+row.getCell(21).getStringCellValue()+"',"+
			"'"+row.getCell(22).getStringCellValue()+"',"+
			"'"+row.getCell(23).getStringCellValue()+"');"
		}

		/*Cell cell = row.getCell(3);
		if (cell == null)
			cell = row.createCell(3);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("a test");*/
	}

}
