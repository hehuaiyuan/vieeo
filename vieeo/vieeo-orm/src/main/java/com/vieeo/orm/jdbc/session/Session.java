package com.vieeo.orm.jdbc.session;

import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.spi.Collector;

/**
 * 事务操作Session总接口
 * @author hehy
 */
public interface Session<C extends Collector> extends java.io.Serializable{

	public static final String LOCAL_SESSION_KEY = "vieeo_jdbc_session";

	/**
	 * 开启一个新的jdbc事务
	 * @throws JdbcException
	 */
	public void beginTransaction()throws JdbcException;

	/**
	 * 提交事务
	 * @throws JdbcException
	 */
	public void commit()throws JdbcException;

	/**
	 * 事务回滚
	 * @throws JdbcException
	 */
	public void rollback()throws JdbcException;

	/**
	 * session销毁操作
	 * @throws JdbcException
	 */
	public void destroy()throws JdbcException;

	/**
	 * 获得jdbc操作对象，可进行相关jdbc操作
	 * @return
	 */
	public C getCollector();
}
