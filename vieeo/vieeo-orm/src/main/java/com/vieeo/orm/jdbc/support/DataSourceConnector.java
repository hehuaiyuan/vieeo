package com.vieeo.orm.jdbc.support;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vieeo.orm.jdbc.Connector;
import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.JdbcUtils;

/**
 * 连接池连接类，负责与连接池通信
 * @author hehy
 *
 */
public class DataSourceConnector implements Connector{

	private static final Log logger = LogFactory.getLog(DataSourceConnector.class);

	private DataSource dataSource;

	public DataSourceConnector(){}

	public DataSourceConnector(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws JdbcException {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("Could not open jdbc connection:"+e);
			throw new JdbcException("Could not open jdbc connection:",e);
		}
	}

	@Override
	public void release(Connection connection) {
		JdbcUtils.closeConnection(connection);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
