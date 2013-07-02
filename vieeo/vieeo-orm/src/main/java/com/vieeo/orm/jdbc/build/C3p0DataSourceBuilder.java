package com.vieeo.orm.jdbc.build;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.vieeo.core.build.Builder;
import com.vieeo.orm.jdbc.JdbcPacket;
import com.vieeo.orm.jdbc.PoolAbstractConnector;

/**
 * c3p0连接池dataSource构造类
 * @author hehy
 *
 */
public class C3p0DataSourceBuilder extends PoolAbstractConnector implements Builder<DataSource,JdbcPacket>{

	private static final Log logger = LogFactory.getLog(C3p0DataSourceBuilder.class);

	@SuppressWarnings("unused")
	private JdbcPacket packet;

	public C3p0DataSourceBuilder(){}

	public C3p0DataSourceBuilder(JdbcPacket packet){
		super(packet);
		this.packet = packet;
	}

	@Override
	public DataSource build(JdbcPacket packet) throws Exception{
		try {
			init(packet);
			ComboPooledDataSource dateSource = new ComboPooledDataSource();
			dateSource.setDriverClass(getDriver());
			dateSource.setJdbcUrl(getUrl());
			dateSource.setUser(getDbname());
			dateSource.setPassword(getDbpassword());
			dateSource.setMinPoolSize(getMinPoolSize());
			dateSource.setMaxPoolSize(getMaxPoolSize());
			dateSource.setInitialPoolSize(getInitialPoolSize());
			return dateSource;
		}catch(Exception e) {
			logger.error("create DataSource error:"+e);
			throw new Exception("create DataSource error:",e);
		}
	}

	private void init(JdbcPacket packet) {
		if(packet != null) {
			setDriver(packet.getDriver());
			setUrl(packet.getUrl());
			setDbname(packet.getDbname());
			setDbpassword(packet.getDbpassword());
			setMinPoolSize(packet.getMinPoolSize());
			setMaxPoolSize(packet.getMaxPoolSize());
			setInitialPoolSize(packet.getInitialPoolSize());
		}
	}

}
