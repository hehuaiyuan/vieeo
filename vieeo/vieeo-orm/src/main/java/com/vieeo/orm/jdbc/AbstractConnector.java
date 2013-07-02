package com.vieeo.orm.jdbc;

import org.apache.commons.lang.StringUtils;


/**
 * jdbc连接操作超类
 * @author roy
 */
public abstract class AbstractConnector {

	private String url;

	private String driver;

	private String dbname;

	private String dbpassword;

	public AbstractConnector(){};

	public AbstractConnector(String driver,String url,String dbname,String dbpassword) {
		this.driver = driver;
		this.url = url;
		this.dbname = dbname;
		this.dbpassword = dbpassword;
	}

	public AbstractConnector(JdbcPacket packet){
		setDriver(packet.getDriver());
		setUrl(packet.getUrl());
		setDbname(packet.getDbname());
		setDbpassword(packet.getDbpassword());
	}

	protected void validate()throws JdbcException{
		if(StringUtils.isBlank(getDriver()) || StringUtils.isBlank(getUrl())
				|| StringUtils.isBlank(getDbname()) || StringUtils.isBlank(getDbpassword())) {
			throw new JdbcException("database properties can not be null");
		}
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

}
