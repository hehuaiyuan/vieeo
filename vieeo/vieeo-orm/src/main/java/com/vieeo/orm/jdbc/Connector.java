package com.vieeo.orm.jdbc;

import java.sql.Connection;

public interface Connector {

	public Connection getConnection()throws JdbcException;

	public void release(Connection connection);

}
