package com.vieeo.orm.jdbc.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vieeo.orm.jdbc.JdbcException;
import com.vieeo.orm.jdbc.JdbcUtils;
import com.vieeo.orm.jdbc.query.QueryCallback;
import com.vieeo.orm.jdbc.spi.JdbcCollector;


/**
 * sql查询操作类
 * @author roy
 */
public class JdbcDataCollector implements JdbcCollector{

	private static final long serialVersionUID = 3698148458001111242L;

	private static final Log logger = LogFactory.getLog(JdbcDataCollector.class);

	private Connection connection;

	public JdbcDataCollector(){}

	public JdbcDataCollector(Connection connection){
		this.connection = connection;
	}

	public <R> R query(String sql, Object[] params, QueryCallback<R> callback) throws CollectorException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql.toString());

			int index = 1;
			if (params != null && params.length > 0) {
				for (Object param : params) {
					preparedStatement.setObject(index, param);
					index++;
				}
			}
			rs = preparedStatement.executeQuery();
			return callback.doInReslutSet(rs);
		} catch (Exception e) {
			throw new CollectorException(e);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(preparedStatement);
		}
	}

	public List<Map<String, Object>> query(String sql, Object[] params) throws CollectorException {
		return this.query(sql, params, new QueryCallback<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> doInReslutSet(ResultSet rs) throws JdbcException {
				try {
					List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
					ResultSetMetaData metaData = rs.getMetaData();
					int columnSize = metaData.getColumnCount();
					while (rs.next()) {
						Map<String, Object> result = new HashMap<String, Object>();
						for (int i = 1; i <= columnSize; i++) {
							result.put(metaData.getColumnLabel(i), rs.getObject(i));
						}
						results.add(result);
					}
					return results;
				}catch(SQLException e) {
					logger.error("query error:"+e);
					throw new JdbcException("query error",e);
				}
			}
		});
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
