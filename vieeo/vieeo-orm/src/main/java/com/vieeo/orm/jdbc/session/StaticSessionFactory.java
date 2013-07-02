package com.vieeo.orm.jdbc.session;

import java.util.Properties;

import javax.sql.DataSource;

import com.vieeo.core.build.Builder;
import com.vieeo.orm.jdbc.Connector;
import com.vieeo.orm.jdbc.JdbcContext;
import com.vieeo.orm.jdbc.JdbcPacket;
import com.vieeo.orm.jdbc.build.C3p0DataSourceBuilder;
import com.vieeo.orm.jdbc.spi.JdbcCollector;
import com.vieeo.orm.jdbc.support.DataSourceConnector;
import com.vieeo.orm.jdbc.support.JdbcConnector;
import com.vieeo.orm.jdbc.transaction.JdbcTransactionSession;
import com.vieeo.util.number.NumberUtils;

/**
 * 暂时的session工厂，需改进
 * @author hehy
 */
public class StaticSessionFactory {

	private static final ThreadLocal<Session<JdbcCollector>> local = new ThreadLocal<Session<JdbcCollector>>();

	private static Connector connector;

	public static void openC3p0JdbcSession(Properties props) throws Exception {
		if(connector == null) {
			Builder<DataSource,JdbcPacket> builder = new C3p0DataSourceBuilder(createJdbcPacket(props));
			connector = new DataSourceConnector(builder.build(null));
		}
	}

	public static Session<JdbcCollector> getC3p0JdbcSession() throws Exception {
		return getSession(connector);
	}

	private static Session<JdbcCollector> getSession(Connector connector) {
		if(local.get() == null) {
			Session<JdbcCollector> session = new JdbcTransactionSession(connector);
			local.set(session);
			return session;
		}else {
			return local.get();
		}
	}

	public static Session<JdbcCollector> getJdbcSession(Properties props){
		return getSession(new JdbcConnector(createJdbcPacket(props)));
	}

	public static Session<JdbcCollector> getJdbcSession(String driver,String url,String dbname,String dbpassword){
		Session<JdbcCollector> session = new JdbcTransactionSession(new JdbcConnector(driver,url,dbname,dbpassword));
		return session;
	}

	public static void closeSession(Session<JdbcCollector> session) {
		local.set(null);
	}

	private static JdbcPacket createJdbcPacket(Properties props){
		return new JdbcPacket(props.getProperty(JdbcContext.CONNECTION_URL),props.getProperty(JdbcContext.CONNECTION_DRIVER),
						props.getProperty(JdbcContext.CONNECTION_DBNAME),props.getProperty(JdbcContext.CONNECTION_DBPASSWORD),
						NumberUtils.parseInteger(props.getProperty(JdbcContext.CONNECTION_MINPOOLSIZE)),
						NumberUtils.parseInteger(props.getProperty(JdbcContext.CONNECTION_MAXPOOLSIZE)),
						NumberUtils.parseInteger(props.getProperty(JdbcContext.CONNECTION_INITIALPOOLSIZE)));
	}

}
