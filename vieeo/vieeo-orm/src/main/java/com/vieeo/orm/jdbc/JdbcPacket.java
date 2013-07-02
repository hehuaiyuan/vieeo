package com.vieeo.orm.jdbc;

import com.vieeo.core.packet.Packet;

/**
 * jdbc属性封装类
 * @author hehy
 */
public class JdbcPacket implements Packet{

	private static final long serialVersionUID = -7775807448586225520L;

	private String url;

	private String driver;

	private String dbname;

	private String dbpassword;

	private int minPoolSize;

	private int maxPoolSize;

	private int initialPoolSize;

	public JdbcPacket(){}

	public JdbcPacket(String url,String driver,String dbname,String dbpassword,int minPoolSize,int maxPoolSize,int initialPoolSize){
		this.url = url;
		this.driver = driver;
		this.dbname = dbname;
		this.dbpassword = dbpassword;
		this.minPoolSize = minPoolSize;
		this.maxPoolSize = maxPoolSize;
		this.initialPoolSize = initialPoolSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}


}
