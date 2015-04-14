package org.vieeo.test.other;

import static org.junit.Assert.*;

import org.junit.Test;

class TestConverToDate {

	static final String PATH ="D:/新建文件夹/差额明细/";

	@Test
	public void test() {
		BufferedWriter writer = new File(PATH+"newSettle.sql").newWriter('utf-8');

		new File(PATH+"bakup.sql").newReader('gbk').readLines().each {line ->
			int index = 0;
			if(line.count("'2013-") == 0) {
				writer.writeLine(line);
				return ;
			}
			for (i in 1.. line.count("'2013-")) {
				index =  line.indexOf("'2013-",index);
				String date = line.substring(index, index+21);
				boolean end = false;
				if(line.count(date)>1){
					end = true;
				}
				String newdate = "to_date(${date},'yyyy-MM-dd hh24:mi:ss')";
				index = index+25;
				line = line.replaceAll(date, newdate)
				if(end) break;
			}
			writer.writeLine(line);
		}
		writer.flush();
		writer.close();
	}

}
