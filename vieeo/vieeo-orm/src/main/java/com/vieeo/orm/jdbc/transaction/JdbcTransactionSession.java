package com.vieeo.orm.jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vieeo.orm.jdbc.Connector;
import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.session.Session;
import com.vieeo.orm.jdbc.spi.JdbcCollector;
import com.vieeo.orm.jdbc.support.JdbcDataCollector;

/**
 * jdbc事务session实现
 * @author hehy
 *
 */
public class JdbcTransactionSession implements Session<JdbcCollector>{

	private static final Log logger = LogFactory.getLog(JdbcTransactionSession.class);

	private static final long serialVersionUID = -8104018975208618112L;

	private Connector connector;

	private Connection connection;

	private JdbcCollector collector ;

	public JdbcTransactionSession(){}

	public JdbcTransactionSession(Connector connector) {
		this.connector = connector;
		try {
			beginTransaction();
		} catch (JdbcException e) {
		}
		collector = new JdbcDataCollector(connection);
	}

	@Override
	public JdbcCollector getCollector() {
		return collector;
	}

	@Override
	public void beginTransaction() throws JdbcException {
		if(connection == null) getConnection();
	}

	@Override
	public void commit() throws JdbcException{
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.error("jdbc error:"+e);
			throw new JdbcException("jdbc error",e);
		}
	}

	@Override
	public void rollback() throws JdbcException{
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.error("jdbc error:"+e);
			throw new JdbcException("jdbc error",e);
		}
	}

	public void getConnection() throws JdbcException {
		connection = connector.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.debug(e);
		}
	}

	@Override
	public void destroy() throws JdbcException {
		connector.release(connection);
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

}
