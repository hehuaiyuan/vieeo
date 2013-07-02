package com.vieeo.orm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vieeo.orm.jdbc.query.QueryCallback;
import com.vieeo.orm.jdbc.session.Session;
import com.vieeo.orm.jdbc.session.factory.SessionFactory;
import com.vieeo.orm.jdbc.spi.JdbcCollector;
import com.vieeo.orm.jdbc.support.CollectorException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.context.xml"})
public class SpringTest {

	@Resource
	private SessionFactory<JdbcCollector> factory;

	@Test
	public void testSession() throws CollectorException{
		Session<JdbcCollector> session = factory.openSession();
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
	}

}
