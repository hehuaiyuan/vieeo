package com.vieeo.test.importdata;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.lang.StringUtils;

public class TestImportData extends TestCase{

	private static final String filePath = "D:\\新建文件夹\\7month.csv";

	private static final String TABLE_NAME = "TMP_SETTLE";

	private static final String COLUMNS = "SID,ORDER_NO,ORDER_AMOUNT,ORDER_STATUS,ORDER_UPDATETIME,DELIVERY_ORDERID,DELIVERY_STATUS,DELIVERY_ORDERNO,SHARING_AMOUNT,SHARING_STATUS,SHARING_UPDATETIME";

	private static final String VALUES = "?,?,?,?,?,?,?,?,?,?,?";

	private static final int size = 2000;

	private DataSource ds ;

	private String sql = "INSERT INTO "+TABLE_NAME+"("+ COLUMNS +") VALUES("+ VALUES +")";

	private static List<String> types = new ArrayList<String>(10);

	static{
		types.add(0, Entry.TYPE_NUMBER);
		types.add(1, Entry.TYPE_STRING);
		types.add(2, Entry.TYPE_DECIMAL);
		types.add(3, Entry.TYPE_NUMBER);
		types.add(4, Entry.TYPE_STRING);
		types.add(5, Entry.TYPE_STRING);
		types.add(6, Entry.TYPE_NUMBER);
		types.add(7, Entry.TYPE_STRING);
		types.add(8, Entry.TYPE_DECIMAL);
		types.add(9, Entry.TYPE_NUMBER);
		types.add(10, Entry.TYPE_STRING);
	}

	@Override
	protected void setUp() throws Exception {
		Properties properties = new Properties();
		properties.put("url", "jdbc:oracle:thin:@10.132.81.134:1521:dsdb1");
		properties.put("driverClassName", "oracle.jdbc.OracleDriver");
		properties.put("username", "sndadec");
		properties.put("password", "sndadec");
		ds  = BasicDataSourceFactory.createDataSource(properties);
	}


	public void testImport() throws Exception{
		LineNumberReader reader  = null;
		try {
			Connection conn = ds.getConnection();
			File file = new File(filePath);
			reader = new LineNumberReader(new FileReader(file));
			reader.readLine();
			String line  = reader.readLine();
			int row = 1;
			List<List<Entry>> data = new ArrayList<List<Entry>>();
			while(StringUtils.isNotBlank(line)){
				String[] columns = StringUtils.split(line,",");
				List<Entry> entries = new ArrayList<Entry>();
				for (int i=0;i<columns.length;i++) {
					String value = columns[i];
					Entry entry = new Entry(types.get(i),StringUtils.replace(value, "\"", ""));
					entries.add(entry);
				}
				data.add(entries);
				if(row % size == 0){
					batchImport(conn, data);
					data.clear();
				}
				line  = reader.readLine();
				row++;
				if(row % 100000 == 0){
					conn.close();
					conn = ds.getConnection();
				}
			}
			if(!data.isEmpty()){
				batchImport(conn, data);
			}
			System.out.println("总插入数："+row);
		}finally{
			if(reader != null) reader.close();
		}

	}

	private int batchImport(Connection conn,List<List<Entry>> data) throws Exception{
		PreparedStatement statement = conn.prepareStatement(sql);
		for(int i=0;i<data.size();i++){
			List<TestImportData.Entry> entries = data.get(i);
			for (int j=0 ;j< entries.size();j++ ) {
				Entry entry = entries.get(j);
				String value = entry.getValue();
				int index = j+1;
				if(Entry.TYPE_NUMBER.equals(entry.getType())){
					if(StringUtils.isBlank(value)){
						statement.setNull(index, Types.INTEGER);
					}else{
						statement.setLong(index,Long.valueOf(entry.getValue()));
					}
				}else if(Entry.TYPE_STRING.equals(entry.getType())){
					statement.setString(index, entry.getValue());
				}else if(Entry.TYPE_DECIMAL.equals(entry.getType())){
					if(StringUtils.isBlank(value)){
						statement.setNull(index, Types.DECIMAL);
					}else{
						statement.setBigDecimal(index,new BigDecimal(value));
					}
				}
			}
			statement.addBatch();
		}
		int[] succ = statement.executeBatch();
		System.out.println("成功插入条数:"+succ.length);
		return succ.length;
	}

	class Entry{

		public static final String TYPE_NUMBER = "1";

		public static final String TYPE_STRING = "2";

		public static final String TYPE_DATE = "3";

		public static final String TYPE_DECIMAL = "4";

		public Entry(String type,String value){
			this.type = type;
			this.value = value;
		}

		private String type;

		private String value;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
