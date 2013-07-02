package com.vieeo.orm.jdbc.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vieeo.orm.jdbc.AbstractConnector;
import com.vieeo.orm.jdbc.Connector;
import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.JdbcPacket;
import com.vieeo.orm.jdbc.JdbcUtils;

/**
 * 简单jdbc连接创建者类
 * @author roy
 */
public class JdbcConnector extends AbstractConnector implements Connector{

	private static final Log logger = LogFactory.getLog(JdbcConnector.class);

	public JdbcConnector(){
		super();
	}

	public JdbcConnector(String driver,String url,String dbname,String dbpassword) {
		super(driver,url,dbname,dbpassword);
	}

	public JdbcConnector(JdbcPacket packet){
		super(packet);
	}

	public Connection getConnection() throws JdbcException {
		try {
			validate();
			Class.forName(getDriver());
			Connection connection = DriverManager.getConnection(getUrl(), getDbname(), getDbpassword());
			connection.setAutoCommit(false);
			connection.setReadOnly(true);
			return connection;
		}catch(SQLException e) {
			logger.error("Could not open jdbc connection:"+e);
			throw new JdbcException("Could not open jdbc connection:",e);
		} catch (ClassNotFoundException e) {
			logger.error("Could not open jdbc connection:"+e);
			throw new JdbcException("Could not open jdbc connection:",e);
		}

	}

	public void release(Connection connection) {
		JdbcUtils.closeConnection(connection);
	}

}
