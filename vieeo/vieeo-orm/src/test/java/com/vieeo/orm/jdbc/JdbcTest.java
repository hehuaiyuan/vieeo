package com.vieeo.orm.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

import com.vieeo.orm.jdbc.query.QueryCallback;
import com.vieeo.orm.jdbc.session.Session;
import com.vieeo.orm.jdbc.session.StaticSessionFactory;
import com.vieeo.orm.jdbc.spi.JdbcCollector;

public class JdbcTest extends TestCase{


	public void testCreate() throws Exception{
		InputStream input = new FileInputStream(JdbcTest.class.getResource("/").getPath()+"/db.properties");
		Properties props = new Properties();
		props.load(input);
		StaticSessionFactory.openC3p0JdbcSession(props);

		Session<JdbcCollector> session = StaticSessionFactory.getC3p0JdbcSession();
		session.getCollector().query("select * from user_t", null,new QueryCallback<Object>(){
			@Override
			public Object doInReslutSet(ResultSet rs) throws JdbcException {
				try {
					while(rs.next()) {
						System.out.println(rs.getString(5));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}

		});
		input.close();
	}
}
