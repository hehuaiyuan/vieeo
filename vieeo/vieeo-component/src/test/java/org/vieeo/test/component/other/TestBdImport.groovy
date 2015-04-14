package org.vieeo.test.component.other;

import static org.junit.Assert.*;

import org.junit.Test;

class TestBdImport {

	@Test
	public void test() {

		Map etl_data = [:];

		Map account_data = [:];

		List account = [];

		BufferedReader reader = new File("D:\\account\\etl3.csv").newReader('utf-8');

		reader.eachLine {line ->
			String[] values = line.split(",");
			String merchantNo = values[0].replaceAll("\"", "");
			String money = values[1].replaceAll("\"", "");
			etl_data.put(merchantNo, money);
		};

		reader.close();

		reader = new File("D:\\account\\account.txt").newReader('utf-8');

		reader.eachLine {line ->
			account.add(line);
		};

		reader.close();

		reader = new File("D:\\account\\account2.txt").newReader('utf-8');

		int i=0;
		reader.eachLine {line ->
			String account_key = account.get(i);
			BigDecimal money = account_data.get(account_key);
			if(money!= null){
				money = money.add(line.toBigDecimal());
			}else {
				money = line.toBigDecimal();
			}
			account_data.put(account.get(i) as String, money);
			i++;
		};

		reader.close();

		account_data.each {rs ->
			String key = rs.key;
			BigDecimal account_money = rs.value;

			def etl_money = etl_data[key];
			if(!etl_money.equals(account_money.toString())){
				println "account:${key}=${account_money},etl_money=${etl_money}";
			}

			if(etl_money == null){
				println "account:${key}=${account_money},etl_money=null";
			}
		}
	}
}
